package com.nbk.report.service;

import com.nbk.report.config.DatabaseConfiguration;
import com.nbk.report.model.Customer;
import com.nbk.report.model.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    private final DatabaseConfiguration databaseConfiguration;

    private final CustomerRepository customerRepository;
    @Autowired
    public ReportService(DatabaseConfiguration databaseConfiguration, CustomerRepository customerRepository) {
        this.databaseConfiguration = databaseConfiguration;
        this.customerRepository = customerRepository;
    }
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }
}
