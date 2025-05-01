package com.astolfo.common.utils;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.astolfo.common.constants.RedisCacheConstant.NULL_PLACEHOLDER;

@Component
public class RedisCacheUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private final ValueOperations<String, Object> valueOps;

    private final ListOperations<String, Object> listOps;

    private final SetOperations<String, Object> setOps;

    private final ZSetOperations<String, Object> zSetOps;

    private final HashOperations<String, String, Object> hashOps;


    public RedisCacheUtil() {
        this.valueOps = redisTemplate.opsForValue();
        this.listOps = redisTemplate.opsForList();
        this.setOps = redisTemplate.opsForSet();
        this.zSetOps = redisTemplate.opsForZSet();
        this.hashOps = redisTemplate.opsForHash();
    }

    public void cacheObject(String key, Object value) {
        valueOps.set(key, value);
    }

    public void cacheObject(
            String key,
            Object value,
            long timeout,
            TimeUnit unit
    ) {
        valueOps.set(key, value, timeout, unit);
    }

    private long toTimeoutWithOffset(
            long baseTimeout,
            TimeUnit unit,
            int offsetPercent
    ) {
        return (long)(unit.toSeconds(baseTimeout) * (1L + Math.random() * offsetPercent / 100.0));
    }

    public void cacheObjectWithRandomOffset(
            String key,
            Object value,
            long baseTimeout,
            TimeUnit unit,
            int offsetPercent
    ) {
        valueOps.set(key, value, toTimeoutWithOffset(baseTimeout, unit, offsetPercent), TimeUnit.SECONDS);
    }

    @SuppressWarnings("unchecked")
    public <T> T getObject(String key, Class<T> type) {
        Object value = valueOps.get(key);

        if (value == null || NULL_PLACEHOLDER.equals(value)) {
            return null;
        }

        return type.isInstance(value) ? (T) value : null;
    }

    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    public void rightPush(String key, Object value) {
        listOps.rightPush(key, value);
    }

    public <T> List<T> getList(String key, Class<T> type) {
        return Objects
                .requireNonNull(listOps.range(key, 0, -1))
                .stream()
                .filter(type::isInstance)
                .map(type::cast)
                .collect(Collectors.toList());
    }

    public void putHash(String key, String hashKey, Object value) {
        hashOps.put(key, hashKey, value);
    }

    public <T> T getHash(String key, String hashKey, Class<T> type) {
        Object value = hashOps.get(key, hashKey);

        return type.isInstance(value) ? type.cast(value) : null;
    }

    public <T> T safeGet(
            String key,
            Class<T> type,
            CacheLoader<T> loader,
            Long timeout,
            TimeUnit unit
    ) {
        T value = getObject(key, type);

        if (value != null){
            return value;
        }

        synchronized (key.intern()) {
            value = getObject(key, type);

            if (value != null){
                return value;
            }

            value = loader.load();

            if (value != null) {
                cacheObject(key, value, timeout, unit);
            } else {
                cacheObject(key, NULL_PLACEHOLDER, 1L, TimeUnit.MINUTES);
            }

            return value;
        }
    }

    @FunctionalInterface
    public interface CacheLoader<T> {
        T load();
    }

    public Map<String, Object> getCacheStats() {
        Properties stats = redisTemplate
                .getRequiredConnectionFactory()
                .getConnection()
                .serverCommands()
                .info("stats");

        if (Objects.isNull(stats)) {
            return null;
        }

        long hits = Long.parseLong(stats.getProperty("keyspace_hits", "0"));
        long misses = Long.parseLong(stats.getProperty("keyspace_misses", "0"));

        return Map.of(
                "hits", hits,
                "misses", misses,
                "hitRate", calculateHitRate(hits, misses)
        );
    }

    private double calculateHitRate(long hits, long misses) {
        long total = hits + misses;

        return total == 0 ? 0.0 : (double) hits / total;
    }
}
