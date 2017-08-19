package pl.com.bottega.photostock.sales.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;

public class Purchase {
    private Collection<Picture> items;
    private Client buyer;
    private LocalDateTime purchaseDate = LocalDateTime.now();

    public Purchase(Client buyer, Collection<Picture> items) {
        this.items = new LinkedList<>(items);
        this.buyer = buyer;
    }
}
