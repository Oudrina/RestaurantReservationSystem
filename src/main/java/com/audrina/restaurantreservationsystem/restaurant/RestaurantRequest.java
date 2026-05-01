package com.audrina.restaurantreservationsystem.restaurant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantRequest {
    private String name;
    private String location;
    private  String phone;
   private List<WorkingHoursDTO> workingHours;
    private LocalDate establishedDate;
}
