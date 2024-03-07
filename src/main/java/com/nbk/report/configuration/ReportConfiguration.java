package com.nbk.report.configuration;

import com.nbk.report.model.ReportConfigModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;
@Configuration
public class ReportConfiguration {
    private final String confFilePath;

    public ReportConfiguration(@Value("${conf:conf.json}")String confFilePath) {
        this.confFilePath = confFilePath;
    }
    public ReportConfigModel getCurrentReportConfiguration(String map){
        List<ReportConfigModel> reportConfigurations = getListOfReports();
        for(ReportConfigModel reportConfiguration: reportConfigurations){
            if (reportConfiguration.getReportConfigRoot().equals(map)){
                return reportConfiguration;
            }
        }
        return null;
    }
    @Bean
    public List<ReportConfigModel> getListOfReports(){
        return JsonReader.parseJson(confFilePath);
    }
}
