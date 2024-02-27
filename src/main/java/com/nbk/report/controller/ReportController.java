package com.nbk.report.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;

@RestController
public class ReportController {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/my_bank_db";
    private static final String JDBC_USER = "reda";
    private static final String JDBC_PASSWORD = "admin";

    @GetMapping("/Report_"+"{id}")
    public static void showData() {
        try {
            // Step 1: Register the JDBC driver
            Class.forName("org.postgresql.Driver");

            // Step 2: Open a connection
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {

                // Step 3: Execute a query
                String sqlQuery = "SELECT * FROM fbnk_customers";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                     ResultSet resultSet = preparedStatement.executeQuery()) {

                    // Step 4: Process the results
                    while (resultSet.next()) {
                        int id = resultSet.getInt("CustomerID");
                        String name = resultSet.getString("Name_EN");
                        String phoneNumber = resultSet.getString("Phone_Number");

                        System.out.println("CustomerID: " + id + ", Name_EN: " + name + ", Phone_Number: " + phoneNumber);
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}

