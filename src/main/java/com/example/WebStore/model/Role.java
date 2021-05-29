package com.example.WebStore.model;
import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    public Integer getID() {
        return ID;
    }

    @Column(name ="ROLE")
    private String role;

    public Role(){

    }

    public Role(String Role){

        this.role = Role;
    }
}
