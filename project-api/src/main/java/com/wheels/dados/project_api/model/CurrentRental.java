package com.wheels.dados.project_api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "CurrentRentals", schema = "dbo")
public class CurrentRental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RentalID")
    private Integer rentalID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CustomerID", nullable = false)
    private DimCustomer dimCustomer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BikeID", nullable = false)
    private DimBike dimBike;

    @Column(name = "RentalStart", nullable = false)
    private LocalDateTime rentalStart;

    @Column(name = "ExpectedReturn", nullable = false)
    private LocalDateTime expectedReturn;

    @Column(name = "Observations")
    private String observations;


    public CurrentRental() {
    }


    public Integer getRentalID() {
        return rentalID;
    }

    public void setRentalID(Integer rentalID) {
        this.rentalID = rentalID;
    }

    public DimCustomer getDimCustomer() {
        return dimCustomer;
    }

    public void setDimCustomer(DimCustomer dimCustomer) {
        this.dimCustomer = dimCustomer;
    }

    public DimBike getDimBike() {
        return dimBike;
    }

    public void setDimBike(DimBike dimBike) {
        this.dimBike = dimBike;
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