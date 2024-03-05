package com.nbk.report.model;

import java.util.List;

// Model class representing a report
public class Report {

    // Fields to store various attributes of the report
    private String reportRoot;
    private String reportName;
    List<String> reportDisplayFields;
    private List<Customer> customers;

    // Constructor to initialize the Report with provided values
    public Report(String reportRoot, String reportName,
                  List<String> reportDisplayFields, List<Customer> customers) {
        // Assign values to the fields
        this.reportRoot = reportRoot;
        this.reportName = reportName;
        this.reportDisplayFields = reportDisplayFields;
        this.customers = customers;
    }

    // Getter method for retrieving the report root
    public String getReportRoot() {
        return reportRoot;
    }

    // Setter method for updating the report root
    public void setReportRoot(String reportRoot) {
        this.reportRoot = reportRoot;
    }

    // Getter method for retrieving the report name
    public String getReportName() {
        return reportName;
    }

    // Setter method for updating the report name
    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    // Getter method for retrieving the list of report display fields
    public List<String> getReportDisplayFields() {
        return reportDisplayFields;
    }

    // Setter method for updating the list of report display fields
    public void setReportDisplayFields(List<String> reportDisplayFields) {
        this.reportDisplayFields = reportDisplayFields;
    }

    // Getter method for retrieving the list of customers associated with the report
    public List<Customer> getCustomers() {
        return customers;
    }

    // Setter method for updating the list of customers associated with the report
    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    // Override toString method to provide a string representation of the object
    @Override
    public String toString() {
        return "Report{" +
                "reportRoot='" + reportRoot + '\'' +
                ", reportName='" + reportName + '\'' +
                ", reportDisplayFields=" + reportDisplayFields +
                ", customers=" + customers +
                '}';
    }
}
