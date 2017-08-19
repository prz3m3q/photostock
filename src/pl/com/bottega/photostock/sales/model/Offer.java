package pl.com.bottega.photostock.sales.model;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class Offer {
    private Collection<Picture> items;

    public Offer(Collection<Picture> items) {
        this.items = new LinkedList<>(items);
    }

    public boolean sameAs(Offer offer, Money money) {
        return false;
    }

    public int getItemsCount() {
        return -1;
    }

    public Money getTotalCost() {
        return null;
    }

    public Collection<Picture> getItems() {
        return Collections.unmodifiableCollection(items);
    }

    public void setItems(Collection<Picture> items) {
        this.items = items;
    }
}
