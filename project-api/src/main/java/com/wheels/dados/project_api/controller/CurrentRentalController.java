package com.wheels.dados.project_api.controller;

import com.wheels.dados.project_api.model.*;

import com.wheels.dados.project_api.model.CurrentRentalResponseDTO;
import com.wheels.dados.project_api.model.DimCustomerResponseDTO;
import com.wheels.dados.project_api.model.DimBikeResponseDTO;

import com.wheels.dados.project_api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/current-rentals")
public class CurrentRentalController {

    private final CurrentRentalRepository currentRentalRepository;
    private final DimCustomerRepository dimCustomerRepository;
    private final DimBikeRepository dimBikeRepository;
    private final FactSalesRepository factSalesRepository;
    private final DimDateRepository dimDateRepository;

    @Autowired
    public CurrentRentalController(CurrentRentalRepository currentRentalRepository,
                                   DimCustomerRepository dimCustomerRepository,
                                   DimBikeRepository dimBikeRepository,
                                   FactSalesRepository factSalesRepository,
                                   DimDateRepository dimDateRepository) {
        this.currentRentalRepository = currentRentalRepository;
        this.dimCustomerRepository = dimCustomerRepository;
        this.dimBikeRepository = dimBikeRepository;
        this.factSalesRepository = factSalesRepository;
        this.dimDateRepository = dimDateRepository;
    }


