package com.tedkvn.erp.entity;

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

    public String getName() {
        return name();
    }

}
