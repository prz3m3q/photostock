package pl.com.bottega.photostock.sales.model;

public class Money {
    public static final String DEFAULT_CURRENCY = "CREDIT";
    private Long cents;
    private String currency;

    public Money() {
        this.cents = 0L;
        this.currency = DEFAULT_CURRENCY;
    }

    public Money(Long value) {
        this.cents = 0L;
        this.currency = DEFAULT_CURRENCY;
    }
}
