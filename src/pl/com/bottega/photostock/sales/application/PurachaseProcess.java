package pl.com.bottega.photostock.sales.application;

import pl.com.bottega.photostock.sales.model.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PurachaseProcess {

    private static final Money TOLERANCE = Money.valueOf(10);

    private ClientRepository clientRepository;
    private ReservationRepository reservationRespository;
    private ProductRepository productRepository;
    private PurchaseRepository purchaseRepository;

    public PurachaseProcess(ClientRepository clientRepository,
                            ReservationRepository reservationRepository,
                            ProductRepository productRepository,
                            PurchaseRepository purchaseRepository
    ) {
        this.clientRepository = clientRepository;
        this.reservationRespository = reservationRepository;
        this.productRepository = productRepository;
        this.purchaseRepository = purchaseRepository;
    }

    public String createReservation(String clientNumber) {
        Client client = clientRepository.get(clientNumber);
        Reservation reservation = new Reservation(client);
        reservationRespository.save(reservation);
        return reservation.getNumber();
    }

    public void add(String reservationNumber, Long productNumber) {
        Reservation reservation = reservationRespository.get(reservationNumber);
        Product product = productRepository.get(productNumber);
        reservation.add(product);
        reservationRespository.save(reservation);
        productRepository.save(product);
    }

    public Offer calculateOffer(String reservationNumber) {
        return reservationRespository.get(reservationNumber).generateOffer();
    }

    public PurchaseStatus confirm(String reservationNumber, Offer clientOffer) {
        Offer currentOffer = calculateOffer(reservationNumber);
        if (!currentOffer.sameAs(currentOffer, TOLERANCE)) {
            return PurchaseStatus.OFFER_MISSMATCH;
        }
        Client client = currentOffer.getOwner();
        Money offerCost = clientOffer.getTotalCost();
        if (!client.canAfford(offerCost)) {
            return PurchaseStatus.NOT_ENOUGHT_FOUNDS;
        }
        Purchase purchase = clientOffer.purchase();
        purchaseRepository.save(purchase);
        clientRepository.save(client);
        Collection<Product> products = clientOffer.getItems();
        for (Product product : products) {
            productRepository.save(product);
        }
        return PurchaseStatus.SUCCESS;
    }
}
