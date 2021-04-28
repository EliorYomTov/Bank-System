import java.util.Comparator;

public class clientAscBalanceSortable implements Comparator<Client> {
	@Override
	public int compare(Client o1, Client o2) {
		return (int) (o2.getAccount().getBalance() - o1.getAccount().getBalance());
	}

}
