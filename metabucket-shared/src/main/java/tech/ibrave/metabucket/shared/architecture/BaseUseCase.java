package tech.ibrave.metabucket.shared.architecture;

import tech.ibrave.metabucket.shared.exception.ErrorCode;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
public interface BaseUseCase<DM, ID> {
    DM save(DM model);

    DM update(ID id, Consumer<DM> consumerModel);

    void delete(ID id);

    Optional<DM> getById(ID id);

    DM getOrElseThrow(ID id);

    ErrorCode notFound();
}