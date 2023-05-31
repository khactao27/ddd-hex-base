package tech.ibrave.metabucket.shared.lock;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;

/**
 * Author: anct
 * Date: 31/05/2023
 * #YWNA
 */
@Component
@Profile("redis")
public class RedisLockManager implements LockManager {

    @Override
    public Lock getLock(String key) {
        return null;
    }

    @Override
    public void removeLock(String key) {

    }
}
