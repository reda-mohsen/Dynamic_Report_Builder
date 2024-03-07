package com.nbk.report.service;

import com.nbk.report.configuration.ReportConfiguration;
import com.nbk.report.model.Customer;
import com.nbk.report.model.Report;
import com.nbk.report.model.ReportConfigModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

// Service annotation indicates that this class is a Spring service component
@Service
public class ReportService {

    private final ReportConfiguration reportConfig;
    private final ReportBuilder reportBuilder;
    @Autowired
    public ReportService(ReportConfiguration reportConfig, ReportBuilder reportBuilder) {
        this.reportConfig = reportConfig;
        this.reportBuilder = reportBuilder;
    }

    // Method to get a report based on configured settings
    public Report getReport(String map) {
        // Get report configuration for the specified map
        ReportConfigModel reportConfiguration = reportConfig.getCurrentReportConfiguration(map.trim());
        if (reportConfiguration !=null){
            // Retrieve report details from ReportConfiguration
            String reportRoot = reportBuilder.getReportRoot(reportConfiguration);
            String reportName = reportBuilder.getReportName(reportConfiguration);

            // Get display fields, customers, and create a Report instance

            List<String> reportDisplayFields = reportBuilder.getDisplayFields(reportConfiguration);
            List<Customer> customers = reportBuilder.getCustomers(reportConfiguration);
            return new Report(reportRoot, reportName, reportDisplayFields, customers);
        }
        else{
            return null;
        }

    }
}
