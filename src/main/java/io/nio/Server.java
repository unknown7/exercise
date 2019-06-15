package io.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	public static Map<Socket, Long> geym_time_stat = new HashMap<Socket, Long>();

	class EchoClient {
		private LinkedList<ByteBuffer> outq;

		EchoClient() {
			outq = new LinkedList<ByteBuffer>();
		}

		public LinkedList<ByteBuffer> getOutputQueue() {
			return outq;
		}

		public void enqueue(ByteBuffer bb) {
			outq.addFirst(bb);
		}
	}

	class HandleMsg implements Runnable {
		SelectionKey sk;
		ByteBuffer bb;

		public HandleMsg(SelectionKey sk, ByteBuffer bb) {
			super();
			this.sk = sk;
			this.bb = bb;
		}

		@Override
		public void run() {
			EchoClient echoClient = (EchoClient) sk.attachment();
			echoClient.enqueue(bb);
			sk.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
			selector.wakeup();
		}

	}
	
	private Selector selector;
	private ExecutorService exec = Executors.newCachedThreadPool();
	public void serve() throws IOException {
		selector = SelectorProvider.provider().openSelector();
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.configureBlocking(false);
		InetSocketAddress isa = new InetSocketAddress(8888);
		ssc.socket().bind(isa);

		ssc.register(selector, SelectionKey.OP_ACCEPT);
		
		for (;;) {
			selector.select();
			Set<SelectionKey> keys = selector.selectedKeys();
			
			long e = 0;
			Iterator<SelectionKey> it = keys.iterator();
			while (it.hasNext()) {
				SelectionKey key = it.next();
				it.remove();
				if (key.isAcceptable())
					accept(key);
				else if (key.isValid() && key.isReadable()) {
					if (!geym_time_stat.containsKey(((SocketChannel) key.channel()).socket())) {
						geym_time_stat.put(
								((SocketChannel) key.channel()).socket(),
								System.currentTimeMillis());
					}
					read(key);
				} else if (key.isValid() && key.isWritable()) {
					write(key);
					e = System.currentTimeMillis();
					long b = geym_time_stat.remove(((SocketChannel) key.channel()).socket());
					System.out.println("spend:" + (e - b) + "ms");
				}
			}
		}
	}
	private void accept(SelectionKey key) {
		ServerSocketChannel server = (ServerSocketChannel) key.channel();
		SocketChannel clientChannel;
		try {
			clientChannel = server.accept();
			clientChannel.configureBlocking(false);
			SelectionKey clientKey = clientChannel.register(selector, SelectionKey.OP_READ);
			EchoClient echoClinet = new EchoClient();
			clientKey.attach(echoClinet);
			InetAddress clientAddress = clientChannel.socket().getInetAddress();
			System.out.println("Accepted connection from " + clientAddress.getHostAddress());
		} catch (Exception e) {
		}
	}
	private void write(SelectionKey sk) {
		SocketChannel channel = (SocketChannel) sk.channel();
		EchoClient echoClient = (EchoClient) sk.attachment();
		LinkedList<ByteBuffer> outq = echoClient.getOutputQueue();
		ByteBuffer bb = outq.getLast();
		try {
			int len = channel.write(bb);
			if (len == -1) {
				disconnect(sk);
				return;
			}
			if (bb.remaining() == 0) {
				outq.removeLast();
			}
		} catch (Exception e) {
			disconnect(sk);
		}
		if (outq.size() == 0) {
			sk.interestOps(SelectionKey.OP_READ);
		}
	}

	private void read(SelectionKey sk) {
		SocketChannel channel = (SocketChannel) sk.channel();
		ByteBuffer bb = ByteBuffer.allocate(8192);
		int len;
		try {
			len = channel.read(bb);
			if (len < 0) {
				disconnect(sk);
				return;
			}
		} catch (Exception e) {
			disconnect(sk);
			return;
		}
		bb.flip();
		exec.execute(new HandleMsg(sk, bb));
	}

	private void disconnect(SelectionKey sk) {
	}
	
	public static void main(String[] args) throws IOException {
		Server echoServer = new Server();
		echoServer.serve();
	}
}
