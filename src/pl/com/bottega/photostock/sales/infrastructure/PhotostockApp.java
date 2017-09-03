package pl.com.bottega.photostock.sales.infrastructure;

import pl.com.bottega.photostock.sales.application.LightBoxManagement;
import pl.com.bottega.photostock.sales.application.ProductsCatalog;
import pl.com.bottega.photostock.sales.infrastructure.repositories.InMemoryClientRepository;
import pl.com.bottega.photostock.sales.infrastructure.repositories.InMemoryLightBoxRepository;
import pl.com.bottega.photostock.sales.infrastructure.repositories.InMemoryProductRepository;
import pl.com.bottega.photostock.sales.infrastructure.repositories.InMemoryReservationRepository;
import pl.com.bottega.photostock.sales.model.repositiories.ClientRepository;
import pl.com.bottega.photostock.sales.model.repositiories.LightBoxRepository;
import pl.com.bottega.photostock.sales.model.repositiories.ProductRepository;
import pl.com.bottega.photostock.sales.model.repositiories.ReservationRepository;
import pl.com.bottega.photostock.sales.ui.*;

import java.util.Scanner;

public class PhotostockApp {

    public static void main(String[] args) {
        new PhotostockApp().start();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        LightBoxRepository lightBoxRepository = new InMemoryLightBoxRepository();
        ClientRepository clientRepository = new InMemoryClientRepository();
        ProductRepository productRepository = new InMemoryProductRepository();
        ReservationRepository reservationRepository = new InMemoryReservationRepository();

        LightBoxManagement lightBoxManagement = new LightBoxManagement(lightBoxRepository, clientRepository, productRepository, reservationRepository);
        LightBoxManagementScreen lightBoxManagementScreen = new LightBoxManagementScreen(scanner, lightBoxManagement);

        AutenticationMaganger autenticationMaganger = new AutenticationMaganger(clientRepository);
        ProductsCatalog productsCatalog = new ProductsCatalog(productRepository);
        SearchScreen searchScreen = new SearchScreen(scanner, autenticationMaganger, productsCatalog);
        AutenticationScreen autenticationScreen = new AutenticationScreen(scanner, autenticationMaganger);

        MainScreen mainScreen = new MainScreen(scanner, lightBoxManagementScreen, searchScreen);
        autenticationScreen.show();
        mainScreen.show();
    }

}
