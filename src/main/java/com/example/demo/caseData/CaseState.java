package com.example.demo.caseData;

import java.util.Optional;

public enum CaseState {
    OPEN("open"),
    CLOSED("closed"),
    DISMISSED("dismissed");

    private final String value;

    CaseState(String value) {
        this.value = value;
    }

    public static Optional<CaseState> from(String value) {
        return switch (value.toUpperCase()) {
            case "OPEN" -> Optional.of(CaseState.OPEN);
            case "CLOSED" -> Optional.of(CaseState.CLOSED);
            case "DISMISSED" -> Optional.of(CaseState.DISMISSED);
            default -> throw new IllegalStateException("Unexpected value: " + value.toUpperCase());
        };
    }

    public String value() {
        return value;
    }
}
