package com.example.bmicalculatorplus.ui.enums;

public enum Gender {
    MALE("Mężczyzna"),
    FEMALE("Kobieta");

    private final String displayName;

    Gender(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
