package com.astolfo.common.utils;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;


@Component
public class RedisCacheUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

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

//    @SuppressWarnings("unchecked")
//    public <T> BoundSetOperations<String, T> boundSet(final String key, final Set<T> set) {
//        BoundSetOperations<String, T> setOperations = (BoundSetOperations<String, T>) redisTemplate.boundSetOps(key);
//
//        for (T t : set) {
//            setOperations.add(t);
//        }
//
//        return setOperations;
//    }
    @SuppressWarnings("unchecked")
    public <T> BoundSetOperations<String, T> boundSet(String key, Set<T> set) {
        BoundSetOperations<String, T> setOps = (BoundSetOperations<String, T>) redisTemplate.boundSetOps(key);

        setOps.add((T[]) set.toArray());

        return setOps;
    }

    @SuppressWarnings("unchecked")
    public <T> Set<T> getSet(final String key) {
        return (Set<T>) redisTemplate.opsForSet().members(key);
    }

    public <T> Map<String, T> getMap(String key, Class<T> clazz) {
        return MapConverter.convertMap(redisTemplate.opsForHash().entries(key), String.class, clazz);
    }




}