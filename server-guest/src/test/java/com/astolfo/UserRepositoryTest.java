package com.astolfo;

import com.astolfo.domain.rbac.model.root.User;
import com.astolfo.domain.rbac.model.valueobject.UserId;
import com.astolfo.domain.rbac.model.valueobject.Username;
import com.astolfo.domain.rbac.repository.UserRepository;
import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;
import java.util.Optional;

@Log4j2
@SpringBootTest
public class UserRepositoryTest {

    @Resource
    private UserRepository userRepository;

    @Test
    public void testUserRepository() {
        try {
            Optional<User> userOptional = userRepository.findUserById(UserId.of(1L));

            User user = userOptional.orElse(null);

            System.out.println(user);
        } catch (Exception exception) {
            log.error("exception: ", exception);
        }
    }

}
