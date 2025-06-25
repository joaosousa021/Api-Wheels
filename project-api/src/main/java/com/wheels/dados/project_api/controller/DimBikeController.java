package com.wheels.dados.project_api.controller;

import com.wheels.dados.project_api.model.DimBike;
import com.wheels.dados.project_api.repository.DimBikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    @PostMapping
    public ResponseEntity<?> createBike(@RequestBody DimBike bike) {
        try {

            if (bike.getBikeID() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("O campo 'bikeID' é obrigatório para cadastrar uma nova bicicleta.");
            }


            if (dimBikeRepository.existsById(bike.getBikeID())) {
                return ResponseEntity.status(HttpStatus.CONFLICT) // 409 Conflict
                        .body("Já existe uma bicicleta com o bikeID fornecido: " + bike.getBikeID());
            }


            DimBike savedBike = dimBikeRepository.save(bike);
            return new ResponseEntity<>(savedBike, HttpStatus.CREATED); // Retorna 201 Created

        } catch (DataIntegrityViolationException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro de dados. Verifique se todos os campos obrigatórios foram preenchidos corretamente.");
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro interno ao cadastrar a bicicleta.");
        }
    }

}