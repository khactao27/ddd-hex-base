package tech.ibrave.metabucket.application.persistence.mapper;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
public interface BaseEntityMapper<E, DM> {

    E fromDomainModel(DM model);

    DM toDomainModel(E entity);
}
