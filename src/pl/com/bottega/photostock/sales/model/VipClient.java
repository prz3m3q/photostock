package pl.com.bottega.photostock.sales.model;

public class VipClient extends Client {
    protected Money creditLimit;

    public VipClient(String name, Address address, ClientStatus status, Money balance, Money creditLimit) {
        super(name, address, status, balance);
        this.creditLimit = creditLimit;
    }

    public VipClient(String name, Address address) {
        this(name, address, ClientStatus.VIP, Money.ZERO, Money.ZERO);
    }

    @Override
    public boolean canAfford(Money amount) {
        return balance().add(creditLimit).gte(amount);
    }
}