    @GetMapping
    public ResponseEntity<List<CurrentRentalResponseDTO>> getAllCurrentRentals() {
        List<CurrentRental> rentalEntities = currentRentalRepository.findAll();
        if (rentalEntities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<CurrentRentalResponseDTO> rentalResponseDTOs = rentalEntities.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(rentalResponseDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CurrentRentalResponseDTO> getCurrentRentalById(@PathVariable("id") Integer id) {
        Optional<CurrentRental> rentalEntityOpt = currentRentalRepository.findById(id);
        if (rentalEntityOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        CurrentRentalResponseDTO responseDTO = convertToResponseDTO(rentalEntityOpt.get());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    private CurrentRentalResponseDTO convertToResponseDTO(CurrentRental rentalEntity) {
        CurrentRentalResponseDTO rentalDTO = new CurrentRentalResponseDTO();
        rentalDTO.setRentalID(rentalEntity.getRentalID());
        rentalDTO.setRentalStart(rentalEntity.getRentalStart());
        rentalDTO.setExpectedReturn(rentalEntity.getExpectedReturn());
        rentalDTO.setObservations(rentalEntity.getObservations());
        if (rentalEntity.getDimCustomer() != null) {
            DimCustomerResponseDTO customerDTO = new DimCustomerResponseDTO();
            DimCustomer customerEntity = rentalEntity.getDimCustomer();
            customerDTO.setCustomerID(customerEntity.getCustomerID());
            customerDTO.setNomeCompleto(customerEntity.getNomeCompleto());
            rentalDTO.setCustomer(customerDTO);
        }
        if (rentalEntity.getDimBike() != null) {
            DimBikeResponseDTO bikeDTO = new DimBikeResponseDTO();
            DimBike bikeEntity = rentalEntity.getDimBike();
            bikeDTO.setBikeID(bikeEntity.getBikeID());
            bikeDTO.setModelo(bikeEntity.getModelo());
            bikeDTO.setDisponivel(bikeEntity.isDisponivel());
            rentalDTO.setBike(bikeDTO);
        }
        return rentalDTO;
    }


    @PostMapping
    @Transactional
    public ResponseEntity<?> createRental(@RequestBody CreateRentalRequestDTO rentalRequestDTO) {
        try {
            if (rentalRequestDTO.getCustomerId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O ID do cliente (customerId) é obrigatório.");
            }
            DimCustomer customer = dimCustomerRepository.findById(rentalRequestDTO.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + rentalRequestDTO.getCustomerId()));

            if (rentalRequestDTO.getBikeId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O ID da bicicleta (bikeId) é obrigatório.");
            }
            DimBike bike = dimBikeRepository.findById(rentalRequestDTO.getBikeId())
                    .orElseThrow(() -> new RuntimeException("Bicicleta não encontrada com ID: " + rentalRequestDTO.getBikeId()));

            if (!bike.isDisponivel()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Bicicleta com ID: " + rentalRequestDTO.getBikeId() + " não está disponível.");
            }

            if (rentalRequestDTO.getExpectedReturn() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de devolução esperada (expectedReturn) é obrigatória.");
            }

            CurrentRental newRental = new CurrentRental();
            newRental.setDimCustomer(customer);
            newRental.setDimBike(bike);
            newRental.setRentalStart(rentalRequestDTO.getRentalStart() != null ? rentalRequestDTO.getRentalStart() : LocalDateTime.now());
            newRental.setExpectedReturn(rentalRequestDTO.getExpectedReturn());
            newRental.setObservations(rentalRequestDTO.getObservations());

            CurrentRental savedRental = currentRentalRepository.save(newRental);

            bike.setDisponivel(false);
            dimBikeRepository.save(bike);

            CurrentRentalResponseDTO responseDto = convertToResponseDTO(savedRental); // Retornando DTO
            return new ResponseEntity<>(responseDto, HttpStatus.CREATED);

        } catch (RuntimeException e) {

            if (e.getMessage().contains("não encontrado")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao processar o aluguel: " + e.getMessage());
        }
    }


    @PostMapping("/{id}/return")
    @Transactional
    public ResponseEntity<?> returnBike(@PathVariable("id") Integer rentalId) {
        try {

            CurrentRental rental = currentRentalRepository.findById(rentalId)
                    .orElseThrow(() -> new RuntimeException("Aluguel não encontrado com ID: " + rentalId));


            DimBike bike = rental.getDimBike();
            if (bike == null) {

                throw new RuntimeException("Bicicleta não associada a este aluguel. ID do Aluguel: " + rentalId);
            }


            if (bike.isDisponivel()) {

                currentRentalRepository.delete(rental);
                return ResponseEntity.ok("Bicicleta ID: " + bike.getBikeID() + " já constava como disponível. Aluguel ID: " + rentalId + " removido de CurrentRentals.");
            }


            bike.setDisponivel(true);
            dimBikeRepository.save(bike);


            FactSales saleFact = new FactSales();
            saleFact.setDimCustomer(rental.getDimCustomer());
            saleFact.setDimBike(bike);

            LocalDate rentalStartDate = rental.getRentalStart().toLocalDate();
            DimDate dateDimension = dimDateRepository.findByData(rentalStartDate)
                    .orElseThrow(() -> new RuntimeException("Entrada DimDate não encontrada para a data: " + rentalStartDate +
                            ". Certifique-se que sua tabela DimDate está populada para esta data."));
            saleFact.setDimDate(dateDimension);


            saleFact.setHorasAlugadas(2.0);
            saleFact.setValorAluguel(bike.getValorHora() != null ? bike.getValorHora() * 2.0 : 0.0);
            saleFact.setTaxaAtraso(0.0);
            saleFact.setTaxaDano(0.0);
            saleFact.setDesconto(0.0);
            saleFact.setValorTotal(saleFact.getValorAluguel() + saleFact.getTaxaAtraso() + saleFact.getTaxaDano() - saleFact.getDesconto());

            factSalesRepository.save(saleFact);


            currentRentalRepository.delete(rental);

            return ResponseEntity.ok("Aluguel ID: " + rentalId + " finalizado e removido. Bicicleta ID: " + bike.getBikeID() +
                    " disponível. Registro de venda criado com ID: " + saleFact.getSaleID());

        } catch (RuntimeException e) {

            if (e.getMessage().contains("não encontrado")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao processar devolução: " + e.getMessage());
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao finalizar o aluguel: " + e.getMessage());
        }
    }
}