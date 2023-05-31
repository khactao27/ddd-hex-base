package tech.ibrave.metabucket.infra.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import tech.ibrave.metabucket.domain.setting.SMTPMailSetting;
import tech.ibrave.metabucket.infra.persistence.jpa.entity.EmailQueueEntity;
import tech.ibrave.metabucket.infra.persistence.jpa.repository.EmailQueueJpaRepository;
import tech.ibrave.metabucket.shared.lock.LockManager;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@Lazy
@Component
@RequiredArgsConstructor
public class SMTPMailSender implements DisposableBean {

    private SMTPMailSetting smtpConfig;
    private JavaMailSenderImpl mailSender;
    private boolean active;
    private ScheduledExecutorService scheduler;
    private final LockManager lockManager;
    private static final int MAX_QUEUE = 50;
    private final Queue<EmailQueueEntity> temporaryQueue = new PriorityQueue<>(MAX_QUEUE,
            Comparator.comparing(EmailQueueEntity::getCreatedTime));
    private final EmailQueueJpaRepository emailQueueJpaRepo;

    public void init(SMTPMailSetting smtpConfig) {
        this.initSMTP(smtpConfig);
        this.initScheduler();
    }

    @EventListener
    public void applyChanges(SMTPMailSetting smtpConfig) {
        try {
            log.info("Apply change on SMTP setting.");
            this.pause();
            this.init(smtpConfig);
            this.start();
            log.info("Mail sender restarted.");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private void initSMTP(SMTPMailSetting smtpConfig) {
        this.smtpConfig = smtpConfig;
        this.mailSender = new JavaMailSenderImpl();
        this.mailSender.setHost(smtpConfig.getHostName());
        this.mailSender.setPort(Integer.parseInt(smtpConfig.getPort()));

        this.mailSender.setUsername(smtpConfig.getUsername());
        this.mailSender.setPassword(smtpConfig.getPassword());

        var props = this.mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", smtpConfig.getTransportProtocol());
        props.put("mail.smtp.auth", smtpConfig.isAuth());
        props.put("mail.smtp.starttls.enable", smtpConfig.isStarttls());
        props.put("mail.debug", smtpConfig.isDebug());
        props.put("mail.smtp.ssl.protocols", smtpConfig.getSslProtocol());
        props.put("mail.smtp.trust", "*");
        props.put("mail.smtp.ssl.checkserveridentity", smtpConfig.isCheckserveridentity());

        log.info("Mail sender ready.");
    }

    private void initScheduler() {
        if (scheduler == null) {
            this.scheduler = Executors.newScheduledThreadPool(2);
            this.scheduler.scheduleAtFixedRate(this::loadNew, 0, 1000, TimeUnit.MILLISECONDS);
            this.scheduler.scheduleAtFixedRate(this::send, 1000, 100, TimeUnit.MILLISECONDS);
        }
    }

    public void pause() {
        this.active = false;
    }

    public void start() {
        this.active = true;
    }

    /**
     * Enhance performance by using saved queue
     */
    private void send() {
        if (active) {
            var mail = temporaryQueue.poll();
            if (mail == null) return;
            try {
                this.sendSimpleMessage(mail.getTo(), mail.getSubject(), mail.getBody());

                emailQueueJpaRepo.sent(Collections.singletonList(mail.getId()), LocalDateTime.now());
            } catch (Exception e) {
                log.error(e.getMessage(), e);

                emailQueueJpaRepo.error(Collections.singletonList(mail.getId()));
            }
        }
    }

    public void loadNew() {
        if (active && temporaryQueue.isEmpty()) {
            var lock = lockManager.getLock("load-mail-lock");
            try {
                if (lock.tryLock()) {
                    var result = emailQueueJpaRepo.findAllByStatusOrderByCreatedTime(MailStatus.NEW, Pageable.ofSize(MAX_QUEUE));

                    if (result.hasContent()) {
                        var mails = result.getContent();
                        var mailIds = mails.stream().map(EmailQueueEntity::getId).toList();
                        emailQueueJpaRepo.take(mailIds, LocalDateTime.now());

                        temporaryQueue.addAll(mails);
                    }
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            } finally {
                lock.unlock();
            }
        }
    }

    public void sendSimpleMessage(String to, String subject, String body) {
        if (active) {
            var message = new SimpleMailMessage();
            message.setFrom(smtpConfig.getFromAddress());
            message.setTo(to);
            message.setSubject(getSubjectWithPrefix(subject));
            message.setText(body);
            mailSender.send(message);
        } else {
            log.warn("Mail Sender not ready, mail can't be sent.");
        }
    }

    private String getSubjectWithPrefix(String rawSubject) {
        if (StringUtils.hasLength(smtpConfig.getEmailPrefix())) {
            return String.format("[%s] %s", smtpConfig.getEmailPrefix(), rawSubject);
        }
        return rawSubject;
    }

    @Override
    public void destroy() throws Exception {
        if (this.scheduler != null) {
            var terminated = this.scheduler.awaitTermination(1, TimeUnit.MINUTES);
            log.info("Stop mail sender {}", terminated ? "done" : "failed");
        }
    }
}

