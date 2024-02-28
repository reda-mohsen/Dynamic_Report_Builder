package com.nbk.report.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CustomerController {
    private final CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping("/Report")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/customerList")
    public String getCustomerList(Model model) {
        List<Customer> customerList = customerService.getCustomers();
        model.addAttribute("customerList", customerList);
        return "customerListTemplate";
    }

}
