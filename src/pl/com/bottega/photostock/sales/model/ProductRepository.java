package pl.com.bottega.photostock.sales.model;

import java.util.Optional;

public interface ProductRepository {
    Product get(Long number);
    //zapis nowego lub aktualizacja obecnego
    void save(Product picture);
    Optional<Product> getOptional(Long number);
}
