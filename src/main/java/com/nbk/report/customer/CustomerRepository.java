package com.nbk.report.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Override
    @Query(value = "SELECT * FROM fbnk_customers;", nativeQuery = true)
    List<Customer> findAll();

    @Query(value = "SELECT customerID, name_EN, phone_Number FROM fbnk_customers", nativeQuery = true)
    List<Customer> findSome();
}
