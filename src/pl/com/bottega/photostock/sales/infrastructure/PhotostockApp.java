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
import pl.com.bottega.photostock.sales.ui.LightBoxManagementScreen;
import pl.com.bottega.photostock.sales.ui.MainScreen;
import pl.com.bottega.photostock.sales.ui.SearchScreen;

public class PhotostockApp {

    public static void main(String[] args) {
        new PhotostockApp().start();
    }

    public void start() {
        LightBoxRepository lightBoxRepository = new InMemoryLightBoxRepository();
        ClientRepository clientRepository = new InMemoryClientRepository();
        ProductRepository productRepository = new InMemoryProductRepository();
        ReservationRepository reservationRepository = new InMemoryReservationRepository();

        LightBoxManagement lightBoxManagement = new LightBoxManagement(lightBoxRepository, clientRepository, productRepository, reservationRepository);
        LightBoxManagementScreen lightBoxManagementScreen = new LightBoxManagementScreen(lightBoxManagement);

        ProductsCatalog productsCatalog = new ProductsCatalog(productRepository);
        SearchScreen searchScreen = new SearchScreen(productsCatalog);

        MainScreen mainScreen = new MainScreen(lightBoxManagementScreen, searchScreen);
        mainScreen.show();
    }

}
