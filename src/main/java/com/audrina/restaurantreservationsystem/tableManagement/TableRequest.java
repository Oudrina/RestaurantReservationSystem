package com.audrina.restaurantreservationsystem.tableManagement;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TableRequest {
    @NotNull(message = "RestaurantId is needed")
    private Long restaurant_id;
    @NotNull(message = "Table Name  is  required to create table")
    private String tableName;
    @NotNull(message = "Capacity is required")
    private int capacity;
}
