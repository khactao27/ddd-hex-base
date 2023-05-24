package tech.ibrave.metabucket.shared.architecture;

import tech.ibrave.metabucket.shared.exception.ErrorCode;

import java.util.Optional;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
public interface BaseUseCase<DM, ID> {
    DM create(DM model);

    DM update(ID id, DM model);

    void delete(ID id);

    Optional<DM> getById(ID id);

    DM getOrElseThrow(ID id);

    ErrorCode notFound();
}