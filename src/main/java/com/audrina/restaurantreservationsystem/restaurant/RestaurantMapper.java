package com.audrina.restaurantreservationsystem.restaurant;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantMapper {
    public RestaurantResponse mapToRestaurantResponse(Restaurant restaurant) {
        RestaurantResponse restaurantResponse = RestaurantResponse.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .location(restaurant.getLocation())
                .phone(restaurant.getPhone())
                .establishedDate(restaurant.getEstablishedDate())
                .workingHours(restaurant.getWorkingHours().stream().map(
                        workingHours ->
                                WorkingHoursDTO.builder()
                                .dayOfWeek(workingHours.getDayOfWeek())
                                .openingTime(workingHours.getOpeningTime())
                                .closingTime(workingHours.getClosingTime())
                                .status(String.valueOf(workingHours.getStatus()))

                                .build()

                ).toList())
                .build();
        return restaurantResponse;
    }

    public void mapToRestaurantRequest(Restaurant restaurant, RestaurantRequest restaurantRequest) {

        restaurant.setName(restaurantRequest.getName());
        restaurant.setLocation(restaurantRequest.getLocation());
        restaurant.setPhone(restaurantRequest.getPhone());

        List<WorkingHours> workingHours = restaurantRequest.getWorkingHours().stream().map(
                workingHoursDTO -> {
                    WorkingHours work =  mapToWorkingHours(workingHoursDTO);
                    work.setRestaurant(restaurant);
                    return work;
                }
        ).toList();
        restaurant.setWorkingHours(workingHours);

        restaurant.setEstablishedDate(restaurantRequest.getEstablishedDate());

    }

    public WorkingHours mapToWorkingHours(WorkingHoursDTO workingHours) {
        return WorkingHours.builder()
                .dayOfWeek(workingHours.getDayOfWeek())
                .openingTime(workingHours.getOpeningTime())
                .closingTime(workingHours.getClosingTime())
                .status(RestaurantStatus.valueOf(workingHours.getStatus()))

                .build();
    }
}
