package org.rodneyparshall.rightrx.service;


import org.rodneyparshall.rightrx.model.Drug;

public interface DrugService {
    Drug create(Drug drug);
    Drug get(Long drugId);
    Drug update(Drug drug);
    Boolean delete(Long drugId);
}
