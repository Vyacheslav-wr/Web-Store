package com.example.WebStore.controller;
import com.example.WebStore.model.Customer;
import com.example.WebStore.repos.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LoginPageController {
    @Autowired
    private CustomerRepo customerRepo;

    @GetMapping("/login")
    public String Login(){
        return "Login";
    }

    @PostMapping ("/login")
    public String DefineRole(@RequestParam String login, @RequestParam String password, Model model){
        String message  = " ";
        List<Customer> customer = customerRepo.findByLogin(login);
        if(customer.size() != 0){
            for(Customer custom: customer){
                if(password.equals(custom.getPassword())){
                    switch(custom.getRole()){
                        case 1: return "redirect:/adminPage/" + custom.getID();
                        case 2: return "redirect:/userHomePage/" + custom.getID();
                    }

                }
                else{
                    message = "Wrong Password";
                    model.addAttribute("message", message);
                }
            }
        }
        else{
            message = "User doesn't exist";
            model.addAttribute("message", message);
        }
        return "Login";
    }
}
