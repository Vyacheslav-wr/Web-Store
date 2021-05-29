package com.example.WebStore.model;
import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "productcategory")
public class Productcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    public Integer getID() {
        return ID;
    }

    @Column(name = "NAME")
    private String productCategoryName;

    public Productcategory(){

    }

    public Productcategory(String ProductCategoryName){
        this.productCategoryName = ProductCategoryName;
    }
}
