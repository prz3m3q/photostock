package pl.com.bottega.photostock.sales.model;

import java.util.LinkedList;
import java.util.List;

public class Client {
    private String name;
    private Address address;
    private ClientStatus status;
    private Money balance;
    private Money creditLimit;
    private List<Transaction> transaction = new LinkedList<>();

    public Client(String name, Address address, ClientStatus status, Money balance, Money creditLimit) {
        this.name = name;
        this.address = address;
        this.status = status;
        this.balance = balance;
        this.creditLimit = creditLimit;
        if (balance.gt(Money.ZERO)) {
            transaction.add(new Transaction(balance, "First charge"));
        }
    }

    public Client(String name, Address address) {
        this(name, address, ClientStatus.STANDARD, Money.ZERO, Money.ZERO);
    }

    public boolean canAfford(Money amount) {
        return balance.add(creditLimit).gte(amount);
    }

    public void charge(Money amount, String reason) {
        if (!canAfford(amount)) {
            throw new IllegalStateException("Not enought balance.");
        }
        balance = balance.substract(amount);
        transaction.add(new Transaction(amount.neg(), reason));
    }

    public void recharge(Money amount) {
        balance = balance.add(amount);
        transaction.add(new Transaction(amount, "Recharge account"));
    }
}
