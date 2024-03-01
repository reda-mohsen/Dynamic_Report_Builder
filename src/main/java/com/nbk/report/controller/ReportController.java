package com.nbk.report.controller;

import com.nbk.report.model.Customer;
import com.nbk.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Controller
public class ReportController {
    private final ReportService reportService;
    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/customerList")
    public String getCustomerList(Model model) {
        List<Customer> customerList = reportService.getCustomers();
        model.addAttribute("customerList", customerList);
        return "customerListTemplate";
    }
}
