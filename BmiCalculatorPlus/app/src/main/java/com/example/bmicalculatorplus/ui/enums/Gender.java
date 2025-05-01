package com.example.bmicalculatorplus.ui.enums;

import org.jetbrains.annotations.NotNull;

public enum Gender {
    MALE("Mężczyzna"),
    FEMALE("Kobieta");

    private final String displayName;

    Gender(String displayName) {
        this.displayName = displayName;
    }

    @NotNull
    @Override
    public String toString() {
        return displayName;
    }
}
