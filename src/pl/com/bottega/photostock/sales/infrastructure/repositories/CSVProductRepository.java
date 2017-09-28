package pl.com.bottega.photostock.sales.infrastructure.repositories;

import pl.com.bottega.photostock.sales.model.Client;
import pl.com.bottega.photostock.sales.model.Money;
import pl.com.bottega.photostock.sales.model.Picture;
import pl.com.bottega.photostock.sales.model.Product;
import pl.com.bottega.photostock.sales.model.repositiories.ClientRepository;
import pl.com.bottega.photostock.sales.model.repositiories.ProductRepository;

import java.io.*;
import java.util.*;
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
        return getOptional(number).orElseThrow((Supplier<RuntimeException>) () -> new IllegalArgumentException("No such object in repository"));
    }

    private Client findClient(String number) {
        if (number.equals("null")) {
            return null;
        }
        return clientRepository.get(number);
    }

    @Override
    public void save(Product picture) {
        Map<Long, Product> repository = new HashMap<>();
        try (BufferedReader bf = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = bf.readLine()) != null) {
                String[] lineSplit = line.split(",");
                Product product = makeProductFromLine(lineSplit);
                if (product.getNumber() != null) {
                    repository.put(product.getNumber(), product);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        repository.put(picture.getNumber(), picture);
        try (OutputStream outputStream = new FileOutputStream(path); PrintStream ps = new PrintStream(outputStream)) {
            for (Product product: repository.values()) {
                ps.println(String.join(",", stringToLine(product)));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String[] stringToLine(Product product) {
        String[] productLine = new String[6];
        productLine[0] = String.valueOf(product.getNumber());
        if (product instanceof Picture) {
            Picture picture = (Picture)product;
            productLine[1] = String.join(";", picture.getTags());
        }
        productLine[2] = product.getPrice().;
        productLine[3] = String.valueOf(product.getActive());
        productLine[4] = product.getReservedByNumber();
        productLine[5] = product.getOwnerNumber();
        return productLine;
    }

    @Override
    public Optional<Product> getOptional(Long number) {
        try (BufferedReader bf = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = bf.readLine()) != null) {
                String[] lineSplit = line.split(",");
                if (lineSplit[0].equals(number.toString())) {
                    return Optional.of(this.makeProductFromLine(lineSplit));
                }
            }
            return Optional.empty();
        } catch (FileNotFoundException e) {
            return Optional.empty();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Product makeProductFromLine(String[] lineSplit) {
        Long nr = Long.parseLong(lineSplit[0]);
        String[] tags = lineSplit[1].split(";");
        Money price = Money.valueOf(Integer.parseInt(lineSplit[2]));
        boolean active = Boolean.valueOf(lineSplit[3]);
        String reservedByNumber = lineSplit[4];
        String ownerNumber = lineSplit[5];
        return new Picture(
            nr,
            tags,
            price,
            findClient(reservedByNumber),
            findClient(ownerNumber),
            active
        );
    }

    @Override
    public List<Product> find(Client client, Set<String> tags, Money from, Money to) {
        List<Product> foundProducts = new LinkedList<>();
        try (BufferedReader bf = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = bf.readLine()) != null) {
                String[] lineSplit = line.split(",");
                Product product = makeProductFromLine(lineSplit);
                if (product instanceof Picture && matchesCriteria((Picture)product, client, tags, from, to)) {
                    foundProducts.add(product);
                }
            }
        } catch (FileNotFoundException e) {
            return foundProducts;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return foundProducts;
    }

    private boolean matchesCriteria(Picture picture, Client client, Set<String> tags, Money from, Money to) {
        if (tags != null && !picture.getTags().containsAll(tags)) {
            return false;
        }
        Money price = picture.calculatePrice(client);
        if (from != null && from.gt(price)) {
            return false;
        }
        if (to != null && to.lt(price)) {
            return false;
        }
        return true;
    }
}
