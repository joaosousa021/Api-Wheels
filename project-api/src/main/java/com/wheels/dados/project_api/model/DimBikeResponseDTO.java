package com.wheels.dados.project_api.model; // ou .dto

public class DimBikeResponseDTO {
    private Integer bikeID;
    private String modelo;
    private boolean disponivel;



    public Integer getBikeID() { return bikeID; }
    public void setBikeID(Integer bikeID) { this.bikeID = bikeID; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public boolean isDisponivel() { return disponivel; } // Para boolean, o getter é isXxxx
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }
}