package com.nbk.report.service;

import com.nbk.report.config.DatabaseConfiguration;
import com.nbk.report.model.Customer;
import com.nbk.report.model.Report;
import com.nbk.report.model.ReportConfiguration;
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

@Service
public class ReportService {
    private final DatabaseConfiguration databaseConfiguration;
    private final ReportConfiguration reportConfiguration;

    @Autowired
    public ReportService(DatabaseConfiguration databaseConfiguration) {
        this.databaseConfiguration = databaseConfiguration;
        reportConfiguration = databaseConfiguration.getReport();
    }
    public Report getReport(){
        String reportRoot = reportConfiguration.getReportConfigRoot().trim();
        String reportName = reportConfiguration.getReportConfigName().trim();
        List<String> reportDisplayFields = getDisplayFields();
        List<Customer> customers = getCustomers();
        return new Report(reportRoot,reportName,reportDisplayFields,customers);
    }

    public List<String> getDisplayFields() {
        List<String> displayFields = new ArrayList<>();
        List<Map.Entry<String, String>> reportFields = reportConfiguration.getReportFields();
        for (Map.Entry<String, String> entry : reportFields) {
            displayFields.add(entry.getValue().trim());
        }
        return displayFields;
    }

    public List<Customer> getCustomers() {
        DataSource dataSource = databaseConfiguration.dataSource(reportConfiguration);
        return getListCustomers(dataSource, reportConfiguration.getSqlQuery(), reportConfiguration.getReportFields());
    }

    private static List<Customer> getListCustomers(DataSource dataSource, String sql, List<Map.Entry<String, String>> reportFields) {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    customers.add(processResultSetRow(resultSet, reportFields));
                }
                return customers;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Customer processResultSetRow(ResultSet resultSet, List<Map.Entry<String,
            String>> reportFields) throws SQLException {
        Customer customer = new Customer();

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

    private static String getFieldKey(String fieldName, List<Map.Entry<String, String>> reportFields) {
        for (Map.Entry<String, String> entry : reportFields) {
            if (entry.getKey().trim().equals(fieldName)) {
                return entry.getKey().trim();
            }
        }
        return null;
    }

    private static boolean containsField(String fieldName, List<Map.Entry<String, String>> reportFields) {
        for (Map.Entry<String, String> entry : reportFields) {
            if (entry.getKey().trim().equals(fieldName)) {
                return true;
            }
        }
        return false;
    }


}
