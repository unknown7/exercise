package interview;

public class ValueOrReference {
	public static void main(String[] args) {
		User u = new User(1, "a");
		System.err.println(u);
		change(u);
		System.err.println(u);
	}
	public static void change(User u) {
		u = new User(1, "b");
	}
}
class User {
	int id;
	String name;
	User(int id, String name) {
		this.id = id;
		this.name = name;
	}
	@Override
	public String toString() {
		return "User id:" + id + ",name:" + name;
	}
}