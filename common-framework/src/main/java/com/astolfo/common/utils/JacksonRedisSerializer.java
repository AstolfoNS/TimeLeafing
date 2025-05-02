package com.astolfo.common.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.util.Objects;

public class JacksonRedisSerializer<T> implements RedisSerializer<T> {

    private final ObjectMapper objectMapper;

    private final Class<T> type;



    public JacksonRedisSerializer(Class<T> type, ObjectMapper objectMapper) {
        this.type = type;
        this.objectMapper = objectMapper
                .copy()
                .activateDefaultTyping(objectMapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (Objects.isNull(t)) {
            return new byte[0];
        }
        try {
            return objectMapper.writeValueAsBytes(t);
        } catch (Exception e) {
            throw new SerializationException("序列化失败", e);
        }
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (Objects.isNull(bytes) || bytes.length == 0) {
            return null;
        }
        try {
            return objectMapper.readValue(bytes, type);
        } catch (Exception e) {
            throw new SerializationException("反序列化失败", e);
        }
    }
}
