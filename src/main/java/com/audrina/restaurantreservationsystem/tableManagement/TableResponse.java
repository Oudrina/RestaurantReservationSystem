package com.audrina.restaurantreservationsystem.tableManagement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableResponse {
    private String tableName;
    private int capacity;
    private TableStatus status;

}
