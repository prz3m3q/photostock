package pl.com.bottega.photostock.sales.model;

import java.util.Optional;

public interface PictureRepository {
    Picture get(Long number);
    //zapis nowego lub aktualizacja obecnego
    void save(Picture picture);
    Optional<Picture> getOptional(Long number);
}
