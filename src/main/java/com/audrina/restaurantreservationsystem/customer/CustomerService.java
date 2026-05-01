package com.audrina.restaurantreservationsystem.customer;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;

    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll().stream().map(
                mapper::mapCustomerToCustomerResponse
        ).toList();
    }


    public Customer createCustomer(CustomerRequest customerRequest) {

        if (customerRepository.existsByEmail(customerRequest.getEmail())) {
            throw new EntityExistsException("Customer with email " + customerRequest.getEmail() + " already exists");
        }
        Customer customer = new Customer();
        mapper.mapCustomerToCustomerRequest(customer, customerRequest);
        return customerRepository.save(customer);

    }

    public CustomerResponse getCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer with id " + id + " not found"));
        return mapper.mapCustomerToCustomerResponse(customer);

    }

    public CustomerResponse updateCustomer(Long id, CustomerRequest customerRequest) {

        Customer existingcustomer = customerRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Customer with id " + id + " not found")
        );

        mapper.mapCustomerToCustomerRequest(existingcustomer, customerRequest);
        customerRepository.save(existingcustomer);
        return mapper.mapCustomerToCustomerResponse(existingcustomer);


    }

    public void removeCustomer(Long id) {
        customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer with id " + id + " not found"));


    }
}
