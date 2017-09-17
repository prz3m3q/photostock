package pl.com.bottega.photostock.sales.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Picture extends AbstractProduct {
    private Set<String> tags;

    public Picture(Long number, Set<String> tags, Money price, boolean active) {
        super(price, active, number);
        this.tags = new HashSet<>(tags);
    }

    public Picture(Long number, Set<String> tags, Money price) {
        this(number, tags, price, true);
    }

    public Picture(Long nr, String[] tags, Money price, Client reservedBy, Client owner, boolean active) {
        super(nr, price, reservedBy, owner, active);
        this.tags = new HashSet<>(Arrays.asList(tags));
    }

    public Set<String> getTags() {
        return tags;
    }

    public boolean nasTags(Set<String> tags) {
        return this.tags.containsAll(tags);
    }
}
