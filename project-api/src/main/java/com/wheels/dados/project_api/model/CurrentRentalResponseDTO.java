package com.wheels.dados.project_api.model; // ou .dto

import java.time.LocalDateTime;

public class CurrentRentalResponseDTO {
    private Integer rentalID;
    private DimCustomerResponseDTO customer;
    private DimBikeResponseDTO bike;
    private LocalDateTime rentalStart;
    private LocalDateTime expectedReturn;
    private String observations;


    public Integer getRentalID() {
        return rentalID;
    }

    public void setRentalID(Integer rentalID) {
        this.rentalID = rentalID;
    }

    public DimCustomerResponseDTO getCustomer() {
        return customer;
    }

    public void setCustomer(DimCustomerResponseDTO customer) {
        this.customer = customer;
    }

    public DimBikeResponseDTO getBike() {
        return bike;
    }

    public void setBike(DimBikeResponseDTO bike) {
        this.bike = bike;
    }

    public LocalDateTime getRentalStart() {
        return rentalStart;
    }

    public void setRentalStart(LocalDateTime rentalStart) {
        this.rentalStart = rentalStart;
    }

    public LocalDateTime getExpectedReturn() {
        return expectedReturn;
    }

    public void setExpectedReturn(LocalDateTime expectedReturn) {
        this.expectedReturn = expectedReturn;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
}