package pl.com.bottega.photostock.sales.model;

import java.util.Collection;
import java.util.LinkedList;

public class LightBox {
    private String name;
    private Client owner;
    private Collection<Picture> items = new LinkedList<>();

    public LightBox(Client owner) {
        this.owner = owner;
    }

    public void add(Picture picture) {
    }

    public void remove(Picture picture) {
    }
}
