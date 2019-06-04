package pl.leman.demoapp.domain;

import java.util.Objects;

public class Price {
    private final String amount;
    private final String currency;

    public Price(String amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return amount == price.amount &&
                Objects.equals(currency, price.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

    @Override
    public String toString() {
        return "Price{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }
}
