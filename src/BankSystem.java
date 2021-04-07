import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class BankSystem {
	private static TreeSet<Client> clients = new TreeSet<>(new clientAscIdSortable());
	private InterestTask task;
	private static Scanner scanner = new Scanner(System.in);
	private static BankSystem instance;

	private BankSystem() {
		if (task == null)
			task = new InterestTask(clients, "New BankSystem");
	}

	public static BankSystem getInstance() {
		if (instance == null)
			synchronized (BankSystem.class) {
				if (instance == null)
					instance = new BankSystem();
			}
		return instance;
	}

	public static TreeSet<Client> getClients() {
		return clients;
	}

	public void showMenu() throws InterruptedException {
		String userChoice = "0";
		while (!userChoice.equals("9")) {
			printMenu();
			userChoice = scanner.next();
			switch (userChoice) {
			case "1":
				addClient();
				break;
			case "2":
				deleteClient();
				break;
			case "3":
				withdraw();
				break;
			case "4":
				deposit();
				break;
			case "5":
				printAll();
				break;
			case "6":
				getRichestClient();
				break;
			case "7":
				getPoorestClient();
				break;
			case "8":
				getAmountMoneyOfTheBank();
				break;
			case "9":
				exit();
				break;
			default:
				invalidOperationMsg();
				break;
			}
		}
		scanner.close();
	}

	public void startSystem() throws InterruptedException {
		InterestTask.getThread().start();
		showMenu();
		endSystem();
	}

	private void endSystem() {
		task.stop();
	}

	private void addClient(Client client) {
		clients.add(client);
		System.out.println("The client was successfully added \r\n");
	}

	private void deleteClient(int id) throws deleteException {
		for (Client client : clients) {
			if (client.getId() == id) {
				if (client.getAccount().getBalance() >= 0) {
					clients.remove(client);
					System.out.println("The client was successfully removed \r\n");
					break;
				} else {
					throw new deleteException(client.getName(), id, client.getAccount().getBalance());
				}
			}
		}
		System.out.println("Client not found \r\n");
	}

	private void withdraw(double amount, int id) throws withdrawException {
		for (Client client : clients) {
			if (client.getId() == id) {
				double currBalance = client.getAccount().getBalance();
				/**
				 * Debt up to 200 possible, Otherwise throw withdraw Exception..
				 */
				if (currBalance - amount < -200)
					throw new withdrawException(client.getName(), id, client.getAccount().getBalance());
				else {
					Client updateClient = client;
					clients.remove(client);
					updateClient.getAccount().setBalance(currBalance - amount);
					clients.add(updateClient);
					System.out.println("The transaction completed successfully \r\n");
					return;
				}
			}
		}
		System.out.println("Client not found \r\n");
	}

	private void deposite(double amount, int id) {
		for (Client client : clients) {
			if (client.getId() == id) {
				double currBalance = client.getAccount().getBalance();
				Client updateClient = client;
				clients.remove(client);
				updateClient.getAccount().setBalance(currBalance + amount);
				clients.add(updateClient);
				System.out.println("The transaction completed successfully \r\n");
				break;
			}
		}
	}

	private void printAll() {
		List<Client> sortClient = new ArrayList<>(clients);
		Collections.sort(sortClient, new clientAscBalanceSortable());
		System.out.println("------------------------------------------------------------");
		for (Client client : sortClient) {
			System.out.println(String.format("id %d, name %s, age %.0f, his balance is %.2f", client.getId(),
					client.getName(), client.getAge(), client.getAccount().getBalance()));
		}
		System.out.println("------------------------------------------------------------ \r\n");
	}

	private int pickClientType() {
		String pick = "";
		int res = -1;
		while (!pick.equalsIgnoreCase("R") && !pick.equalsIgnoreCase("V")) {
			System.out.print("Please choose R for regular client and V for VIP: ");
			pick = scanner.next();
			if (pick.equalsIgnoreCase("R"))
				res = 1;
			else if (pick.equalsIgnoreCase("V"))
				res = 2;
			else
				System.out.println("Invalid input..\r\n");
		}
		return res;
	}

	private boolean checkIfValid(Client client) {
		return client != null ? true : false;
	}

	private void exit() throws InterruptedException {
		System.out.print("Log out of the system");
		Thread.sleep(400);
		System.out.print(".");
		Thread.sleep(400);
		System.out.print(".");
		Thread.sleep(400);
		System.out.print(". \r\n");
		Thread.sleep(2500);
		System.out.println("Goodbye:) \r\n");
	}

	private void getRichestClient() {
		if (checkIfValid(BankStatistics.getRichestClient(clients)))
			System.out.println("The richest client: " + BankStatistics.getRichestClient(clients) + "\r\n");
		else
			System.out.println("None");
	}

	private void getPoorestClient() {
		if (checkIfValid(BankStatistics.getRichestClient(clients)))
			System.out.println("The poorest client: " + BankStatistics.printPoorestClient(clients) + "\r\n");
		else
			System.out.println("None");
	}

	private void getAmountMoneyOfTheBank() {
		System.out.println(
				String.format("The amount of money in the bank is: %.2f \r\n", BankStatistics.getBankBalance()));
	}

	private void invalidOperationMsg() {
		System.out.println("Invalid operation \r\n" + "Please enter your choice: (1-9) \r\n");
	}

	private void addClient() {
		if (pickClientType() == 1)
			addClient(new RegularClient());
		else
			addClient(new VipClient());
	}

	private void printMenu() {
		System.out.println("-- Bank Menu -- \r\n" + "1.Add client \r\n" + "2.Remove client \r\n"
				+ "3.Withdraw cash \r\n" + "4.Deposite cash \r\n" + "5.Print all customers in the system \r\n"
				+ "6.Show the richest client \r\n" + "7.Show the poorest client \r\n"
				+ "8.The amount of money in the bank \r\n" + "9.Exit");
	}

	private void deleteClient() {
		System.out.print("Please enter Id for delete: \r\n");
		int id = scanner.nextInt();
		try {
			deleteClient(id);
		} catch (deleteException e) {
			System.out.println(e.getMessage());
		}
	}

	private void withdraw() {
		System.out.print("Please enter a client Id number to withdraw money: \r\n");
		int id = scanner.nextInt();
		System.out.print("What amount would you like to withdraw? \r\n");
		double withdraw = scanner.nextDouble();
		try {
			withdraw(withdraw, id);
		} catch (withdrawException e) {
			System.out.println(e.getMessage());
		}
	}

	private void deposit() {
		System.out.print("Please enter a client Id number to deposit money: \r\n");
		int id = scanner.nextInt();
		System.out.print("What amount would you like to deposit? \r\n");
		double deposit = scanner.nextDouble();
		deposite(deposit, id);
	}
}
