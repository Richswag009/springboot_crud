package com.richcodes.model;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("fake")
public interface CustomerDao {
    List<Customer> getCustomers();
    Optional<Customer> getCustomerByID(Integer customerId);
    void addCustomers(Customer customers);
    boolean existsEmail(String email);
}
