package pl.com.bottega.photostock.sales.model;

import java.util.HashMap;
import java.util.Map;

public enum ClientStatus {
    STANDARD(0), VIP(0), GOLD(10), PLATINUM(15), SILVER(5);

    private int discountPercent;

    ClientStatus(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public int discountPercent() {
        return discountPercent;
    }
}
