package com.example.WebStore.controller;
import com.example.WebStore.model.Customer;
import com.example.WebStore.model.Order;
import com.example.WebStore.model.Product;
import com.example.WebStore.repos.CustomerRepo;
import com.example.WebStore.repos.orderRepo;
import com.example.WebStore.repos.productRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {
    Integer ID;
    @Autowired
    CustomerRepo Customers;

    @Autowired
    productRepo productrepo;

    @Autowired
    orderRepo orderrepo;

    @GetMapping("/adminHomePage")
    public String home(Model model){
        return "redirect:/adminPage/" + ID;
    }

    @GetMapping("/adminProducts")
    public String productsPage(Model model){
        return "redirect:/adminProducts/" + ID;
    }

    @PostMapping("/adminProducts/{id}")
    public String EditProduct(@PathVariable(value = "id") Integer id, @RequestParam String product_name,
                          @RequestParam Float price, @RequestParam String brand,
                          @RequestParam Integer quantity, @RequestParam Integer category, Model model){
        Product product = productrepo.findById(id).orElseThrow();
        product.setName(product_name);
        product.setPrice(price);
        product.setBrand(brand);
        product.setQuantity(quantity);
        product.setCategoryID(category);
        productrepo.save(product);
        Iterable<Product> all = productrepo.findAll();
        model.addAttribute("allProducts", all);
        return "AdminProductList";
    }

    @GetMapping("/adminPage/{id}")
    public String Admin(@PathVariable(value ="id") Integer id, Model model){
        Optional<Customer> admins = Customers.findById(id);
        Customer admin = admins.get();
        ID = admin.getID();
        model.addAttribute("admin", admin);
        return "AdminHomePage";
    }

    @GetMapping("/adminUserList/{id}")
    public String AdminList(@PathVariable(value = "id") Integer id, Model model){
        List<Customer> users = Customers.findByRole(2);
        model.addAttribute("users", users);
        return "AdminUserList";
    }

    @GetMapping("/adminProducts/{id}")
    public String AdminProducts(@PathVariable(value = "id") Integer id, Model model){
        Iterable<Product> all = productrepo.findAll();
        model.addAttribute("allProducts", all);
        return "AdminProductList";
    }

    @GetMapping("/adminProducts/{id}/deleteProduct")
    public String DeleteProduct(@PathVariable(value = "id") Integer id, Model model){
        productrepo.deleteById(id);
        Iterable<Product> all = productrepo.findAll();
        model.addAttribute("allProducts", all);
        return "AdminProductList";
    }

    @GetMapping("/adminProduct/{id}/EditProduct")
    public String EditProduct(@PathVariable(value = "id") Integer id, Model model){
        Product product= productrepo.findById(id).orElseThrow();
        model.addAttribute("product", product);
        return "EditProductInfo";
    }

    @GetMapping("/action/{id}/delete")
    public String Uspdate(@PathVariable(value = "id") Integer id, Model model){
        Customers.deleteById(id);
        List<Customer> users = Customers.findByRole(2);
        model.addAttribute("users", users);
        return "AdminUserList";
    }

    @GetMapping("/action/{id}/block")
    public String Block(@PathVariable(value = "id") Integer id, Model model){
        Customer user = Customers.findById(id).orElseThrow();
        List<Customer> users = Customers.findByRole(2);
        model.addAttribute("users", users);
        user.setState("blocked");
        Customers.save(user);
        return "AdminUserList";
    }

    @GetMapping("/action/{id}/unblock")
    public String Unblock(@PathVariable(value = "id") Integer id, Model model){
        Customer user = Customers.findById(id).orElseThrow();
        List<Customer> users = Customers.findByRole(2);
        model.addAttribute("users", users);
        user.setState("unblocked");
        Customers.save(user);
        return "AdminUserList";
    }

    @GetMapping("/adminProducts/addProduct")
    public String AddProduct(){
        return "AddingProduct";
    }

    @PostMapping("/adminProducts/addProduct")
    public String AddToDb(@RequestParam String product_name,
                          @RequestParam Float price, @RequestParam String brand,
                          @RequestParam Integer quantity, @RequestParam Integer category, Model model){
        Product product = new Product(product_name, price, brand, quantity, category);
        productrepo.save(product);
        Iterable<Product> all = productrepo.findAll();
        model.addAttribute("allProducts", all);
        return "AdminProductList";
    }

    @GetMapping("/ordersList")
    public String Orders(Model model){
        Iterable<Order> all = orderrepo.findAll();
        model.addAttribute("orders", all);
        model.addAttribute("customerRepo", Customers);
        return "AdminOrder";
    }
}
