package tech.ibrave.metabucket.infra;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.ibrave.metabucket.domain.setting.SMTPMailSetting;

import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * Author: anct
 * Date: 31/05/2023
 * #YWNA
 */
@SpringBootTest
class ObjectMapperTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @SneakyThrows
    void test() {
        var json = "{\"hostName\":\"smtp.gmail.com\",\"username\":\"internalservice@trustdatas.com\",\"password\":\"nnxsjdfducqehshm\",\"port\":\"587\",\"fromAddress\":\"internalservice@trustdatas.com\",\"tls\":true,\"timeout\":10000,\"emailPrefix\":null,\"debug\":false,\"transportProtocol\":\"smtp\",\"auth\":true,\"starttls\":true,\"sslProtocol\":\"TLSv1.2\",\"checkserveridentity\":true}";
        var smtpMailSetting = objectMapper.readValue(json, SMTPMailSetting.class);
        assertNotNull(smtpMailSetting);
    }
}
