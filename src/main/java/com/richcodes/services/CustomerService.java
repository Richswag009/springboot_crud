package com.richcodes.services;

import com.richcodes.model.Customer;
import com.richcodes.dto.CustomerDataAccessService;
import com.richcodes.model.CustomerRegistrationRequest;
import com.richcodes.exceptions.DuplicateException;
import com.richcodes.exceptions.RequestValidationException;
import com.richcodes.exceptions.ResourceNotFound;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private  final CustomerDataAccessService customerDao;


    public CustomerService(@Qualifier("jpa") CustomerDataAccessService customerDao) {
        this.customerDao = customerDao;
    }

    public List<Customer> getAllCustomer(){
        return  customerDao.getCustomers();
    }

    public Customer getCustomerById(Integer id){
        return  customerDao.getCustomerByID(id).orElseThrow(
                ()->new ResourceNotFound("customer with id [%s] not found".formatted(id))
        );


    }

    public void saveCustomer(CustomerRegistrationRequest customerRegistrationRequest){
        String email = customerRegistrationRequest.email();
        if(customerDao.existsEmail(email)){
            throw new DuplicateException(
                    "email already taken"
            );
        }
        Customer customer = new Customer(
                customerRegistrationRequest.name(),
                customerRegistrationRequest.email(),
                customerRegistrationRequest.age()
        );
        customerDao.addCustomers(customer);

    }


    public  void deleteCustomerById(Integer id){
        if (customerDao.getCustomerByID(id).isEmpty()){
            throw  new ResourceNotFound("customer wasnt found");
        }
        customerDao.deleteCustomerById(id);
    }

    public void updateCustomer(Integer id,CustomerRegistrationRequest request){
        Customer customer = getCustomerById(id);
        boolean changes = false;

        if (request.name() != null && !request.name().equals(customer.getName())){
            customer.setName(request.name());
            changes=true;
        }

        if (request.age() != null && !request.age().equals(customer.getAge())){
            customer.setAge(request.age());
            changes=true;
        }
        if (request.email() != null && !request.email().equals(customer.getEmail())){
            customer.setEmail(request.email());
            changes=true;
        }
        if(!changes){
            throw  new RequestValidationException("cannot");
        }
        customerDao.updateCustomer(customer);

    }
}
