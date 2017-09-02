package pl.com.bottega.photostock.sales.exception;

import pl.com.bottega.photostock.sales.model.Product;

public class ProductNotAvalibleException extends RuntimeException {
    public ProductNotAvalibleException(Product product) {
        super(String.format("Product %s is not avalible.", product.getNumber()));
    }
}
