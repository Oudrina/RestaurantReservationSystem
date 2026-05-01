package com.audrina.restaurantreservationsystem.reservation;

import com.audrina.restaurantreservationsystem.customer.Customer;
import com.audrina.restaurantreservationsystem.tableManagement.TableManagement;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime reservationTime;
    private  int duration;
    private  int partySize;
    @Enumerated(EnumType.STRING)
    private  ReservationStatus status;
    private  String specialRequest;
    private  LocalDateTime startTime;
    private  LocalDateTime endTime;
    @ManyToOne
    @JoinColumn(name = "customer_Id", nullable = false)
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "table_Id",nullable = false)
    private TableManagement tables;

    @CreationTimestamp
    private  LocalDateTime createAt;

    @UpdateTimestamp
    private  LocalDateTime updateAt;
}
