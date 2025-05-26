package com.crudapi.crud;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum SortField {

    ID("id"),
    FIRSTNAME("firstName"),
    LASTNAME("lastName"),
    ROLE("role"),
    EMAIL("email");

    private final String sortBy;

    SortField(String sortBy) {
        this.sortBy = sortBy;
    }
}
