package tech.ibrave.metabucket.shared.request;

/**
 * Author: anct
 * Date: 23/05/2023
 * #YWNA
 */
public interface UseCaseReq<DM> {

    DM toDomainModel();
}
