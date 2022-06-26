package org.rodneyparshall.rightrx.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rodneyparshall.rightrx.model.Drug;
import org.rodneyparshall.rightrx.repo.DrugRepo;
import org.rodneyparshall.rightrx.service.DrugService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class DrugServiceImpl implements DrugService {

    private final DrugRepo drugRepo;

    @Override
    public Drug create(Drug drug) {
        log.info("Saving new Drug: {}", drug.getName());
        return drugRepo.save(drug);
    }

    @Override
    public Drug findByDrugName(String drugName) {
        return drugRepo.findByName(drugName);
    }
    @Override
    public Drug update(Drug drug) {
        return drugRepo.save(drug);
    }

    @Override
    public Boolean delete(Long drugId) {
        drugRepo.deleteById(drugId);
        return Boolean.TRUE;
    }

    @Override
    public List<Drug> getAll() {
        return drugRepo.findAll();
    }
}
