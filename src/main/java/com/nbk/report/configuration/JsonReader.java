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

    // Method to parse a JSON configuration file and return a list of ReportConfigModel objects
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

            // Create a list to store ReportConfigModel objects
            List<ReportConfigModel> reports = new ArrayList<>();

            // Iterate through the fields of the root JSON object
            Iterator<Map.Entry<String, JsonNode>> nodesIterator = rootNode.fields();
            while (nodesIterator.hasNext()) {
                // Get the key-value pair of the current field
                Map.Entry<String, JsonNode> entry = nodesIterator.next();

                // Build a ReportConfigModel from the JSON field and add it to the list
                ReportConfigModel report = buildReportConfiguration(entry);
                reports.add(report);
            }

            // Return the list of ReportConfigModel objects
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

    // Method to build a ReportConfigModel from a JSON field entry
    private static ReportConfigModel buildReportConfiguration(Map.Entry<String, JsonNode> entry) {
        // Extract information from the JSON field entry
        String reportRoot = entry.getKey();
        JsonNode reportNode = entry.getValue();
        String dbConnection = reportNode.get("DB_Connection").asText().strip();
        String reportName = reportNode.get("ReportName").asText().strip();
        String sqlQuery = reportNode.get("SQL").asText().strip();
        List<Map.Entry<String, String>> reportFields = buildReportFields(reportNode.get("ReportFields"));

        // Create and return a new ReportConfigModel
        return new ReportConfigModel(reportRoot, dbConnection, reportName, sqlQuery, reportFields);
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
