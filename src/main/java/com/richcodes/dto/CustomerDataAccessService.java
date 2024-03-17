package com.richcodes.dto;

import com.richcodes.model.Customer;
import com.richcodes.model.CustomerDao;
import com.richcodes.repository.CustomerRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("jpa")
public class CustomerDataAccessService implements CustomerDao {
    private final CustomerRepository customerRepository;


    public CustomerDataAccessService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getCustomerByID(Integer customerId) {
       return customerRepository.findById(customerId);
    }

    @Override
    public void addCustomers(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public boolean existsEmail(String email) {
        return customerRepository.existsCustomerByEmail(email);
    }
    public void deleteCustomerById(Integer id){
        customerRepository.deleteById(id);
    }
    public  void updateCustomer(Customer update){
        customerRepository.save(update);
    }

}
