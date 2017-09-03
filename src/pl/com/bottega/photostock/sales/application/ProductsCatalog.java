package pl.com.bottega.photostock.sales.application;

import pl.com.bottega.photostock.sales.model.Client;
import pl.com.bottega.photostock.sales.model.Money;
import pl.com.bottega.photostock.sales.model.Product;
import pl.com.bottega.photostock.sales.model.ProductRepository;

import java.util.List;
import java.util.Set;

public class ProductsCatalog {

    ProductRepository repository;

    public ProductsCatalog(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> find(Client client, Set<String> tags, Money from, Money to) {
        return repository.find(client, tags, from, to);
    }
}
