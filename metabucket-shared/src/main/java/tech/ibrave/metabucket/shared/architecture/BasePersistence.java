package tech.ibrave.metabucket.shared.architecture;

import tech.ibrave.metabucket.shared.request.PageReq;

import java.util.List;
import java.util.Optional;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
public interface BasePersistence<DM, ID> {

    DM save(DM model);

    List<DM> saveAll(List<DM> models);

    Optional<DM> findById(ID id);

    void deleteById(ID id);
    
    List<DM> findAllById(List<ID> ids);

    Page<DM> findAll(PageReq pageRequest);

    boolean existsById(ID id);
}
