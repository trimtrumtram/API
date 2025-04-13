package com.teammanager.employeeHub.service.employee;

import com.teammanager.employeeHub.dto.employee.CreateEmployeeDTO;
import com.teammanager.employeeHub.dto.employee.EmployeeResponseDTO;
import com.teammanager.employeeHub.dto.employee.UpdateEmployeeDTO;
import com.teammanager.employeeHub.model.employee.Employee;
import com.teammanager.employeeHub.model.employee.Role;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final List<Employee> employees = new ArrayList<>();
    private Long nextId = 1L;

    public EmployeeResponseDTO createEmployee(CreateEmployeeDTO dto) {

        Employee employee = Employee.builder()
                .id(nextId++)
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(dto.getRole())
                .build();

        employee.createFromDTO(dto);

        employees.add(employee);
        return mapToResponseDTO(employee);
    }

    public EmployeeResponseDTO getEmployeeById(Long id) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .map(this::mapToResponseDTO)
                .orElseThrow(() -> new IllegalArgumentException("Employee with id " + id + " not found"));
    }

    public EmployeeResponseDTO updateEmployee(Long id, UpdateEmployeeDTO dto) {
        Employee employee = employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Employee with id " + id + " not found"));

       employee.createFromDTO(dto);

        return mapToResponseDTO(employee);
    }

    public void deleteEmployee(Long id) {
        Employee employee = employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Employee with id " + id + " not found"));
        employees.remove(employee);
    }

    public List<EmployeeResponseDTO> getAllEmployees(String firstName, String lastName, String email, String password, Role role, String sortBy) {
        List<Employee> filtered = new ArrayList<>(employees.stream()
                .filter(e -> firstName == null || e.getFirstName().equalsIgnoreCase(firstName))
                .filter(e -> lastName == null || e.getLastName().equalsIgnoreCase(lastName))
                .filter(e -> email == null || e.getEmail().equalsIgnoreCase(email))
                .filter(e -> role == null || e.getRole() == role)
                .toList());
        if("firstName".equals(sortBy)) {
            filtered.sort(Comparator.comparing(Employee::getFirstName));
        } else if ("role".equals(sortBy)) {
            filtered.sort(Comparator.comparing(Employee::getRole));
        }
        return filtered.stream().map(this::mapToResponseDTO).collect(Collectors.toList());
    }

    private EmployeeResponseDTO mapToResponseDTO(Employee employee) {
        return EmployeeResponseDTO.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .role(employee.getRole())
                .build();
    }
}
