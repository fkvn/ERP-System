package com.tedkvn.erp.entity.privilege;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum AuthorityName {
    ACCESS_COMPANY_DATA("Read Company Data"), WRITE_COMPANY_DATA("Write Company Data");
    private final String description;

    AuthorityName(String description) {
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
