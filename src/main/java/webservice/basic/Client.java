package webservice.basic;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
//		Socket socket = new Socket("192.168.1.101", 1991);
//		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//		writer.write("Hello");
//		writer.close();
		InetSocketAddress isa = new InetSocketAddress("192.168.1.101", 1991);
		SocketChannel channel = SocketChannel.open(isa);
		channel.write(ByteBuffer.wrap("Hello".getBytes()));
		channel.close();
	}
}
