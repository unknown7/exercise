package io.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class TransferTo {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		FileChannel
		in = new FileInputStream("data.txt").getChannel(),
		out = new FileOutputStream("data2.txt").getChannel();
		
		in.transferTo(0, in.size(), out);
	}
}
