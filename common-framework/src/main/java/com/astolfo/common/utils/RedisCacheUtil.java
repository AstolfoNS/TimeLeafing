package com.astolfo.common.utils;

import jakarta.annotation.Resource;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Data
@Component
public class RedisCacheUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Value("#{redisTemplate.opsForValue()}")
    private ValueOperations<String, Object> valueOps;

    @Value("#{redisTemplate.opsForList()}")
    private ListOperations<String, Object> listOps;

    @Value("#{redisTemplate.opsForSet()}")
    private SetOperations<String, Object> setOps;

    @Value("#{redisTemplate.opsForZSet()}")
    private ZSetOperations<String, Object> zSetOps;

    @Value("#{redisTemplate.opsForHash()}")
    private HashOperations<String, String, Object> hashOps;


    // ============================ Common ============================

    /**
     * 设置缓存（永不过期）
     * @param key 键
     * @param value 值
     */
    public void cacheObject(String key, Object value) {
        valueOps.set(key, value);
    }

    /**
     * 设置缓存（带过期时间）
     * @param key 键
     * @param value 值
     * @param timeout 时间量
     * @param unit 时间单位
     */
    public void cacheObject(
            String key,
            Object value,
            Long timeout,
            TimeUnit unit
    ) {
        valueOps.set(key, value, timeout, unit);
    }


    private static Long toTimeoutSecondsWithRandomOffset(
            Long baseTimeout,
            TimeUnit unit,
            Integer offsetRange
    ) {
        return unit.toSeconds(baseTimeout) * (long)(1L + Math.random() * offsetRange / 100.0);
    }

    /**
     * 设置缓存（带随机偏移的过期时间，防雪崩）
     * @param key 键
     * @param value 值
     * @param baseTimeout 基础过期时间
     * @param unit 时间单位
     * @param offsetRange 随机偏移范围（百分比 0-100）
     */
    public void cacheObjectWithRandomOffset(
            String key,
            Object value,
            Long baseTimeout,
            TimeUnit unit,
            Integer offsetRange
    ) {
        valueOps.set(key, value, toTimeoutSecondsWithRandomOffset(baseTimeout, unit, offsetRange), TimeUnit.SECONDS);
    }

    /**
     * 获取缓存对象
     * @param key 键
     * @param type 返回值类型
     * @return 对象或null
     */
    @SuppressWarnings("unchecked")
    public <T> T getObject(String key, Class<T> type) {
        Object value = valueOps.get(key);

        return type.isInstance(value) ? (T) value : null;
    }

    /**
     * 删除单个key
     * @param key 键
     */
    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    // ============================ List ============================

    /**
     * 获取列表缓存
     * @param key 键
     * @param type 元素类型
     * @return 整个列表
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> getList(String key, Class<T> type) {
        return (List<T>) listOps.range(key, 0, -1);
    }

    /**
     * 右压栈元素
     * @param key 键
     * @param value 值
     */
    public void rightPush(String key, Object value) {
        listOps.rightPush(key, value);
    }

    // ============================ Hash ============================

    /**
     * 设置Hash缓存
     * @param key 外部键
     * @param hashKey 哈希键
     * @param value 值
     */
    public void putHash(
            String key,
            String hashKey,
            Object value
    ) {
        hashOps.put(key, hashKey, value);
    }

    /**
     * 获取Hash字段
     * @param key 外部键
     * @param hashKey 哈希键
     * @param type 返回值类型
     */
    @SuppressWarnings("unchecked")
    public <T> T getHash(String key, String hashKey, Class<T> type) {
        Object value = hashOps.get(key, hashKey);
        return type.isInstance(value) ? (T) value : null;
    }

    // ============================ Advanced ============================

    /**
     * 安全获取缓存（防缓存穿透）
     * @param key 键
     * @param type 返回值类型
     * @param loader 数据加载器（当缓存未命中时调用）
     * @param timeout 过期时间
     * @param unit 时间单位
     */
    public <T> T safeGet(
            String key,
            Class<T> type,
            CacheLoader<T> loader,
            Long timeout,
            TimeUnit unit
    ) {
        T value = getObject(key, type);

        if (value != null) {
            return value;
        }

        synchronized (key.intern()) {
            value = getObject(key, type);

            if (value != null) {
                return value;
            }

            value = loader.load();

            if (value != null) {
                cacheObject(key, value, timeout, unit);
            } else {
                cacheObject(key, new NullValue(), 1L, TimeUnit.MINUTES);
            }

            return value;
        }
    }

    private static class NullValue {}

    @FunctionalInterface
    public interface CacheLoader<T> {
        T load();
    }

    public Map<String, Object> getCacheStats() {
        Properties info = redisTemplate
                .getRequiredConnectionFactory()
                .getConnection()
                .serverCommands()
                .info("stats");

        if (info == null) {
            return null;
        }

        return Map.of(
                "hits", info.getProperty("keyspace_hits"),
                "misses", info.getProperty("keyspace_misses"),
                "hitRate", calculateHitRate(
                        Long.parseLong(info.getProperty("keyspace_hits")),
                        Long.parseLong(info.getProperty("keyspace_misses"))
                )
        );
    }

    private static Double calculateHitRate(long hits, long total) {
        return total == 0 ? 0 : (double) hits / total;
    }

}