package com.audrina.restaurantreservationsystem.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {
    private final CustomerService customerService;


    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest customerRequest) {
        Customer customer = customerService.createCustomer(customerRequest);
        log.info("Customer Created Successfully {}", customer.getFirstName());
        if (customer != null) {
            return new ResponseEntity<>("Customer Created Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Customer Creation Failed", HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        log.info("Get All Customers Successfully");
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);

    }


    @GetMapping("{id}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable Long id) {
        log.info("Get Customer Successfully");
       CustomerResponse response = customerService.getCustomer(id);
       return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PutMapping("{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerRequest customerRequest) {
        log.info("Update Customer Successfully");
     CustomerResponse updatedCustomer = customerService.updateCustomer(id, customerRequest);

     return  new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {

        log.info("Delete Customer Successfully");

        customerService.removeCustomer(id);
        return new ResponseEntity<>("Customer Deleted Successfully", HttpStatus.OK);
    }


}
