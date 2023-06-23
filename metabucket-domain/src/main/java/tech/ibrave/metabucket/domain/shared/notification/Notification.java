package tech.ibrave.metabucket.domain.shared.notification;

import lombok.Getter;
import lombok.Setter;

/**
 * @author an.cantuong
 * created 6/23/2023
 */
@Getter
@Setter
public class Notification {
    private String id;
    private String title;
    private String message;
    private String receiver; // can be user group or single user
    private boolean readStatus;
}
