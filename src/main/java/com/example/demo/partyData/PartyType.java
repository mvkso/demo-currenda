package com.example.demo.partyData;


import java.util.Optional;

public enum PartyType {
    PLAINTIFF("plaintiff"),
    DEFENDANT("defendant");

    private final String value;

    PartyType(String value) {
        this.value = value;
    }

    public static Optional<PartyType> from(String value) {
        return switch (value.toUpperCase()) {
            case "PLAINTIFF" -> Optional.of(PartyType.PLAINTIFF);
            case "DEFENDANT" -> Optional.of(PartyType.DEFENDANT);
            default -> throw new IllegalStateException("Unexpected value: " + value.toUpperCase());
        };
    }

    public String value() {
        return value;
    }
}
