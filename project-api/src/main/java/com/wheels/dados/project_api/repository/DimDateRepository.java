package com.wheels.dados.project_api.repository;

import com.wheels.dados.project_api.model.DimDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface DimDateRepository extends JpaRepository<DimDate, Integer> {

    Optional<DimDate> findByData(LocalDate data);
}