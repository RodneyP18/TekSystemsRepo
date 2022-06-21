package org.rodneyparshall.rightrx.repo;

import org.rodneyparshall.rightrx.model.Drug;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugRepo extends JpaRepository<Drug, Long> {
}
