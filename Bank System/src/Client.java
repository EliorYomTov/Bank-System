
public abstract class Client extends Person {
	private float interestRate;
	private Account account;

	public Client() {
		account = new Account();
	}

	public float getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(float interestRate) {
		this.interestRate = interestRate;
	}

	public Account getAccount() {
		return account;
	}

	@Override
	public String toString() {
		return super.toString() + String.format(" balance: %.2f", account.getBalance());
	}
}
