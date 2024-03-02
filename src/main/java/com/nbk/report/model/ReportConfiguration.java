package com.nbk.report.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReportConfiguration {
    private String reportConfigRoot;
    private String dbConnection;
    private String reportConfigName;
    private String sqlQuery;
    private List<Map.Entry<String, String>> reportFields;

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

    public void setReportConfigRoot(String reportConfigRoot) {
        this.reportConfigRoot = reportConfigRoot;
    }

    public String getDbConnection() {
        if (dbConnection != null){
            return dbConnection;
        }
        else {
            return null;
        }
    }

    public void setDbConnection(String dbConnection) {
        this.dbConnection = dbConnection;
    }

    public String getReportConfigName() {
        return reportConfigName;
    }

    public void setReportConfigName(String reportConfigName) {
        this.reportConfigName = reportConfigName;
    }

    public String getSqlQuery() {
        return sqlQuery;
    }

    public void setSqlQuery(String sqlQuery) {
        this.sqlQuery = sqlQuery;
    }

    public List<Map.Entry<String, String>> getReportFields() {
        return new ArrayList<>(reportFields);
    }

    public void setReportFields(List<Map.Entry<String, String>> reportFields) {
        this.reportFields = reportFields;
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