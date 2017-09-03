package pl.com.bottega.photostock.sales.model;

import pl.com.bottega.photostock.sales.exception.ProductNotAvalibleException;

import java.util.*;
import java.util.List;

public class LightBox {
    private String name;
    private Client owner;
    private String number;
    private List<Picture> items = new LinkedList<>();

    public LightBox(Client owner, String name) {
        this.name = name;
        this.owner = owner;
        this.number = UUID.randomUUID().toString();
    }

    public void add(Picture picture) {
        if (items.contains(picture)) {
            throw new IllegalStateException("Product already added");
        }
        picture.ensureAvailable();
        items.add(picture);
    }

    public void remove(Picture picture) {
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

    public List<Picture> getItems() {
        return Collections.unmodifiableList(items);
    }

    public String getNumber() {
        return number;
    }

    public Picture getPictureByNumber(Long number) {
        for (Picture picture : items) {
            if (number == picture.getNumber()) {
                return picture;
            }
        }
        throw new IllegalStateException("Product is not part of this lightbox.");
    }

    public List<Picture> getPictures(Set<Long> picturesNumbers) {
        List<Picture> findedPictures = new LinkedList<>();
        for (Picture picture : items) {
            if (picturesNumbers.contains(picture.getNumber())) {
                findedPictures.add(picture);
            }
        }
        return findedPictures;
    }

    public boolean hasClientNumber(String clientNumber) {
        return clientNumber.equals(owner.getNumber());
    }
}
