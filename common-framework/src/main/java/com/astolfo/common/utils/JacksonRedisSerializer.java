package com.astolfo.common.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class JacksonRedisSerializer<T> implements RedisSerializer<T> {

    private final ObjectMapper objectMapper;

    private final Class<T> clazz;

    private final TypeReference<T> typeReference;


    public JacksonRedisSerializer(Class<T> clazz) {
        this.clazz = clazz;
        this.typeReference = null;
        this.objectMapper = createSecureObjectMapper();
    }

    public JacksonRedisSerializer(TypeReference<T> typeReference) {
        this.clazz = null;
        this.typeReference = typeReference;
        this.objectMapper = createSecureObjectMapper();
    }

    private ObjectMapper createSecureObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();

        BasicPolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator
                .builder()
                .allowIfSubType(Object.class)
                .build();

        mapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);

        return mapper;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        }
        try {
            return objectMapper.writeValueAsBytes(t);
        } catch (Exception e) {
            throw new SerializationException("无法序列化对象", e);
        }
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        try {
            if (clazz != null) {
                return objectMapper.readValue(bytes, clazz);
            }
            if (typeReference != null) {
                return objectMapper.readValue(bytes, typeReference);
            }

            throw new IllegalStateException("类型信息缺失");
        } catch (Exception e) {
            throw new SerializationException("无法反序列化对象", e);
        }
    }
}
