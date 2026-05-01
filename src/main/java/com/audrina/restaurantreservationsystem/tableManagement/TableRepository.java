package com.audrina.restaurantreservationsystem.tableManagement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<TableManagement, Long> {
    List<TableManagement> findByRestaurantId(Long id);


    TableManagement findByIdAndRestaurantId(Long id, Long restaurant_id);
}
