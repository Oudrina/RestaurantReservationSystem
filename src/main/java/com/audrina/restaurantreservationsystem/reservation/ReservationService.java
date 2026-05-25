package com.audrina.restaurantreservationsystem.reservation;

import com.audrina.restaurantreservationsystem.customer.Customer;
import com.audrina.restaurantreservationsystem.customer.CustomerRepository;
import com.audrina.restaurantreservationsystem.restaurant.Restaurant;
import com.audrina.restaurantreservationsystem.restaurant.RestaurantRepository;
import com.audrina.restaurantreservationsystem.tableManagement.TableManagement;
import com.audrina.restaurantreservationsystem.tableManagement.TableRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final RestaurantRepository restaurantRepository;
    private final TableRepository tableRepository;
    private final CustomerRepository customerRepository;

    public Reservation createReservation(@Valid ReservationRequest reservationRequest) {
        //todo:1. Customer exists
        Customer customer = customerRepository.findById(reservationRequest.getCustomerId())
                .orElseThrow(() -> new RuntimeException
                        ("Customer Not Found with Id : " + reservationRequest.getCustomerId()));
        // todo: 2. Restaurant exists
        Restaurant restaurant = restaurantRepository.findById(reservationRequest.getRestaurantId())
                .orElseThrow(() -> new RuntimeException
                        ("Restaurant Not Found with Id:  " + reservationRequest.getRestaurantId()));

        // todo:3. Table exists
        TableManagement table = tableRepository.findById(reservationRequest.getTableId())
                .orElseThrow(() -> new RuntimeException
                        ("Table Not Found with Id:  " + reservationRequest.getTableId()));
        //todo:4. Table belongs to that restaurant
        if (!table.getRestaurant().getId().equals(reservationRequest.getRestaurantId())) {
            throw new RuntimeException
                    ("Table Not Found with Id:  " + reservationRequest.getTableId());
        }
        //todo:5. partySize <= table.capacity
        if (reservationRequest.getPartySize() > table.getCapacity()) {
            throw new RuntimeException
                    ("Party Size Exceeded for Table Reservation");
        }

        // todo 6: calculate start and endTime
        LocalDateTime startTime = reservationRequest.getReservationTime();
        LocalDateTime endTime = startTime.plusMinutes(reservationRequest.getDuration());

        //todo:7. Table is AVAILABLE at that time (no overlap)
        List<Reservation> reservationConflicts = reservationRepository.findOverlappingReservations(table.getId(), startTime, endTime);
        if (!reservationConflicts.isEmpty()) {
            throw new RuntimeException
                    ("Table Reservation Conflict for Table Reservation");
        }

        //todo create reservation

        Reservation reservation = new Reservation();
        reservation.setCustomer(customer);
        reservation.setTables(table);
        reservation.setReservationTime(startTime);
        reservation.setDuration(reservationRequest.getDuration());
        reservation.setPartySize(reservationRequest.getPartySize());
        reservation.setEndTime(endTime);
        reservation.setSpecialRequest(reservationRequest.getSpecialRequests());
        reservation.setStatus(ReservationStatus.CONFIRMED);


      return   reservationRepository.save(reservation);


    }


    public Reservation viewReservation(Long customerId) {
        return reservationRepository.findByCustomerId(customerId);
    }

    public List<Reservation> findAll() {
        return  reservationRepository.findAll();
    }

    public void cancelReservation(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                ()-> new RuntimeException("Customer Not Found with Id: " + customerId)
        );
        Reservation savedReservation = reservationRepository.findByCustomerId(customerId);

        if (!customer.getReservations().isEmpty()) {
            savedReservation.setStatus(ReservationStatus.CANCELLED);
            reservationRepository.save(savedReservation);
        }
    }
}
;