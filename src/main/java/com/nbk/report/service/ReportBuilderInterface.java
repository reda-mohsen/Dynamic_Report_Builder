package com.nbk.report.service;

import com.nbk.report.model.Customer;
import com.nbk.report.model.ReportConfigModel;

import java.util.List;

public interface ReportBuilderInterface {
    String getReportRoot(ReportConfigModel reportConfiguration);
    String getReportName(ReportConfigModel reportConfiguration);
    List<String> getDisplayFields(ReportConfigModel reportConfiguration);
    List<Customer> getCustomers(ReportConfigModel reportConfiguration);
}
