package org.rodneyparshall.rightrx.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rodneyparshall.rightrx.model.Prescription;
import org.rodneyparshall.rightrx.repo.PrescriptionRepo;
import org.rodneyparshall.rightrx.service.PrescriptionService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionRepo prescriptionRepo;

    @Override
    public Prescription create(Prescription prescription) {

        return prescriptionRepo.save(prescription);
    }

    @Override
    public Prescription get(Long rxId) {

        return prescriptionRepo.findById(rxId).get();
    }

    @Override
    public Prescription update(Prescription prescription) {

        return prescriptionRepo.save(prescription);
    }

    @Override
    public Boolean delete(Long rxId) {
        prescriptionRepo.deleteById(rxId);
        return Boolean.TRUE;
    }
}
