package io.serializable;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class Blip3 implements Externalizable {
	
	private int i;
	private String s;
	
	public Blip3() {
		System.err.println("Blip3 Constructor");
	}
	
	public Blip3(int i, String s) {
		this.i = i;
		this.s = s;
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeInt(i);
		out.writeObject(s);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
//		this.i = in.readInt();
//		this.s = (String) in.readObject();
	}
	
	@Override
	public String toString() {
		return i + s;
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Blip3 b3 = new Blip3(3, "a");
		System.err.println(b3);
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Blip3.out"));
		oos.writeObject(b3);
		oos.close();
		
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Blip3.out"));
		b3 = (Blip3) ois.readObject();
		System.err.println(b3);
		ois.close();
	}
}
