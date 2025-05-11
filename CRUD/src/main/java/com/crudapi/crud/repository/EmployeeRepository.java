package com.crudapi.crud.repository;

import com.crudapi.crud.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    boolean existsEmployeeByEmail(String Email);

    Optional<Employee> findEmployeeByEmail(String Email);
}
