package pl.com.bottega.photostock.sales.model;

import java.util.Collection;

public class Reservation {
    private Client owner;
    private Collection<Picture> items;

    public Reservation(Client owner) {
        this.owner = owner;
    }

    public void add(Picture picture) {
    }

    public void remove(Picture picture) {
    }

    public Offer generateOffer() {
        return null;
    }

    public int getItemsCount() {
        return -1;
    }
}
