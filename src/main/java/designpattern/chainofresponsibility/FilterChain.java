package designpattern.chainofresponsibility;

import java.util.ArrayList;
import java.util.List;

public class FilterChain implements Filter {
	private int i;
	private List<Filter> filters = new ArrayList<Filter>();
	public FilterChain addFilter(Filter f) {
		filters.add(f);
		return this;
	}
	@Override
	public void doFilter(Request request, Response response, FilterChain chain) {
		if (i >= filters.size()) return;
		Filter filter = filters.get(i);
		i++;
		filter.doFilter(request, response, chain);
	}
}
