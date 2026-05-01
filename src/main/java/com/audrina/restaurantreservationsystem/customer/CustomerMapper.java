package com.audrina.restaurantreservationsystem.customer;

import org.springframework.stereotype.Service;

@Service

public class CustomerMapper {


    public void mapCustomerToCustomerRequest(Customer customer, CustomerRequest customerRequest) {
        customer.setFirstName(customerRequest.getFirstName());
        customer.setLastName(customerRequest.getLastName());
        customer.setEmail(customerRequest.getEmail());
        customer.setPhoneNumber(customerRequest.getPhoneNumber());
        Address address = new Address();
        if (customerRequest.getAddress() != null) {
            address.setStreet(customerRequest.getAddress().getStreet());
            address.setCity(customerRequest.getAddress().getCity());
            address.setState(customerRequest.getAddress().getState());
            address.setCountry(customerRequest.getAddress().getCountry());
            address.setZipCode(customerRequest.getAddress().getZipCode());

        }
        customer.setAddress(address);

    }

    public CustomerResponse mapCustomerToCustomerResponse(Customer customer) {
        CustomerResponse response = CustomerResponse.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .phoneNumber(String.valueOf(customer.getPhoneNumber()))
                .address(AddressDTO.builder()
                        .city(customer.getAddress().getCity())
                        .street(customer.getAddress().getStreet())
                        .state(customer.getAddress().getState())
                        .zipCode(customer.getAddress().getZipCode())
                        .country(customer.getAddress().getCountry())

                        .build())
                .createdAt(customer.getCreatedAt())

                .build();
        return response;
    }


}
