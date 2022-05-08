package com.iiitb.trello;

import com.iiitb.trello.model.entities.UserEntity;
import com.iiitb.trello.repo.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Order(1)
    @Test
    @Rollback(value = false)
    public void saveUserTest(){
        var user = new UserEntity();
        user.setFullName("Test user");
        user.setActive(true);
        user.setEmail("testuser@testemail.com");
        user.setPassword("sjfsjgsulgas28u363875w8igf^8%&");
        user.setTimeZoneId(37);
        user.setCreatedOn(Timestamp.from(Instant.now()));

        userRepository.save(user);

        Assertions.assertThat(user.getId()).isGreaterThan(0);
    }

    @Order(2)
    @Test
    public void findUserTest(){
         var user = userRepository.findByEmail("testuser@testemail.com");
         Assertions.assertThat(user.getEmail()).isEqualTo("testuser@testemail.com");
    }

    @Order(3)
    @Test
    public void findUsersListTest(){
        var users = userRepository.findAll();
        Assertions.assertThat(users.size()).isGreaterThan(0);
    }

    @Order(4)
    @Test
    @Rollback(value = false)
    public void deleteEmployeeTest(){
        var user = userRepository.findByEmail("testuser@testemail.com");
        userRepository.delete(user);
        Optional<UserEntity> deletedUser = Optional.ofNullable(userRepository.findByEmail("testuser@testemail.com"));

        UserEntity deletedUserEntity = null;
        if(deletedUser.isPresent()){
            deletedUserEntity = deletedUser.get();
        }

        Assertions.assertThat(deletedUserEntity).isNull();
    }
}
