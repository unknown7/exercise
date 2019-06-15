package io.serializable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

public class Worm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Random random = new Random();
	private Data[] d = {
		new Data(random.nextInt(10)),
		new Data(random.nextInt(10)),
		new Data(random.nextInt(10))
	};
	private Worm next;
	private char c;

	public Worm(int i, char x) {
		System.err.println("Worm constructor: " + i);
		c = x;
		if (--i > 0)
			next = new Worm(i, (char) (x + 1));
	}
	public Worm() {
		System.err.println("Default constructor");
	}
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(":");
		result.append(c);
		result.append("(");
		for (Data data : d) {
			result.append(data);
		}
		result.append(")");
		if (next != null) {
			result.append(next);
		}
		return result.toString();
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Worm w = new Worm(6, 'a');
		System.err.println("w = " + w);
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("worm.out"));
		oos.writeObject("Worm storage\n");
		oos.writeObject(w);
		oos.close();
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("worm.out"));
		String s = (String) ois.readObject();
		Worm w2 = (Worm) ois.readObject();
		System.err.println(s + "w2 = " + w2);
		ois.close();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos2 = new ObjectOutputStream(baos);
		oos2.writeObject("Worm storage\n");
		oos2.writeObject(w);
		oos2.flush();
		ObjectInputStream ois2 = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
		s = (String) ois2.readObject();
		Worm w3 = (Worm) ois2.readObject();
		System.err.println(s + "w3 = " + w3);
	}
}

class Data implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int n;

	public Data(int n) {
		this.n = n;
	}

	@Override
	public String toString() {
		return Integer.toString(n);
	}
}