package com.audrina.restaurantreservationsystem.tableManagement;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tables")
@RequiredArgsConstructor
@Slf4j
public class TableController {
    private final TableService tableService;

    @PostMapping
    public ResponseEntity<TableResponse> createTable(@RequestBody @Valid TableRequest tableRequest) {
        log.info("createTable {}", tableRequest);
        TableResponse response = tableService.createTableForRestaurant(tableRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping
    public ResponseEntity<List<TableResponse>> getTableByRestaurant(@RequestParam(name = "table") Long id) {
        log.info("getTableByRestaurant {}", id);

        List<TableResponse> responses = tableService.getAllTableByRestaurant(id);
        return ResponseEntity.status(HttpStatus.OK).body(responses);

    }

    @PutMapping("{id}")
    public ResponseEntity<TableResponse> updateTable(@PathVariable Long id, @Valid @RequestBody TableRequest tableRequest) {
        log.info("updateTable {}", tableRequest);

        TableResponse updateTable = tableService.updateTable(id, tableRequest);
        return ResponseEntity.ok(updateTable);

    }
}
