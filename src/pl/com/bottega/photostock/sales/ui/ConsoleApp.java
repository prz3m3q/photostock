package pl.com.bottega.photostock.sales.ui;

import pl.com.bottega.photostock.sales.infrastructure.InMemoryProductRepository;
import pl.com.bottega.photostock.sales.model.*;

public class ConsoleApp {
    public static void main(String[] args) {
        ProductRepository pictureRepository = new InMemoryProductRepository();
        Product picture1 = pictureRepository.get(1L);
        Product picture2 = pictureRepository.get(2L);
        Product picture3 = pictureRepository.get(3L);
        Client client = new Client("Ja Nowak", new Address("ul. Północna 11", "Poland", "Lublin", "20-222"));
        client.recharge(Money.valueOf(1000000));
        Reservation reservation = new Reservation(client);

        LightBox lightBox = new LightBox(client, "kotki");
        lightBox.add(picture1);
        lightBox.add(picture2);
        lightBox.add(picture3);

        LightBoxPresenter lightBoxPresenter = new LightBoxPresenter();
        lightBoxPresenter.show(lightBox);

        reservation.add(picture1);
        reservation.add(picture2);
        lightBoxPresenter.show(lightBox);
        reservation.add(picture3);

        System.out.println(String.format("W rezerwacji jest %d produktów", reservation.getItemsCount()));

        Offer offer = reservation.generateOffer();

        System.out.println(offer);
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
