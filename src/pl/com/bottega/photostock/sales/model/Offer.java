package pl.com.bottega.photostock.sales.model;

import java.util.*;

public class Offer {
    private Client owner;
    private List<Picture> items;

    public Offer(Client owner, Collection<Picture> items) {
        this.items = new LinkedList<>(items);
        this.owner = owner;
        this.items.sort(new Comparator<Picture>() {
            @Override
            public int compare(Picture p1, Picture p2) {
                return p2.calculatePrice(Offer.this.owner).compareTo(p1.calculatePrice(Offer.this.owner));
            }
        });
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

    @Override
    public String toString() {
        return "\nOffer{" +
            "owner=" + owner +
            ", items=" + items +
            '}';
    }
}
