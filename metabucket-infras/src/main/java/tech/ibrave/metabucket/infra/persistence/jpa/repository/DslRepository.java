package tech.ibrave.metabucket.infra.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Author: hungnm
 * Date: 28/05/2023
 */
@NoRepositoryBean
public interface DslRepository<T, ID> extends JpaRepository<T, ID>, QuerydslPredicateExecutor<T> {
}
