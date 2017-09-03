package pl.com.bottega.photostock.sales.application;

import pl.com.bottega.photostock.sales.model.*;
import pl.com.bottega.photostock.sales.model.repositiories.ClientRepository;
import pl.com.bottega.photostock.sales.model.repositiories.LightBoxRepository;
import pl.com.bottega.photostock.sales.model.repositiories.ProductRepository;
import pl.com.bottega.photostock.sales.model.repositiories.ReservationRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class LightBoxManagement {
    LightBoxRepository lightBoxRepository;
    ClientRepository clientRepository;
    ProductRepository productRepository;
    ReservationRepository reservationRepository;

    public LightBoxManagement(LightBoxRepository lightBoxRepository, ClientRepository clientRepository, ProductRepository productRepository, ReservationRepository reservationRepository) {
        this.lightBoxRepository = lightBoxRepository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.reservationRepository = reservationRepository;
    }

    public String create(String clientNumber, String lightBoxName) {
        Client client = clientRepository.get(clientNumber);
        LightBox lightBox = new LightBox(client, lightBoxName);
        lightBoxRepository.save(lightBox);
        return lightBox.getNumber();
    }

    public void add(String lightBoxNumber, Long pictureNumber) {
        LightBox lightBox = lightBoxRepository.get(lightBoxNumber);
        Product product = productRepository.get(pictureNumber);
        if (!(product instanceof Picture)) {
            throw new IllegalArgumentException("Product number is not a picture number.");
        }
        lightBox.add((Picture)product);
        lightBoxRepository.save(lightBox);
        productRepository.save(product);
    }

    public void reserve(String lightBoxNumber, Set<Long> pictureNumbers, String reservationNumber) {
        LightBox lightBox = lightBoxRepository.get(lightBoxNumber);
        Reservation reservation = reservationRepository.get(reservationNumber);
        List<Picture> lightBoxPictures = lightBox.getPictures(pictureNumbers);
        if (lightBoxPictures.size() != pictureNumbers.size()) {
            throw new IllegalArgumentException("Invalid product numbers.");
        }
        reservation.addPictures(lightBoxPictures);
        for (Picture picture : lightBoxPictures) {
            productRepository.save(picture);
        }
        reservationRepository.save(reservation);
    }

    public List<LightBox> getLightBoxes(String clientNumber) {
        return lightBoxRepository.getByClientNumber(clientNumber);
    }
}