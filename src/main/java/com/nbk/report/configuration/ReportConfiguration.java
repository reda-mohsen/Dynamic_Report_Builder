package com.nbk.report.configuration;

import com.nbk.report.model.ReportConfigModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ReportConfiguration {
    private String defaultReportMap;

    public ReportConfiguration(@Value("${map}") String defaultReportMap) {
        this.defaultReportMap = defaultReportMap;
    }
    @Bean
    public ReportConfigModel getCurrentReportConfiguration(@Value("${map}") String map){
        List<ReportConfigModel> reportConfigurations = JsonReader.parseJson("conf.json");
        for(ReportConfigModel reportConfiguration: reportConfigurations){
            if (reportConfiguration.getReportConfigRoot().equals(map.trim())){
                return reportConfiguration;
            }
        }
        return null;
    }
    @Bean
    public String getDefaultReportMap() {
        return defaultReportMap;
    }
}
