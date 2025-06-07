package com.wheels.dados.project_api.model; // ou .dto

public class FactSalesResponseDTO {
    private Integer saleID;
    private DimDateResponseDTO dateDetails;
    private DimCustomerResponseDTO customerDetails;
    private DimBikeResponseDTO bikeDetails;
    private Double horasAlugadas;
    private Double valorAluguel;
    private Double taxaAtraso;
    private Double taxaDano;
    private Double desconto;
    private Double valorTotal;


    public Integer getSaleID() { return saleID; }
    public void setSaleID(Integer saleID) { this.saleID = saleID; }
    public DimDateResponseDTO getDateDetails() { return dateDetails; }
    public void setDateDetails(DimDateResponseDTO dateDetails) { this.dateDetails = dateDetails; }
    public DimCustomerResponseDTO getCustomerDetails() { return customerDetails; }
    public void setCustomerDetails(DimCustomerResponseDTO customerDetails) { this.customerDetails = customerDetails; }
    public DimBikeResponseDTO getBikeDetails() { return bikeDetails; }
    public void setBikeDetails(DimBikeResponseDTO bikeDetails) { this.bikeDetails = bikeDetails; }
    public Double getHorasAlugadas() { return horasAlugadas; }
    public void setHorasAlugadas(Double horasAlugadas) { this.horasAlugadas = horasAlugadas; }
    public Double getValorAluguel() { return valorAluguel; }
    public void setValorAluguel(Double valorAluguel) { this.valorAluguel = valorAluguel; }
    public Double getTaxaAtraso() { return taxaAtraso; }
    public void setTaxaAtraso(Double taxaAtraso) { this.taxaAtraso = taxaAtraso; }
    public Double getTaxaDano() { return taxaDano; }
    public void setTaxaDano(Double taxaDano) { this.taxaDano = taxaDano; }
    public Double getDesconto() { return desconto; }
    public void setDesconto(Double desconto) { this.desconto = desconto; }
    public Double getValorTotal() { return valorTotal; }
    public void setValorTotal(Double valorTotal) { this.valorTotal = valorTotal; }
}