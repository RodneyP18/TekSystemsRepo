package org.rodneyparshall.rightrx;

import org.junit.jupiter.api.Test;
import org.rodneyparshall.rightrx.domain.User;
import org.rodneyparshall.rightrx.exception.domain.UserNotFoundException;
import org.rodneyparshall.rightrx.exception.domain.UsernameExistsException;
import org.rodneyparshall.rightrx.model.Drug;
import org.rodneyparshall.rightrx.service.DrugService;
import org.rodneyparshall.rightrx.service.ReviewService;
import org.rodneyparshall.rightrx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Rollback()
public class ServiceTests {

    @Autowired
    private UserService userService;

    @Autowired
    private DrugService drugService;

    @Autowired
    private ReviewService reviewService;



    // Since both .Register() and .Get() are independent of each other. They are both preformed in the test to prove functionality.

    @Test
    public void testRegisterAndGetUser() throws UserNotFoundException, UsernameExistsException {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");
        userService.register(user.getUsername(), user.getPassword());

        User toTest = userService.findUserByUsername("testUser");

        assertEquals(user.getUsername(), toTest.getUsername());
    }

    @Test
    public void testDeleteUser(){
        User toTest = userService.findUserByUsername("testUser");

        userService.deleteUser(toTest.getUserId());
        List<User> userList = userService.getAll();

        assertEquals(userList.size(), 0);
    }

    @Test
    public void testUpdateUser() throws UserNotFoundException, UsernameExistsException {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");
        userService.register(user.getUsername(), user.getPassword());
        User toSave = userService.findUserByUsername("testUser");
        User toTest = userService.updateUser(toSave.getUsername(), "newUsername", user.getPassword());

        assertEquals(toTest.getUsername(), "newUsername");
        userService.deleteUser(toTest.getUserId());
    }

    @Test
    public void testCreateDrug(){
        Drug drug = new Drug();
        drug.setName("testDrug");

        drugService.create(drug);
        List<Drug> drugs = drugService.getAll();

        assertEquals(drugs.size(), 1);
    }

    @Test
    public void testDeleteDrug(){
        Drug toTest = drugService.findByDrugName("testDrug");

        drugService.delete(toTest.getDrugId());
        List<Drug> drugList = drugService.getAll();

        assertEquals(drugList.size(), 0);
    }
//
//    @Test
//    public void testCreateReview() throws UserNotFoundException, UsernameExistsException {
//        User user = new User();
//        user.setUsername("testUser");
//        user.setPassword("testPassword");
//        userService.register(user.getUsername(), user.getPassword());
//
//        Drug drug = new Drug();
//        drug.setName("testDrug");
//        drugRepo.save(drug);
//
//        Review review = new Review();
//        review.setReviewInfo("testReview");
//        review.setDrug(drug);
//        review.setUser(user);
//        Review toSave = reviewRepo.save(review);
//
//        Review toTest = reviewRepo.getById(toSave.getReviewId());
//
//        assertEquals(toSave.getReviewId(), toTest.getReviewId());
//    }
//
//    @Test
//    public void testDeleteReview() throws UserNotFoundException, UsernameExistsException {
//        User user = new User();
//        user.setUsername("testUser");
//        user.setPassword("testPassword");
//        userService.register(user.getUsername(), user.getPassword());
//
//        Drug drug = new Drug();
//        drug.setName("testDrug");
//        drugRepo.save(drug);
//
//        Review review = new Review();
//        review.setReviewInfo("testReview");
//        review.setDrug(drug);
//        review.setUser(user);
//        Review toSave = reviewRepo.save(review);
//
//        Review toTest = reviewRepo.getById(toSave.getReviewId());
//
//        reviewRepo.deleteById(toTest.getReviewId());
//        List<Review> reviewList = reviewRepo.findAll();
//
//        assertEquals(reviewList.size(), 0);
//    }
}
