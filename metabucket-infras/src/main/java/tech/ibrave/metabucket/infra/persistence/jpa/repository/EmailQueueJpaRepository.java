package tech.ibrave.metabucket.infra.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.ibrave.metabucket.infra.persistence.jpa.entity.EmailQueueEntity;

/**
 * Author: anct
 * Date: 30/05/2023
 * #YWNA
 */
@Repository
@SuppressWarnings("all")
public interface EmailQueueJpaRepository extends JpaRepository<EmailQueueEntity, String> {
}
