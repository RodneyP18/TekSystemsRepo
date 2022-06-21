package org.rodneyparshall.rightrx.service;

import org.rodneyparshall.rightrx.model.Review;

import java.util.List;

public interface ReviewService {
    Review create(Review review);
    Review get(Long reviewId);
    Review update(Review review);
    Boolean delete(Long reviewId);

    List<Review> getAll();
}
