package pl.com.bottega.photostock.sales.model;

import pl.com.bottega.photostock.sales.exception.ProductNotAvalibleException;

public abstract class AbstractProduct implements Product {
    protected Long number;
    protected Money price;
    protected boolean active;
    private Client reservedBy, owner;

    public AbstractProduct(Money price, Boolean active, Long number) {
        this.price = price;
        this.active = active;
        this.number = number;
    }

    @Override
    public Money calculatePrice(Client client) {
        return price.percent(100 - client.discountPercent());
    }

    @Override
    public boolean isAvalible() {
        return active && reservedBy == null;
    }

    @Override
    public void reservedPer(Client client) {
        ensureAvailable();
        reservedBy = client;
    }

    @Override
    public void unreservedPer(Client client) {
        if (owner != null) {
            throw new IllegalStateException(String.format("Product is already purchased"));
        }
        checkReservation(client);
        reservedBy = null;
    }

    private void checkReservation(Client client) {
        if (reservedBy == null || !reservedBy.equals(client)) {
            throw new IllegalStateException(String.format("Product is not reserved by %s", client));
        }
    }

    @Override
    public void soldPer(Client client) {
        checkReservation(client);
        owner = client;
    }

    public Money getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return number.equals(product.getNumber());
    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }

    @Override
    public Long getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "\nProduct{" +
            "number=" + number +
            ", price=" + price +
            '}';
    }
}
