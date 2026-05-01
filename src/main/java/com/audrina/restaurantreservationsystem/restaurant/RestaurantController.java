package com.audrina.restaurantreservationsystem.restaurant;

import com.audrina.restaurantreservationsystem.tableManagement.TableResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/restaurant")
@RequiredArgsConstructor
@Slf4j
public class RestaurantController {
    private final RestaurantService restaurantService;

    @GetMapping
    public List<RestaurantResponse> getAllRestaurant() {
        log.debug("REST request to get all restaurants");
        return restaurantService.getAllRestaurants();
    }

    @PostMapping
        public ResponseEntity<String> createRestaurant(@Valid @RequestBody RestaurantRequest restaurantRequest) {
        log.info("RESTAURANT request to save restaurant : {}", restaurantRequest);

        Restaurant createdRestaurant = restaurantService.createRestaurant(restaurantRequest);
        if (createdRestaurant != null) {
            return new ResponseEntity<>("Restaurant created successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Restaurant not created", HttpStatus.BAD_REQUEST);

    }

    @GetMapping("{id}")
    public ResponseEntity<RestaurantResponse> getRestaurantById(@PathVariable Long id) {
        log.info("RESTAURANT request to get restaurant : {}", id);
        RestaurantResponse getRestaurant = restaurantService.getRestaurantById(id);

       return  new ResponseEntity<>(getRestaurant, HttpStatus.OK);
    }



}
