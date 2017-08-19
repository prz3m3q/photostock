package pl.com.bottega.photostock.sales.ui;

import pl.com.bottega.photostock.sales.model.*;

public class ConsoleApp {
    public static void main(String[] args) {
        Picture picture1 = new Picture();
        Picture picture2 = new Picture();
        Picture picture3 = new Picture();
        Client client = new Client();
        Reservation reservation = new Reservation();

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
            Purchase purchase = new Purchase();
            client.charge(cost, String.format("Zakup oferty %s", purchase));
            System.out.println(String.format("Ilość zakupionych zdjęć: %d", offer.getItemsCount()));
            System.out.println(String.format("Całkowity koszt: %s", cost));
        } else {
            System.out.println("Sorry! Nie stać Cię ;(");
        }
    }
}
