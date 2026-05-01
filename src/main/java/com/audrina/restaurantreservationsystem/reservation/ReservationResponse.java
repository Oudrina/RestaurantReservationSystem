package com.audrina.restaurantreservationsystem.reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponse {
    private LocalDateTime reservationTime;
    private  int duration;
    private  int partySize;
    private  String specialRequest;
    private String customerName;
    private String status;

}
