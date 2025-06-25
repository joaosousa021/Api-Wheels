package com.wheels.dados.project_api.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "DimDate", schema = "dbo")
public class DimDate {

    @Id
    @Column(name = "DateId")
    private Integer dateId;

    @Column(name = "Data", nullable = false)
    private LocalDate data;

    @Column(name = "Dia", nullable = false)
    private Integer dia;

    @Column(name = "Mes", nullable = false)
    private Integer mes;

    @Column(name = "NomeMes", nullable = false, length = 50)
    private String nomeMes;

    @Column(name = "Ano", nullable = false)
    private Integer ano;

    @Column(name = "DiaSemana", nullable = false)
    private Integer diaSemana;

    @Column(name = "NomeDia", nullable = false, length = 50)
    private String nomeDia;

    @Column(name = "FinalDeSemana", nullable = false)
    private boolean finalDeSemana;


    public DimDate() {
    }


    public DimDate(Integer dateId, LocalDate data, Integer dia, Integer mes, String nomeMes, Integer ano, Integer diaSemana, String nomeDia, boolean finalDeSemana) {
        this.dateId = dateId;
        this.data = data;
        this.dia = dia;
        this.mes = mes;
        this.nomeMes = nomeMes;
        this.ano = ano;
        this.diaSemana = diaSemana;
        this.nomeDia = nomeDia;
        this.finalDeSemana = finalDeSemana;
    }

    public Integer getDateId() {
        return dateId;
    }

    public void setDateId(Integer dateId) {
        this.dateId = dateId;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public String getNomeMes() {
        return nomeMes;
    }

    public void setNomeMes(String nomeMes) {
        this.nomeMes = nomeMes;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(Integer diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getNomeDia() {
        return nomeDia;
    }

    public void setNomeDia(String nomeDia) {
        this.nomeDia = nomeDia;
    }

    public boolean isFinalDeSemana() {
        return finalDeSemana;
    }

    public void setFinalDeSemana(boolean finalDeSemana) {
        this.finalDeSemana = finalDeSemana;
    }
}