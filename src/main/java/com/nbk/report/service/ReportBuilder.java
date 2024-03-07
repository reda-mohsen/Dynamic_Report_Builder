package com.nbk.report.service;

import com.nbk.report.configuration.DataSourceConfiguration;
import com.nbk.report.model.Customer;
import com.nbk.report.model.ReportConfigModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class ReportBuilder implements ReportBuilderInterface {
    // Logger for logging messages
    private static final Logger logger = LoggerFactory.getLogger(ReportService.class);

    // Inject DataSourceConfiguration and ReportConfiguration dependencies through constructor
    private final DataSourceConfiguration dataSourceConfig;

    public ReportBuilder(DataSourceConfiguration dataSourceConfig) {
        this.dataSourceConfig = dataSourceConfig;
    }
    @Override
    public String getReportRoot(ReportConfigModel reportConfiguration) {
        return reportConfiguration.getReportConfigRoot().trim();
    }
    @Override
    public String getReportName(ReportConfigModel reportConfiguration) {
        return reportConfiguration.getReportConfigName().trim();
    }

    // Method to get display fields from ReportConfiguration
    @Override
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
    @Override
    public List<Customer> getCustomers(ReportConfigModel reportConfiguration) {
        // Obtain DataSource from DatabaseConfiguration
        DataSource dataSource = dataSourceConfig.createDataSource();
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
            // Handle SQL exceptions and log the error
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
