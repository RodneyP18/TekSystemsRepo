package org.rodneyparshall.rightrx.repo;

import org.rodneyparshall.rightrx.model.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugRepo extends JpaRepository<Drug, Long> {
    Drug findByName(String name);
}
