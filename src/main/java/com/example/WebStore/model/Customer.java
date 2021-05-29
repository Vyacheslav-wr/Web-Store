package com.example.WebStore.model;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "Customers")
public class Customer {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer ID;

    public Integer getID() {
        return ID;
    }

    @Column(name = "FIRST_NAME")
    private String first_name;

    @Column(name = "LAST_NAME")
    private String last_name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ROLE")
    private Integer role;

    @Column(name ="LOGIN")
    private  String login;

    @Column(name ="USER_STATE")
    private String state;

    public Customer(){

    }

    public Customer(String First_Name, String Last_Name, String CustomerEmail, String CustomerPhoneNumber, String CustomerPassword, String UserLogin){
        this.first_name = First_Name;
        this.last_name = Last_Name;
        this.email = CustomerEmail;
        this.phoneNumber = CustomerPhoneNumber;
        this.password = CustomerPassword;
        this.role = 2;
        this.login = UserLogin;
        this.state = "unblocked";
    }

}
