package com.audrina.restaurantreservationsystem.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    @NotNull(message = "First is required")
    private String firstName;
    @NotNull(message = "LastName is required")
    private String lastName;
    @NotNull(message = "Email is required")
    @Email(message = "Email must be well formatted")
    private String email;

    @NotNull(message = "PhoneNumber is required")
    private String phoneNumber;

    @NotNull(message = "Address is required")
    private AddressDTO address;
}
