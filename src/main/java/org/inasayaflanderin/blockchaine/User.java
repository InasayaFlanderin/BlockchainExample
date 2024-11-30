package org.inasayaflanderin.blockchaine;

import lombok.Getter;

import java.util.Date;
import java.util.GregorianCalendar;

public class User {
    @Getter
    private final String name;
    @Getter
    private final Date birthDate;
    private Blockchain localBlockchain;
    @Getter
    private final long id;

    public User(String name, int day, int month, int year, Blockchain local) {
        this.name = name;
        var createDate = new GregorianCalendar();
        createDate.set(year, month, day);
        this.birthDate = createDate.getTime();
        local.addUser(this);
        this.localBlockchain = local;
        this.id = (System.currentTimeMillis() & name.hashCode()) | (createDate.getTimeInMillis() ^ 32) >> 3;
    }

    public Blockchain getLocal() {
        return localBlockchain;
    }

    public void setLocal(Blockchain local) {
        this.localBlockchain = local;
    }

    public void performTransaction(User received, String data, int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        var transaction = new Transaction(this, received, data, new Date(), amount);
        localBlockchain.addTransaction(transaction);
    }

    public boolean equals(Object o) {
        if(!(o instanceof User)) return false;

        return ((User) o).getId() == this.id;
    }
}
