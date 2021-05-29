package com.example.WebStore.repos;
import org.springframework.data.repository.CrudRepository;
import com.example.WebStore.model.Customer;

import java.util.List;

public interface CustomerRepo extends CrudRepository<Customer, Integer>{
    List<Customer> findByLogin(String login);
    List<Customer> findByRole(Integer role);
}
