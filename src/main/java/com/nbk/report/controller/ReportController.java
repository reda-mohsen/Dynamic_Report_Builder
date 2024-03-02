package com.nbk.report.controller;

import com.nbk.report.model.Customer;
import com.nbk.report.model.Report;
import com.nbk.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ReportController {
    private final ReportService reportService;
    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/{map}")
    public String getReportTemplate(@PathVariable String map, Model model) {
        Report report = reportService.getReport();
        if (map.equals(report.getReportRoot())){
            model.addAttribute("reportRoot", report.getReportRoot());
            model.addAttribute("reportName", report.getReportName());
            model.addAttribute("reportDisplayFields", report.getReportDisplayFields());
            model.addAttribute("customers", report.getCustomers());
            return "reportTemplate";
        }
        else{
            return "errorTemplate";
        }

    }


}
