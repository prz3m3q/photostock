package pl.com.bottega.photostock.sales.model;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class ClientTest {

    private final Address address = new Address("ul. Północna 11", "Poland", "Lublin", "20-222");
    private Client clientWithCredit = new Client(
        "Ja Nowak",
        address,
        ClientStatus.VIP,
        Money.valueOf(100),
        Money.valueOf(100)
    );
    private Client clientWithNoMoney = new Client("Ja Nowak", address);

    @Test
    public void shouldCheckIfClientCanAfford() {
        //when
        clientWithNoMoney.recharge(Money.valueOf(100));
        //then
        assertTrue(clientWithNoMoney.canAfford(Money.valueOf(50)));
        assertTrue(clientWithNoMoney.canAfford(Money.valueOf(100)));
        assertFalse(clientWithNoMoney.canAfford(Money.valueOf(101)));
    }

    @Test
    public void shouldCheckIfClientCanAffordCredit() {
        assertTrue(clientWithCredit.canAfford(Money.valueOf(200)));
        assertTrue(clientWithCredit.canAfford(Money.valueOf(170)));
        assertFalse(clientWithCredit.canAfford(Money.valueOf(201)));
    }

    @Test
    public void shouldChargeAndRecharge() {
        clientWithCredit.charge(Money.valueOf(200), "testowy zakup");
        clientWithCredit.recharge(Money.valueOf(100));

        assertTrue(clientWithCredit.canAfford(Money.valueOf(100)));
        assertFalse(clientWithCredit.canAfford(Money.valueOf(101)));
    }
}
