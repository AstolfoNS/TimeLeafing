package com.astolfo;

import com.astolfo.domain.rbac.model.root.User;
import com.astolfo.domain.rbac.model.valueobject.UserId;
import com.astolfo.domain.rbac.model.valueobject.Username;
import com.astolfo.domain.rbac.repository.UserRepository;
import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@Log4j2
@SpringBootTest
public class UserRepositoryTest {

    @Resource
    private UserRepository userRepository;

    @Test
    public void testUserRepository() {
        Optional<User> userOptional1 = userRepository.findUserWithoutRoleIdListById(UserId.of(1L));

//        log.info(userOptional1);

        System.out.println(userOptional1.orElse(null));

        Optional<User> userOptional2 = userRepository.findUserWithoutRoleIdListByUsername(Username.of("Astolfo"));

//        log.info(userOptional2);

        System.out.println(userOptional2.orElse(null));
    }

}
