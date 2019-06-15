package designpattern.chainofresponsibility;

public class SensitiveFilter implements Filter {
	@Override
	public void doFilter(Request request, Response response, FilterChain chain) {
		System.err.println("SensitiveFilter proccess request...2");
		request.setRequest(request.getRequest().replace("习近平", "习大大").replace("敏感", "不敏感"));
		chain.doFilter(request, response, chain);
		System.err.println("SensitiveFilter proccess response...2");
		response.setResponse(response.getResponse() + "-sensitive");
	}
}
