package pl.com.bottega.photostock.sales.model;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class Offer {
    private Client owner;
    private Collection<Picture> items;

    public Offer(Client owner, Collection<Picture> items) {
        this.items = new LinkedList<>(items);
        this.owner = owner;
    }

    public boolean sameAs(Offer offer, Money money) {
        return false;
    }

    public int getItemsCount() {
        return items.size();
    }

    public Money getTotalCost() {
        Money amount = Money.ZERO;
        for (Picture picture : items) {
            amount.add(picture.calculatePrice(owner));
        }
        return amount;
    }

    public Collection<Picture> getItems() {
        return Collections.unmodifiableCollection(items);
    }

    public void setItems(Collection<Picture> items) {
        this.items = items;
    }
}
