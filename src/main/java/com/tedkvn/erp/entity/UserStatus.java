package com.tedkvn.erp.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum UserStatus {
    ACTIVE("Fully functional user account."), INACTIVE("Disabled user account"),
    PENDING_ACTIVATION("Created user awaiting activation"),
    LOCKED("Temporarily locked user account");

    private final String description;

    UserStatus(String description) {
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
