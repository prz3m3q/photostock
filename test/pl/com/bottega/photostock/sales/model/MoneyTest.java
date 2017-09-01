package pl.com.bottega.photostock.sales.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MoneyTest {

    private Money fiftyCredit = Money.valueOf(50);
    private Money seventeenCredit = Money.valueOf(70);
    private Money fiftyEuro = Money.valueOf(50, "EURO");

    @Test
    public void shouldAddMoney() {
        //when
        Money diff = fiftyCredit.add(seventeenCredit);
        //then
        Money expected = Money.valueOf(120);
        assertEquals(expected, diff);
    }

    @Test
    public void shouldSubstractMoney() {
        //when
        Money diff = fiftyCredit.substract(seventeenCredit);
        //then
        Money expected = Money.valueOf(-20);
        assertEquals(expected, diff);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAddMoneyInDiffrentCurrencies() {
        //when
        fiftyEuro.add(seventeenCredit);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotSubstractMoneyInDiffrentCurrencies() {
        //when
        fiftyEuro.substract(seventeenCredit);
    }

    @Test
    public void testGratherEquals() {
        //then
        assertEquals(true, seventeenCredit.gte(fiftyCredit));
    }

    @Test
    public void shouldCompareMoney() {
        assertTrue(fiftyCredit.compareTo(seventeenCredit) < 0);
        assertTrue(seventeenCredit.compareTo(fiftyCredit) > 0);
        assertTrue(fiftyCredit.compareTo(fiftyCredit) == 0);
    }

    @Test
    public void shouldCompareMoneyUsngBooleanMethods() {
        assertTrue(fiftyCredit.lt(seventeenCredit));
        assertTrue(fiftyCredit.lte(seventeenCredit));
        assertTrue(seventeenCredit.gt(fiftyCredit));
        assertTrue(seventeenCredit.gte(fiftyCredit));
        assertFalse(fiftyCredit.gt(seventeenCredit));
        assertFalse(fiftyCredit.gte(seventeenCredit));
        assertFalse(seventeenCredit.lt(fiftyCredit));
        assertFalse(seventeenCredit.lte(fiftyCredit));
        assertTrue(fiftyCredit.lte(fiftyCredit));
        assertTrue(fiftyCredit.gte(fiftyCredit));
        assertFalse(fiftyCredit.lt(fiftyCredit));
        assertFalse(fiftyCredit.gt(fiftyCredit));
    }

    @Test
    public void shouldCalculatePercent() {
        assertEquals(Money.valueOf(5), fiftyCredit.percent(10));
        assertEquals(Money.valueOf(5.50), fiftyCredit.percent(11));
        assertEquals(Money.valueOf(75), fiftyCredit.percent(150));
        assertEquals(Money.valueOf(0.01), Money.valueOf(0.11).percent(10));
        assertEquals(Money.valueOf(0.01), Money.valueOf(0.19).percent(10));
    }

    @Test
    public void shouldConvertCurrencies() {
        assertEquals(Money.valueOf(3.5, "PLN"), Money.valueOf(1, "USD").convert("PLN", 3.5));
    }

}
