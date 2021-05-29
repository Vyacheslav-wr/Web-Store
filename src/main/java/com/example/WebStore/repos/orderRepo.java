package com.example.WebStore.repos;
import org.springframework.data.repository.CrudRepository;
import com.example.WebStore.model.Order;

import java.util.List;

public interface orderRepo extends CrudRepository<Order, Integer>{
    List<Order> findByCustomerID(Integer customerID);
}
