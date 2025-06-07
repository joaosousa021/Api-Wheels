package com.wheels.dados.project_api.model; // ou .dto

public class DimCustomerResponseDTO {
    private Integer customerID;
    private String nomeCompleto;



    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

}