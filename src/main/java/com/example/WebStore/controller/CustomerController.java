package com.example.WebStore.controller;
import com.example.WebStore.model.*;
import com.example.WebStore.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class CustomerController {
    private Integer ID;

    private Integer orderID;

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    productRepo productrepo;

    @Autowired
    productcategoryRepo categoryRepo;

    @Autowired
    orderRepo OrderRepo;

    @Autowired
    orderdetailsRepo detailsRepo;

    private Integer flag = 0;

    @GetMapping("/userHomePage")
    public String home(Model model){
        return "redirect:/userHomePage/" + ID;
    }

    @GetMapping("/userOrder")
    public String OrderPage(Model model){
        StringBuffer url = new StringBuffer("redirect:/orderAction/");
        url.append(ID);
        url.append("/show");
        return url.toString();
    }

    @GetMapping("/userHomePage/{id}")
    public String User(@PathVariable(value ="id") Integer id, Model model){
        flag = 0;
        ID = id;
        Optional<Customer> users = customerRepo.findById(id);
        Customer user = users.get();
        model.addAttribute("user", user);
        return "UserHomePage";
    }

    @PostMapping("/productFilterList")
    public String ShowWithFilter(@RequestParam String filter, Model model){
        Iterable<Product> products = productrepo.findAll();
        ArrayList<Product> filtered = new ArrayList<Product>();
        for(Product prod: products){
            if(prod.getName().toLowerCase(Locale.ROOT).contains(filter.toLowerCase(Locale.ROOT)) || prod.getBrand().toLowerCase(Locale.ROOT).contains(filter.toLowerCase(Locale.ROOT))){
                filtered.add(prod);
            }
        }
        model.addAttribute("products", filtered);
        model.addAttribute("category", categoryRepo);
        return "ProductsList";
    }

    @GetMapping("/productsList/{id}")
    public String ShowCategories(@PathVariable(value = "id") Integer id, Model model){
        Iterable<Product> products = productrepo.findAll();
        model.addAttribute("products", products);
        model.addAttribute("category", categoryRepo);
        return "ProductsList";
    }

    @GetMapping("/orderAction/{id}/show")
    public String showOrders(@PathVariable(value="id") Integer id, Model model){
        List<Order> orders = OrderRepo.findByCustomerID(id);
        model.addAttribute("orders", orders);
        return "OrderList";
    }

    @GetMapping("/action/{id}/showDetails")
    public String showDetails(@PathVariable(value="id") Integer id, Model model){
        List<Orderdetail> byOrderID = detailsRepo.findByOrderID(id);
        model.addAttribute("products", productrepo);
        model.addAttribute("details", byOrderID);
        return "OrderDetailList";
    }

    @GetMapping("/action/{id}/add")
    public String addToCart(@PathVariable(value = "id") Integer id, Model model){
        Product product = productrepo.findById(id).orElseThrow();
        Customer byId = customerRepo.findById(ID).orElseThrow();
        String message = " ";
        if(byId.getState().equals("unblocked") && product.getQuantity() != 0){
            Date date = new Date();
            String time  = date.toString();
           if(flag != 1){ Order order = new Order(time, ID);
                OrderRepo.save(order);
               orderID = order.getID();
           }
           flag = 1;
            Orderdetail detail = new Orderdetail(time, 1, product.getPrice(), product.getID(), orderID);
            detailsRepo.save(detail);
            product.setQuantity(product.getQuantity() - 1);
            productrepo.save(product);
        }

        if(!byId.getState().equals("unblocked")){
            message = "Your account is blocked";
        }
        else{
            if(product.getQuantity() == 0){
                message = "Product is out of Stock";
            }
            else{
                message = " ";
            }
        }
        model.addAttribute("message", message);
        Iterable<Product> all = productrepo.findAll();
        model.addAttribute("products", all);
        model.addAttribute("category", categoryRepo);
        return "ProductsList";
    }

    @GetMapping("/edit/{id}")
    public String EditInfo(@PathVariable(value = "id") Integer id, Model model){
        Customer user = customerRepo.findById(ID).orElseThrow();
        model.addAttribute("user", user);
        return "ProfileEdit";
    }

    @PostMapping("/claim")
    public String ClaimChanges(@RequestParam String first_name, @RequestParam String last_name
            , @RequestParam String email, @RequestParam String phone_Num, @RequestParam String login
            ,@RequestParam String password){
        Customer customer = customerRepo.findById(ID).orElseThrow();
        customer.setFirst_name(first_name);
        customer.setLast_name(last_name);
        customer.setEmail(email);
        customer.setPhoneNumber(phone_Num);
        customer.setLogin(login);
        customer.setPassword(password);
        customerRepo.save(customer);
        return "redirect:/userHomePage";
    }
}
