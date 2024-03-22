package com.tedkvn.erp.enumEntity;

import lombok.Getter;

@Getter
public enum RoleEnum {
    ADMIN("Administrator privileges"), USER("Standard user privileges");

    private final String description;

    RoleEnum(String description) {
        this.description = description;
    }

    public String getName() {
        return name();
    }

}
