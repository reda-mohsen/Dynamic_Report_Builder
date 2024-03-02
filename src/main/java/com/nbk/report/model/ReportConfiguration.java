package com.nbk.report.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReportConfiguration {
    private final String reportConfigRoot;
    private final String dbConnection;
    private final String reportConfigName;
    private final String sqlQuery;
    private final List<Map.Entry<String, String>> reportFields;

    public ReportConfiguration(String reportConfigRoot, String dbConnection,
                  String reportConfigName, String sqlQuery,
                  List<Map.Entry<String, String>> reportFields) {
        this.reportConfigRoot = reportConfigRoot;
        this.dbConnection = dbConnection;
        this.reportConfigName = reportConfigName;
        this.sqlQuery = sqlQuery;
        this.reportFields = new ArrayList<>(reportFields);
    }

    public String getReportConfigRoot() {
        return reportConfigRoot;
    }

    public String getDbConnection() {
        return dbConnection;
    }

    public String getReportConfigName() {
        return reportConfigName;
    }

    public String getSqlQuery() {
        return sqlQuery;
    }

    public List<Map.Entry<String, String>> getReportFields() {
        return new ArrayList<>(reportFields);
    }

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