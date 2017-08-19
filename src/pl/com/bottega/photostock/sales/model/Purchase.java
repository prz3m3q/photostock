package pl.com.bottega.photostock.sales.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;

public class Purchase {
    private Collection<Picture> items;
    private Client buyer;
    private LocalDateTime purchaseDate = LocalDateTime.now();

    public Purchase(Collection<Picture> items, Client buyer) {
        this.items = new LinkedList<>(items);
        this.buyer = buyer;
    }
}
