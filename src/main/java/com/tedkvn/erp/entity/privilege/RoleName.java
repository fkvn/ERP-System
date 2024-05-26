package com.tedkvn.erp.entity.privilege;

import lombok.Getter;

@Getter
public enum RoleName {
    ADMIN("Administrator privileges"), USER("Standard user privileges");

    private final String description;

    RoleName(String description) {
        this.description = description;
    }

    public String getName() {
        return name();
    }

}
