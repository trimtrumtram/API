package com.crudapi.crud.service;

import com.crudapi.crud.dto.employee.CreateEmployeeDTO;
import com.crudapi.crud.dto.employee.EmployeeResponseDTO;
import com.crudapi.crud.dto.employee.UpdateEmployeeDTO;
import com.crudapi.crud.mapper.EmployeeMapper;
import com.crudapi.crud.model.Employee;
import com.crudapi.crud.repository.EmployeeRepository;
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

    private Employee findEmployee(Long id, UpdateEmployeeDTO dto) {
        Employee employee;
        if(id != null) {
            return employee = employeeRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Employee with id " + id + " not found"));
        }
        if(dto.getEmail() != null) {
            return employee = employeeRepository.findEmployeeByEmail(dto.getEmail())
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
}
