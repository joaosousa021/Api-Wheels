package com.wheels.dados.project_api.repository;

import com.wheels.dados.project_api.model.FactSales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactSalesRepository extends JpaRepository<FactSales, Integer> {

}