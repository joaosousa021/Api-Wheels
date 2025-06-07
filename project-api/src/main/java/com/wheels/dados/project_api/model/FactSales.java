package com.wheels.dados.project_api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "FactSales", schema = "dbo")
public class FactSales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SaleID")
    private Integer saleID;


    @ManyToOne(fetch = FetchType.LAZY) // LAZY é geralmente bom para performance
    @JoinColumn(name = "DateID", nullable = false)
    private DimDate dimDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BikeID", nullable = false)
    private DimBike dimBike;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CustomerID", nullable = false)
    private DimCustomer dimCustomer;

    @Column(name = "HorasAlugadas", nullable = false)
    private Double horasAlugadas;

    @Column(name = "ValorAluguel", nullable = false)
    private Double valorAluguel;

    @Column(name = "TaxaAtraso", nullable = false)
    private Double taxaAtraso;

    @Column(name = "TaxaDano", nullable = false)
    private Double taxaDano;

    @Column(name = "Desconto", nullable = false)
    private Double desconto;

    @Column(name = "ValorTotal", nullable = false)
    private Double valorTotal;


    public FactSales() {
    }


    public Integer getSaleID() {
        return saleID;
    }

    public void setSaleID(Integer saleID) {
        this.saleID = saleID;
    }

    public DimDate getDimDate() {
        return dimDate;
    }

    public void setDimDate(DimDate dimDate) {
        this.dimDate = dimDate;
    }

    public DimBike getDimBike() {
        return dimBike;
    }

    public void setDimBike(DimBike dimBike) {
        this.dimBike = dimBike;
    }

    public DimCustomer getDimCustomer() {
        return dimCustomer;
    }

    public void setDimCustomer(DimCustomer dimCustomer) {
        this.dimCustomer = dimCustomer;
    }

    public Double getHorasAlugadas() {
        return horasAlugadas;
    }

    public void setHorasAlugadas(Double horasAlugadas) {
        this.horasAlugadas = horasAlugadas;
    }

    public Double getValorAluguel() {
        return valorAluguel;
    }

    public void setValorAluguel(Double valorAluguel) {
        this.valorAluguel = valorAluguel;
    }

    public Double getTaxaAtraso() {
        return taxaAtraso;
    }

    public void setTaxaAtraso(Double taxaAtraso) {
        this.taxaAtraso = taxaAtraso;
    }

    public Double getTaxaDano() {
        return taxaDano;
    }

    public void setTaxaDano(Double taxaDano) {
        this.taxaDano = taxaDano;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
}