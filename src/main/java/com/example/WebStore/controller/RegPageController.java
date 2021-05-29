package com.example.WebStore.controller;
import com.example.WebStore.model.Customer;
import com.example.WebStore.repos.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RegPageController {
    @Autowired
    private CustomerRepo customerRepo;

    @GetMapping("/register")
    public String RegisterPage(){
        return "/Register";
    }

    @PostMapping("/register")
    public String UserRegister(@RequestParam String first_name, @RequestParam String last_name
            , @RequestParam String email, @RequestParam String phone_Num, @RequestParam String login
            ,@RequestParam String password){
        Customer newCustomer = new Customer(first_name, last_name, email, phone_Num, password, login);
        List<Customer> existCustomer = customerRepo.findByLogin(login);
        if(existCustomer.isEmpty()){
            customerRepo.save(newCustomer);
            return "redirect:/userHomePage/" + newCustomer.getID();
        }
        return "Register";
    }
}
