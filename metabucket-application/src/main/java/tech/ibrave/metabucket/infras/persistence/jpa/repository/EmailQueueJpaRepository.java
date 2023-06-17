package tech.ibrave.metabucket.infras.persistence.jpa.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.ibrave.metabucket.infras.mail.MailStatus;
import tech.ibrave.metabucket.infras.persistence.jpa.entity.EmailQueueEntity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Author: anct
 * Date: 30/05/2023
 * #YWNA
 */
@Repository
@SuppressWarnings("all")
public interface EmailQueueJpaRepository extends JpaRepository<EmailQueueEntity, String> {

    Page<EmailQueueEntity> findAllByStatusOrderByCreatedTime(MailStatus status, Pageable pageable);

    @Modifying
    @Transactional
    @Query("update EmailQueueEntity set status = 'ERROR' where id in ?1")
    void error(List<String> ids);

    @Modifying
    @Transactional
    @Query("update EmailQueueEntity set status = 'SENT', sentDate = ?2 where id in ?1")
    void sent(List<String> ids, LocalDateTime sentDate);

    @Modifying
    @Transactional
    @Query("update EmailQueueEntity set status = 'TAKEN', takenDate = ?2 where id in ?1")
    void take(List<String> ids, LocalDateTime takenDate);

    @Modifying
    @Transactional
    @Query("update EmailQueueEntity set status = 'NEW' where status = 'TAKEN' and takenDate <= ?1")
    void relaseTakenMail(LocalDateTime fromDate);

    @Modifying
    @Transactional
    @Query("delete EmailQueueEntity where status = 'SENT' and sentDate <= ?1")
    void cleanSentMail(LocalDateTime fromDate);
}
