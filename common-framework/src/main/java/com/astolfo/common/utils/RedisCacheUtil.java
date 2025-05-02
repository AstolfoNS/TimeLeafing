package com.astolfo.common.utils;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class RedisCacheUtil {

    private static final Logger logger = LoggerFactory.getLogger(RedisCacheUtil.class);

    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    public <T> void setObject(final String key, final T value) {
        try {
            redisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
            logger.error("Error setting object to Redis: {}", key, e);
        }
    }

    public <T> void setObject(final String key, final T value, final long timeout, final TimeUnit unit) {
        try {
            redisTemplate.opsForValue().set(key, value, timeout, unit);
        } catch (Exception e) {
            logger.error("Error setting object with timeout to Redis: {}", key, e);
        }
    }

    public Boolean expire(final String key, final long timeout, final TimeUnit unit) {
        try {
            return redisTemplate.expire(key, timeout, unit);
        } catch (Exception e) {
            logger.error("Error setting expiration for key: {}", key, e);

            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T getObject(final String key) {
        try {
            return (T) redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            logger.error("Error getting object from Redis: {}", key, e);

            return null;
        }
    }

    public Boolean delete(final String key) {
        try {
            return redisTemplate.delete(key);
        } catch (Exception e) {
            logger.error("Error deleting key from Redis: {}", key, e);

            return false;
        }
    }

    public Long delete(final Collection<String> keys) {
        try {
            return redisTemplate.delete(keys);
        } catch (Exception e) {
            logger.error("Error deleting keys from Redis: {}", keys, e);

            return 0L;
        }
    }

    public <T> Long setList(final String key, final List<T> list) {
        try {
            return redisTemplate.opsForList().rightPushAll(key, list);
        } catch (Exception e) {
            logger.error("Error setting list to Redis: {}", key, e);

            return 0L;
        }
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> getList(final String key) {
        try {
            return (List<T>) redisTemplate.opsForList().range(key, 0, -1);
        } catch (Exception e) {
            logger.error("Error getting list from Redis: {}", key, e);

            return Collections.emptyList();
        }
    }

    @SuppressWarnings("unchecked")
    public <T> BoundSetOperations<String, T> boundSet(String key, Set<T> set) {
        try {
            BoundSetOperations<String, T> setOps = (BoundSetOperations<String, T>) redisTemplate.boundSetOps(key);

            setOps.add((T) set);

            return setOps;
        } catch (Exception e) {
            logger.error("Error setting bound set to Redis: {}", key, e);

            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public <T> Set<T> getSet(final String key) {
        try {
            return (Set<T>) redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            logger.error("Error getting set from Redis: {}", key, e);

            return Collections.emptySet();
        }
    }

    public <T> Map<String, T> getMap(final String key, Class<T> clazz) {
        try {
            Map<Object, Object> rawMap = redisTemplate.opsForHash().entries(key);
            return MapConverter.convertMap(rawMap, String.class, clazz);
        } catch (Exception e) {
            logger.error("Error getting map from Redis: {}", key, e);

            return Collections.emptyMap();
        }
    }

    public <T> void setMapValue(final String key, final String hashKey, final T value) {
        try {
            redisTemplate.opsForHash().put(key, hashKey, value);
        } catch (Exception e) {
            logger.error("Error setting map value to Redis: {} -> {}", key, hashKey, e);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T getMapValue(final String key, final String hashKey) {
        try {
            return (T) redisTemplate.opsForHash().get(key, hashKey);
        } catch (Exception e) {
            logger.error("Error getting map value from Redis: {} -> {}", key, hashKey, e);

            return null;
        }
    }

    public void delete(final String key, final String hashKey) {
        try {
            redisTemplate.opsForHash().delete(key, hashKey);
        } catch (Exception e) {
            logger.error("Error deleting map value from Redis: {} -> {}", key, hashKey, e);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> getMultiMapValue(final String key, final Collection<Object> hashKeys) {
        try {
            return (List<T>) redisTemplate.opsForHash().multiGet(key, hashKeys);
        } catch (Exception e) {
            logger.error("Error getting multiple map values from Redis: {}", key, e);

            return Collections.emptyList();
        }
    }

    public Collection<String> keys(final String pattern) {
        try {
            return redisTemplate.keys(pattern);
        } catch (Exception e) {
            logger.error("Error fetching keys with pattern: {}", pattern, e);

            return Collections.emptyList();
        }
    }
}
