package com.audrina.restaurantreservationsystem.staff;

import com.audrina.restaurantreservationsystem.restaurant.Restaurant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @Column(nullable = false)
    private  String name;
    @Column(nullable = false,unique = true)
    private  String email;
    @Enumerated(EnumType.STRING)
    private  StaffStatus role;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

}
