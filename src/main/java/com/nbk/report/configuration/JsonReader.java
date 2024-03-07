package com.nbk.report.configuration;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nbk.report.model.ReportConfigModel;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Component
public class JsonReader {
    static String dbConnection;
    public static String getDbConnection() {
        return dbConnection;
    }
    public static void setDbConnection(String dbConnection) {
        JsonReader.dbConnection = dbConnection;
    }
    // Method to parse a JSON configuration file and return a list of TestReportModel objects
    public static List<ReportConfigModel> parseJson(String configFilePath) {
        try {
            // Create a File object from the provided file path
            File configFile = new File(configFilePath);

            // Validate that the config file exists; throw an exception if not
            validateConfigFile(configFile);

            // Create an ObjectMapper to read JSON content
            ObjectMapper objectMapper = new ObjectMapper();

            // Read the JSON content into a JsonNode
            JsonNode rootNode = objectMapper.readTree(configFile);

            // Retrieve the DB connection information
            setDbConnection(rootNode.get("Reports").get("DB_Connection").asText().trim());

            // Create a list to store TestReportModel objects
            List<ReportConfigModel> reports = new ArrayList<>();

            // Iterate through the "Reports" field of the root JSON object
            JsonNode reportsNode = rootNode.get("Reports");
            Iterator<Map.Entry<String, JsonNode>> reportsIterator = reportsNode.fields();
            while (reportsIterator.hasNext()) {
                // Get the key-value pair of the current report
                Map.Entry<String, JsonNode> reportEntry = reportsIterator.next();

                // Skip the "DB_Connection" entry
                if (reportEntry.getKey().equals("DB_Connection")) {
                    continue;
                }

                // Build a TestReportModel from the JSON report entry and add it to the list
                ReportConfigModel report = buildReportConfiguration(reportEntry);
                reports.add(report);
            }

            // Return the list of TestReportModel objects
            return reports;
        } catch (IOException e) {
            // Handle IOException by printing the stack trace and returning null
            e.printStackTrace();
            return null;
        }
    }

    // Method to validate that the config file exists; throw an exception if not
    private static void validateConfigFile(File configFile) {
        if (!configFile.exists()) {
            throw new IllegalArgumentException("Config file does not exist: " + configFile.getPath());
        }
    }

    // Method to build a TestReportModel from a JSON report entry
    private static ReportConfigModel buildReportConfiguration(Map.Entry<String, JsonNode> reportEntry) {
        // Extract information from the JSON report entry
        String reportRoot = reportEntry.getKey();
        JsonNode reportNode = reportEntry.getValue();
        String reportName = reportNode.get("ReportName").asText().strip();
        String sqlQuery = reportNode.get("SQL").asText().strip();
        List<Map.Entry<String, String>> reportFields = buildReportFields(reportNode.get("ReportFields"));

        // Create and return a new TestReportModel
        return new ReportConfigModel(reportRoot, reportName, sqlQuery, reportFields);
    }

    // Method to build a list of report fields from a JSON node
    private static List<Map.Entry<String, String>> buildReportFields(JsonNode reportFieldsNode) {
        // Create a list to store report fields
        List<Map.Entry<String, String>> reportFields = new ArrayList<>();

        // Iterate through the fields of the ReportFields JSON object
        Iterator<Map.Entry<String, JsonNode>> fieldsIterator = reportFieldsNode.fields();
        while (fieldsIterator.hasNext()) {
            // Get the key-value pair of the current field
            Map.Entry<String, JsonNode> fieldEntry = fieldsIterator.next();

            // Extract field name and field value from the JSON field entry
            String fieldName = fieldEntry.getKey();
            String fieldValue = fieldEntry.getValue().asText();

            // Add the field to the list
            reportFields.add(new AbstractMap.SimpleEntry<>(fieldName.strip(), fieldValue.strip()));
        }

        // Return the list of report fields
        return reportFields;
    }
}
