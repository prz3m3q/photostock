package pl.com.bottega.photostock.sales.model;

public interface ClientRepository {
    Client get(String number);
    void save(Client client);
}
