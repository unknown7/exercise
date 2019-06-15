package io.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class UsingBuffers {
	private static void symmetricScramble(CharBuffer buffer) {
		while (buffer.hasRemaining()) {
			buffer.mark();
			char c1 = buffer.get();
			char c2 = buffer.get();
			buffer.reset();
			buffer.put(c2).put(c1);
		}
	}
	public static void main(String[] args) {
		char[] chars = "UsingBuffers".toCharArray();
		ByteBuffer bb = ByteBuffer.allocate(chars.length * 2);
		CharBuffer cb = bb.asCharBuffer();
		cb.put(chars);
		System.err.println(cb.rewind());
		symmetricScramble(cb);
		System.err.println(cb.rewind());
	}
}
