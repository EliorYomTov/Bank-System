import java.util.Date;

public class deleteException extends Exception {
	private static final long serialVersionUID = 1L;
	private Date timeStamp;
	private String clientName;
	private int clientId;
	private double balance;

	public deleteException(String name, int id, double balance) {
		clientName = name;
		clientId = id;
		timeStamp = new Date();
		this.balance = balance;
	}

	@Override
	public String getMessage() {
		return String.format(
				"Client name %s, id %d, date %tc, his balance is %.2f - the client has a debt, it can not be deleted \r\n",
				clientName, clientId, timeStamp, balance);
	}
}
