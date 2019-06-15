package designpattern.chainofresponsibility;

public class HTMLFilter implements Filter {
	@Override
	public void doFilter(Request request, Response response, FilterChain chain) {
		System.err.println("HTMLFilter process request...1");
		request.setRequest(request.getRequest().replace('<', '[').replace('>', ']'));
		chain.doFilter(request, response, chain);
		System.err.println("HTMLFilter process response...1");
		response.setResponse(response.getResponse() + "-html");
	}
}
