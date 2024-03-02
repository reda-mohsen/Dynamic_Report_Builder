# Dynamic Report Builder
Dynamic Report Builder Web Application

## Overview
In many business there is requirements to represent data from relational database to visual web page 
for business user, and this is very systematic process that may consume developer time without learning 
or achieving something new. Dynamic display of a data base query will help very much on that.

### Prerequisites
- JDK 17

### Configuration
- Run local database contains fbnk_customers table
- Configure the "conf.json" file as you like

### Installation
   ```bash
   git clone https://github.com/reda-mohsen/Dynamic_Report_Builder.git
   cd Dynamic_Report_Builder
   mvn clean package
   java -jar target/report-0.0.1-SNAPSHOT.jar
