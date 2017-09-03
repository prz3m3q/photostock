package pl.com.bottega.photostock.sales.model.repositiories;

import pl.com.bottega.photostock.sales.model.Client;
import pl.com.bottega.photostock.sales.model.Money;
import pl.com.bottega.photostock.sales.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductRepository {
    Product get(Long number);
    //zapis nowego lub aktualizacja obecnego
    void save(Product picture);
    Optional<Product> getOptional(Long number);

    List<Product> find(Client client, Set<String> tags, Money from, Money to);
}
