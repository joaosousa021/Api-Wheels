package com.wheels.dados.project_api.model;

import java.time.LocalDateTime;


public class CreateRentalRequestDTO {

    private Integer customerId;
    private Integer bikeId;
    private LocalDateTime rentalStart;
    private LocalDateTime expectedReturn;
    private String observations;



    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getBikeId() {
        return bikeId;
    }

    public void setBikeId(Integer bikeId) {
        this.bikeId = bikeId;
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