package tech.ibrave.metabucket.infras.persistence.jpa.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.ibrave.metabucket.infras.persistence.jpa.entity.LogEntity;

import java.time.LocalDateTime;

/**
 * @author an.cantuong
 * created 6/23/2023
 */
@Repository
public interface LogJpaRepository extends JpaRepository<LogEntity, String> {

    @Modifying
    @Transactional
    @Query("delete from LogEntity where createdTime <= ?1")
    void cleanLog(LocalDateTime beforeTime);
}
