package collection.linkedlist;

import java.util.Random;

public class Main {
	private static final String[] NAMES = { "Jeff", "Jack", "Ann", "Amy",
			"Anderson", "Chris", "Odom", "Kidd", "Allen", "Park", "Diaw",
			"Wilford" };

	public static void main(String[] args) {
		Random random = new Random();

		SList<Student> list = new SList<Student>();
		for (int i = 0; i < NAMES.length; i++)
			list.add(new Student(NAMES[random.nextInt(NAMES.length)], i));
		System.err.println(list);

		System.err.println("------");

		SSet<Student> set = new SSet<Student>();
		for (int i = 0; set.size() < NAMES.length; i++)
			set.add(new Student(NAMES[random.nextInt(NAMES.length)], i));
		System.err.println(set);
	}
}
