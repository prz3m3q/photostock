package pl.com.bottega.photostock.sales.ui;

import pl.com.bottega.photostock.sales.application.LightBoxManagement;
import pl.com.bottega.photostock.sales.model.LightBox;

import java.util.List;
import java.util.Scanner;

public class LightBoxManagementScreen {

    private Scanner scanner;
    private LightBoxManagement lightBoxManagement;
    private AutenticationMaganger autenticationMaganger;

    public LightBoxManagementScreen(Scanner scanner, LightBoxManagement lightBoxManagement, AutenticationMaganger autenticationMaganger) {
        this.scanner = scanner;
        this.lightBoxManagement = lightBoxManagement;
        this.autenticationMaganger = autenticationMaganger;
    }

    public void show() {
        System.out.println("Twoje lajt boxy: ");
        List<LightBox> lightBoxes = lightBoxManagement.getLightBoxes(autenticationMaganger.getClientNumber());
        if (lightBoxes.isEmpty()) {
            System.out.println("Nie ma aktualnie żadnych lightboxów");
        } else {
            int index = 0;
            for (LightBox lightBox : lightBoxes) {
                System.out.println(String.format("%d. - %s", ++index, lightBox.getName()));
            }
        }
    }
}
