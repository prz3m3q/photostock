package pl.com.bottega.photostock.sales.model;

import java.util.HashSet;
import java.util.Set;

public class Picture {
    private Long number;
    private Set<String> tags;
    private Money price;
    private boolean active;
    private Client reservedBy, owner;

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
        return price;
    }

    public boolean isAvalible() {
        return active && reservedBy == null;
    }

    public void reservedPer(Client client) {
        if (!isAvalible()) {
            throw new IllegalStateException("Product is not avalible");
        }
        reservedBy = client;
    }

    public void unreservedPer(Client client) {
        if (owner != null) {
            throw new IllegalStateException(String.format("Product is already purchased"));
        }
        checkReservation(client);
        reservedBy = null;
    }

    private void checkReservation(Client client) {
        if (reservedBy != null || !reservedBy.equals(client)) {
            throw new IllegalStateException(String.format("Product is not reserved by %s", client));
        }
    }

    public void soldPer(Client client) {
        checkReservation(client);
        owner = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Picture picture = (Picture) o;

        return number.equals(picture.number);
    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }

    public Long getNumber() {
        return number;
    }
}
