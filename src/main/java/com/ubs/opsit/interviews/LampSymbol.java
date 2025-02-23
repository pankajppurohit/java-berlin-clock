package com.ubs.opsit.interviews;

/**
 * @author Pankaj
 */
public enum LampSymbol {
    OFF("O"),
    YELLOW("Y"),
    RED("R");

    private final String lampValue;

    // Constructor to set the lamp value
    LampSymbol(String lampValue) {
        this.lampValue = lampValue;
    }

    // Method to get the string representation for the lamp symbol
    public String getLampValue() {
        return this.lampValue;
    }

    // Static method to return the lamp symbol as a string based on the enum
    public static String getLampSymbol(LampSymbol symbol) {
        return symbol.getLampValue();
    }
}
