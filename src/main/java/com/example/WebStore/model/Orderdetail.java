package com.example.WebStore.model;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "orderdetails")
public class Orderdetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    public Integer getID() {
        return ID;
    }

    @Column(name = "DATE")
    private String orderDate;

    @Column(name = "PRODUCT_QUANTITY")
    private Integer orderProductQuantity;

    @Column(name = "PRODUCT_PRICE")
    private Float orderProductPrice;

    @Column(name = "PRODUCT_ID")
    private Integer productID;

    @Column(name = "ORDER_ID")
    private Integer orderID;

    public Orderdetail(){

    }

    public Orderdetail(String OrderDate, Integer OrderProductQuantity, Float OrderProductPrice, Integer ProductID, Integer OrderID){
        this.orderDate = OrderDate;
        this.orderProductQuantity = OrderProductQuantity;
        this.orderProductPrice = OrderProductPrice;
        this.productID = ProductID;
        this.orderID = OrderID;
    }
}
