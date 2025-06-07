package com.wheels.dados.project_api.controller;

import com.wheels.dados.project_api.model.DimDate;
import com.wheels.dados.project_api.repository.DimDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dates")
public class DimDateController {

    private final DimDateRepository dimDateRepository;

    @Autowired
    public DimDateController(DimDateRepository dimDateRepository) {
        this.dimDateRepository = dimDateRepository;
    }


    @GetMapping
    public ResponseEntity<List<DimDate>> getAllDates() {
        List<DimDate> dates = dimDateRepository.findAll();
        if (dates.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(dates, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DimDate> getDateById(@PathVariable("id") Integer id) {
        Optional<DimDate> dateData = dimDateRepository.findById(id);

        return dateData.map(date -> new ResponseEntity<>(date, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}