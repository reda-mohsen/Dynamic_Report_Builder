package com.nbk.report.config;

import com.nbk.report.model.Report;
import com.nbk.report.model.ReportConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class DatabaseConfiguration {
    private String url;
    private String driverClassName;
    private String username;
    private String password;

    @Bean
    @Primary
    public DataSource dataSource(ReportConfiguration reportConfiguration) {
        String dbConnectionProperties = reportConfiguration.getDbConnection();
        if (dbConnectionProperties != null && !dbConnectionProperties.isEmpty()) {
            try {
                String[] parts = dbConnectionProperties.split(";");

                for (String part : parts) {
                    String[] keyValue = part.split("=");
                    if (keyValue.length == 2) {
                        String key = keyValue[0].trim();
                        String value = keyValue[1].trim();

                        switch (key) {
                            case "url":
                                setUrl(value);
                                break;
                            case "driver":
                                setDriverClassName(value);
                                break;
                            case "username":
                                setUsername(value);
                                break;
                            case "password":
                                setPassword(value);
                                break;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("Database Connection properties string is empty!");
        }
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    // Setters for individual connection properties
    public void setUrl(String url) {
        this.url = url;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Bean
    public ReportConfiguration getReport(){
        return JsonReader.parseJson("conf.json");
    }

}
