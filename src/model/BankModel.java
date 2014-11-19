package model;


public class BankModel {
	Account account;

	public BankModel(Account acc) {

		this.account = acc;
	}

	public String isTransactionAllowed(double totalCost) {

		try {

			if (account.getBalance() - totalCost >= 0) {
				String msg = "Yes";
				return msg;
			} else {
				String msg = "No";
				return msg;
			}
		} catch (NumberFormatException e) {
			String msg = "Please, enter a valid account";
			return msg;
		} catch (NullPointerException e) {
			String msg = "Invalid account information";
			return msg;

		}

	}
}
