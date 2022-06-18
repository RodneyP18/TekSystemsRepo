package org.rodneyparshall.rightrx.repo;

import org.rodneyparshall.rightrx.model.Patient;
import org.rodneyparshall.rightrx.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepo extends JpaRepository<Prescription, Long> {
}
