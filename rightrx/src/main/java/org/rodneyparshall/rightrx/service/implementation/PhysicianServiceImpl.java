package org.rodneyparshall.rightrx.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rodneyparshall.rightrx.model.Physician;
import org.rodneyparshall.rightrx.repo.PhysicianRepo;
import org.rodneyparshall.rightrx.service.PhysicianService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class PhysicianServiceImpl implements PhysicianService {

    private final PhysicianRepo physicianRepo;

    @Override
    public Physician create(Physician physician) {
        log.info("Saving new Physician: {}{}", physician.getFirstName(), physician.getLastName());
        return physicianRepo.save(physician);
    }

    @Override
    public Physician get(Long phyId) {

        return physicianRepo.findById(phyId).get();
    }

    @Override
    public Physician update(Physician physician) {

        return physicianRepo.save(physician);
    }

    @Override
    public Boolean delete(Long phyId) {
        physicianRepo.deleteById(phyId);
        return Boolean.TRUE;
    }
}
