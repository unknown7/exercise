package collection.linkedlist;

public class Student {
	String name;
	int age;
	public Student(String name, int age) {
		this.name = name;
		this.age = age;
	}
	@Override
	public String toString() {
		return "{name:" + name + ",age:" + age + "}";
	}
	@Override
	public boolean equals(Object obj) {
		return obj != null && obj instanceof Student && ((Student) obj).name.equals(name);
	}
}
