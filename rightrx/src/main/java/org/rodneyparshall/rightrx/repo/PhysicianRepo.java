package org.rodneyparshall.rightrx.repo;

import org.rodneyparshall.rightrx.model.Physician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysicianRepo extends JpaRepository<Physician, Long> {
}
