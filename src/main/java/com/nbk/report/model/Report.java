package com.nbk.report.model;

import java.util.List;

public class Report {
    private String reportRoot;
    private String reportName;
    List<String> reportDisplayFields;
    private List<Customer> customers;

    public Report(String reportRoot, String reportName, List<String> reportDisplayFields, List<Customer> customers) {
        this.reportRoot = reportRoot;
        this.reportName = reportName;
        this.reportDisplayFields = reportDisplayFields;
        this.customers = customers;
    }

    public String getReportRoot() {
        return reportRoot;
    }

    public void setReportRoot(String reportRoot) {
        this.reportRoot = reportRoot;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public List<String> getReportDisplayFields() {
        return reportDisplayFields;
    }

    public void setReportDisplayFields(List<String> reportDisplayFields) {
        this.reportDisplayFields = reportDisplayFields;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

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
