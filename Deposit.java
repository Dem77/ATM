
public class Deposit extends Transaction
{
	private double amount;
	private Keypad keypad;
	private DepositSlot depositSlot;
	private final static int CANCELED = 0;
	
	public Deposit (int userAccountNumber, Screen atmScreen, BankDatabase atmBankDataBase,
			Keypad atmKeypad, DepositSlot atmDepositSlot)
	{
		super (userAccountNumber, atmScreen, atmBankDataBase);
		keypad = atmKeypad;
		depositSlot = atmDepositSlot;
	}

	@Override
	public void excecute() 
	{
		BankDatabase bankDatabase = getBankDatabase();
		Screen screen = getScreen();
		
		amount = promptForDepositAmount ();
		
		if (amount != CANCELED)
		{
			screen.displayMessage("\n Please enter a deposit envelope containing ");
			screen.displayDollarAmount(amount);
			screen.displayMessageLine(".");
			
			boolean envelopeReceived = depositSlot.isEnvelopeReceived();
			
			if (envelopeReceived)
			{
				screen.displayMessageLine("Envelope has been received");
				
				bankDatabase.debit(getAccountNumber(), amount);
			}
			else
			{
				screen.displayMessageLine("\n No envelope was received");
			}
		}
		else
		{
			screen.displayMessageLine("\nCanceling transaction...");
		}
	}
	
	private double promptForDepositAmount ()
	{
		Screen screen = getScreen();
		
		screen.displayMessage ("Please enter a deposit amount in CENTS (or 0 to cancel): ");
		int input = keypad.getInput();
		
		if (input == CANCELED)
			return CANCELED;
		else
		{
			return (double) input / 100;
		}
	}
}
