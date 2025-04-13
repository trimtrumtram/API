package com.teammanager.employeeHub.controller.employee;

import com.teammanager.employeeHub.dto.employee.CreateEmployeeDTO;
import com.teammanager.employeeHub.dto.employee.EmployeeResponseDTO;
import com.teammanager.employeeHub.dto.employee.UpdateEmployeeDTO;
import com.teammanager.employeeHub.service.employee.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.teammanager.employeeHub.model.employee.Role;

import java.util.List;

@RequiredArgsConstructor
@RestController()
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/employee")
    public EmployeeResponseDTO createEmployee (@RequestBody CreateEmployeeDTO employeeDTO) {
        return employeeService.createEmployee(employeeDTO);
    }

    @GetMapping("/employee/{id}")
    public EmployeeResponseDTO getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/employee/{id}")
    public EmployeeResponseDTO updateEmployee(@PathVariable Long id, @RequestBody UpdateEmployeeDTO employeeDTO) {
        return employeeService.updateEmployee(id, employeeDTO);
    }

    @DeleteMapping("/employee/{id}")
    public boolean deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return true;
    }

    @GetMapping("/employee")
    public List<EmployeeResponseDTO> getAllEmployees(
            @RequestParam (required = false) String firstName,
            @RequestParam (required = false) String lastName,
            @RequestParam (required = false) String email,
            @RequestParam (required = false) String password,
            @RequestParam (required = false) Role role,
            @RequestParam (defaultValue = "id") String sortBy)
    {
        return employeeService.getAllEmployees(firstName, lastName, email, password, role, sortBy);
    }
}
