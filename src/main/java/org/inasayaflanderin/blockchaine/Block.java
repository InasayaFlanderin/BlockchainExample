package org.inasayaflanderin.blockchaine;

import lombok.Getter;

public class Block {
    @Getter
    private final int previousHash;
    @Getter
    private final int hash;
    private final Transaction transaction;

    public Block(int previousHash, Transaction transaction) {
        this.previousHash = previousHash;
        this.transaction = transaction;
        this.hash = calculateHash(transaction, previousHash);
    }

    private int calculateHash(Transaction transaction, int previousHash) {
        int rawHash = transaction.hashCode();
        rawHash |= 3;
        rawHash ^= previousHash;
        rawHash <<= 3 * transaction.hashCode() + previousHash;
        rawHash -= 17;
        rawHash *= 31;

        return rawHash;
    }

    public String toString() {
        return "Block{" +
                "previousHash=" + previousHash +
                ", hash=" + hash +
                ", transaction=" + transaction.getPerformed().getId() + " -> " + transaction.getReceived().getId() + ": " + transaction.getData() + " amount: " + transaction.getAmount() +
                '}';
    }
}
