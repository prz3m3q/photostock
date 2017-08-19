package pl.com.bottega.photostock.sales.model;

public class Offer {
    public boolean sameAs(Offer offer, Money money) {
        return false;
    }

    public int getItemsCount() {
        return -1;
    }

    public Money getTotalCost() {
        return null;
    }
}
