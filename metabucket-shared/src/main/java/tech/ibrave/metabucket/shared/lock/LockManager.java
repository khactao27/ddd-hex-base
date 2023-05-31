package tech.ibrave.metabucket.shared.lock;

import java.util.concurrent.locks.Lock;

/**
 * Author: anct
 * Date: 31/05/2023
 * #YWNA
 */
public interface LockManager {

    Lock getLock(String key);
    
    void removeLock(String key);
}
