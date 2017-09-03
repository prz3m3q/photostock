package pl.com.bottega.photostock.sales.model.repositiories;

import pl.com.bottega.photostock.sales.model.LightBox;

import java.util.List;

public interface LightBoxRepository {
    LightBox get(String number);
    List<LightBox> getByClientNumber(String clientNumber);
    void save(LightBox lightBox);
}
