import java.util.ArrayList;
import java.util.List;

/**
 * Represents the payment unit in a vending machine.
 */
public class PaymentUnit {
    private int numLoonies;   // number of loonies banked in machine for making change
    private int numQuarters;  // number of quarters banked in machine for making change
    private int numDimes;     // number of dimes banked in machine for making change
    private int numNickels;   // number of nickels banked in machine for making change
    private List<Coin> currentTransaction;   // list of coins inserted into machine during current transaction
    private List<Coin> currentTransactionHolder;

    // EFFECTS: constructs a payment unit with no banked coins and no coins inserted into the machine
    // as part of a payment
    public PaymentUnit() {
        numLoonies = 0;
        numQuarters = 0;
        numDimes = 0;
        numNickels = 0;
        currentTransaction = new ArrayList<Coin>();
        currentTransactionHolder = new ArrayList<Coin>();

    }

    // MODIFIES: this
    // EFFECTS: clears all the coins banked in the unit
    public void clearCoinsBanked() {
        numLoonies = 0;
        numQuarters = 0;
        numDimes = 0;
        numNickels = 0;
    }

    // REQUIRES: number > 0
    // MODIFIES: this
    // EFFECTS: adds number coins of type c to the banked coins in the unit
    public void addCoinsToBanked(Coin c, int number) {
        switch (c) {
            case LOONIE:
                numLoonies = numLoonies + number;
                break;
            case QUARTER:
                numQuarters = numQuarters + number;
                break;
            case DIME:
                numDimes = numDimes + number;
                break;
            case NICKEL:
                numNickels = numNickels + number;
                break;
        }

    }

    // EFFECTS: returns number of coins banked of the given type
    public int getNumberOfCoinsBankedOfType(Coin c) {

        switch (c) {
            case LOONIE:
                return numLoonies;
            case QUARTER:
                return numQuarters;
            case DIME:
                return numDimes;
            case NICKEL:
                return numNickels;
        }
        return 0;
    }


    // EFFECTS: returns the total value of all coins banked in the unit
    public int getValueOfCoinsBanked() {
        return ((numLoonies * 100) + (numQuarters * 25) + (numDimes * 10)
                + (numNickels * 5));
    }

    // MODIFIES: this
    // EFFECTS: adds coin c to the unit as a part of a transaction
    public void insertCoin(Coin c) {
        currentTransaction.add(c);
        currentTransactionHolder.add(c);


    }

    // EFFECTS: returns value of coins inserted for current transaction
    public int getValueOfCoinsInserted() {
        int AmountSoFar = 0;
        for (Coin next: currentTransaction) {
            AmountSoFar = next.getValue() + AmountSoFar;
        }
        return AmountSoFar;
    }

    // MODIFIES: this
    // EFFECTS: coins inserted for current transaction are cleared; list of coins
    // inserted for current transaction is returned in the order in which they were inserted.
    public List<Coin> cancelTransaction() {
        currentTransaction.clear();
        return currentTransactionHolder;

    }

    // REQUIRES: cost <= total value of coins inserted as part of current transaction
    // MODIFIES: this
    // EFFECTS: adds coins inserted to coins banked in unit and returns list of coins that will be provided as change.
    // Coins of largest possible value are used when determining the change.  Change in full is not guaranteed -
    // will provide only as many coins as are banked in the machine, without going over the amount due.
    public List<Coin> makePurchase(int cost) {
        List<Coin> ChangeBack = new ArrayList<Coin>();
        for (Coin next : currentTransaction) {
            switch (next) {
                case LOONIE:
                    numLoonies++;
                    break;
                case QUARTER:
                    numQuarters++;
                    break;
                case DIME:
                    numDimes++;
                    break;
                case NICKEL:
                    numNickels++;
                    break;
            }
        }
        int AmountSoFar = 0;
        int Change;
        for (Coin next : currentTransaction) {
            AmountSoFar = next.getValue() + AmountSoFar;
        }
        Change = AmountSoFar - cost;
        currentTransaction.clear();
        while (Change >= 100 && numLoonies >= 1) {
            numLoonies = numLoonies - 1;
            ChangeBack.add(Coin.LOONIE);
            Change = Change - 100;
        }
        while (Change >= 25 && numQuarters >= 1) {
            numQuarters = numQuarters - 1;
            ChangeBack.add(Coin.QUARTER);
            Change = Change - 25;
        }
        while (Change >= 10 && numDimes >= 1) {
            numDimes = numDimes - 1;
            ChangeBack.add(Coin.DIME);
            Change = Change - 10;
        }
        while (Change >= 5 && numNickels >= 1) {
            numNickels = numNickels - 1;
            ChangeBack.add(Coin.NICKEL);
            Change = Change - 5;
        }

        return ChangeBack;

    }


}
