package org.rodneyparshall.rightrx;

import org.junit.jupiter.api.Test;
import org.rodneyparshall.rightrx.domain.User;
import org.rodneyparshall.rightrx.model.Drug;
import org.rodneyparshall.rightrx.model.Review;
import org.rodneyparshall.rightrx.repo.DrugRepo;
import org.rodneyparshall.rightrx.repo.ReviewRepo;
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
public class RepoTests {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private DrugRepo drugRepo;

    @Autowired
    private ReviewRepo reviewRepo;


    // Since both .Save() and .Get() are independent of each other. They are both preformed in the test to prove functionality.
    // Update would require the same processes as Create() so the test was not created.

    @Test
    public void testCreateAndGetUser(){
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

    @Test
    public void testCreateAndGetDrug(){
        Drug drug = new Drug();

        drug.setName("testDrug");

        drugRepo.save(drug);

        Drug toTest = drugRepo.findByName("testDrug");

        assertEquals(drug.getDrugId(), toTest.getDrugId());
    }

    @Test
    public void testDeleteDrug(){
        Drug drug = new Drug();

        drug.setName("testDrug");

        drugRepo.save(drug);
        Drug toTest = drugRepo.findByName("testDrug");

        drugRepo.deleteById(toTest.getDrugId());
        List<Drug> drugList = drugRepo.findAll();

        assertEquals(drugList.size(), 0);
    }

    @Test
    public void testCreateAndGetReview(){
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");
        userRepo.save(user);

        Drug drug = new Drug();
        drug.setName("testDrug");
        drugRepo.save(drug);

        Review review = new Review();
        review.setReviewInfo("testReview");
        review.setDrug(drug);
        review.setUser(user);
        Review toSave = reviewRepo.save(review);

        Review toTest = reviewRepo.getById(toSave.getReviewId());

        assertEquals(toSave.getReviewId(), toTest.getReviewId());
    }

    @Test
    public void testDeleteReview(){
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");
        userRepo.save(user);

        Drug drug = new Drug();
        drug.setName("testDrug");
        drugRepo.save(drug);

        Review review = new Review();
        review.setReviewInfo("testReview");
        review.setDrug(drug);
        review.setUser(user);
        Review toSave = reviewRepo.save(review);

        Review toTest = reviewRepo.getById(toSave.getReviewId());

        reviewRepo.deleteById(toTest.getReviewId());
        List<Review> reviewList = reviewRepo.findAll();

        assertEquals(reviewList.size(), 0);
    }
}
