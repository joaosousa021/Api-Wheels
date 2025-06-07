package com.wheels.dados.project_api.repository;

import com.wheels.dados.project_api.model.DimCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DimCustomerRepository extends JpaRepository<DimCustomer, Integer> {

}