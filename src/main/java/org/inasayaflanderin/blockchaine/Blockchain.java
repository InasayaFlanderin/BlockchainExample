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

    //add new user and update all blockchain from other user
    public void addUser(User user) {
        notifiedUsers.add(user);
        notifiedUsers.stream().filter(users -> !users.equals(user)).forEach(users -> users.setLocal(this));
    }

    /*
    * Creating a new Block on transaction
    * Check nonce for all users except for who except it
    * if refused print message
    * else add and perform the transaction and update to all user
    * */
    public void addTransaction(Transaction transaction) {
        Block block = blockList.isEmpty() ? new Block(0, transaction) : new Block(blockList.getLast().getHash(), transaction);

        if(checkNonce(transaction.getPerformed())) {
            blockList.add(block);
            notifiedUsers.forEach(user -> user.setLocal(this));
        } else {
            System.out.println("Transaction cannot performed: " + transaction);
        }
    }

    //check nonce ( real logic is more complicated )
    public boolean checkNonce(User performed) {
        return notifiedUsers.stream().filter(user -> !user.equals(performed)).allMatch(user -> user.getLocal().equals(this));
    }

    public boolean equals(Object o) {
        if(!(o instanceof Blockchain)) return false;

        //check if the block is modified or not, then check if both blockchain is right
        return ((Blockchain) o).blockList.equals(this.blockList) && IntStream.range(1, blockList.size())
                .allMatch(i -> blockList.get(i).getPreviousHash() == blockList.get(i - 1).getHash());
    }

    public String toString() {
        return blockList.toString();
    }
}
