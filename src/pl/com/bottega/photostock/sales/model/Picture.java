package pl.com.bottega.photostock.sales.model;

import java.util.HashSet;
import java.util.Set;

public class Picture {
    private Long number;
    private Set<String> tags;
    private Money price;
    private boolean active;

    public Picture(Long number, Set<String> tags, Money price, boolean active) {
        this.number = number;
        this.tags = new HashSet<>(tags);
        this.price = price;
        this.active = active;
    }

    public Picture(Long number, Set<String> tags, Money price) {
        this(number, tags, price, true);
    }

    public Money calculatePrice(Client client) {
        return null;
    }

    public boolean isAvalible() {
        return false;
    }

    public void reservedPer(Client client) {
    }

    public void unreservedPer(Client client) {
    }

    public void soldPer(Client client) {
    }
}
