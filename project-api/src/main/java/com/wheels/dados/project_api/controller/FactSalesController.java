package com.wheels.dados.project_api.controller;


import com.wheels.dados.project_api.model.FactSales;
import com.wheels.dados.project_api.model.FactSalesResponseDTO;
import com.wheels.dados.project_api.model.DimDateResponseDTO;
import com.wheels.dados.project_api.model.DimCustomerResponseDTO;
import com.wheels.dados.project_api.model.DimBikeResponseDTO;

import com.wheels.dados.project_api.model.DimDate;
import com.wheels.dados.project_api.model.DimCustomer;
import com.wheels.dados.project_api.model.DimBike;

import com.wheels.dados.project_api.repository.FactSalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sales")
public class FactSalesController {

    private final FactSalesRepository factSalesRepository;

    @Autowired
    public FactSalesController(FactSalesRepository factSalesRepository) {
        this.factSalesRepository = factSalesRepository;
    }


    @GetMapping
    public ResponseEntity<List<FactSalesResponseDTO>> getAllSales() {
        List<FactSales> salesEntities = factSalesRepository.findAll();

        if (salesEntities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<FactSalesResponseDTO> salesResponseDTOs = salesEntities.stream()
                .map(this::convertToResponseDTO) // Chama um método auxiliar para converter
                .collect(Collectors.toList());

        return new ResponseEntity<>(salesResponseDTOs, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<FactSalesResponseDTO> getSaleById(@PathVariable("id") Integer id) {
        Optional<FactSales> saleEntityOpt = factSalesRepository.findById(id);

        if (saleEntityOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        FactSalesResponseDTO responseDTO = convertToResponseDTO(saleEntityOpt.get());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // Método auxiliar para converter uma Entidade FactSales em um FactSalesResponseDTO
    private FactSalesResponseDTO convertToResponseDTO(FactSales saleEntity) {
        FactSalesResponseDTO saleDTO = new FactSalesResponseDTO();
        saleDTO.setSaleID(saleEntity.getSaleID());
        saleDTO.setHorasAlugadas(saleEntity.getHorasAlugadas());
        saleDTO.setValorAluguel(saleEntity.getValorAluguel());
        saleDTO.setTaxaAtraso(saleEntity.getTaxaAtraso());
        saleDTO.setTaxaDano(saleEntity.getTaxaDano());
        saleDTO.setDesconto(saleEntity.getDesconto());
        saleDTO.setValorTotal(saleEntity.getValorTotal());

        if (saleEntity.getDimDate() != null) {
            DimDate dateEntity = saleEntity.getDimDate();
            DimDateResponseDTO dateDTO = new DimDateResponseDTO();
            dateDTO.setDateId(dateEntity.getDateId());
            dateDTO.setData(dateEntity.getData());
            dateDTO.setNomeMes(dateEntity.getNomeMes());
            dateDTO.setAno(dateEntity.getAno());
            saleDTO.setDateDetails(dateDTO);
        }

        if (saleEntity.getDimCustomer() != null) {
            DimCustomer customerEntity = saleEntity.getDimCustomer();
            DimCustomerResponseDTO customerDTO = new DimCustomerResponseDTO();
            customerDTO.setCustomerID(customerEntity.getCustomerID());
            customerDTO.setNomeCompleto(customerEntity.getNomeCompleto());

            saleDTO.setCustomerDetails(customerDTO);
        }

        if (saleEntity.getDimBike() != null) {
            DimBike bikeEntity = saleEntity.getDimBike();
            DimBikeResponseDTO bikeDTO = new DimBikeResponseDTO();
            bikeDTO.setBikeID(bikeEntity.getBikeID());
            bikeDTO.setModelo(bikeEntity.getModelo());
            bikeDTO.setDisponivel(bikeEntity.isDisponivel());

            saleDTO.setBikeDetails(bikeDTO);
        }
        return saleDTO;
    }


}