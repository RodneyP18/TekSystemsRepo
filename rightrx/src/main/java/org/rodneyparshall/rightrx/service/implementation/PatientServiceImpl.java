package org.rodneyparshall.rightrx.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rodneyparshall.rightrx.model.Patient;
import org.rodneyparshall.rightrx.repo.PatientRepo;
import org.rodneyparshall.rightrx.service.PatientService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class PatientServiceImpl implements PatientService {

    private final PatientRepo patientRepo;


    @Override
    public Patient create(Patient patient) {
        log.info("Saving new Patient: {}{}", patient.getFirstName(), patient.getLastName());
        return patientRepo.save(patient);
    }

    @Override
    public Patient get(Long patientId) {

        return patientRepo.findById(patientId).get();
    }

    @Override
    public Patient update(Patient patient) {

        return patientRepo.save(patient);
    }

    @Override
    public Boolean delete(Long patientId) {
        patientRepo.deleteById(patientId);
        return Boolean.TRUE;
    }
}
