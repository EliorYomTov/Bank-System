import java.util.Date;

public class withdrawException extends Exception {
	private static final long serialVersionUID = 1L;
	private Date timeStamp;
	private String clientName;
	private int clientId;
	private double balance;

	public withdrawException(String name, int id, double balance) {
		clientName = name;
		clientId = id;
		timeStamp = new Date();
		this.balance = balance;
	}
	
	@Override
	public String getMessage() {
		return String.format(
				"Client name %s, id %d, date %tc, his balance is %.2f - there is not enough money to withdraw \r\n",
				clientName, clientId, timeStamp, balance);
	}
}
