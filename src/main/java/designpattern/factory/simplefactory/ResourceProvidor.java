package designpattern.factory.simplefactory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class ResourceProvidor {
	protected InputStream bis;
	protected OutputStream bos;
	{
		bis = new BufferedInputStream(System.in);
		bos = new BufferedOutputStream(System.out);
		try {
			byte[] b = new byte[16];
			while (bis.read(b) != -1) {
				String read = new String(b).trim();
				handle(read);
				b = new byte[16];
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bis.close();
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public abstract void handle(String read) throws IOException;
}
