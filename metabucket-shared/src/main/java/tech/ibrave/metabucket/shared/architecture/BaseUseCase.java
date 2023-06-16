package tech.ibrave.metabucket.shared.architecture;

import tech.ibrave.metabucket.shared.exception.ErrorCode;
import tech.ibrave.metabucket.shared.request.PageReq;

import java.util.List;
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

    void deleteById(ID id);

    Optional<DM> getById(ID id);

    DM getOrElseThrow(ID id);

    ErrorCode notFound();

    Page<DM> findAll(PageReq pageRequest);

    Page<DM> findAll(int pageIndex, int pageSize);

    void existByIdOrElseThrow(ID id);

    boolean existById(ID id);

     DM deleteIfExist(ID id);

     void deleteAllByIdInBatch(List<ID> ids);
}