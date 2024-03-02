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

    public static ReportConfiguration parseJson(String configFilePath) {
        try {
            File configFile = new File(configFilePath);
            validateConfigFile(configFile);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(configFile);

            Iterator<Map.Entry<String, JsonNode>> nodesIterator = rootNode.fields();
            if (nodesIterator.hasNext()) {
                Map.Entry<String, JsonNode> entry = nodesIterator.next();
                return buildReportConfiguration(entry);
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void validateConfigFile(File configFile) {
        if (!configFile.exists()) {
            throw new IllegalArgumentException("Config file does not exist: " + configFile.getPath());
        }
    }

    private static ReportConfiguration buildReportConfiguration(Map.Entry<String, JsonNode> entry) {
        String reportRoot = entry.getKey();
        JsonNode reportNode = entry.getValue();
        String dbConnection = reportNode.get("DB_Connection").asText().strip();
        String reportName = reportNode.get("ReportName").asText().strip();
        String sqlQuery = reportNode.get("SQL").asText().strip();
        List<Map.Entry<String, String>> reportFields = buildReportFields(reportNode.get("ReportFields"));

        return new ReportConfiguration(reportRoot, dbConnection, reportName, sqlQuery, reportFields);
    }

    private static List<Map.Entry<String, String>> buildReportFields(JsonNode reportFieldsNode) {
        List<Map.Entry<String, String>> reportFields = new ArrayList<>();
        Iterator<Map.Entry<String, JsonNode>> fieldsIterator = reportFieldsNode.fields();

        while (fieldsIterator.hasNext()) {
            Map.Entry<String, JsonNode> fieldEntry = fieldsIterator.next();
            String fieldName = fieldEntry.getKey();
            String fieldValue = fieldEntry.getValue().asText();
            reportFields.add(new AbstractMap.SimpleEntry<>(fieldName.strip(), fieldValue.strip()));
        }

        return reportFields;
    }
}
