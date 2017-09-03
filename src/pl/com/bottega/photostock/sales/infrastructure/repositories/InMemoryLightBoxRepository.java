package pl.com.bottega.photostock.sales.infrastructure.repositories;

import pl.com.bottega.photostock.sales.model.*;
import pl.com.bottega.photostock.sales.model.repositiories.LightBoxRepository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class InMemoryLightBoxRepository implements LightBoxRepository {

    private static final Map<String, LightBox> REPO = new HashMap<>();;

    @Override
    public LightBox get(String number) {
        if (!REPO.containsKey(number)) {
            throw new IllegalArgumentException(String.format("No lightbox with number %s.", number));
        }
        return REPO.get(number);
    }

    @Override
    public List<LightBox> getByClientNumber(String clientNumber) {
        List<LightBox> lightBoxes = new LinkedList<>();
        for (LightBox lightBox : REPO.values()) {
            if (lightBox.hasClientNumber(clientNumber)) {
                lightBoxes.add(lightBox);
            }
        }
        return lightBoxes;
    }

    @Override
    public void save(LightBox lightBox) {
        REPO.put(lightBox.getNumber(), lightBox);
    }
}
