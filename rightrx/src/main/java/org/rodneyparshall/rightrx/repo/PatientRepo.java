package org.rodneyparshall.rightrx.repo;

import org.rodneyparshall.rightrx.model.Patient;
import org.rodneyparshall.rightrx.model.Physician;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepo extends JpaRepository<Patient, Long> {
}
