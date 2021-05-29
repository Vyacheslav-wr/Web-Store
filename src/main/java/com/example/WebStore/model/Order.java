package com.example.WebStore.model;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    public Integer getID() {
        return ID;
    }

    @Column(name = "DATE")
    private String date;

    @Column(name = "CUSTOMER_ID")
    private Integer customerID;

    public Order(){

    }

    public Order(String OrderDate, Integer CustomerID){
        this.date = OrderDate;
        this.customerID = CustomerID;
    }
}
