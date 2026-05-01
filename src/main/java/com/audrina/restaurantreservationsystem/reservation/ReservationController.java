package com.audrina.restaurantreservationsystem.reservation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reservation")
public class ReservationController {
    private  final ReservationService reservationService;
    public Reservation createReservation(@RequestBody @Valid ReservationRequest reservationRequest){
       Reservation reservation =   reservationService.createReservation(reservationRequest);
    return  reservation;
    }
}
