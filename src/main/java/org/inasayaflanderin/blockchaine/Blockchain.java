package org.inasayaflanderin.blockchaine;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class Blockchain {
    private final List<Block> blockList;
    private final List<User> notifiedUsers;

    public Blockchain() {
        blockList = new LinkedList<>();
        notifiedUsers = new LinkedList<>();
    }

    public void addUser(User user) {
        notifiedUsers.add(user);
    }

    public void addTransaction(Transaction transaction) {
        Block block;
        if (blockList.isEmpty()) {
            block = new Block(0, transaction);
        } else {
            block = new Block(blockList.getLast().getHash(), transaction);
        }

        if(checkNonce(transaction.getPerformed())) {
            blockList.add(block);
            notifiedUsers.forEach(user -> user.setLocal(this));
        } else {
            System.out.println("Transaction cannot performed: " + transaction);
        }
    }

    public boolean checkNonce(User performed) {
        return notifiedUsers.stream().filter(user -> !user.equals(performed)).allMatch(user -> user.getLocal().equals(this));
    }

    public boolean equals(Object o) {
        if(!(o instanceof Blockchain)) return false;

        return ((Blockchain) o).blockList.equals(this.blockList) && IntStream.range(1, blockList.size())
                .allMatch(i -> blockList.get(i).getPreviousHash() == blockList.get(i - 1).getHash());
    }

    public String toString() {
        return blockList.toString();
    }
}
