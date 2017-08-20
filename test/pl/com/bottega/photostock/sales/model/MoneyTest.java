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
}
