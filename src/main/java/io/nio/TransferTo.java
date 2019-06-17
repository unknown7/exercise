package io.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class TransferTo {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
//		FileChannel
//		in = new FileInputStream("data.txt").getChannel(),
//		out = new FileOutputStream("data2.txt").getChannel();
//
//		in.transferTo(0, in.size(), out);

		FileChannel from = new RandomAccessFile("D:\\projects\\idea\\exercise\\src\\main\\java\\io\\nio\\TransferTo.java", "rw").getChannel();
		FileChannel to = new RandomAccessFile("D:\\projects\\idea\\exercise\\src\\main\\java\\io\\nio\\TransferToTemp.java", "rw").getChannel();
		from.transferTo(0, from.size(), to);
	}
}
