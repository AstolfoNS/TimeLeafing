package com.astolfo;

import com.astolfo.domain.domain.rbac.model.User;
import com.astolfo.domain.domain.rbac.repository.UserRepository;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryTest {

    @Resource
    private UserRepository userRepository;

    @Test
    public void testUserRepository() {
        User user1 = userRepository.findUserByUsername("Astolfo").orElse(null);
        User user2 = userRepository.findUserById(1L).orElse(null);

        System.out.println(user1);
        System.out.println(user2);
    }

}
