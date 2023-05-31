package tech.ibrave.metabucket.domain.setting;

import lombok.Getter;
import lombok.Setter;

/**
 * Author: anct
 * Date: 31/05/2023
 * #YWNA
 */
@Getter
@Setter
public class Setting {
    private Integer id;
    private String name;
    private String code;
    private String value;


    public interface Code {
        String SMTP_SETTING = "smtp-mail-setting";
    }
}
