package com.example.WebStore.repos;
import org.springframework.data.repository.CrudRepository;
import com.example.WebStore.model.Product;

public interface productRepo extends CrudRepository<Product, Integer>{
}
