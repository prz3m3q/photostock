package pl.com.bottega.photostock.sales.model;

import java.util.*;

public class Offer {
    private Client owner;
    private List<Product> items;

    public Offer(Client owner, Collection<Product> items) {
        this.items = new LinkedList<>(items);
        this.owner = owner;
        this.items.sort(new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
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
        for (Product picture : items) {
            amount = amount.add(picture.calculatePrice(owner));
        }
        return amount;
    }

    public Collection<Product> getItems() {
        return Collections.unmodifiableCollection(items);
    }

    @Override
    public String toString() {
        return "\nOffer{" +
            "owner=" + owner +
            ", items=" + items +
            '}';
    }

    public Client getOwner() {
        return owner;
    }

    public Purchase purchase() {
        Money cost = getTotalCost();
        Purchase purchase = new Purchase(owner, getItems());
        owner.charge(cost, String.format("Purchase number %s", purchase.getNumber()));
        return purchase;
    }
}
