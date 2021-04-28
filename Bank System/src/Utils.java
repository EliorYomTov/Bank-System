
public class Utils {
	public static int generateRandom(int min, int max) {
		return (int) (Math.random() * max-min) + min;
	}
}
