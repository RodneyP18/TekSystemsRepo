package org.rodneyparshall.rightrx.service;


import org.rodneyparshall.rightrx.model.Physician;

public interface PhysicianService {
    Physician create(Physician physician);
    Physician get(Long phyId);
    Physician update(Physician physician);
    Boolean delete(Long phyId);
}
