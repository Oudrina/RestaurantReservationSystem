package com.audrina.restaurantreservationsystem.tableManagement;

import com.audrina.restaurantreservationsystem.reservation.Reservation;
import com.audrina.restaurantreservationsystem.restaurant.Restaurant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TableManagement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    private String tableName;
    private int capacity;
    @Enumerated(EnumType.STRING)
    private TableStatus status;
    @CreationTimestamp
     private LocalDateTime createdAt;
    @UpdateTimestamp
     private LocalDateTime updatedAt;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "reservationId")
    List<Reservation> reservations;
}
