package com.audrina.restaurantreservationsystem.restaurant;

import com.audrina.restaurantreservationsystem.staff.Staff;
import com.audrina.restaurantreservationsystem.tableManagement.TableManagement;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    private String phone;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<WorkingHours> workingHours;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    List<TableManagement> tables;

    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL)
    List<Staff> staffs;
    private LocalDate establishedDate;



    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;


}
