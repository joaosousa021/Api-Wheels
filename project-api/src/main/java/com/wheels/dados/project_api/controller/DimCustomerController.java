package com.wheels.dados.project_api.controller;

import com.wheels.dados.project_api.model.DimCustomer;
import com.wheels.dados.project_api.repository.DimCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/customers")
public class DimCustomerController {

    private final DimCustomerRepository dimCustomerRepository;

    @Autowired
    public DimCustomerController(DimCustomerRepository dimCustomerRepository) {
        this.dimCustomerRepository = dimCustomerRepository;
    }


    @GetMapping
    public ResponseEntity<List<DimCustomer>> getAllCustomers() {
        List<DimCustomer> customers = dimCustomerRepository.findAll();
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DimCustomer> getCustomerById(@PathVariable("id") Integer id) {
        Optional<DimCustomer> customerData = dimCustomerRepository.findById(id);

        return customerData.map(customer -> new ResponseEntity<>(customer, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }




    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody DimCustomer customerRequestData) { // Renomeado para clareza
        try {

            DimCustomer newCustomer = new DimCustomer();


            newCustomer.setCpf(customerRequestData.getCpf());
            newCustomer.setNomeCompleto(customerRequestData.getNomeCompleto());
            newCustomer.setGenero(customerRequestData.getGenero());
            newCustomer.setDataNascimento(customerRequestData.getDataNascimento());
            newCustomer.setEmail(customerRequestData.getEmail());
            newCustomer.setCelular(customerRequestData.getCelular());
            newCustomer.setCidade(customerRequestData.getCidade());
            newCustomer.setPais(customerRequestData.getPais());
            newCustomer.setPrimeiraCompra(customerRequestData.getPrimeiraCompra());
            newCustomer.setUltimaCompra(customerRequestData.getUltimaCompra());

            newCustomer.setQntdAlugueis(0);
            newCustomer.setTotalGasto(0.0);

            DimCustomer savedCustomer = dimCustomerRepository.save(newCustomer);
            return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);

        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro ao criar cliente: Verifique os dados fornecidos não são nulos. Detalhe: " + e.getCause().getMessage());
        } catch (Exception e) {

            e.printStackTrace();
            return new ResponseEntity<>("Ocorreu um erro inesperado ao processar sua solicitação.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}