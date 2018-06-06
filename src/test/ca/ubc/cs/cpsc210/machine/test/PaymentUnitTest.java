package ca.ubc.cs.cpsc210.machine.test;


import ca.ubc.cs.cpsc210.machine.model.Coin;
import ca.ubc.cs.cpsc210.machine.model.PaymentUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PaymentUnitTest {
    private PaymentUnit testPaymentUnit;

    @BeforeEach
    public void runBefore() {testPaymentUnit = new PaymentUnit();
    }


    @Test
    public void testConstructor() {
        assertEquals(0, testPaymentUnit.getNumberOfCoinsBankedOfType(Coin.LOONIE));
        assertEquals(0, testPaymentUnit.getNumberOfCoinsBankedOfType(Coin.QUARTER));
        assertEquals(0, testPaymentUnit.getNumberOfCoinsBankedOfType(Coin.DIME));
        assertEquals(0, testPaymentUnit.getNumberOfCoinsBankedOfType(Coin.NICKEL));
        assertEquals(0, testPaymentUnit.getValueOfCoinsBanked());
    }

    @Test
    public void tesGetNumberOfCoinsBankedOfType() {
        testPaymentUnit.addCoinsToBanked(Coin.NICKEL, 5);
        testPaymentUnit.addCoinsToBanked(Coin.LOONIE,3);
        testPaymentUnit.addCoinsToBanked(Coin.QUARTER,2);
        testPaymentUnit.addCoinsToBanked(Coin.DIME, 6);
        assertEquals(5, testPaymentUnit.getNumberOfCoinsBankedOfType(Coin.NICKEL));
        assertEquals(3, testPaymentUnit.getNumberOfCoinsBankedOfType(Coin.LOONIE));
        assertEquals(2, testPaymentUnit.getNumberOfCoinsBankedOfType(Coin.QUARTER));
        assertEquals(6, testPaymentUnit.getNumberOfCoinsBankedOfType(Coin.DIME));

    }

    @Test
    public void testClearCoinsBanked() {
        testPaymentUnit.addCoinsToBanked(Coin.QUARTER, 5);
        testPaymentUnit.addCoinsToBanked(Coin.LOONIE, 2);
        testPaymentUnit.addCoinsToBanked(Coin.DIME, 7);
        testPaymentUnit.addCoinsToBanked(Coin.NICKEL, 10);
        testPaymentUnit.clearCoinsBanked();
        assertEquals(0, testPaymentUnit.getNumberOfCoinsBankedOfType(Coin.LOONIE));
        assertEquals(0, testPaymentUnit.getNumberOfCoinsBankedOfType(Coin.QUARTER));
        assertEquals(0, testPaymentUnit.getNumberOfCoinsBankedOfType(Coin.DIME));
        assertEquals(0, testPaymentUnit.getNumberOfCoinsBankedOfType(Coin.NICKEL));
        assertEquals(0, testPaymentUnit.getValueOfCoinsBanked());

    }

    @Test
    public void testAddCoinsBanked() {
        testPaymentUnit.addCoinsToBanked(Coin.LOONIE,3);
        assertEquals(300, testPaymentUnit.getValueOfCoinsBanked());
        testPaymentUnit.addCoinsToBanked(Coin.QUARTER, 5);
        assertEquals(425, testPaymentUnit.getValueOfCoinsBanked());
        testPaymentUnit.addCoinsToBanked(Coin.DIME, 2);
        assertEquals(445, testPaymentUnit.getValueOfCoinsBanked());
        testPaymentUnit.addCoinsToBanked(Coin.NICKEL, 1);
        assertEquals(450, testPaymentUnit.getValueOfCoinsBanked());
    }

    @Test
    public void testInsertCoin() {
        testPaymentUnit.insertCoin(Coin.LOONIE);
        assertEquals(100, testPaymentUnit.getValueOfCoinsInserted());
        testPaymentUnit.insertCoin(Coin.QUARTER);
        assertEquals(125, testPaymentUnit.getValueOfCoinsInserted());
        testPaymentUnit.insertCoin(Coin.DIME);
        assertEquals(135, testPaymentUnit.getValueOfCoinsInserted());
        testPaymentUnit.insertCoin(Coin.NICKEL);
        assertEquals(140, testPaymentUnit.getValueOfCoinsInserted());

    }

    @Test
    public void testCancelTransaction() {
        List<Coin> InsertedCoins = new ArrayList<>();
        InsertedCoins.add(Coin.LOONIE);
        InsertedCoins.add(Coin.QUARTER);
        InsertedCoins.add(Coin.DIME);
        InsertedCoins.add(Coin.NICKEL);
        testPaymentUnit.insertCoin(Coin.LOONIE);
        testPaymentUnit.insertCoin(Coin.QUARTER);
        testPaymentUnit.insertCoin(Coin.DIME);
        testPaymentUnit.insertCoin(Coin.NICKEL);
        testPaymentUnit.cancelTransaction();
        assertEquals(0, testPaymentUnit.getValueOfCoinsInserted());
        assertEquals(InsertedCoins, testPaymentUnit.cancelTransaction());
    }

    @Test
    public void testMakePurchase() {
        testPaymentUnit.addCoinsToBanked(Coin.LOONIE, 5);
        testPaymentUnit.addCoinsToBanked(Coin.QUARTER, 5);
        testPaymentUnit.addCoinsToBanked(Coin.DIME, 5);
        testPaymentUnit.addCoinsToBanked(Coin.NICKEL, 5);
        testPaymentUnit.insertCoin(Coin.LOONIE);
        testPaymentUnit.insertCoin(Coin.LOONIE);
        testPaymentUnit.insertCoin(Coin.QUARTER);
        testPaymentUnit.makePurchase(225);
        assertEquals(925, testPaymentUnit.getValueOfCoinsBanked());
        assertEquals(0, testPaymentUnit.getValueOfCoinsInserted());
        


    }
    @Test
    public void testMakePurchase2() {
        testPaymentUnit.addCoinsToBanked(Coin.LOONIE, 5);
        testPaymentUnit.addCoinsToBanked(Coin.QUARTER, 5);
        testPaymentUnit.addCoinsToBanked(Coin.DIME, 5);
        testPaymentUnit.addCoinsToBanked(Coin.NICKEL, 5);
        testPaymentUnit.insertCoin(Coin.LOONIE);
        testPaymentUnit.insertCoin(Coin.QUARTER);
        testPaymentUnit.insertCoin(Coin.QUARTER);
        testPaymentUnit.insertCoin(Coin.DIME);
        testPaymentUnit.insertCoin(Coin.NICKEL);
        testPaymentUnit.makePurchase(160);
        assertEquals(860, testPaymentUnit.getValueOfCoinsBanked());
        assertEquals(0, testPaymentUnit.getValueOfCoinsInserted());
    }

    @Test
    public void testMakePurchase3() {
        testPaymentUnit.addCoinsToBanked(Coin.LOONIE, 5);
        testPaymentUnit.addCoinsToBanked(Coin.QUARTER, 5);
        testPaymentUnit.addCoinsToBanked(Coin.DIME, 5);
        testPaymentUnit.addCoinsToBanked(Coin.NICKEL, 5);
        testPaymentUnit.insertCoin(Coin.LOONIE);
        testPaymentUnit.insertCoin(Coin.QUARTER);
        testPaymentUnit.insertCoin(Coin.QUARTER);
        testPaymentUnit.makePurchase(140);
        assertEquals(840, testPaymentUnit.getValueOfCoinsBanked());
        assertEquals(0, testPaymentUnit.getValueOfCoinsInserted());
    }
    @Test
    public void testMakePurchase4() {
        testPaymentUnit.addCoinsToBanked(Coin.LOONIE, 5);
        testPaymentUnit.addCoinsToBanked(Coin.QUARTER, 5);
        testPaymentUnit.addCoinsToBanked(Coin.DIME, 5);
        testPaymentUnit.addCoinsToBanked(Coin.NICKEL, 5);
        testPaymentUnit.insertCoin(Coin.LOONIE);
        testPaymentUnit.insertCoin(Coin.QUARTER);
        testPaymentUnit.insertCoin(Coin.LOONIE);
        testPaymentUnit.makePurchase(100);
        assertEquals(800, testPaymentUnit.getValueOfCoinsBanked());
        assertEquals(0, testPaymentUnit.getValueOfCoinsInserted());
    }
    @Test
    public void testMakePurchase5() {
        testPaymentUnit.addCoinsToBanked(Coin.LOONIE, 0);
        testPaymentUnit.addCoinsToBanked(Coin.QUARTER, 0);
        testPaymentUnit.addCoinsToBanked(Coin.DIME, 0);
        testPaymentUnit.addCoinsToBanked(Coin.NICKEL, 5);
        testPaymentUnit.insertCoin(Coin.LOONIE);
        testPaymentUnit.makePurchase(50);
        assertEquals(100, testPaymentUnit.getValueOfCoinsBanked());
        assertEquals(0, testPaymentUnit.getValueOfCoinsInserted());
    }


}
