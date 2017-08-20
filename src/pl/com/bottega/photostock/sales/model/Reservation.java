package pl.com.bottega.photostock.sales.model;

import java.util.Collection;
import java.util.LinkedList;

public class Reservation {
    private Client owner;
    private Collection<Picture> items = new LinkedList<>();

    public Reservation(Client owner) {
        this.owner = owner;
    }

    public void add(Picture picture) {
        if (!picture.isAvalible()) {
            throw new IllegalStateException("Product is not avalible");
        }
        items.add(picture);
        picture.reservedPer(owner);
    }

    public void remove(Picture picture) {
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
