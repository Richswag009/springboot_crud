package com.richcodes.dto;

import com.richcodes.model.Customer;
import com.richcodes.model.CustomerDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("list")
public class CustomerListAccessService implements CustomerDao {

    private static final List<Customer> customers;

    static {
        customers = new ArrayList<>();
        Customer alex = new Customer(1,"alex","gmail.cpm",21);
        customers.add(alex);
        Customer jamila = new Customer(2,"jamila","gmail.cpm",21);
        customers.add(jamila);
        Customer praise = new Customer(3,"praise","gmail.cpm",25);
        customers.add(praise);
    }
    @Override
    public List<Customer> getCustomers() {
        return customers;
    }

    @Override
    public Optional<Customer> getCustomerByID(Integer customerId) {
        return  customers.stream()
                .filter(s->s.getId().equals(customerId))
                .findFirst();
    }

    @Override
    public void addCustomers(Customer customer) {
        customers.add(customer);
    }


    @Override
    public boolean existsEmail(String email) {
       return customers.stream().anyMatch(s->s.getEmail().equals(email));
    }

    public  void updateCustomer(Customer update){
        customers.add(update);
    }

}
