package designpattern.chainofresponsibility;

public class Main {
	public static void main(String[] args) {
		FilterChain fc = new FilterChain();
		fc.addFilter(new HTMLFilter()).addFilter(new SensitiveFilter());
		FilterChain fc2 = new FilterChain();
		fc2.addFilter(new EmojiFilter());
		fc.addFilter(fc2);
		
		Request request = new Request();
		request.setRequest("哈哈@@，习近平，<script>，敏感");
		Response response = new Response();
		response.setResponse("response");
		fc.doFilter(request, response, fc);
		System.err.println(request.getRequest());
		System.err.println(response.getResponse());
	}
}
