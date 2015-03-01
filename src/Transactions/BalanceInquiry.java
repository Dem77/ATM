package Transactions;

import Models.BankDatabase;
import javafx.stage.Screen;

public class BalanceInquiry extends Transaction {
    public BalanceInquiry(int userAccountNumber, Screen atmScreen,
                          BankDatabase bankDatabase) {
        super(userAccountNumber, atmScreen, bankDatabase);
    }

    @Override
    public void excecute() {
        BankDatabase bankDatabase = getBankDatabase();
        Screen screen = getScreen();

        double availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());
        double totalBalance = bankDatabase.getTotalBalance(getAccountNumber());

        screen.displayMessageLine("\nBalance Information:");
        screen.displayMessage("- Available Balance: ");
        screen.displayDollarAmount(availableBalance);
        screen.displayMessage("\n - Total Balance:         ");
        screen.displayDollarAmount(totalBalance);
        screen.displayMessageLine("");
    }
}
