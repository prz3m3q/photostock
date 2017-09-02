package pl.com.bottega.photostock.sales.infrastructure;

import pl.com.bottega.photostock.sales.model.*;

import java.util.HashMap;
import java.util.Map;

public class InMemoryClientRepository implements ClientRepository {

    private static final Map<String, Client> REPO;

    static {
        REPO = new HashMap<>();
        Client c1 = new VipClient("Ja Nowak", new Address("ul. Północna 11", "Poland", "Lublin", "20-222"));
        Client c2 = new VipClient("Jak dNowak", new Address("ul. Północna 13", "Poland", "Warszawa", "20-222"));
        Client c3 = new VipClient("Jak sNowak", new Address("ul. Północna 22", "Poland", "Gdańsk", "20-222"));
        REPO.put(c1.getNumber(), c1);
        REPO.put(c2.getNumber(), c2);
        REPO.put(c3.getNumber(), c3);
    }

    @Override
    public Client get(String number) {
        if (!REPO.containsKey(number)) {
            throw new IllegalArgumentException(String.format("No client with number %s.", number));
        }
        return REPO.get(number);
    }

    @Override
    public void save(Client client) {
        REPO.put(client.getNumber(), client);
    }
}
