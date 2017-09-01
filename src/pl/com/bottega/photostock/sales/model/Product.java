package pl.com.bottega.photostock.sales.model;

public interface Product {
    Money calculatePrice(Client client);

    boolean isAvalible();

    void reservedPer(Client client);

    void unreservedPer(Client client);

    void soldPer(Client client);

    Long getNumber();
}
