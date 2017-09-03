package pl.com.bottega.photostock.sales.model.repositiories;

import pl.com.bottega.photostock.sales.model.Purchase;

public interface PurchaseRepository {
    void save(Purchase purchase);
    Purchase get(String number);
}
