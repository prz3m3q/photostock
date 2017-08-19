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
    }

    public void remove(Picture picture) {
    }

    public Offer generateOffer() {
        return new Offer(items);
    }

    public int getItemsCount() {
        return -1;
    }
}
