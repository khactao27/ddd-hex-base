package tech.ibrave.metabucket.infra.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.ibrave.metabucket.infra.persistence.jpa.entity.SettingEntity;

import java.util.Optional;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
@Repository
public interface SettingJpaRepository extends JpaRepository<SettingEntity, Integer> {

    Optional<SettingEntity> findByCode(String code);
}
