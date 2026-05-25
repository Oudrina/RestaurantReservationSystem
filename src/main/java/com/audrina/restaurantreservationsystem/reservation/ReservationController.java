package com.audrina.restaurantreservationsystem.reservation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reservation")
public class ReservationController {

    private final ReservationService reservationService;
    @GetMapping
    public List<Reservation> findAllReservations() {
        return reservationService.findAll();
    }

    @PostMapping
    public Reservation createReservation(@RequestBody @Valid ReservationRequest reservationRequest) {
        return reservationService.createReservation(reservationRequest);
    }

    @GetMapping("{CustomerId}")
    public ResponseEntity<Reservation> viewReservation(@RequestHeader(name = "X-USER-ID") Long CustomerId) {
        return ResponseEntity.ok(reservationService.viewReservation(CustomerId));
    }

    @DeleteMapping("{CustomerId}")
    public String cancelReservation(@RequestHeader(name = "X-USER-ID") Long CustomerId) {
        reservationService.cancelReservation(CustomerId);
        return "Reservation has been cancelled";
    }

}
