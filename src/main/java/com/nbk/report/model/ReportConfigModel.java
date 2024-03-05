package com.nbk.report.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Model class representing a configuration for a report
public class ReportConfigModel {

    // Fields to store various attributes of the report configuration
    private final String reportConfigRoot;
    private final String dbConnection;
    private final String reportConfigName;
    private final String sqlQuery;
    private final List<Map.Entry<String, String>> reportFields;

    // Constructor to initialize the ReportConfigModel with provided values
    public ReportConfigModel(String reportConfigRoot, String dbConnection,
                             String reportConfigName, String sqlQuery,
                             List<Map.Entry<String, String>> reportFields) {
        // Assign values to the fields
        this.reportConfigRoot = reportConfigRoot;
        this.dbConnection = dbConnection;
        this.reportConfigName = reportConfigName;
        this.sqlQuery = sqlQuery;
        // Create a new ArrayList to store a copy of the provided reportFields
        this.reportFields = new ArrayList<>(reportFields);
    }

    // Getter method for retrieving the report configuration root
    public String getReportConfigRoot() {
        return reportConfigRoot;
    }

    // Getter method for retrieving the database connection information
    public String getDbConnection() {
        return dbConnection;
    }

    // Getter method for retrieving the report configuration name
    public String getReportConfigName() {
        return reportConfigName;
    }

    // Getter method for retrieving the SQL query associated with the report configuration
    public String getSqlQuery() {
        return sqlQuery;
    }

    // Getter method for retrieving a copy of the report fields as a list
    public List<Map.Entry<String, String>> getReportFields() {
        // Return a new ArrayList to avoid exposing the internal representation
        return new ArrayList<>(reportFields);
    }

    // Override toString method to provide a string representation of the object
    @Override
    public String toString() {
        return "ReportConfiguration{" +
                "reportRoot='" + reportConfigRoot + '\'' +
                ", dbConnection='" + dbConnection + '\'' +
                ", reportName='" + reportConfigName + '\'' +
                ", sqlQuery='" + sqlQuery + '\'' +
                ", reportFields=" + reportFields +
                '}';
    }
}
