package org.rodneyparshall.rightrx.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rodneyparshall.rightrx.model.Review;
import org.rodneyparshall.rightrx.repo.ReviewRepo;
import org.rodneyparshall.rightrx.service.ReviewService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepo reviewRepo;

    @Override
    public Review create(Review review) {
        return reviewRepo.save(review);
    }

    @Override
    public Review get(Long reviewId) {
        return reviewRepo.findById(reviewId).get();
    }

    @Override
    public Review update(Review review) {
        return reviewRepo.save(review);
    }

    @Override
    public Boolean delete(Long reviewId) {
        reviewRepo.deleteById(reviewId);
        return Boolean.TRUE;
    }

    @Override
    public List<Review> getAll() {
        return reviewRepo.findAll();
    }
}
