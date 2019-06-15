package designpattern.chainofresponsibility;

public class EmojiFilter implements Filter {
	@Override
	public void doFilter(Request request, Response response, FilterChain chain) {
		System.err.println("EmojiFilter proccess request...3");
		request.setRequest(request.getRequest().replace("@@", "^~^"));
		chain.doFilter(request, response, chain);
		System.err.println("EmojiFilter proccess response...3");
		response.setResponse(response.getResponse() + "-emoji");
	}
}
