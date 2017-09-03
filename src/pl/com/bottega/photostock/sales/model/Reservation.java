package pl.com.bottega.photostock.sales.model;

import pl.com.bottega.photostock.sales.exception.ProductNotAvalibleException;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Reservation {
    private Client owner;
    private String number;
    private Collection<Product> items = new LinkedList<>();

    public Reservation(Client owner) {
        this.owner = owner;
        this.number = UUID.randomUUID().toString();
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

    public String getNumber() {
        return number;
    }

    public void addPictures(List<Picture> pictures) {
        for (Picture picture : pictures) {
            picture.ensureAvailable();
        }
        items.addAll(pictures);
    }
}
