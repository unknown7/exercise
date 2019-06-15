package io.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZIPcompress {
	private static String[] files = { "data.txt", "data2.txt" };

	public static void main(String[] args) throws IOException {
		ZipOutputStream zos = new ZipOutputStream(
				new CheckedOutputStream(
					new FileOutputStream("test.zip"),
					new Adler32()
				)
			);
		BufferedOutputStream bos = new BufferedOutputStream(zos);
		zos.setComment("A test of Java Zipping");
		for (String file : files) {
			System.err.println("Writing file " + file);
			BufferedReader br = new BufferedReader(new FileReader(file));
			zos.putNextEntry(new ZipEntry(file));
			int c;
			while ((c = br.read()) != -1) {
				bos.write(c);
			}
			br.close();
			bos.flush();
		}
		bos.close();
		zos.close();
		
		System.err.println("Reading file");
		
		ZipInputStream zis = new ZipInputStream(
				new CheckedInputStream(
					new FileInputStream("test.zip"),
					new Adler32()
				)
			);
		BufferedInputStream bis = new BufferedInputStream(zis);
		ZipEntry entry;
		while ((entry = zis.getNextEntry()) != null) {
			System.err.println("Reading file " + entry);
			int c;
			while ((c = bis.read()) != -1) {
				System.err.print((char) c);
			}
			System.err.println();
		}
		bis.close();
		zis.close();
		
		ZipFile zf = new ZipFile("test.zip");
		Enumeration<? extends ZipEntry> entries = zf.entries();
		while (entries.hasMoreElements()) {
			ZipEntry e = entries.nextElement();
			System.err.println(e);
		}
		zf.close();
	}
}
