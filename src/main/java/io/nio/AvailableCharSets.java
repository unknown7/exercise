package io.nio;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.SortedMap;

public class AvailableCharSets {
	public static void main(String[] args) {
		SortedMap<String,Charset> charsets = Charset.availableCharsets();
		Iterator<String> iterator = charsets.keySet().iterator();
		while (iterator.hasNext()) {
			String csName = iterator.next();
			System.err.println(csName);
		}
	}
}
