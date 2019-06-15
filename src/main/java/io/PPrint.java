package io;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class PPrint {
	public static String pformat(Collection<?> c) {
		Iterator<?> it = c.iterator();
		if (!it.hasNext()) return "";
		StringBuilder builder = new StringBuilder("[");
		for (;;) {
			Object next = it.next();
			builder.append('\n').append(next);
			if (!it.hasNext())
				return builder.append('\n').append(']').toString();
		}
	}
	public static void pprint(Collection<?> c) {
		System.err.println(pformat(c));
	}
	public static void pprint(Object[] o) {
		System.err.println(pformat(Arrays.asList(o)));
	}
}
