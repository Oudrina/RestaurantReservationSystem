package com.audrina.restaurantreservationsystem.restaurant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RestaurantResponse {
    private  Long id;
    private String name;
    private String location;
    private  String phone;
    private LocalDate establishedDate;
    private List<WorkingHoursDTO> workingHours = new ArrayList<>();


}
