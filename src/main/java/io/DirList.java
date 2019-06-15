package io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

public class DirList {
	public static void main(String[] args) {
		File path = new File(".");
		String[] list = path.list(new FilenameFilter() {
			private Pattern pattern = Pattern.compile(".git");
			@Override
			public boolean accept(File dir, String name) {
				return pattern.matcher(name).matches();
			}
		});
		Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
		System.err.println(Arrays.toString(list));
	}
}
