package com.elastic.search.controller;

import com.elastic.search.dto.ApiResponse;
import com.elastic.search.dto.EmployeeDto;
import com.elastic.search.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/employee")
    public ResponseEntity<ApiResponse> addEmployee(@RequestBody EmployeeDto employee) {
        return service.addEmployee(employee);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/employee/{id}")
    public ResponseEntity<ApiResponse> getEmployeeById(@PathVariable("id") String id) {
        return service.getEmployeeById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/employees")
    public ResponseEntity<ApiResponse> getAllEmployees(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                       @RequestParam(name = "size", defaultValue = "0") Integer size) {
        return service.getAllEmployees(page, size);
    }
}