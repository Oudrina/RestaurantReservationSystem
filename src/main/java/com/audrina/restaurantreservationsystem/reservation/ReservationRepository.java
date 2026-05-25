package com.audrina.restaurantreservationsystem.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("""
            SELECT r FROM Reservation r
            WHERE r.tables.id = :tableId
            AND r.status <> 'CANCELLED'
            AND r.reservationTime < :endTime
            AND r.endTime > :startTime
            """)
    List<Reservation> findOverlappingReservations(Long tableId, LocalDateTime startTime, LocalDateTime endTime);

    Reservation findByCustomerId(Long customerId);
}
