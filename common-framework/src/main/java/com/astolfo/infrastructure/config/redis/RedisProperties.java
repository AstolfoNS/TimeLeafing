package com.astolfo.infrastructure.config.redis;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class RedisProperties {

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private Integer redisPort;

    @Value("${spring.data.redis.password}")
    private String redisPassword;

    @Value("${custom.redis.redisson-address}")
    private String redissonAddress;

    @Value("${custom.redis.redisson-database}")
    private Integer redissonDatabase;
    
}
