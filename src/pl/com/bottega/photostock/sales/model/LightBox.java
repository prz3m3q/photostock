package pl.com.bottega.photostock.sales.model;

import pl.com.bottega.photostock.sales.exception.ProductNotAvalibleException;

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

    public void add(Product product) {
        if (items.contains(product)) {
            throw new IllegalStateException("Product already added");
        }
        product.ensureAvailable();
        items.add(product);
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
