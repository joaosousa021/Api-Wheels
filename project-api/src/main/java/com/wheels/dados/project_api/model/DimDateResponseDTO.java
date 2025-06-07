package com.wheels.dados.project_api.model; // ou .dto

import java.time.LocalDate;

public class DimDateResponseDTO {
    private Integer dateId;
    private LocalDate data; // O campo 'Data' da sua tabela DimDate
    private String nomeMes;
    private Integer ano;



    public Integer getDateId() { return dateId; }
    public void setDateId(Integer dateId) { this.dateId = dateId; }
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
    public String getNomeMes() { return nomeMes; }
    public void setNomeMes(String nomeMes) { this.nomeMes = nomeMes; }
    public Integer getAno() { return ano; }
    public void setAno(Integer ano) { this.ano = ano; }
}