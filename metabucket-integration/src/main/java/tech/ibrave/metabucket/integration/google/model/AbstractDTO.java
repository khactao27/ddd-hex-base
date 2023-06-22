package tech.ibrave.metabucket.integration.google.model;

import lombok.Getter;
import lombok.Setter;
/**
 * Author: hungnm
 * Date: 21/06/2023
 */
@Getter
@Setter
public abstract class AbstractDTO <T>{
    private String id;
    private String name;
    private String link;
}
