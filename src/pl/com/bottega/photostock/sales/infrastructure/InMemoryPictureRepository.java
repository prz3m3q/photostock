package pl.com.bottega.photostock.sales.infrastructure;

import com.sun.org.apache.regexp.internal.RE;
import pl.com.bottega.photostock.sales.model.Money;
import pl.com.bottega.photostock.sales.model.Picture;
import pl.com.bottega.photostock.sales.model.PictureRepository;

import java.util.*;

public class InMemoryPictureRepository implements PictureRepository {

    private static final Map<Long, Picture> REPO = new HashMap<>();

    static {
        Set<String> tags = new HashSet<>();
        tags.add("kotki");

        Picture picture1 = new Picture(1L, tags, Money.valueOf(10));
        Picture picture2 = new Picture(2L, tags, Money.valueOf(5));
        Picture picture3 = new Picture(3L, tags, Money.valueOf(15));
        REPO.put(1L, picture1);
        REPO.put(2L, picture2);
        REPO.put(3L, picture3);
    }

    @Override
    public Picture get(Long number) {
        if (!REPO.containsKey(number)) {
            throw new IllegalArgumentException("No such object in repository");
        }
        return REPO.get(number);
    }

    @Override
    public void save(Picture picture) {
        REPO.put(picture.getNumber(), picture);
    }

    @Override
    public Optional<Picture> getOptional(Long number) {
        if (REPO.containsKey(number)) {
            return Optional.of(REPO.get(number));
        }
        return Optional.empty();
    }
}
