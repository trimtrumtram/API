package com.crudapi.crud.dto.employee;

import com.crudapi.crud.enums.SortDirection;
import com.crudapi.crud.enums.SortField;
import com.crudapi.crud.enums.Role;
import lombok.Data;

@Data
public class EmployeeFilterDTO {

    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private Integer page;
    private Integer size;
    private SortField sortField;
    private SortDirection sortDirection;
}
