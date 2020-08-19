package com.bjss.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bjss.domain.Cv;
import com.bjss.exception.CvNotFoundException;
import com.bjss.repository.CvRepository;

@RestController
@RequestMapping(path = "api/cvs")
public class CvApi {
    private final CvRepository repository;

    CvApi(CvRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    List<Cv> all() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    Cv getCv(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new CvNotFoundException(id));
    }

    @PostMapping
    Cv newCv(@Valid @RequestBody Cv newCv) {
        return repository.save(newCv);
    }

    @PutMapping("/{id}")
    Cv replaceCv(@PathVariable Long id, @Valid @RequestBody Cv newCv) {
        return repository.findById(id).map(cv -> {
            cv.setName(newCv.getName());
            cv.setSkills(newCv.getSkills());
            cv.setCompanyHistories(newCv.getCompanyHistories());
            return repository.save(cv);
        }).orElseThrow(() -> new CvNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    void deleteCv(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
