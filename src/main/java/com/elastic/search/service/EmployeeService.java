package com.elastic.search.service;

import com.elastic.search.dto.ApiResponse;
import com.elastic.search.dto.EmployeeDto;
import org.springframework.http.ResponseEntity;


public interface EmployeeService {
    ResponseEntity<ApiResponse> addEmployee(EmployeeDto employee);

    ResponseEntity<ApiResponse> getEmployeeById(String id);

    ResponseEntity<ApiResponse> getAllEmployees(Integer page, Integer size);
}