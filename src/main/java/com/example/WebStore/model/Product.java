package com.example.WebStore.model;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    public Integer getProductID() {

        return ID;
    }

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private Float price;

    @Column(name = "BRAND")
    private String brand;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "CATEGORY_ID")
    private  Integer categoryID;

    public Product(){

    }

    public Product(String ProductName, Float ProductPrice, String ProductBrand, Integer ProductQuantity, Integer ProductCategoryID){
        this.name = ProductName;
        this.price = ProductPrice;
        this.brand = ProductBrand;
        this.quantity = ProductQuantity;
        this.categoryID = ProductCategoryID;
    }
}
