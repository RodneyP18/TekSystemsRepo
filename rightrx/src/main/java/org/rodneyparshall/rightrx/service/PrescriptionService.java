package org.rodneyparshall.rightrx.service;

import org.rodneyparshall.rightrx.model.Prescription;
import org.springframework.security.core.parameters.P;

public interface PrescriptionService {
    Prescription create(Prescription prescription);
    Prescription get(Long rxId);
    Prescription update(Prescription prescription);
    Boolean delete(Long rxId);
}
