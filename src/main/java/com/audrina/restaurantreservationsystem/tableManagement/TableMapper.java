package com.audrina.restaurantreservationsystem.tableManagement;

import com.audrina.restaurantreservationsystem.restaurant.Restaurant;
import com.audrina.restaurantreservationsystem.restaurant.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class TableMapper {
    private final RestaurantRepository restaurantRepository;

    public void mapToTableEntity(TableManagement createTable, TableRequest tableRequest) {
        createTable.setTableName(tableRequest.getTableName());
        createTable.setCapacity(tableRequest.getCapacity());
        createTable.setStatus(TableStatus.AVAILABLE);


    }

    public TableResponse toTableResponse(TableManagement createTable ) {
        TableResponse tableResponse = new TableResponse();
        tableResponse.setTableName(createTable.getTableName());
        tableResponse.setCapacity(createTable.getCapacity());
        tableResponse.setStatus(createTable.getStatus());
        return tableResponse;
    }
}
