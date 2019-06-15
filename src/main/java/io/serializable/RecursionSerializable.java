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

public class RecursionSerializable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Random random = new Random();
	private int id;
	private SerializeData[] sd = {
		new SerializeData(random.nextInt(10)),
		new SerializeData(random.nextInt(10)),
		new SerializeData(random.nextInt(10))
	};
	public RecursionSerializable(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("{");
		result.append("id : " + id);
		result.append(",");
		for (SerializeData serializeData : sd) {
			result.append(serializeData);
		}
		result.append("}");
		return result.toString();
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		RecursionSerializable rs = new RecursionSerializable(1);
		
		
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("test.out"));
		oos.writeObject(rs);
		oos.close();
		
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test.out"));
		RecursionSerializable rs2 = (RecursionSerializable) ois.readObject();
		System.err.println(rs2);
		ois.close();
		
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos2 = new ObjectOutputStream(baos);
		oos2.writeObject(rs);
		oos2.close();
		
		ObjectInputStream ois2 = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
		RecursionSerializable rs3 = (RecursionSerializable) ois2.readObject();
		System.err.println(rs3);
		ois2.close();
	}
}

class SerializeData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int i;

	public SerializeData(int i) {
		this.i = i;
	}

	@Override
	public String toString() {
		return String.valueOf(i);
	}
}
