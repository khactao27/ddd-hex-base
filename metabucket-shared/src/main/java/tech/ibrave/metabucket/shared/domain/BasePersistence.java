package tech.ibrave.metabucket.shared.domain;

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
}
