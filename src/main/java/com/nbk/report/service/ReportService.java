package com.nbk.report.service;

import com.nbk.report.configuration.DataSourceConfiguration;
import com.nbk.report.configuration.ReportConfiguration;
import com.nbk.report.model.Customer;
import com.nbk.report.model.Report;
import com.nbk.report.model.ReportConfigModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Service annotation indicates that this class is a Spring service component
@Service
public class ReportService {
    private static final Logger logger = LoggerFactory.getLogger(ReportService.class);
    private DataSourceConfiguration dataSourceConfig;
    private ReportConfiguration reportConfig;
    @Autowired
    public ReportService(DataSourceConfiguration dataSourceConfig,
                         ReportConfiguration reportConfig) {
        this.dataSourceConfig = dataSourceConfig;
        this.reportConfig = reportConfig;
    }

    // Method to get a report based on configured settings
    public Report getReport(String map) {
        ReportConfigModel reportConfiguration = reportConfig.getCurrentReportConfiguration(map.trim());
        // Retrieve report details from ReportConfiguration
        String reportRoot = reportConfiguration.getReportConfigRoot().trim();
        String reportName = reportConfiguration.getReportConfigName().trim();

        // Get display fields, customers, and create a Report instance
        List<String> reportDisplayFields = getDisplayFields(reportConfiguration);
        List<Customer> customers = getCustomers(reportConfiguration);
        return new Report(reportRoot, reportName, reportDisplayFields, customers);
    }

    // Method to get display fields from ReportConfiguration
    public List<String> getDisplayFields(ReportConfigModel reportConfiguration) {
        List<String> displayFields = new ArrayList<>();
        List<Map.Entry<String, String>> reportFields = reportConfiguration.getReportFields();

        // Extract values from report fields and trim them
        for (Map.Entry<String, String> entry : reportFields) {
            displayFields.add(entry.getValue().trim());
        }
        return displayFields;
    }

    // Method to get customers based on configured SQL query and report fields
    public List<Customer> getCustomers(ReportConfigModel reportConfiguration) {
        // Obtain DataSource from DatabaseConfiguration
        DataSource dataSource = dataSourceConfig.createDataSource(reportConfiguration);
        return getListCustomers(dataSource, reportConfiguration.getSqlQuery(), reportConfiguration.getReportFields());
    }

    // Method to retrieve a list of customers from the database
    private static List<Customer> getListCustomers(DataSource dataSource, String sql, List<Map.Entry<String, String>> reportFields) {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                // Process each row in the ResultSet and add customers to the list
                while (resultSet.next()) {
                    customers.add(processResultSetRow(resultSet, reportFields));
                }
                return customers;
            }
        } catch (SQLException e) {
            // Handle SQL exceptions and print the stack trace
            // Log the error instead of printing the stack trace
            logger.error("Error while retrieving customers from the database", e);
            return null;
        }
    }

    // Method to process a single ResultSet row and create a Customer object
    private static Customer processResultSetRow(ResultSet resultSet, List<Map.Entry<String, String>> reportFields) throws SQLException {
        Customer customer = new Customer();

        // Set customer properties based on the report fields and ResultSet
        if (containsField("CustomerID", reportFields)) {
            customer.setCustomerID(resultSet.getInt(getFieldKey("CustomerID", reportFields)));
        }
        if (containsField("Name_EN", reportFields)) {
            customer.setName_En(resultSet.getString(getFieldKey("Name_EN", reportFields)));
        }
        if (containsField("Phone_Number", reportFields)) {
            customer.setPhone_Number(resultSet.getString(getFieldKey("Phone_Number", reportFields)));
        }
        if (containsField("Address_EN", reportFields)) {
            customer.setAddress_EN(resultSet.getString(getFieldKey("Address_EN", reportFields)));
        }
        if (containsField("Name_AR", reportFields)) {
            customer.setName_AR(resultSet.getString(getFieldKey("Name_AR", reportFields)));
        }
        if (containsField("Address_AR", reportFields)) {
            customer.setAddress_AR(resultSet.getString(getFieldKey("Address_AR", reportFields)));
        }
        return customer;
    }

    // Method to get the key associated with a field name in report fields
    private static String getFieldKey(String fieldName, List<Map.Entry<String, String>> reportFields) {
        for (Map.Entry<String, String> entry : reportFields) {
            if (entry.getKey().trim().equals(fieldName)) {
                return entry.getKey().trim();
            }
        }
        return null;
    }

    // Method to check if a field is present in report fields
    private static boolean containsField(String fieldName, List<Map.Entry<String, String>> reportFields) {
        for (Map.Entry<String, String> entry : reportFields) {
            if (entry.getKey().trim().equals(fieldName)) {
                return true;
            }
        }
        return false;
    }
}
