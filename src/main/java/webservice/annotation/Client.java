package webservice.annotation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class Client {
	public static void main(String[] args) throws IOException {
		URL url = new URL("http://192.168.1.101:1991/invoke");
		URLConnection c = url.openConnection();
		c.setDoInput(true);
		c.setDoOutput(true);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(c.getOutputStream()));
		writer.write("saveOrder");
		writer.close();
	}
}
