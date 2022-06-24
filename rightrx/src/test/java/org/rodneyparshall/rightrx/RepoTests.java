package org.rodneyparshall.rightrx;

import org.junit.jupiter.api.Test;
import org.rodneyparshall.rightrx.domain.User;
import org.rodneyparshall.rightrx.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Rollback()
public class UserTests {

    @Autowired
    private UserRepo userRepo;

    @Test
    public void testCreateUser(){
        User user = new User();

        user.setUsername("testUser");
        user.setPassword("testPassword");

        userRepo.save(user);

        User toTest = userRepo.findUserByUsername("testUser");

        assertEquals(user.getUserId(), toTest.getUserId());
    }

    @Test
    public void testGetUser(){
        User user = new User();

        user.setUsername("testUser");
        user.setPassword("testPassword");

        userRepo.save(user);

        User toTest = userRepo.findUserByUsername("testUser");

        assertEquals(user.getUserId(), toTest.getUserId());
    }


    @Test
    public void testDeleteUser(){
        User user = new User();

        user.setUsername("testUser");
        user.setPassword("testPassword");

        userRepo.save(user);
        User toTest =userRepo.findUserByUsername("testUser");

        userRepo.deleteById(toTest.getUserId());
        List<User> userList = userRepo.findAll();

        assertEquals(userList.size(), 0);


    }
}
