package com.audrina.restaurantreservationsystem.tableManagement;

import com.audrina.restaurantreservationsystem.restaurant.Restaurant;
import com.audrina.restaurantreservationsystem.restaurant.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TableService {
    private final TableRepository tableRepository;
    private final TableMapper mapper;
    private final RestaurantRepository restaurantRepository;

    public TableResponse createTableForRestaurant(TableRequest tableRequest) {
        Restaurant restaurant = restaurantRepository
                .findById(tableRequest.getRestaurant_id())
                .orElseThrow(
                        () -> new RuntimeException
                                ("Restaurants not found with id : "+ tableRequest.getRestaurant_id()));


        TableManagement createTable = new TableManagement();
        mapper.mapToTableEntity(createTable, tableRequest);
        createTable.setRestaurant(restaurant);

        tableRepository.save(createTable);
        return mapper.toTableResponse(createTable);

    }


    public List<TableResponse> getAllTableByRestaurant(Long id) {

        List<TableManagement> tables = tableRepository.findByRestaurantId(id);

        return tables.stream().map(
                mapper::toTableResponse
        ).toList();
    }

    public TableResponse updateTable(Long id, TableRequest tableRequest) {
       TableManagement table = tableRepository
                .findByIdAndRestaurantId(id, tableRequest.getRestaurant_id());

        if (table == null) {
            throw new RuntimeException("Table not found with id : " + id +" But restaurant found : " + tableRequest.getRestaurant_id());
        }

        mapper.mapToTableEntity(table, tableRequest);
        tableRepository.save(table);
        return mapper.toTableResponse(table);

    }
}
