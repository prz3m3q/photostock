package pl.com.bottega.photostock.sales.model;

import pl.com.bottega.photostock.sales.exception.ProductNotAvalibleException;

public interface Product {
    Money calculatePrice(Client client);

    boolean isAvalible();

    void reservedPer(Client client);

    void unreservedPer(Client client);

    void soldPer(Client client);

    Long getNumber();

    default void ensureAvailable() {
        if (!isAvalible()) {
            throw new ProductNotAvalibleException(this);
        }
    }

    Money getPrice();

    boolean getActive();

    String getReservedByNumber();

    String getOwnerNumber();
}
