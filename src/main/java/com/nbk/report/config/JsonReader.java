package com.nbk.report.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nbk.report.model.Report;
import com.nbk.report.model.ReportConfiguration;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.AbstractMap;
import java.util.Iterator;

@Component
public class JsonReader {

    // Method to parse JSON and create a ReportConfiguration object
    public static ReportConfiguration parseJson(String configFilePath) {
        try {
            // Create a File object from the given file path
            File configFile = new File(configFilePath);
            // Validate the existence of the config file
            validateConfigFile(configFile);

            // Create an ObjectMapper for reading JSON
            ObjectMapper objectMapper = new ObjectMapper();
            // Read the JSON file into a JsonNode
            JsonNode rootNode = objectMapper.readTree(configFile);

            // Get an iterator for the fields in the JSON object
            Iterator<Map.Entry<String, JsonNode>> nodesIterator = rootNode.fields();
            // Check if there is at least one field
            if (nodesIterator.hasNext()) {
                // Get the first entry in the JSON object
                Map.Entry<String, JsonNode> entry = nodesIterator.next();
                // Build a ReportConfiguration object based on the entry
                return buildReportConfiguration(entry);
            } else {
                // Return null if the JSON object is empty
                return null;
            }
        } catch (IOException e) {
            // Print the stack trace if an IOException occurs
            e.printStackTrace();
            return null;
        }
    }

    // Validate the existence of the config file
    private static void validateConfigFile(File configFile) {
        if (!configFile.exists()) {
            // Throw an exception if the config file does not exist
            throw new IllegalArgumentException("Config file does not exist: " + configFile.getPath());
        }
    }

    // Build a ReportConfiguration object based on a JSON entry
    private static ReportConfiguration buildReportConfiguration(Map.Entry<String, JsonNode> entry) {
        // Extract reportRoot and reportNode from the entry
        String reportRoot = entry.getKey();
        JsonNode reportNode = entry.getValue();
        // Extract values from the reportNode for dbConnection, reportName, and sqlQuery
        String dbConnection = reportNode.get("DB_Connection").asText().strip();
        String reportName = reportNode.get("ReportName").asText().strip();
        String sqlQuery = reportNode.get("SQL").asText().strip();
        // Build a list of reportFields based on the reportNode
        List<Map.Entry<String, String>> reportFields = buildReportFields(reportNode.get("ReportFields"));

        // Return a new ReportConfiguration object
        return new ReportConfiguration(reportRoot, dbConnection, reportName, sqlQuery, reportFields);
    }

    // Build a list of report fields based on a JSON node
    private static List<Map.Entry<String, String>> buildReportFields(JsonNode reportFieldsNode) {
        // Create a list to store report fields
        List<Map.Entry<String, String>> reportFields = new ArrayList<>();
        // Get an iterator for the fields in the reportFieldsNode
        Iterator<Map.Entry<String, JsonNode>> fieldsIterator = reportFieldsNode.fields();

        // Iterate through the fields and add them to the list
        while (fieldsIterator.hasNext()) {
            // Get the next field entry
            Map.Entry<String, JsonNode> fieldEntry = fieldsIterator.next();
            // Extract field name and field value from the entry
            String fieldName = fieldEntry.getKey();
            String fieldValue = fieldEntry.getValue().asText();
            // Add a new entry to the reportFields list
            reportFields.add(new AbstractMap.SimpleEntry<>(fieldName.strip(), fieldValue.strip()));
        }

        // Return the list of report fields
        return reportFields;
    }
}
