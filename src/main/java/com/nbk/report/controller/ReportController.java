package com.nbk.report.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {
    @GetMapping("/Report_"+"id")
    public String showReport() {
        return "Report id";
    }
}

