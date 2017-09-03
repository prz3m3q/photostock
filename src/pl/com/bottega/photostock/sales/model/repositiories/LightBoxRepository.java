package pl.com.bottega.photostock.sales.model.repositiories;

import pl.com.bottega.photostock.sales.model.LightBox;

public interface LightBoxRepository {
    LightBox get(String number);
    void save(LightBox lightBox);
}
