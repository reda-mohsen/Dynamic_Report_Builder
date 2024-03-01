package com.nbk.report.controller;

import com.nbk.report.model.Customer;
import com.nbk.report.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TestController {
    private final TestService testService;
    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/{test}")
    public String getTest(@PathVariable String test, Model model) {
        if (test.equals(testService.getReportMap())) {
            List<Customer> testList = testService.getSelectedCustomers();
            model.addAttribute("testReportName", testService.getReportDisplayName());
            model.addAttribute("testDisplayFields", testService.getDisplayFields());
            model.addAttribute("testList", testList);
            return "testTemplate";
        }
        else{
            return "errorTemplate";
        }
    }


}
