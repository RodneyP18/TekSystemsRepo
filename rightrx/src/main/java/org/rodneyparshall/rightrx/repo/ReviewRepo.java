package org.rodneyparshall.rightrx.repo;

import org.rodneyparshall.rightrx.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepo extends JpaRepository<Review, Long> {
}
