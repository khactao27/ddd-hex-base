package tech.ibrave.metabucket.shared.lock;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
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
@RequiredArgsConstructor
public class RedisLockManager implements LockManager {

    private final RedissonClient client;

    @Override
    public Lock getLock(String key) {
        return client.getLock(key);
    }

    @Override
    public void removeLock(String key) {
        // do not thing
    }
}
