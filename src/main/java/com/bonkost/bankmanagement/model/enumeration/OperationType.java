package com.bonkost.bankmanagement.model.enumeration;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author Vladimir Luchnikov
 */

public enum OperationType {

    DEPOSIT(0), WITHDRAW(1);

    private final int number;

    OperationType(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public static OperationType valueOf(int value) {
        return Arrays.stream(values())
                .filter(type -> type.number == value)
                .findFirst()
                .orElse(null);
    }
}
