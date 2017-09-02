package pl.com.bottega.photostock.sales.model;

import java.util.*;
import java.util.List;

public class LightBox {
    private String name;
    private Client owner;
    private List<Product> items = new LinkedList<>();

    public LightBox(Client owner, String name) {
        this.name = name;
        this.owner = owner;
    }

    public void add(Product picture) {
        if (items.contains(picture)) {
            throw new IllegalStateException("Product already added");
        }
        if (!picture.isAvalible()) {
            throw new IllegalArgumentException("Product is not avalible");
        }
        items.add(picture);
    }

    public void remove(Product picture) {
        if (!items.remove(picture)) {
            throw new IllegalStateException("Product is not part of this lightbox.");
        }
    }

    public Client getClient() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public List<Product> getItems() {
        return Collections.unmodifiableList(items);
    }
}
