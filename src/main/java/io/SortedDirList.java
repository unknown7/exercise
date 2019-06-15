package io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

public class SortedDirList {
	private File file;
	public SortedDirList(String path) {
		file = new File(path);
	}
	public String[] list() {
		return file.list();
	}
	public String[] list(final String regex) {
		return file.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return Pattern.compile(regex).matcher(name).matches();
			}
		});
	}
	public static void main(String[] args) {
		SortedDirList sdl = new SortedDirList(".");
		System.err.println(Arrays.toString(sdl.list()));
		System.err.println(Arrays.toString(sdl.list(".git")));
	}
}
