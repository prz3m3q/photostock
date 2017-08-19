package pl.com.bottega.photostock.sales.ui;

import pl.com.bottega.photostock.sales.model.*;

import java.util.HashSet;
import java.util.Set;

public class ConsoleApp {
    public static void main(String[] args) {
        Set<String> tags = new HashSet<>();
        tags.add("kotki");
        Picture picture1 = new Picture(1L, tags, Money.valueOf(10));
        Picture picture2 = new Picture(2L, tags, Money.valueOf(5));
        Picture picture3 = new Picture(3L, tags, Money.valueOf(15));
        Client client = new Client("Ja Nowak", new Address("ul. Północna 11", "Poland", "Lublin", "20-222"));
        Reservation reservation = new Reservation(client);

        reservation.add(picture1);
        reservation.add(picture2);
        reservation.add(picture3);

        reservation.add(picture1);
        reservation.add(picture2);
        reservation.add(picture3);

        System.out.println(String.format("W rezerwacji jest %d produktów", reservation.getItemsCount()));

        Offer offer = reservation.generateOffer();
        Money cost = offer.getTotalCost();
        if (client.canAfford(cost)) {
            Purchase purchase = new Purchase(client, offer.getItems());
            client.charge(cost, String.format("Zakup oferty %s", purchase));
            System.out.println(String.format("Ilość zakupionych zdjęć: %d", offer.getItemsCount()));
            System.out.println(String.format("Całkowity koszt: %s", cost));
        } else {
            System.out.println("Sorry! Nie stać Cię ;(");
        }
    }
}
