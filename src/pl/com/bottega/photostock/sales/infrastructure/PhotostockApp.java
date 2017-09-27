package pl.com.bottega.photostock.sales.infrastructure;

import pl.com.bottega.photostock.sales.application.LightBoxManagement;
import pl.com.bottega.photostock.sales.application.ProductsCatalog;
import pl.com.bottega.photostock.sales.application.PurachaseProcess;
import pl.com.bottega.photostock.sales.infrastructure.repositories.*;
import pl.com.bottega.photostock.sales.model.repositiories.*;
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
        CSVProductRepository productRepository = new CSVProductRepository("/home/przemek/products.csv", clientRepository);
        ReservationRepository reservationRepository = new InMemoryReservationRepository();
        PurchaseRepository purchaseRepository = new InMemoryPurchaseRepository();

        AutenticationMaganger autenticationMaganger = new AutenticationMaganger(clientRepository);
        LightBoxManagement lightBoxManagement = new LightBoxManagement(lightBoxRepository, clientRepository, productRepository, reservationRepository);
        AddProductToLightBoxScreen addProductToLightBoxScreen = new AddProductToLightBoxScreen(lightBoxManagement, scanner);
        PurachaseProcess purachaseProcess = new PurachaseProcess(clientRepository, reservationRepository, productRepository, purchaseRepository);
        PurchaseLightBoxScreen purchaseLightBoxScreen = new PurchaseLightBoxScreen(lightBoxManagement, scanner, purachaseProcess);
        LightBoxManagementScreen lightBoxManagementScreen = new LightBoxManagementScreen(scanner, lightBoxManagement, autenticationMaganger, addProductToLightBoxScreen, purchaseLightBoxScreen);

        ProductsCatalog productsCatalog = new ProductsCatalog(productRepository);
        SearchScreen searchScreen = new SearchScreen(scanner, autenticationMaganger, productsCatalog);
        AutenticationScreen autenticationScreen = new AutenticationScreen(scanner, autenticationMaganger);

        MainScreen mainScreen = new MainScreen(scanner, lightBoxManagementScreen, searchScreen);
        autenticationScreen.show();
        mainScreen.show();
    }

}
