package org.rodneyparshall.rightrx.service;


import org.rodneyparshall.rightrx.model.Patient;

public interface PatientService {
    Patient create(Patient patient);
    Patient get(Long patientId);
    Patient update(Patient patient);
    Boolean delete(Long patientId);
}
