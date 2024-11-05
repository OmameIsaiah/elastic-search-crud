package com.elastic.search.service.impl;

import com.elastic.search.dto.ApiResponse;
import com.elastic.search.dto.EmployeeDto;
import com.elastic.search.model.Employee;
import com.elastic.search.repository.EmployeeRepository;
import com.elastic.search.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repo;

    @Override
    public ResponseEntity<ApiResponse> addEmployee(EmployeeDto employee) {
        Employee employee1 = new Employee();
        employee1.setId(UUID.randomUUID().toString());
        employee1.setFirstName(employee.getFirstName());
        employee1.setLastName(employee.getLastName());
        employee1.setSalary(employee.getSalary());
        employee1.setDateCreated(LocalDateTime.now());
        Employee employee2 = repo.save(employee1);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse("Success",
                        HttpStatus.CREATED.toString(),
                        employee2));
    }

    @Override
    public ResponseEntity<ApiResponse> getEmployeeById(String id) {
        Optional<Employee> result = repo.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse("Success",
                        HttpStatus.OK.toString(),
                        result.isPresent() ? result.get() : null));
    }

    @Override
    public ResponseEntity<ApiResponse> getAllEmployees(Integer page, Integer size) {
        Page<Employee> result = repo.findAll(PageRequest.of(page, size, Sort.by("dateCreated").descending()));

        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse("Success",
                            HttpStatus.OK.toString(),
                            result.getContent(),
                            result.getPageable()));
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse("Success",
                        HttpStatus.OK.toString(),
                        new ArrayList<>(),
                        null));
    }

}