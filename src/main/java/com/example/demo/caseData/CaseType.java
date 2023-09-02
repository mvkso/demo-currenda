package com.example.demo.caseData;

import java.util.Optional;

public enum CaseType {
    C("C"),
    Co("CO"),
    K("K"),
    W("W"),
    P("P");

    private final String value;

    CaseType(String value) {
        this.value = value;
    }

    public static Optional<CaseType> from(String value) {
        return switch (value.toUpperCase()) {
            case "C" -> Optional.of(CaseType.C);
            case "CO" -> Optional.of(CaseType.Co);
            case "K" -> Optional.of(CaseType.K);
            case "W" -> Optional.of(CaseType.W);
            case "P" -> Optional.of(CaseType.P);
            default -> throw new IllegalStateException("Unexpected value: " + value.toUpperCase());
        };
    }

    public String value() {
        return value;
    }

}
