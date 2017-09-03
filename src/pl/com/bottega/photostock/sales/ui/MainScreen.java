package pl.com.bottega.photostock.sales.ui;

import java.util.Scanner;

public class MainScreen {

    private Scanner scanner;
    private LightBoxManagementScreen lightBoxManagementScreen;
    private SearchScreen searchScreen;

    public MainScreen(Scanner scanner, LightBoxManagementScreen lightBoxManagementScreen, SearchScreen searchScreen) {
        this.scanner = scanner;
        this.lightBoxManagementScreen = lightBoxManagementScreen;
        this.searchScreen = searchScreen;
    }

    public void show() {
        while (true) {
            showMenu();
            int decision = scanner.nextInt();
            scanner.nextLine();
            switch (decision) {
                case 1:
                    searchScreen.show();
                    break;
                case 2:
                    lightBoxManagementScreen.show();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Nie rozumiem!!!");
            }
        }
    }

    private void showMenu() {
        System.out.println("WITAMY W PHOTOSTOCK");
        System.out.println("1. Wyszukaj produkty.");
        System.out.println("2. Lajt boksy.");
        System.out.println("3. Zakończ.");
        System.out.print("Co chcesz zrobić? ");
    }
}
