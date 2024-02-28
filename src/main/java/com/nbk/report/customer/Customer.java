
package com.nbk.report.customer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Customer {
    @Id
    private Integer CustomerID;
    @Column(name = "name_en")
    private String Name_En;
    @Column(name = "name_ar")
    private String Name_AR;
    @Column(name = "phone_number")
    private String Phone_Number;
    @Column(name = "address_en")
    private String Address_EN;
    @Column(name = "address_ar")
    private String Address_AR;

    // Default constructor
    public Customer() {
        // Set default values
        this.CustomerID = 0;
        this.Name_En = "";
        this.Name_AR = "";
        this.Phone_Number = "";
        this.Address_EN = "";
        this.Address_AR = "";
    }

    public Customer(Integer customerID, String name_En,
                    String name_AR, String phone_Number,
                    String address_EN, String address_AR) {
        CustomerID = customerID;
        Name_En = name_En;
        Name_AR = name_AR;
        Phone_Number = phone_Number;
        Address_EN = address_EN;
        Address_AR = address_AR;
    }

    public Integer getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(Integer customerID) {
        CustomerID = customerID;
    }

    public String getName_En() {
        return Name_En;
    }

    public void setName_En(String name_En) {
        Name_En = name_En;
    }

    public String getName_AR() {
        return Name_AR;
    }

    public void setName_AR(String name_AR) {
        Name_AR = name_AR;
    }

    public String getPhone_Number() {
        return Phone_Number;
    }

    public void setPhone_Number(String phone_Number) {
        Phone_Number = phone_Number;
    }

    public String getAddress_EN() {
        return Address_EN;
    }

    public void setAddress_EN(String address_EN) {
        Address_EN = address_EN;
    }

    public String getAddress_AR() {
        return Address_AR;
    }

    public void setAddress_AR(String address_AR) {
        Address_AR = address_AR;
    }

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
