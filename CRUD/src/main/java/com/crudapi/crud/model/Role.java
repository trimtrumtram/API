package com.crudapi.crud.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    ADMIN("Administrator", "Администратор"),
    MANAGER("Manager", "Менеджер");

    private final String englishDescription;
    private final String russianDescription;
}
