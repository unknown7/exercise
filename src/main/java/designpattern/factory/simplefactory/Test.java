package designpattern.factory.simplefactory;

import java.io.FileOutputStream;
import java.io.IOException;

public class Test extends ResourceProvidor {
	@Override
	public void handle(String read) throws IOException {
		bos = new FileOutputStream("/home/unknown7/test.txt", true);
		bos.write((read + "\n").getBytes());
	}
	public static void main(String[] args) {
		new Test();
	}
}
