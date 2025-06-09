package com.crudapi.crud.enums;

import lombok.Getter;

@Getter
public enum OrderSortField {

    CREATIONDATETIME("creationDateTime");

    private final String sortBy;

    OrderSortField(String sortBy) {
        this.sortBy = sortBy;
    }
}
