package pl.com.bottega.photostock.sales.model;

import java.util.Map;

public class CurrencyConverter {
    private Map<String, Double> exchangeRates;
    private String mainCurrency;

    public CurrencyConverter(String mainCurrency, Map<String, Double> exchangeRates) {
        this.mainCurrency = mainCurrency;
        this.exchangeRates = exchangeRates;
    }

    public Money convert(Money amount) {
        if(mainCurrency.equals(amount.currency()))
            return amount;
        return amount.convert(mainCurrency, exRate(amount.currency()));
    }

    private Double exRate(String currency) {
        if(!exchangeRates.containsKey(currency))
            throw new IllegalArgumentException("No ex rate for " + currency);
        return exchangeRates.get(currency);
    }

    public Money convert(Money amount, String targetCurrency) {
        if(targetCurrency.equals(mainCurrency))
            return convert(amount);
        if(amount.currency().equals(mainCurrency))
            return amount.convert(targetCurrency, 1/exRate(targetCurrency));
        return convert(convert(amount), targetCurrency);
    }
}
