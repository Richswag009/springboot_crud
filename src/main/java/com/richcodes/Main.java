package com.richcodes;

import com.richcodes.model.Customer;
import com.richcodes.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class Main {


    public static void main(String[] args) {
        /* never do this
        CustomerService customerService = new CustomerService(new CustomerDataAccessService());
        CustomerController customerController = new CustomerController(customerService);
        */
        SpringApplication.run(Main.class,args);
    }

    @Bean
    CommandLineRunner runner(CustomerRepository customerRepository){
        return  args -> {
            Customer alex = new Customer("alex","gmail.cpm",21);
            Customer jamila = new Customer("jamila","gmail.cpm",21);
            List<Customer> customers  = List.of(alex,jamila);
            customerRepository.saveAll(customers);
        };
    }

}
