package pl.com.bottega.photostock.sales.model.repositiories;

import pl.com.bottega.photostock.sales.model.Client;

public interface ClientRepository {
    Client get(String number);
    void save(Client client);
}
