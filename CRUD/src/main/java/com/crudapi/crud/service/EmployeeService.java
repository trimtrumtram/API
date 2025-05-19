package com.crudapi.crud.service;

import com.crudapi.crud.dto.employee.CreateEmployeeDTO;
import com.crudapi.crud.dto.employee.EmployeeResponseDTO;
import com.crudapi.crud.dto.employee.UpdateEmployeeDTO;
import com.crudapi.crud.mapper.EmployeeMapper;
import com.crudapi.crud.model.Employee;
import com.crudapi.crud.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeRepository employeeRepository, BCryptPasswordEncoder passwordEncoder, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
        this.employeeMapper = employeeMapper;
    }

    public EmployeeResponseDTO createEmployee(CreateEmployeeDTO dto) {
        if(employeeRepository.existsEmployeeByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        Employee employee = employeeMapper.mapToEntity(dto);
        employee.setPassword(passwordEncoder.encode(dto.getPassword()));
        Employee savedEmployee = employeeRepository.save(employee);

        return employeeMapper.mapToResponseEmployeeDTO(savedEmployee);
    }

    public EmployeeResponseDTO updateEmployee(Long id, UpdateEmployeeDTO dto) {
        Employee employee = findEmployee(id, dto);
        validateEmail(employee, dto.getEmail());
        Employee updatedEmployee = employeeRepository.save(employee);
        return employeeMapper.mapToResponseEmployeeDTO(updatedEmployee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public Page<Employee> getAllEmployees (
            String firstNameFilter, String lastNameFilter, String roleFilter, String emailFilter,
            int page, int size, String sortBy, String sortDirection) {
        try {
            String firstName = (firstNameFilter != null && !firstNameFilter.trim().isEmpty()) ? firstNameFilter : null;
            String lastName = (lastNameFilter != null && !lastNameFilter.trim().isEmpty()) ? lastNameFilter : null;
            String role = (roleFilter != null && !roleFilter.trim().isEmpty()) ? roleFilter : null;
            String email = (emailFilter != null && !emailFilter.trim().isEmpty()) ? emailFilter : null;

            String validSortBy = validateSortBy(sortBy);
            Sort.Direction direction = Sort.Direction.fromString(sortDirection.toUpperCase());

            Sort sort = Sort.by(direction, validSortBy);
            PageRequest pageRequest = PageRequest.of(page, size, sort);

            return employeeRepository.getByFilter(firstName, lastName, role, email, pageRequest);

        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid filter" + e.getMessage());
        }
    }

    private Employee findEmployee(Long id, UpdateEmployeeDTO dto) {
        if(id != null) {
            return employeeRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Employee with id " + id + " not found"));
        }
        if(dto.getEmail() != null) {
            return employeeRepository.findEmployeeByEmail(dto.getEmail())
                    .orElseThrow(() -> new IllegalArgumentException("Employee with email " + dto.getEmail() + " not found"));
        }
        else throw new IllegalArgumentException("Email or id is required");
    }

    private void validateEmail(Employee employee, String newEmail) {
        if(newEmail != null && !newEmail.isBlank() && !newEmail.equals(employee.getEmail())) {
            if(employeeRepository.existsEmployeeByEmail(newEmail)) {
                throw new IllegalArgumentException("Employee with email " + newEmail + " already exists");
            }
        }
    }

    private String validateSortBy(String sortBy) {
        if(sortBy != null && !sortBy.trim().isEmpty()) {
            return "lastName";
        }
        String lowerSortBy = sortBy.trim().toLowerCase();
        if(lowerSortBy.equals("firstName") || lowerSortBy.equals("lastName") ||
                lowerSortBy.equals("email") || lowerSortBy.equals("role")) {
            return lowerSortBy;
        }
        return "lastName";
    }
}
