package com.astolfo.common.utils;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class RedisCacheUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private RedissonClient redissonClient;


    public <T> void setObject(final String key, final T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public <T> void setObject(
            final String key,
            final T value,
            final long timeout,
            final TimeUnit unit
    ) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    public Boolean expire(
            final String key,
            final long timeout,
            final TimeUnit unit
    ) {
        return redisTemplate.expire(key, timeout, unit);
    }

    @SuppressWarnings("unchecked")
    public <T> T getObject(final String key) {
        return (T) redisTemplate.opsForValue().get(key);
    }

    public Boolean delete(final String key) {
        return redisTemplate.delete(key);

    }

    public Long delete(final Collection<String> keys) {
        return redisTemplate.delete(keys);
    }

    public <T> Long setList(final String key, final List<T> list) {
        return redisTemplate.opsForList().rightPushAll(key, list);
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> getList(final String key) {
        return (List<T>) redisTemplate.opsForList().range(key, 0, -1);
    }

    @SuppressWarnings("unchecked")
    public <T> BoundSetOperations<String, T> boundSet(final String key, final Set<T> set) {
        BoundSetOperations<String, T> setOps = (BoundSetOperations<String, T>) redisTemplate.boundSetOps(key);

        setOps.add((T) set);

        return setOps;
    }

    @SuppressWarnings("unchecked")
    public <T> Set<T> getSet(final String key) {
        return (Set<T>) redisTemplate.opsForSet().members(key);
    }

    public <T> Map<String, T> getMap(final String key, final Class<T> clazz) {
        return MapConverter.convertMap(redisTemplate.opsForHash().entries(key), String.class, clazz);
    }

    public <T> void setMapValue(
            final String key,
            final String hashKey,
            final T value
    ) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T getMapValue(final String key, final String hashKey) {
        return (T) redisTemplate.opsForHash().get(key, hashKey);
    }

    public void delete(final String key, final String hashKey) {
        redisTemplate.opsForHash().delete(key, hashKey);
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> getMultiMapValue(final String key, final Collection<Object> hashKeys) {
        return (List<T>) redisTemplate.opsForHash().multiGet(key, hashKeys);
    }

    public Collection<String> keys(final String pattern) {
        return redisTemplate.keys(pattern);
    }

    public RLock tryLock(
            final String lockKey,
            final long waitTime,
            final long leaseTime,
            final TimeUnit unit
    ) throws InterruptedException {
        RLock lock = redissonClient.getLock(lockKey);

        if (lock.tryLock(waitTime, leaseTime, unit)) {
            return lock;
        } else {
            return null;
        }
    }

    public void unlock(final RLock lock) {
        if (Objects.nonNull(lock) && lock.isHeldByCurrentThread()) {
            lock.unlock();
        }
    }

}
