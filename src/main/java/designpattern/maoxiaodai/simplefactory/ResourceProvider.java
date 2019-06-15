package designpattern.maoxiaodai.simplefactory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class ResourceProvider {
	protected InputStream is = new BufferedInputStream(System.in);
	protected OutputStream os = new BufferedOutputStream(System.out);
	{
		try {
			byte[] bytes = new byte[16];
			while (is.read(bytes) != -1) {
				String line = new String(bytes).trim();
				handle(line);
				bytes = new byte[16];
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public abstract void handle(String line);
}
