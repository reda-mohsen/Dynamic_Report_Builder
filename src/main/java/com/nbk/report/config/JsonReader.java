package com.nbk.report.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class JsonReader {

    public static List<ReportConfiguration> parseJson(String configFilePath) {
        try {
            File configFile = new File(configFilePath);
            if (!configFile.exists()) {
                throw new IllegalArgumentException("Config file does not exist: " + configFilePath);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(configFile);
            List<ReportConfiguration> reportConfigurations = new ArrayList<>();

            for (Iterator<Map.Entry<String, JsonNode>> nodesIterator = rootNode.fields(); nodesIterator.hasNext(); ) {
                Map.Entry<String, JsonNode> entry = nodesIterator.next();
                String reportRoot = entry.getKey();
                JsonNode reportNode = entry.getValue();
                String dbConnection = reportNode.get("DB_Connection").asText();
                String reportName = reportNode.get("ReportName").asText();
                String sqlQuery = reportNode.get("SQL").asText();
                List<Map.Entry<String, String>> reportFields = new ArrayList<>();
                JsonNode reportFieldsNode = reportNode.get("ReportFields");
                Iterator<Map.Entry<String, JsonNode>> fieldsIterator = reportFieldsNode.fields();
                while (fieldsIterator.hasNext()) {
                    Map.Entry<String, JsonNode> fieldEntry = fieldsIterator.next();
                    String fieldName = fieldEntry.getKey();
                    String fieldValue = fieldEntry.getValue().asText();
                    reportFields.add(new AbstractMap.SimpleEntry<>(fieldName, fieldValue.strip()));
                }

                reportConfigurations.add(new ReportConfiguration(reportRoot, dbConnection, reportName, sqlQuery, reportFields));
            }

            return reportConfigurations;
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
