package com.audrina.restaurantreservationsystem.reservation;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequest {
    private Long customerId;
    private Long tableId;
    private Long restaurantId;

    private LocalDateTime reservationTime;
    private int duration; // in minutes
    private int partySize;

    private String specialRequests;

}
