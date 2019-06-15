package io;

import java.io.File;

public class ProccessFiles {
	public interface Strategy {
		void proccess(File file);
	}
	private Strategy strategy;
	private String ext;
	public ProccessFiles (Strategy strategy, String ext) {
		this.strategy = strategy;
		this.ext = ext;
	}
	public void start(String[] args) {
		try {
			if (args.length == 0)
				proccessDirectoryTree(new File("."));
			else
				for (String arg : args) {
					File fileArg = new File(arg);
					if (fileArg.isDirectory())
						proccessDirectoryTree(fileArg);
					else {
						if (!arg.endsWith("." + ext))
							arg += "." + ext;
						strategy.proccess(new File(arg).getCanonicalFile());
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void proccessDirectoryTree(File root) throws Exception {
		for (File file : Dictionary.walk(root.getAbsolutePath(), ".*\\." + ext))
			strategy.proccess(file.getCanonicalFile());
	}
	
	public static void main(String[] args) {
		new ProccessFiles(new ProccessFiles.Strategy() {
			@Override
			public void proccess(File file) {
				System.err.println(file);
			}
		}, "java").start(args);
	}
}
