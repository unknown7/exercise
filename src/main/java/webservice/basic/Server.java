package webservice.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(1991);
		Socket accept = server.accept();
		BufferedReader reader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
		System.err.println(reader.readLine());
		reader.close();
	}
}
