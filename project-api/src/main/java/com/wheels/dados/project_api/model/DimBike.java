package com.wheels.dados.project_api.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "DimBikes", schema = "dbo")
public class DimBike {

    @Id
    @Column(name = "BikeID")
    private Integer bikeID;

    @Column(name = "Modelo", nullable = false, length = 50)
    private String modelo;

    @Column(name = "DataAquisicao", nullable = false)
    private LocalDate dataAquisicao;

    @Column(name = "TaxaAtraso", nullable = false)
    private Double taxaAtraso;

    @Column(name = "TaxaDano", nullable = false)
    private Double taxaDano;

    @Column(name = "Descricao", nullable = false, length = 100)
    private String descricao;

    @Column(name = "Infantil", nullable = false)
    private boolean infantil;

    @Column(name = "ValorHora", nullable = false)
    private Double valorHora;

    @Column(name = "Disponivel", nullable = false)
    private boolean disponivel;


    public DimBike() {
    }


    public DimBike(Integer bikeID, String modelo, LocalDate dataAquisicao, Double taxaAtraso, Double taxaDano, String descricao, boolean infantil, Double valorHora, boolean disponivel) {
        this.bikeID = bikeID;
        this.modelo = modelo;
        this.dataAquisicao = dataAquisicao;
        this.taxaAtraso = taxaAtraso;
        this.taxaDano = taxaDano;
        this.descricao = descricao;
        this.infantil = infantil;
        this.valorHora = valorHora;
        this.disponivel = disponivel;
    }


    public Integer getBikeID() {
        return bikeID;
    }

    public void setBikeID(Integer bikeID) {
        this.bikeID = bikeID;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public LocalDate getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(LocalDate dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isInfantil() {
        return infantil;
    }

    public void setInfantil(boolean infantil) {
        this.infantil = infantil;
    }

    public Double getValorHora() {
        return valorHora;
    }

    public void setValorHora(Double valorHora) {
        this.valorHora = valorHora;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}