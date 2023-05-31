package tech.ibrave.metabucket.shared.lock;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: anct
 * Date: 31/05/2023
 * #YWNA
 */
@Component
@ConditionalOnMissingBean(RedisLockManager.class)
public class InternalLockManager implements LockManager {

    private final Map<String, Lock> locks = new ConcurrentHashMap<>();

    @Override
    public synchronized Lock getLock(String key) {
        var lock = locks.get(key);

        if (lock != null) {
            return lock;
        }

        lock = new ReentrantLock();

        locks.put(key, lock);

        return lock;
    }

    @Override
    public void removeLock(String key) {
        locks.remove(key);
    }
}
