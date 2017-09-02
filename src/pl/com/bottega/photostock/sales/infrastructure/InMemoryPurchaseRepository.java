package pl.com.bottega.photostock.sales.infrastructure;

import pl.com.bottega.photostock.sales.model.Purchase;
import pl.com.bottega.photostock.sales.model.PurchaseRepository;
import pl.com.bottega.photostock.sales.model.Reservation;
import pl.com.bottega.photostock.sales.model.ReservationRepository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryPurchaseRepository implements PurchaseRepository {

    private static final Map<String, Purchase> REPO = new HashMap<>();

    @Override
    public void save(Purchase purchase) {
        REPO.put(purchase.getNumber(), purchase);
    }

    @Override
    public Purchase get(String number) {
        if (!REPO.containsKey(number)) {
            throw new IllegalArgumentException(String.format("No purchase with number %s.", number));
        }
        return REPO.get(number);
    }
}
