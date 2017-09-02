package pl.com.bottega.photostock.sales.model;

public interface ReservationRepository {
    void save(Reservation reservation);
    Reservation get(String number);
}
