package com.astolfo.common.utils;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.astolfo.common.constants.RedisCacheConstant.NULL_PLACEHOLDER;

@Slf4j
@Component
public class RedisCacheUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private RedissonClient redissonClient;

    private ValueOperations<String, Object> valueOps;

    private ListOperations<String, Object> listOps;

    private SetOperations<String, Object> setOps;

    private ZSetOperations<String, Object> zSetOps;

    private HashOperations<String, String, Object> hashOps;

    /* --------------------- 初始化 Operations --------------------- */
    @PostConstruct
    private void initOperations() {
        valueOps = redisTemplate.opsForValue();
        listOps = redisTemplate.opsForList();
        setOps = redisTemplate.opsForSet();
        zSetOps = redisTemplate.opsForZSet();
        hashOps = redisTemplate.opsForHash();
    }

    /* ====================== String 操作 ====================== */

    /**
     * 缓存对象（永不过期）
     */
    public void cacheObject(String key, Object value) {
        try {
            valueOps.set(key, value);
        } catch (Exception e) {
            log.error("[Redis] 缓存写入失败 key: {}", key, e);
        }
    }

    /**
     * 缓存对象（带过期时间）
     */
    public void cacheObject(
            String key,
            Object value,
            long timeout,
            TimeUnit unit
    ) {
        try {
            valueOps.set(key, value, timeout, unit);
        } catch (Exception e) {
            log.error("[Redis] 缓存写入失败 key: {}, timeout: {}", key, timeout, e);
        }
    }

    /**
     * 缓存对象（带随机偏移的过期时间）
     */
    public void cacheObjectWithRandomOffset(
            String key,
            Object value,
            long baseTimeout,
            TimeUnit unit,
            int offsetPercent
    ) {
        long baseSeconds = unit.toSeconds(baseTimeout);
        long randomOffset = (long) (baseSeconds * (Math.random() * offsetPercent / 100));
        long finalTimeout = baseSeconds + randomOffset;

        try {
            valueOps.set(key, value, finalTimeout, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("[Redis] 随机偏移缓存失败 key: {}", key, e);
        }
    }

    /**
     * 获取缓存对象
     */
    @SuppressWarnings("unchecked")
    public <T> T getObject(String key, Class<T> type) {
        try {
            Object value = valueOps.get(key);

            if (Objects.isNull(value)) {
                return null;
            }

            return type.isInstance(value) ? (T) value : null;
        } catch (Exception e) {
            log.error("[Redis] 缓存读取失败 key: {}", key, e);

            return null;
        }
    }

    /* ====================== List 操作 ====================== */

    /**
     * 右推入列表
     */
    public void rightPush(String key, Object value) {
        try {
            listOps.rightPush(key, value);
        } catch (Exception e) {
            log.error("[Redis] 列表右推失败 key: {}", key, e);
        }
    }

    /**
     * 获取列表范围
     */
    public <T> List<T> getList(String key, Class<T> type) {
        try {
            List<Object> values = listOps.range(key, 0, -1);

            return convertList(values, type);
        } catch (Exception e) {
            log.error("[Redis] 列表获取失败 key: {}", key, e);

            return Collections.emptyList();
        }
    }

    /* ====================== Set 操作 ====================== */

    /**
     * 添加集合元素
     */
    public void addToSet(String key, Object value) {
        try {
            setOps.add(key, value);
        } catch (Exception e) {
            log.error("[Redis] 集合添加失败 key: {}", key, e);
        }
    }

    /**
     * 获取整个集合
     */
    public <T> Set<T> getSet(String key, Class<T> type) {
        try {
            Set<Object> values = setOps.members(key);

            return convertSet(values, type);
        } catch (Exception e) {
            log.error("[Redis] 集合获取失败 key: {}", key, e);

            return Collections.emptySet();
        }
    }

    /* ====================== ZSet 操作 ====================== */

    /**
     * 添加有序集合元素
     */
    public void addToZSet(
            String key,
            Object value,
            double score
    ) {
        try {
            zSetOps.add(key, value, score);
        } catch (Exception e) {
            log.error("[Redis] ZSet添加失败 key: {}", key, e);
        }
    }

    /**
     * 获取有序集合前N名
     */
    public <T> Set<T> getTopZSet(
            String key,
            int topN,
            Class<T> type
    ) {
        try {
            Set<Object> values = zSetOps.reverseRange(key, 0, topN - 1);

            return convertSet(values, type);
        } catch (Exception e) {
            log.error("[Redis] ZSet获取失败 key: {}", key, e);

            return Collections.emptySet();
        }
    }

    /**
     * 获取元素分数
     */
    public Double getZSetScore(String key, Object member) {
        try {
            return zSetOps.score(key, member);
        } catch (Exception e) {
            log.error("[Redis] ZSet分数获取失败 key: {}", key, e);

            return null;
        }
    }

    /* ====================== Hash 操作 ====================== */

    /**
     * 添加哈希字段
     */
    public void putHash(
            String key,
            String hashKey,
            Object value
    ) {
        try {
            hashOps.put(key, hashKey, value);
        } catch (Exception e) {
            log.error("[Redis] Hash写入失败 key: {}, field: {}", key, hashKey, e);
        }
    }

    /**
     * 获取哈希字段
     */
    @SuppressWarnings("unchecked")
    public <T> T getHash(
            String key,
            String hashKey,
            Class<T> type
    ) {
        try {
            Object value = hashOps.get(key, hashKey);

            return type.isInstance(value) ? (T) value : null;
        } catch (Exception e) {
            log.error("[Redis] Hash读取失败 key: {}, field: {}", key, hashKey, e);

            return null;
        }
    }

    /* ====================== 分布式锁 + 缓存防穿透 ====================== */

    /**
     * 安全获取缓存（带分布式锁）
     */
    public <T> T safeGet(
            String key,
            Class<T> type,
            CacheLoader<T> loader,
            long timeout,
            TimeUnit unit,
            long nullTimeout
    ) {
        try {
            T value = getObject(key, type);

            if (Objects.nonNull(value)) {
                return value;
            }

            RLock lock = redissonClient.getLock(key + ":LOCK");
            try {
                // 非阻塞锁（等待0秒），锁有效期设置为业务超时的2倍
                if (lock.tryLock(0, timeout * 2, unit)) {
                    // 双重检查
                    value = getObject(key, type);
                    if (Objects.nonNull(value)) {
                        return value;
                    }

                    value = loader.load();

                    if (Objects.nonNull(value)) {
                        cacheObject(key, value, timeout, unit);
                    } else {
                        cacheObjectWithRandomOffset(key, NULL_PLACEHOLDER, nullTimeout, unit, 20);
                    }
                    return value;
                }
            } finally {
                assert lock != null;

                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();

            log.error("[Redis] 锁获取被中断 key: {}", key);
        } catch (Exception e) {
            log.error("[Redis] 安全获取缓存异常 key: {}", key, e);
        }
        return null;
    }

    @FunctionalInterface
    public interface CacheLoader<T> {
        T load();
    }

    /* ====================== 公共工具方法 ====================== */

    /**
     * 删除键
     */
    public boolean delete(String key) {
        try {
            return redisTemplate.delete(key);
        } catch (Exception e) {
            log.error("[Redis] 删除键失败 key: {}", key, e);

            return false;
        }
    }

    /**
     * 批量删除键
     */
    public long deleteBatch(Collection<String> keys) {
        try {
            return Optional.of(redisTemplate.delete(keys)).orElse(0L);
        } catch (Exception e) {
            log.error("[Redis] 批量删除失败 keys: {}", keys, e);

            return 0L;
        }
    }

    /**
     * 设置过期时间
     */
    public boolean expire(
            String key,
            long timeout,
            TimeUnit unit
    ) {
        try {
            return redisTemplate.expire(key, timeout, unit);
        } catch (Exception e) {
            log.error("[Redis] 设置过期时间失败 key: {}", key, e);

            return false;
        }
    }

    /* ====================== 类型转换工具 ====================== */

    private <T> List<T> convertList(List<Object> source, Class<T> type) {
        if (CollectionUtils.isEmpty(source)) {
            return Collections.emptyList();
        } else {
            return source
                    .stream()
                    .filter(Objects::nonNull)
                    .map(obj -> safeCast(obj, type))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }
    }

    private <T> Set<T> convertSet(Set<Object> source, Class<T> type) {
        if (CollectionUtils.isEmpty(source)) {
            return Collections.emptySet();
        } else {
            return source
                    .stream()
                    .filter(Objects::nonNull)
                    .map(obj -> safeCast(obj, type))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());
        }
    }

    @SuppressWarnings("unchecked")
    private <T> T safeCast(Object obj, Class<T> type) {
        try {
            return type.isInstance(obj) ? (T) obj : null;
        } catch (ClassCastException e) {
            log.warn("[Redis] 类型转换失败 期望类型: {}, 实际类型: {}", type.getName(), obj.getClass().getName());

            return null;
        }
    }

    /* ====================== 统计信息 ====================== */

    /**
     * 获取缓存命中统计
     */
    public Map<String, Object> getCacheStats() {
        try {
            assert redisTemplate.getConnectionFactory() != null;

            try (RedisConnection connection = redisTemplate.getConnectionFactory().getConnection()) {
                Properties stats = connection.serverCommands().info("stats");

                assert stats != null;

                long hits = Long.parseLong(stats.getProperty("keyspace_hits", "0"));
                long misses = Long.parseLong(stats.getProperty("keyspace_misses", "0"));

                return Map.of(
                        "hits", hits,
                        "misses", misses,
                        "hitRate", calculateHitRate(hits, misses)
                );
            }
        } catch (Exception e) {
            log.error("[Redis] 获取统计信息失败", e);

            return Collections.emptyMap();
        }
    }

    private double calculateHitRate(long hits, long misses) {
        long total = hits + misses;

        return total == 0 ? 0.0 : (double) hits / total * 100;
    }
}