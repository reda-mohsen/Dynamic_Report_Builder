package com.nbk.report.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// Interface defining a repository for Customer entities
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    // Override the findAll method to use a native SQL query
    @Override
    @Query(value = "SELECT * FROM fbnk_customers;", nativeQuery = true)
    // Custom findAll method using a native SQL query to retrieve all customers
    List<Customer> findAll();
}
