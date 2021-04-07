import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

public class BankStatistics {
	public static int countMembers() {
		return BankSystem.getClients().size();
	}

	public static double getBankBalance() {
		return BankSystem.getClients().stream().mapToDouble(c -> c.getAccount().getBalance()).sum();
	}

	public static Client getRichestClient(TreeSet<Client> clients) {
		if (countMembers() != 0) {
			List<Client> sortClient = new ArrayList<>(clients);
			Collections.sort(sortClient, new clientAscBalanceSortable());
			return sortClient.get(0);
		} else
			return null;
	}

	public static Client printPoorestClient(TreeSet<Client> clients) {
		if (countMembers() != 0) {
			List<Client> sortClient = new ArrayList<>(clients);
			Collections.sort(sortClient, new clientAscBalanceSortable());
			return sortClient.get(clients.size() - 1);
		} else
			return null;
	}
}
