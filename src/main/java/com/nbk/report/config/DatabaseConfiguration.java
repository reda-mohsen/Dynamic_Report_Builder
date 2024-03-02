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
    // Properties for database connection
    private String url;
    private String driverClassName;
    private String username;
    private String password;

    // Bean definition for the primary DataSource
    @Bean
    @Primary
    public DataSource dataSource(ReportConfiguration reportConfiguration) {
        // Get the database connection properties from ReportConfiguration
        String dbConnectionProperties = reportConfiguration.getDbConnection();

        // Check if the properties are not null and not empty
        if (dbConnectionProperties != null && !dbConnectionProperties.isEmpty()) {
            try {
                // Split the properties into parts
                String[] parts = dbConnectionProperties.split(";");

                // Iterate through the parts and set the corresponding properties
                for (String part : parts) {
                    String[] keyValue = part.split("=");
                    if (keyValue.length == 2) {
                        String key = keyValue[0].trim();
                        String value = keyValue[1].trim();

                        // Use a switch statement to set the appropriate property based on the key
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
                // Print the stack trace if an exception occurs during property assignment
                e.printStackTrace();
            }
        } else {
            // Throw an exception if the database connection properties string is empty
            throw new IllegalArgumentException("Database Connection properties string is empty!");
        }

        // Create a new DriverManagerDataSource and set its properties
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        // Return the configured DataSource
        return dataSource;
    }

    // Setter methods for individual connection properties
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

    // Bean definition for obtaining the ReportConfiguration from a JSON file
    @Bean
    public ReportConfiguration getReport(){
        return JsonReader.parseJson("conf.json");
    }
}
