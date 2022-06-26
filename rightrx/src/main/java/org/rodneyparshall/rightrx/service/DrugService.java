package org.rodneyparshall.rightrx.service;


import org.rodneyparshall.rightrx.model.Drug;

import java.util.List;

public interface DrugService {
    Drug create(Drug drug);
    Drug findByDrugName(String drugName);
    Drug update(Drug drug);
    Boolean delete(Long drugId);
    List<Drug> getAll();
}
