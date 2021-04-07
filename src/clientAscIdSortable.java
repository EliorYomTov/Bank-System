import java.util.Comparator;

public class clientAscIdSortable implements Comparator<Client> {
	@Override
	public int compare(Client o1, Client o2) {
		return o1.getId() - o2.getId();
	}
}
