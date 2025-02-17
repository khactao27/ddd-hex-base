package tech.ibrave.metabucket.infras.persistence.adapter;

import org.springframework.stereotype.Component;
import tech.ibrave.metabucket.domain.setting.Setting;
import tech.ibrave.metabucket.domain.setting.persistence.SettingPersistence;
import tech.ibrave.metabucket.infras.persistence.jpa.BaseJpaRepository;
import tech.ibrave.metabucket.infras.persistence.jpa.entity.SettingEntity;
import tech.ibrave.metabucket.infras.persistence.jpa.repository.SettingJpaRepository;
import tech.ibrave.metabucket.infras.persistence.mapper.SettingEntityMapper;

import java.util.Optional;

/**
 * Author: anct
 * Date: 31/05/2023
 */
@Component
@SuppressWarnings("all")
public class SettingPersistenceAdapter extends BaseJpaRepository<SettingEntity, Setting, Integer>
        implements SettingPersistence {

    protected SettingPersistenceAdapter(SettingJpaRepository repo,
                                        SettingEntityMapper mapper) {
        super(repo, mapper);
    }

    @Override
    public Optional<Setting> findByCode(String code) {
        return Optional.ofNullable(mapper.toDomainModel(repo().findByCode(code).orElse(null)));
    }

    @Override
    public SettingJpaRepository repo() {
        return (SettingJpaRepository) repo;
    }
}