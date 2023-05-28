package tech.ibrave.metabucket.infra.persistence.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * author: anct
 * date: 5/25/2023
 * YNWA
 */
@Entity
@Getter
@Setter
@Table(name = "tbl_email_queue")
public class EmailQueueEntity {

    @Id
    private String id;
}
