package pl.com.bottega.photostock.sales.ui;

import pl.com.bottega.photostock.sales.model.Client;
import pl.com.bottega.photostock.sales.model.LightBox;
import pl.com.bottega.photostock.sales.model.Picture;

public class LightBoxPresenter {
    public void show(LightBox lightBox) {
        System.out.println(lightBox.getName());
        System.out.println("------------------------------------");
        Client client = lightBox.getClient();
        int i = 1;
        for (Picture picture : lightBox.getItems()) {
            System.out.println(String.format(
                "%s %d. %d | %s",
                picture.isAvalible() ? "" : "x",
                i++,
                picture.getNumber(),
                picture.calculatePrice(client)
            ));
        }
    }
}
