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

    public static List<ReportConfigModel> parseJson(String configFilePath) {
        try {
            File configFile = new File(configFilePath);
            validateConfigFile(configFile);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(configFile);

            List<ReportConfigModel> reports = new ArrayList<>();
            Iterator<Map.Entry<String, JsonNode>> nodesIterator = rootNode.fields();

            while (nodesIterator.hasNext()) {
                Map.Entry<String, JsonNode> entry = nodesIterator.next();
                ReportConfigModel report = buildReportConfiguration(entry);
                reports.add(report);
            }

            return reports;
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

    private static ReportConfigModel buildReportConfiguration(Map.Entry<String, JsonNode> entry) {
        String reportRoot = entry.getKey();
        JsonNode reportNode = entry.getValue();
        String dbConnection = reportNode.get("DB_Connection").asText().strip();
        String reportName = reportNode.get("ReportName").asText().strip();
        String sqlQuery = reportNode.get("SQL").asText().strip();
        List<Map.Entry<String, String>> reportFields = buildReportFields(reportNode.get("ReportFields"));

        return new ReportConfigModel(reportRoot, dbConnection, reportName, sqlQuery, reportFields);
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
