package io.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo {

    public static void main(String[] args) throws IOException {
        RandomAccessFile rw = new RandomAccessFile("D:\\projects\\idea\\exercise\\src\\main\\java\\io\\nio\\FileChannelDemo.java", "rw");
        FileChannel channel = rw.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int read = channel.read(buffer);
        while (read != -1) {
            System.err.println("Read " + read);
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.err.print((char) buffer.get());
            }
            buffer.clear();
            read = channel.read(buffer);
        }
        rw.close();
    }
}
