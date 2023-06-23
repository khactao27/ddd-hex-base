package tech.ibrave.metabucket.infras.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.ibrave.metabucket.infras.persistence.jpa.entity.NotificationEntity;

/**
 * @author an.cantuong
 * created 6/23/2023
 */
@Repository
public interface NotificationJpaRepository extends JpaRepository<NotificationEntity, String> {
}
