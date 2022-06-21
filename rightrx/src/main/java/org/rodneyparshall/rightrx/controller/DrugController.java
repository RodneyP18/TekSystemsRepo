package org.rodneyparshall.rightrx.controller;

import org.rodneyparshall.rightrx.model.Drug;
import org.rodneyparshall.rightrx.service.DrugService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drug")
public class DrugController {

    DrugService drugService;

    public DrugController(DrugService drugService) {
        this.drugService = drugService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Drug>> getAllDrugs(){
        List<Drug> drugs = drugService.getAll();
        return new ResponseEntity<>(drugs, HttpStatus.OK);
    }

    @GetMapping(path = "/getDrug/{id}")
    public ResponseEntity<Drug> getDrug(@PathVariable("id") Long id){
        Drug drug = drugService.get(id);
        return new ResponseEntity<>(drug, HttpStatus.OK);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Drug> addDrug(@RequestBody Drug drug){
        Drug addedDrug = drugService.update(drug);
        return new ResponseEntity<>(addedDrug, HttpStatus.CREATED);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Drug> updateDrug(@RequestBody Drug drug){
        Drug updatedDrug = drugService.update(drug);
        return new ResponseEntity<>(updatedDrug, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDrug(@PathVariable("id") Long id){
        drugService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
}
}