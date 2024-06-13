package com.tedkvn.erp.entity.privilege;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum RoleName {
    ADMIN("Administrator privileges"), USER("Standard user privileges");

    private final String description;

    RoleName(String description) {
        this.description = description;
    }

    @JsonValue
    public String toString() {
        return getName() + "|" + getDescription();
    }

    public String getName() {
        return name();
    }
}
