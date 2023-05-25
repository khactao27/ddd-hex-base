package tech.ibrave.metabucket.infra.peristence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.ibrave.metabucket.infra.peristence.jpa.entity.SettingEntity;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
@Repository
public interface SettingJpaRepository extends JpaRepository<SettingEntity, Long> {
}
