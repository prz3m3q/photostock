package pl.com.bottega.photostock.sales.model;

import pl.com.bottega.photostock.sales.exception.ProductNotAvalibleException;

import java.util.Collection;
import java.util.LinkedList;

public class Reservation {
    private Client owner;
    private Collection<Product> items = new LinkedList<>();

    public Reservation(Client owner) {
        this.owner = owner;
    }

    public void add(Product product) {
        product.ensureAvailable();
        items.add(product);
        product.reservedPer(owner);
    }

    public void remove(Product picture) {
        if (items.remove(picture)) {
            picture.unreservedPer(owner);
        } else {
            throw new IllegalStateException("Product is not part of this reservation.");
        }
    }

    public Offer generateOffer() {
        return new Offer(owner, items);
    }

    public int getItemsCount() {
        return items.size();
    }
}
