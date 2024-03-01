package com.nbk.report.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Report {
    private String reportRoot;
    private String dbConnection;
    private String reportName;
    private String sqlQuery;
    private List<Map.Entry<String, String>> reportFields;

    public Report() {}

    public Report(String reportRoot, String dbConnection,
                  String reportName, String sqlQuery,
                  List<Map.Entry<String, String>> reportFields) {
        this.reportRoot = reportRoot;
        this.dbConnection = dbConnection;
        this.reportName = reportName;
        this.sqlQuery = sqlQuery;
        this.reportFields = new ArrayList<>(reportFields);
    }

    public String getReportRoot() {
        return reportRoot;
    }

    public void setReportRoot(String reportRoot) {
        this.reportRoot = reportRoot;
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

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
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
                "reportRoot='" + reportRoot + '\'' +
                ", dbConnection='" + dbConnection + '\'' +
                ", reportName='" + reportName + '\'' +
                ", sqlQuery='" + sqlQuery + '\'' +
                ", reportFields=" + reportFields +
                '}';
    }
}