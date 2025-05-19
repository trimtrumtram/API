package com.crudapi.crud.repository;

import com.crudapi.crud.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    boolean existsEmployeeByEmail(String Email);

    Optional<Employee> findEmployeeByEmail(String Email);

    @Query(value = "SELECT * FROM employee WHERE " +
            "(:firstNameFilter IS NULL OR LOWER(first_name) LIKE LOWER(CONCAT('%', :fristNameFilter, '%'))) AND " +
            "(:lastNameFilter IS NULL OR LOWER(last_name) LIKE LOWER(CONCAT('%', :lastNameFilter, '%'))) AND " +
            "(:roleFilter IS NULL OR LOWER(role) LIKE LOWER(CONCAT('%', :roleFilter, '%'))) AND " +
            "(:emailFilter IS NULL OR LOWER(email) LIKE LOWER(CONCAT('%', :emailFilter, '%')))",
            countQuery ="SELECT COUNT (*) FROM employee WHERE " +
                        "(:firstNameFilter IS NULL OR LOWER(first_name) LIKE LOWER(CONCAT('%', :fristNameFilter, '%'))) AND " +
                        "(:lastNameFilter IS NULL OR LOWER(last_name) LIKE LOWER(CONCAT('%', :lastNameFilter, '%'))) AND " +
                        "(:roleFilter IS NULL OR LOWER(role) LIKE LOWER(CONCAT('%', :roleFilter, '%'))) AND " +
                        "(:emailFilter IS NULL OR LOWER(email) LIKE LOWER(CONCAT('%', :emailFilter, '%')))",
    nativeQuery = true)
    Page<Employee> getByFilter (
            @Param("firstNameFilter") String firstNameFilter,
            @Param("lastNameFilter") String lastNameFilter,
            @Param("roleFilter") String roleFilter,
            @Param("emailFilter") String emailFilter,
            Pageable pageable
    );
}
