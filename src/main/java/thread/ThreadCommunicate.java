package thread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class ThreadCommunicate {
	private static PipedInputStream pis;
	private static ObjectInputStream ois;
	private static PipedOutputStream pos;
	private static ObjectOutputStream oos;
	private static Queue<Student> students;
	private static boolean canRead;
	static {
		try {
			pis = new PipedInputStream();
			pos = new PipedOutputStream();
			
			pis.connect(pos);
			
			students = new LinkedList<Student>();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	static class ReadThread implements Runnable {
		@Override
		public void run() {
			synchronized (students) {
				if (canRead) {
					try {
						ois = new ObjectInputStream(pis);
						Student student = (Student) ois.readObject();
						System.err.println(student.id);
						System.err.println(student.name);
						canRead = false;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
		}
	}
	static class WriteThread implements Runnable {
		@Override
		public void run() {
			synchronized (students) {
				try {
					if (!canRead) {
						oos.writeObject(students.poll());
						canRead = true;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) throws Exception {
		String[] genders = { "male", "female" };
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			Professor professor = new Professor(i, String.valueOf(i));
			Student student = 
					new Student(
						i,
						String.valueOf(i),
						random.nextInt(20),
						genders[random.nextInt(genders.length)],
						professor
					);
			students.add(student);
		}
		Thread.sleep(1000);
		oos = new ObjectOutputStream(pos);
		
		while (students.peek() != null) {
			new Thread(new WriteThread()).start();
			new Thread(new ReadThread()).start();
		}
		
	}
}
class Student implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	public transient int id;
	public String name;
	public int age;
	public String gender;
	
	public Professor professor;
	
	public Student(int id, String name, int age, String gender,
			Professor professor) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.professor = professor;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Student student = (Student) super.clone();
		Professor professor = (Professor) student.professor.clone();
		student.professor = professor;
		return student;
	}
}
class Professor implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	public transient int id;
	public String name;
	
	public Professor(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}

