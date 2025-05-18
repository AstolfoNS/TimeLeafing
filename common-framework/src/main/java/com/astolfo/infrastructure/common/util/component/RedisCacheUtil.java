package com.astolfo.infrastructure.common.util.component;

import com.astolfo.infrastructure.common.util.shared.MapConverter;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RedisCacheUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private RedissonClient redissonClient;


    public Boolean expire(
            String key,
            long timeout,
            TimeUnit unit
    ) {
        return redisTemplate.expire(key, timeout, unit);
    }

    public Boolean hasKey(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public Long delete(Collection<String> keys) {
        return redisTemplate.delete(keys);
    }

    public Collection<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    public <T> void set(String key, T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public <T> void set(
            String key,
            T value,
            long timeout,
            TimeUnit unit
    ) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        Object value = redisTemplate.opsForValue().get(key);

        if (Objects.isNull(value)) {
            return null;
        } else {
            return (T) value;
        }
    }

    public <T> Long setList(String key, @Nonnull List<T> list) {
        return redisTemplate.opsForList().rightPushAll(key, list.toArray());
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> getList(String key) {
        List<Object> list = redisTemplate.opsForList().range(key, 0, -1);

        if (Objects.isNull(list)) {
            return null;
        } else {
            return (List<T>) list;
        }
    }

    public <T> void setSet(String key, @Nonnull Set<T> set) {
        redisTemplate.opsForSet().add(key, set.toArray());
    }

    @SuppressWarnings("unchecked")
    public <T> Set<T> getSet(String key) {
        Set<Object> members = redisTemplate.opsForSet().members(key);

        if (Objects.isNull(members)) {
            return Collections.emptySet();
        } else {
            return (Set<T>) members;
        }
    }

    public <T> void putHash(String key, String hashKey, T value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T getHashValue(String key, String hashKey) {
        Object value = redisTemplate.opsForHash().get(key, hashKey);

        if (Objects.isNull(value)) {
            return null;
        } else {
            return (T) value;
        }
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> getHashMultiValues(String key, Collection<Object> hashKeys) {
        return (List<T>) redisTemplate.opsForHash().multiGet(key, hashKeys);
    }

    public void removeHashValue(String key, String hashKey) {
        redisTemplate.opsForHash().delete(key, hashKey);
    }

    public <T> Map<String, T> getHashAll(String key, Class<T> clazz) {
        return MapConverter.convertMap(redisTemplate.opsForHash().entries(key), String.class, clazz);
    }

    public RLock tryLock(
            String lockKey,
            long waitTime,
            long leaseTime,
            TimeUnit unit
    ) {
        RLock lock = redissonClient.getLock(lockKey);
        try {
            if (lock.tryLock(waitTime, leaseTime, unit)) {
                return lock;
            } else {
                return null;
            }
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();

            log.error("尝试获取锁异常：{}", lockKey, exception);

            return null;
        }
    }

    public void unlock(RLock lock) {
        try {
            if (lock != null && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        } catch (Exception exception) {
            log.error("释放锁失败", exception);
        }
    }
}
