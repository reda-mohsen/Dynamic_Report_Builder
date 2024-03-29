package com.nbk.report.model;

// Model class representing a Customer entity
public class Customer {

    // Fields to store various attributes of the customer
    private Integer CustomerID;
    private String Name_En;
    private String Name_AR;
    private String Phone_Number;
    private String Address_EN;
    private String Address_AR;

    // Default constructor for creating instances without initial values
    public Customer() {}

    // Parameterized constructor for creating instances with initial values
    public Customer(Integer customerID, String name_En,
                    String name_AR, String phone_Number,
                    String address_EN, String address_AR) {
        // Assign values to the fields
        CustomerID = customerID;
        Name_En = name_En;
        Name_AR = name_AR;
        Phone_Number = phone_Number;
        Address_EN = address_EN;
        Address_AR = address_AR;
    }

    // Setter methods for updating each field

    public void setCustomerID(Integer customerID) {
        CustomerID = customerID;
    }

    public void setName_En(String name_En) {
        Name_En = name_En;
    }

    public void setName_AR(String name_AR) {
        Name_AR = name_AR;
    }

    public void setPhone_Number(String phone_Number) {
        Phone_Number = phone_Number;
    }

    public void setAddress_EN(String address_EN) {
        Address_EN = address_EN;
    }

    public void setAddress_AR(String address_AR) {
        Address_AR = address_AR;
    }

    // Getter methods for retrieving the values of each field

    public Integer getCustomerID() {
        return CustomerID;
    }

    public String getName_En() {
        return Name_En;
    }

    public String getName_AR() {
        return Name_AR;
    }

    public String getPhone_Number() {
        return Phone_Number;
    }

    public String getAddress_EN() {
        return Address_EN;
    }

    public String getAddress_AR() {
        return Address_AR;
    }

    // Override the toString method for meaningful string representation
    @Override
    public String toString() {
        return "Customer{" +
                "CustomerID=" + CustomerID +
                ", Name_En='" + Name_En + '\'' +
                ", Name_AR='" + Name_AR + '\'' +
                ", Phone_Number='" + Phone_Number + '\'' +
                ", Address_EN='" + Address_EN + '\'' +
                ", Address_AR='" + Address_AR + '\'' +
                '}';
    }
}
