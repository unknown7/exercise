package io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MyServer {
    private static final int PORT = 38888;
	public static void main(String[] args) throws IOException, InterruptedException {
		MyServer server = new MyServer(PORT);
        server.listen(); 
	}

	private ByteBuffer send = ByteBuffer.allocate(1024);
	private ByteBuffer receive = ByteBuffer.allocate(1024);
	Selector selector;

	public MyServer(int port) throws IOException {
		// 打开服务器套接字通道
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

		// 检索与此通道关联的服务器套接字
		ServerSocket serverSocket = serverSocketChannel.socket();
		// 套接字的地址端口绑定
		serverSocket.bind(new InetSocketAddress(port));
		// 服务器配置为非阻塞
		serverSocketChannel.configureBlocking(false);
		// 通过open()方法找到Selector
		selector = Selector.open();
		// 注册到selector，等待连接
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		System.out.println("Server Start----" + PORT + ":");
		// 向发送缓冲区加入数据
		send.put("data come from server".getBytes());
	}

	// 监听
	private void listen() throws IOException {
		while (true) {
			// 等待一个连接，可能会返回多个key
			int count = selector.select();
			System.out.println("count=" + count);
			// 返回此选择器的已选择键集。
			Set<SelectionKey> selectionKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = selectionKeys.iterator();
			while (iterator.hasNext()) {
				SelectionKey selectionKey = iterator.next();
                handle(selectionKey);
                // 这里记得手动的把他remove掉，不然selector中的selectedKeys集合不会自动去除
                iterator.remove();
			}
		}
	}

	// 处理请求
	private void handle(SelectionKey selectionKey) throws IOException {
		ServerSocketChannel server;
		SocketChannel client;
		String receiveText;
		String sendText;
		int count = 0;
		// 测试此键的通道是否已准备好接受新的套接字连接。
		if (selectionKey.isAcceptable()) {
			System.out.println("selectionKey.isAcceptable()");
			// 返回为之创建此键的通道。
			server = (ServerSocketChannel) selectionKey.channel();
			// 此方法返回的套接字通道（如果有）将处于阻塞模式。
			client = server.accept();
			// 配置为非阻塞
			client.configureBlocking(false);
			// 注册到selector，等待连接
			client.register(selector, SelectionKey.OP_READ);
		} else if (selectionKey.isReadable()) {
			System.out.println("selectionKey.isReadable()");
			// 返回为之创建此键的通道。
			client = (SocketChannel) selectionKey.channel();
			// 将缓冲区清空以备下次读取
			receive.clear();
			// 读取服务器发送来的数据到缓冲区中
            int read = client.read(receive);
            while (read != -1) {
                receive.flip();
                while (receive.hasRemaining()) {
                    System.err.print((char) receive.get());
                }
                receive.clear();
                System.err.println("begin read..");
                read = client.read(receive);
                System.err.println("end read..READ=" + read);
            }
            System.err.println();
            client.close();
		} else if (selectionKey.isWritable()) {
			System.out.println("selectionKey.isWritable()");
			// 将缓冲区清空以备下次写入
			send.flip();
			// 返回为之创建此键的通道。
			client = (SocketChannel) selectionKey.channel();
			// 输出到通道
			client.write(send);
			// selectionKey.interestOps(SelectionKey.OP_READ);
		}
	}
}
