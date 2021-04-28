import java.util.TreeSet;

public class InterestTask implements Runnable {
	private TreeSet<Client> clients = new TreeSet<Client>(new clientAscBalanceSortable());
	private String threadName;
	private boolean exit;
	private static Thread thread;

	public InterestTask(TreeSet<Client> clients, String name) {
		setClients(clients);
		setThreadName(name);
		thread = new Thread(this, name);
		System.out.println("New thread: " + threadName + "\r\n");
		exit = false;
	}

	public TreeSet<Client> getClients() {
		return clients;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public void setClients(TreeSet<Client> clients) {
		this.clients = clients;
	}

	public String getThreadName() {
		return threadName;
	}

	public static Thread getThread() {
		return thread;
	}

	@Override
	public void run() {
		while (!exit) {
			System.out.println("\r\n" + threadName + "\r\n thread Started running");
			UpdateRate();
			printClient();
			try {
				System.out.println(threadName + "\r\n Stopped and went to sleep \r\n");
				Thread.sleep(1000 * 60 * 60 * 24);
			} catch (InterruptedException e) {
			}
		}
		System.out.println("Thread " + threadName + " Shutting down thread \r\n");
	}

	public void stop() {
		exit = true;
	}

	private void printClient() {
		for (Client client : clients) {
			System.out.println(String.format("id %d, name %s, his balance is %.2f", client.getId(), client.getName(),
					client.getAccount().getBalance()));
		}
	}

	private void UpdateRate() {
		for (Client client : clients) {
			double currBalance = client.getAccount().getBalance();
			client.getAccount().setBalance(currBalance * client.getInterestRate());
		}
	}
}
