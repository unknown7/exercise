package io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

public class MyClient {
	public static void main(String[] args) throws IOException, InterruptedException {
		SocketChannel sc = SocketChannel.open();
		sc.connect(new InetSocketAddress(38888));
		ByteBuffer buff = ByteBuffer.allocate(1024);
		buff.put("Hello World".getBytes());
		buff.flip();
		sc.write(buff);
		buff.clear();
		TimeUnit.SECONDS.sleep(5);
		buff.put("ops!".getBytes());
		buff.flip();
		sc.write(buff);
		buff.clear();
//		sc.read(buff);
//		System.err.println(new String(buff.array()));
		sc.close();
	}
}
