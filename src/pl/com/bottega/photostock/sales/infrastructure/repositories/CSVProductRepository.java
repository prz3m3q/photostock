package pl.com.bottega.photostock.sales.infrastructure.repositories;

import pl.com.bottega.photostock.sales.model.Client;
import pl.com.bottega.photostock.sales.model.Money;
import pl.com.bottega.photostock.sales.model.Picture;
import pl.com.bottega.photostock.sales.model.Product;
import pl.com.bottega.photostock.sales.model.repositiories.ClientRepository;
import pl.com.bottega.photostock.sales.model.repositiories.ProductRepository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;

public class CSVProductRepository implements ProductRepository {

    private String path;
    private ClientRepository clientRepository;

    public CSVProductRepository(String path, ClientRepository clientRepository) {
        this.path = path;
        this.clientRepository = clientRepository;
    }

    @Override
    public Product get(Long number) {
        return getOptional(number).orElseThrow((Supplier<RuntimeException>) () -> new IllegalArgumentException("No such obkject in repository"));
    }

    private Client findClient(String number) {
        if (number.equals("null")) {
            return null;
        }
        return clientRepository.get(number);
    }

    @Override
    public void save(Product picture) {

    }

    @Override
    public Optional<Product> getOptional(Long number) {
        try (BufferedReader bf = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = bf.readLine()) != null) {
                String[] lineSplit = line.split(",");
                if (lineSplit[0].equals(number.toString())) {
                    Long nr = Long.parseLong(lineSplit[0]);
                    String[] tags = lineSplit[1].split(";");
                    Money price = Money.valueOf(Integer.parseInt(lineSplit[2]));
                    boolean active = Boolean.valueOf(lineSplit[3]);
                    String reservedByNumber = lineSplit[4];
                    String ownerNumber = lineSplit[5];
                    return Optional.of(new Picture(
                        nr,
                        tags,
                        price,
                        findClient(reservedByNumber),
                        findClient(ownerNumber),
                        active
                    ));
                }
            }
            return Optional.empty();
        } catch (FileNotFoundException e) {
            return Optional.empty();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> find(Client client, Set<String> tags, Money from, Money to) {
        return null;
    }
}
