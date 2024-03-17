package com.richcodes.controller;

import com.richcodes.model.Customer;
import com.richcodes.model.CustomerRegistrationRequest;
import com.richcodes.services.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    private  final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping
    public List<Customer> getCustomers(){
        return customerService.getAllCustomer();
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable("id")Integer customerId){
      return  customerService.getCustomerById(customerId);
    }

    @PostMapping
    public  void registerCustomer(@RequestBody CustomerRegistrationRequest request){
        customerService.saveCustomer(request);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id")Integer id){
         customerService.deleteCustomerById(id);
    }

    @PutMapping("/{id}")
    public void updateCustomer(@PathVariable("id")Integer id,CustomerRegistrationRequest request){
         customerService.updateCustomer(id,request);
    }
}
