package com.nbk.report.controller;

import com.nbk.report.model.Customer;
import com.nbk.report.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    private final TestService testService;
    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/Report")
    public List<Customer> getCustomerList(Model model) {
        List<Customer> customerList = testService.getSelectedCustomers();
        return customerList;
    }
}
