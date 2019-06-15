package io.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel.MapMode;

public class LargeMappedFiles {
	private static int length = 0x8FFFFFF;

	@SuppressWarnings("resource")
	public static void main(String[] args) throws FileNotFoundException,
			IOException {
		MappedByteBuffer out = new RandomAccessFile("test.data", "rw")
				.getChannel().map(MapMode.READ_WRITE, 0, length);
		for (int i = 0; i < length; i++) {
			out.put((byte) 'x');
		}
		System.err.println("Finished writing");
		for (int i = length / 2; i < length / 2 + 6; i++) {
			System.err.println((char) out.get(i));
		}
	}
}
