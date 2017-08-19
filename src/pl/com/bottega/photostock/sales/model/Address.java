package pl.com.bottega.photostock.sales.model;

public class Address {
    private String line1, line2, country, city, postalcode;

    public Address(String line1, String line2, String country, String city, String postalcode) {
        this.line1 = line1;
        this.line2 = line2;
        this.country = country;
        this.city = city;
        this.postalcode = postalcode;
    }

    public Address(String line1, String country, String city, String postalcode) {
        this(line1, null, country, city, postalcode);
    }
}
