package io;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class OSExecute {
	private static class OSExecueException extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public OSExecueException(String msg) {
			super(msg);
		}
	}
	public static void command(String command) {
		boolean err = false;
		try {
			Process process = new ProcessBuilder(command.split(" ")).start();
			BufferedReader results = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String s;
			while ((s = results.readLine()) != null) {
				System.out.println(s);
			}
			BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			while ((s = error.readLine()) != null) {
				System.err.println(s);
				err = true;
			}
		} catch (Exception e) {
			if (!command.startsWith("CMD /C"))
				command("CMD /C" + command);
			else
				throw new RuntimeException(e);
		}
		if (err)
			throw new OSExecueException("Errors executing " + command);
	}
}
