package org.rodneyparshall.rightrx.repo;

import org.rodneyparshall.rightrx.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Long> {
}
