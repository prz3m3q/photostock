package pl.com.bottega.photostock.sales.ui;

import pl.com.bottega.photostock.sales.application.LightBoxManagement;
import pl.com.bottega.photostock.sales.application.PurachaseProcess;
import pl.com.bottega.photostock.sales.application.PurchaseStatus;
import pl.com.bottega.photostock.sales.model.*;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class PurchaseLightBoxScreen {
    LightBoxManagement lightBoxManagement;
    Scanner scanner;
    PurachaseProcess purachaseProcess;

    public PurchaseLightBoxScreen(LightBoxManagement lightBoxManagement, Scanner scanner, PurachaseProcess purachaseProcess) {
        this.lightBoxManagement = lightBoxManagement;
        this.scanner = scanner;
        this.purachaseProcess = purachaseProcess;
    }

    public void show(LightBox lightBox) {
//        Set<Long> numbers = new HashSet<>();
//        for (Product p : lightBox.getItems()) {
//            numbers.add(p.getNumber());
//        }

        Set<Long> numbers = lightBox.getItems().stream().map(product -> product.getNumber()).collect(Collectors.toSet());

        String reservationNumber = purachaseProcess.createReservation(lightBox.getClient().getNumber());
        lightBoxManagement.reserve(lightBox.getNumber(), numbers, reservationNumber);
        Offer offer = purachaseProcess.calculateOffer(reservationNumber);
        System.out.println(String.format("Cena wybranych zdjęć: %s", offer.getTotalCost()));
        System.out.print("Czy chcesz dodkonać zakupu (t/n):");
        String decision = scanner.nextLine();
        if (decision.equals("t")) {
            PurchaseStatus purchaseStatus = purachaseProcess.confirm(reservationNumber, offer);
            if (purchaseStatus.equals(PurchaseStatus.SUCCESS)) {
                System.out.println("Dziękujemy za uddane zakupy!");
            } else if (purchaseStatus.equals(PurchaseStatus.NOT_ENOUGHT_FOUNDS)) {
                System.out.println("Nie masz wystarczających środkow.");
            } else {
                System.out.println("Spóźniłeś się oferta wygasła.");
            }
        } else {
            System.out.println("Przykro mi.");
        }
    }
}
