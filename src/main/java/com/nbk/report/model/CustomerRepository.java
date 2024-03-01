package com.nbk.report.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Override
    @Query(value = "SELECT * FROM fbnk_customers;", nativeQuery = true)
    List<Customer> findAll();
}
