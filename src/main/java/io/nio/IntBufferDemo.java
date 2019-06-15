package io.nio;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class IntBufferDemo {
	private static final int BSIZE = 1024;

	public static void main(String[] args) {
		ByteBuffer bb = ByteBuffer.allocate(BSIZE);
		IntBuffer ib = bb.asIntBuffer();
		ib.put(new int[] { 1, 2, 3 });
		System.err.println(ib.get(1));
		ib.put(1, 22);
		ib.flip();
		while (ib.hasRemaining()) {
			System.err.println(ib.get());
		}
	}
}
