package org.inasayaflanderin.blockchaine;

import java.util.Date;

public record Transaction(User getPerformed, User getReceived, String getData, Date getDate, double getAmount) {
    public Transaction {
        if (getAmount < 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
    }
}
