package com.example.WebStore.repos;
import com.example.WebStore.model.Customer;
import org.springframework.data.repository.CrudRepository;
import com.example.WebStore.model.Role;

public interface roleRepo extends CrudRepository<Role, Integer>{
}
