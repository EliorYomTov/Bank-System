
public abstract class Person {
	private static final int MIN_AGE = 16;
	private static final int MAX_AGE = 120;
	private static int idCounter = 0;
	private int id;
	private String name;
	private double age;

	public Person() {
		id = ++idCounter;
		name = this.getClass().getSimpleName() + id;
		age = Utils.generateRandom(MIN_AGE, MAX_AGE);
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getAge() {
		return age;
	}

	@Override
	public String toString() {
		return "id: " + id + " name: " + name + " age: " + age;
	}
}
