package pl.com.bottega.photostock.sales.model;

public class Money {
    public static final String DEFAULT_CURRENCY = "CREDIT";
    public static final Money ZERO = new Money();
    private Long cents;
    private String currency;

    private Money() {
        this.cents = 0L;
        this.currency = DEFAULT_CURRENCY;
    }

    private Money(Integer value, String currency) {
        this.cents = value * 100L;
        this.currency = currency;
    }

    public static Money valueOf(Integer value) {
        return new Money(value, DEFAULT_CURRENCY);
    }
}
