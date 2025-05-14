package com.astolfo;

import com.astolfo.infrastructure.persistence.entity.UserEntity;
import com.astolfo.infrastructure.persistence.mapper.UserMapper;
import jakarta.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;


    @Test
    public void testUserMapper() {
        UserEntity userEntity = userMapper.selectUserEntityByUsername("Astolfo");

        System.out.println(userEntity);
    }
}
