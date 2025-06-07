package com.wheels.dados.project_api.controller;

import com.wheels.dados.project_api.model.DimBike;
import com.wheels.dados.project_api.repository.DimBikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bikes")
public class DimBikeController {

    private final DimBikeRepository dimBikeRepository;

    @Autowired
    public DimBikeController(DimBikeRepository dimBikeRepository) {
        this.dimBikeRepository = dimBikeRepository;
    }


    @GetMapping
    public ResponseEntity<List<DimBike>> getAllBikes() {
        List<DimBike> bikes = dimBikeRepository.findAll();
        if (bikes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bikes, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DimBike> getBikeById(@PathVariable("id") Integer id) {
        Optional<DimBike> bikeData = dimBikeRepository.findById(id);

        return bikeData.map(bike -> new ResponseEntity<>(bike, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}