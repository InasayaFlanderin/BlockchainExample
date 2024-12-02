package org.inasayaflanderin.blockchaine;

public class Main {
    public static void main(String[] args) {
        Blockchain bc = new Blockchain();
        //test code here
        //Initial user
        User alice = new User("Alice", 1, 1, 1998, bc);
        User bob = new User("Bob", 3, 12, 2004, bc);
        User charlie = new User("Charlie", 13, 11, 2007, bc);
        User dave = new User("Dave", 26, 3, 1980, bc);
        User eve = new User("Eve", 31, 12, 1999, bc);

        //Initial test
        alice.performTransaction(bob, "Test", 100);
        charlie.performTransaction(dave, "Test2", 320);
        eve.performTransaction(alice, "Test3", 50);

        //Blockchain initial state
        System.out.println(bc);

        //Append new transaction
        bob.performTransaction(charlie, "Test4", 200);
        dave.performTransaction(eve, "Test5", 1000);

        //Modified blockchain state
        System.out.println(bc);
        //Check if a local state is updated
        System.out.println(alice.getLocal().equals(bc));
    }
}