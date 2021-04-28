
public class Run {
	public static void main(String[] args)  {
		ArtUtils.wellcome();
		BankSystem bankSystem = BankSystem.getInstance();
		bankSystem.startSystem();
	}
}
