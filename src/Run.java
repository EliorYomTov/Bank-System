
public class Run {
	public static void main(String[] args) throws InterruptedException {
		ArtUtils.wellcome();
		BankSystem bankSystem = BankSystem.getInstance();
		bankSystem.startSystem();
	}
}
