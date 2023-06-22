package tech.ibrave.metabucket.integration.google.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
/**
 * Author: hungnm
 * Date: 21/06/2023
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class GoogleDriveFileDTO extends AbstractDTO<GoogleDriveFileDTO> implements Serializable {

    private String size;
    private String thumbnailLink;
    private boolean shared;
}