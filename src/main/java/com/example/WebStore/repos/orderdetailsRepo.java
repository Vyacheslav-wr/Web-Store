package com.example.WebStore.repos;
import org.springframework.data.repository.CrudRepository;
import com.example.WebStore.model.Orderdetail;

import java.util.List;

public interface orderdetailsRepo extends CrudRepository<Orderdetail, Integer>{
List<Orderdetail> findByOrderID(Integer OrderID);
}
