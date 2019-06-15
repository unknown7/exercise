package io.serializable;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class Blips {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Blip1 b1 = new Blip1();
		Blip2 b2 = new Blip2();
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Blip.out"));
		oos.writeObject(b1);
		oos.writeObject(b2);
		oos.close();
		
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Blip.out"));
		b1 = (Blip1) ois.readObject();
//		b2 = (Blip2) ois.readObject();
		ois.close();
	}
}

class Blip1 implements Externalizable {
	
	public Blip1() {
		System.err.println("Blip1 Constructor");
	}
	
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		System.err.println("Blip1.writeExternal");
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		System.err.println("Blip1.readExternal");
	}
}

class Blip2 implements Externalizable {

	Blip2() {
		System.err.println("Blip2 Constructor");
	}
	
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		System.err.println("Blip2.writeExternal");
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		System.err.println("Blip2.readExternal");
	}
	
}