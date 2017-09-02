package pl.com.bottega.photostock.sales.application;

import pl.com.bottega.photostock.sales.model.Client;
import pl.com.bottega.photostock.sales.model.ClientRepository;
import pl.com.bottega.photostock.sales.model.Reservation;
import pl.com.bottega.photostock.sales.model.ReservationRepository;

public class PurachaseProcess {

    private ClientRepository clientRepository;
    private ReservationRepository reservationRespository;

    public PurachaseProcess(ClientRepository clientRepository, ReservationRepository reservationRepository) {
        this.clientRepository = clientRepository;
        this.reservationRespository = reservationRepository;
    }

    public String createReservation(Long clientNumber) {
        Client client = clientRepository.get(clientNumber);
        Reservation reservation = new Reservation(client);
        reservationRespository.save(reservation);
        return reservation.getNumber();
    }
}
