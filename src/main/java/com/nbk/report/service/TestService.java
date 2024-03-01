package com.nbk.report.service;

import com.nbk.report.config.DatabaseConfiguration;
import com.nbk.report.model.Customer;
import com.nbk.report.model.CustomerRepository;
import com.nbk.report.model.Report;
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
public class TestService {
    private final DatabaseConfiguration databaseConfiguration;
    private final Report report;

    @Autowired
    public TestService(DatabaseConfiguration databaseConfiguration, CustomerRepository customerRepository) {
        this.databaseConfiguration = databaseConfiguration;
        report = databaseConfiguration.getReport();
    }

    public List<Customer> getSelectedCustomers() {
        performOperations();
        return null;
    }

    private void performOperations() {
        DataSource dataSource = databaseConfiguration.dataSource(report);
//        performDatabaseOperations(dataSource, report.getSqlQuery(), report.getReportFields());
    }

    public String getReportMap() {
        return this.report.getReportRoot();
    }

    public String getReportDisplayName() {
        return this.report.getReportName();
    }

    public List<String> getDisplayFields() {
        List<String> displayFields = new ArrayList<>();
        List<Map.Entry<String, String>> reportFields = this.report.getReportFields();
        for (Map.Entry<String, String> entry : reportFields) {
            displayFields.add(entry.getValue().trim());
        }
        return displayFields;
    }


    private static void performDatabaseOperations(DataSource dataSource, String sql, List<Map.Entry<String, String>> reportFields) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    processResultSetRow(resultSet, reportFields);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void processResultSetRow(ResultSet resultSet, List<Map.Entry<String, String>> reportFields) throws SQLException {
        if (containsField("CustomerID", reportFields)){
            int id = resultSet.getInt(getFieldKey("CustomerID", reportFields));
            System.out.println("CustomerID: " + id);
        }
        if (containsField("Name_EN", reportFields)){
            String nameEn = resultSet.getString(getFieldKey("Name_EN", reportFields));
            System.out.println("Name_EN: " + nameEn);
        }
        if (containsField("Phone_Number", reportFields)){
            String phoneNumber = resultSet.getString(getFieldKey("Phone_Number", reportFields));
            System.out.println("Phone_Number: " + phoneNumber);
        }
        if (containsField("Address_EN", reportFields)){
            String addressEn = resultSet.getString(getFieldKey("Address_EN", reportFields));
            System.out.println("Address_EN: " + addressEn);
        }
        if (containsField("Name_AR", reportFields)){
            String nameAr = resultSet.getString(getFieldKey("Name_AR", reportFields));
            System.out.println("Name_AR: " + nameAr);
        }
        if (containsField("Address_AR", reportFields)){
            String addressAr = resultSet.getString(getFieldKey("Address_AR", reportFields));
            System.out.println("Address_AR: " + addressAr);
        }
    }

    public static String getFieldKey(String fieldName, List<Map.Entry<String, String>> reportFields) {
        for (Map.Entry<String, String> entry : reportFields) {
            if (entry.getKey().equals(fieldName)) {
                return entry.getKey();
            }
        }
        return null;
    }
    public static String getFieldValue(String fieldName, List<Map.Entry<String, String>> reportFields) {
        for (Map.Entry<String, String> entry : reportFields) {
            if (entry.getKey().equals(fieldName)) {
                return entry.getValue();
            }
        }
        return null;
    }
    public static boolean containsField(String fieldName, List<Map.Entry<String, String>> reportFields) {
        for (Map.Entry<String, String> entry : reportFields) {
            if (entry.getKey().equals(fieldName)) {
                return true;
            }
        }
        return false;
    }


}
