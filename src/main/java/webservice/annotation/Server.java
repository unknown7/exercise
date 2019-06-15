package webservice.annotation;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService
public class Server {

	public void invoke(String methodName, String args) {
		System.err.println(methodName);
		System.err.println(args);
	}
	
	public static void main(String[] args) {
		Endpoint.publish("http://192.168.1.101:1991/invokea", new Server());
		System.err.println("server start up");
	}
}
