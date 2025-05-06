package com.astolfo.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class JacksonRedisSerializer<T> implements RedisSerializer<T> {

    private final ObjectMapper objectMapper;

    private final Class<T> type;


    public JacksonRedisSerializer(Class<T> type, ObjectMapper objectMapper) {
        this.type = type;
        this.objectMapper = objectMapper;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        }
        try {
            return objectMapper.writeValueAsBytes(t);
        } catch (Exception e) {
            throw new SerializationException("Error serializing object of type " + type.getName(), e);
        }
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        try {
            return objectMapper.readValue(bytes, type);
        } catch (Exception e) {
            throw new SerializationException("Error deserializing to " + type.getName(), e);
        }
    }
}
