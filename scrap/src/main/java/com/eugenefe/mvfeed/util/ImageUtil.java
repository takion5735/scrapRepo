package com.eugenefe.mvfeed.util;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageUtil {
	private final static Logger logger = LoggerFactory.getLogger(ImageUtil.class);
	
	public static void getImages(String directory, String src) throws IOException {
		// Exctract the name of the image from the src attribute
		int indexname = src.lastIndexOf("/");
		if (indexname == src.length()) {
			src = src.substring(1, indexname);
		}
		indexname = src.lastIndexOf("/");
		String name = src.substring(indexname, src.length());
		logger.info("File Name : {}", name);

		// Open a URL Stream
		URL url = new URL(src);
		InputStream in = url.openStream();

		FileUtil.makeDir(directory);
		OutputStream out = new BufferedOutputStream(new FileOutputStream(directory + "/" +  name));

		for (int b; (b = in.read()) != -1;) {
			out.write(b);
		}
		out.close();
		in.close();
	}
	
	public static void downloadImages(String directory, String src) throws IOException {
		// Exctract the name of the image from the src attribute
		int indexname = src.lastIndexOf("/");
		if (indexname == src.length()) {
			src = src.substring(1, indexname);
		}
		indexname = src.lastIndexOf("/");
		String name = src.substring(indexname, src.length());
		logger.info("File Name : {}", name);

		// Open a URL Stream
		URL url = new URL(src);
		InputStream in = url.openStream();

		FileUtil.makeDir(directory);
		OutputStream out = new BufferedOutputStream(new FileOutputStream(directory + "/" +  name));

		for (int b; (b = in.read()) != -1;) {
			out.write(b);
		}
		out.close();
		in.close();
	}

	
}
