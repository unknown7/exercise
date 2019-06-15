package io.zip;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZIPcompress {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("data.txt"));
		BufferedOutputStream bos = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream("data.gz")));
		System.err.println("Writing file");
		int c;
		while ((c = br.read()) != -1) {
			bos.write(c);
		}
		br.close();
		bos.close();
		System.err.println("Reading file");
		BufferedReader br2 = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream("data.gz"))));
		String s;
		while ((s = br2.readLine()) != null) {
			System.err.println(s);
		}
		br2.close();
	}
}
