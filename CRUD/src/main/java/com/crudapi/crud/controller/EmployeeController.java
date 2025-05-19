package com.crudapi.crud.controller;

import com.crudapi.crud.dto.employee.CreateEmployeeDTO;
import com.crudapi.crud.dto.employee.EmployeeResponseDTO;
import com.crudapi.crud.dto.employee.UpdateEmployeeDTO;
import com.crudapi.crud.model.Employee;
import com.crudapi.crud.service.ClientService;
import com.crudapi.crud.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ClientService clientService;

    @PostMapping("/employee")
    public EmployeeResponseDTO createEmployee(@RequestBody CreateEmployeeDTO dto) {
        return employeeService.createEmployee(dto);
    }

    @PutMapping("/employee/{id}")
    public EmployeeResponseDTO updateEmployee(@PathVariable Long id, @RequestBody UpdateEmployeeDTO dto) {
        return employeeService.updateEmployee(id, dto);
    }

    @DeleteMapping("/employee/{id}")
    public boolean deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return true;
    }

    @GetMapping("/employee")
    public ResponseEntity<Page<Employee>> getAllEmployees(
            @RequestParam(required = false) String firstNameFilter,
            @RequestParam(required = false) String lastNameFilter,
            @RequestParam(required = false) String roleFilter,
            @RequestParam(required = false) String emailFilter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "lastName") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection) {
        Page<Employee> employees = employeeService.getAllEmployees(
                firstNameFilter, lastNameFilter, roleFilter, emailFilter, page, size, sortBy, sortDirection);
        return ResponseEntity.ok(employees);
    }

}
