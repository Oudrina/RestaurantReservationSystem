package com.audrina.restaurantreservationsystem.restaurant;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper mapper;

    public List<RestaurantResponse> getAllRestaurants() {
        return restaurantRepository.findAll()
                .stream().map(
                        mapper::mapToRestaurantResponse
                ).toList();
    }

    public Restaurant createRestaurant(RestaurantRequest restaurantRequest) {
        Restaurant restaurant = new Restaurant();

        mapper.mapToRestaurantRequest(restaurant, restaurantRequest);

        return restaurantRepository.save(restaurant);
    }

    public RestaurantResponse getRestaurantById(Long id) {

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Restaurant not found"));
        return mapper.mapToRestaurantResponse(restaurant);
    }
}
