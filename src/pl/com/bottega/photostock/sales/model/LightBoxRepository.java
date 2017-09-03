package pl.com.bottega.photostock.sales.model;

public interface LightBoxRepository {
    LightBox get(String number);
    void save(LightBox lightBox);
}
