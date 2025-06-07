package com.wheels.dados.project_api.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "DimCustomers", schema = "dbo")
public class DimCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustomerID")
    private Integer customerID;

    @Column(name = "CPF", nullable = false, length = 50) // Ajuste o length se necessário
    private String cpf;

    @Column(name = "NomeCompleto", nullable = false, length = 50)
    private String nomeCompleto;

    @Column(name = "Genero", nullable = false, length = 10)
    private String genero;

    @Column(name = "DataNascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "Email", nullable = false, length = 50)
    private String email;

    @Column(name = "Celular", nullable = false, length = 50) // Ajuste o length se necessário
    private String celular;

    @Column(name = "Cidade", nullable = false, length = 50)
    private String cidade;

    @Column(name = "Pais", nullable = false, length = 50)
    private String pais;

    @Column(name = "PrimeiraCompra", nullable = false)
    private LocalDate primeiraCompra;

    @Column(name = "UltimaCompra", nullable = false)
    private LocalDate ultimaCompra;

    @Column(name = "QntdAlugueis", nullable = false)
    private Integer qntdAlugueis;

    @Column(name = "TotalGasto", nullable = false)
    private Double totalGasto;

    public DimCustomer() {
    }


    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public LocalDate getPrimeiraCompra() {
        return primeiraCompra;
    }

    public void setPrimeiraCompra(LocalDate primeiraCompra) {
        this.primeiraCompra = primeiraCompra;
    }

    public LocalDate getUltimaCompra() {
        return ultimaCompra;
    }

    public void setUltimaCompra(LocalDate ultimaCompra) {
        this.ultimaCompra = ultimaCompra;
    }

    public Integer getQntdAlugueis() {
        return qntdAlugueis;
    }

    public void setQntdAlugueis(Integer qntdAlugueis) {
        this.qntdAlugueis = qntdAlugueis;
    }

    public Double getTotalGasto() {
        return totalGasto;
    }

    public void setTotalGasto(Double totalGasto) {
        this.totalGasto = totalGasto;
    }
}