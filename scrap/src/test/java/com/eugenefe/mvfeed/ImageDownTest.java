package com.eugenefe.mvfeed;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eugenefe.mvfeed.util.FileUtil;
import com.eugenefe.mvfeed.util.ImageUtil;

public class ImageDownTest {
	private final static Logger logger = LoggerFactory
			.getLogger(ImageDownTest.class);
	// The url of the website. This is just an example
	private static final String webSiteURL = "http://www.supercars.net/garage2/119513/galleries.html";

	private static String linkUrl = "http://www.supercars.net/gallery/119513/3140/1.html";

	// The path of the folder that you want to save the images to
	private static final String folderPath = "/home/takion77/scrapping";
	private static String fileName;
	private static Map<String, String> rst = new HashMap<String, String>();

	public static void main(String[] args) {
		fileName = "/meta.txt";
		String fileName1 = "/meta_out.txt";
		List<String> excludeList = new ArrayList<String>();
//		excludeList.add("http://www.supercars.net/gallery/119513/3114/1.html");
		int cnt =0;
		
		try {
//			FileUtil.writeFile(folderPath+ fileName, getUpLink(webSiteURL));

			/*Map<String, String> aa = FileUtil.readFileToMap(folderPath+fileName, ";");
			
			
			for(Map.Entry<String, String> entry : aa.entrySet()){
				cnt = cnt+1;
				rst.put(entry.getKey(), entry.getValue());
				logger.info("File Content :{},{},{}", cnt, entry.getKey());
//				if(!excludeList.contains(entry.getKey())){
					getSubLinks(entry.getKey(), rst);
//				}
			}
			
			FileUtil.writeFile(folderPath+ fileName1, rst);
			*/
			
			Map<String, String> bb = FileUtil.readFileToMap(folderPath+fileName1, ";");
			for( Map.Entry<String, String> entry : bb.entrySet()){
				logger.info("File Content :{},{}", entry.getKey(), entry.getValue());
				getlinksNew(entry.getKey(), entry.getValue());
				
			}
			
			

		} catch (IOException ex) {
			System.err.println("There was an error");
			// Logger.getLogger(DownloadImages.class.getName()).log(Level.SEVERE,null,
			// ex);
		}
	}

	

	private static Map<String, String> getUpLink(String url) throws IOException {
		Map<String, String> rst = new HashMap<String, String>();
		Document doc = Jsoup.connect(url).get();

		// Elements img = doc.getElementsByTag("div[class =theThumbs]");
		Elements links = doc.select("table[class= tborder]>tbody>tr>td>b>a");

		for (Element el : links) {
			String href = el.attr("href");
//			rst.put(el.text(), href);
			rst.put(href, el.text());
//			logger.info("Element0 :{},{}", href.toString(), el.text());
//			getSubLinks(href, rst);
		}
		return rst;
	}

	public static void aaaa() {
		try {

			Document doc = Jsoup.connect(webSiteURL).get();

			// Elements img = doc.getElementsByTag("div[class =theThumbs]");
			Elements links = doc
					.select("table[class= tborder]>tbody>tr>td>b>a");

			for (Element el : links) {
				String href = el.attr("href");
				logger.info("Element0 :{},{}", href.toString());

				Document thumbsDoc = Jsoup.connect(href).get();
				//
				Elements thumbs = doc.select("div[class =theThumbs]");

				for (Element aa : thumbs) {
					String imgHref = el.attr("href");
					logger.info("Element1 :{},{}", imgHref.toString());

					// Document imgDoc = Jsoup.connect(imgHref).get();
					//
					// Elements img1 = imgDoc.select("#largeImage>a>img");
					// logger.info("Element :{},{}", href.toString(),
					// img1.attr("alt").toString());
					// for(Element zz : img1){
					// String src1 = zz.absUrl("src");
					// getImages(src1);
					// }
				}

				// for each element get the srs url
				// String src = el.absUrl("src");

			}

		} catch (IOException ex) {
			System.err.println("There was an error");
			// Logger.getLogger(DownloadImages.class.getName()).log(Level.SEVERE,null,
			// ex);
		}
	}

	private static void getSubLinks(String link, Map<String, String> rst) throws IOException{
		Document doc = Jsoup.connect(link).get();

		Elements arrowBar = doc.select("div[class = arrowBar]");
		String header = null;
		String nextUrl = null;

		for (Element el : arrowBar) {
			header = el.getElementsByTag("h2").text();
			for (Element child : el.children()) {
				if (child.text().startsWith("Next")) {
					nextUrl = child.attr("href");
				}

			}
		}
		if (nextUrl != null) {
			rst.put(nextUrl, header);
			logger.info("Element1 :{},{}", nextUrl, header);
			getSubLinks(nextUrl, rst);
		}
	}
	
	private static void getlinksNew(String link, String subFolder) throws IOException {
		Document doc = Jsoup.connect(link).get();
		Elements thumbs = doc.select("div[class = theThumbs]>a");

		for (Element el : thumbs) {
			String href = el.attr("href");

			Document imageDoc = Jsoup.connect(href).get();

			Elements img2 = imageDoc.select("#contentcolumn>div[class = innertube] img");
			
//			logger.info("Element1 :{},{}", href.toString(), img2.size());
			

			for (Element zz : img2) {
//				logger.info("Element3 :{},{}", zz.toString());
				String src1 = zz.absUrl("src");
//				getImages(src1, subFolder);
				ImageUtil.getImages(folderPath+"/"+ subFolder, src1);
				// getImages(src1);
			}
			Elements img1 = imageDoc.select("#largeImage>a>img");
			for (Element zz : img1) {
//				logger.info("Element4 :{},{}", zz.toString());
				String src1 = zz.absUrl("src");
//				getImages(src1, subFolder);
				ImageUtil.getImages(folderPath+"/"+ subFolder, src1);
				// getImages(src1);
			}
		}
	}
	
	private static void getlinks(String link) throws IOException {

		Document doc = Jsoup.connect(link).get();

		Elements arrowBar = doc.select("div[class = arrowBar]");
		String subFolder = null;
		String nextPage = null;

		for (Element el : arrowBar) {
			subFolder = el.getElementsByTag("h2").text();
			for (Element child : el.children()) {
				if (child.text().startsWith("Next")) {
					nextPage = child.attr("href");
				}

			}
		}
		

		logger.info("Element0 :{},{}", nextPage, subFolder);
		Elements thumbs = doc.select("div[class = theThumbs]>a");

		for (Element el : thumbs) {
			String href = el.attr("href");
//			logger.info("Element1 :{},{}", href.toString(), thumbs.size());

			Document imageDoc = Jsoup.connect(href).get();

			// Elements img1 = imageDoc.select("#largeImage>a>img");

			Elements img2 = imageDoc.select("#contentcolumn>div[class = innertube] img");

			// for (Element zz : img1) {
			// logger.info("Element2 :{},{}", zz.toString());
			// String src1 = zz.absUrl("src");
			// // getImages(src1, zz.attr("alt"));
			// // getImages(src1);
			// }

			for (Element zz : img2) {
				logger.info("Element3 :{},{}", zz.toString());
				String src1 = zz.absUrl("src");
				getImages(src1, subFolder);
				// getImages(src1);
			}
		}
		if (nextPage != null) {
			getlinks(nextPage);
		}
	}

	
	private static void getImages(String src, String subFolder)	throws IOException {

		String folder = null;

		// Exctract the name of the image from the src attribute
		int indexname = src.lastIndexOf("/");

		if (indexname == src.length()) {
			src = src.substring(1, indexname);
		}

		indexname = src.lastIndexOf("/");
		String name = src.substring(indexname, src.length());

		System.out.println(name);

		// Open a URL Stream
		URL url = new URL(src);
		InputStream in = url.openStream();

		makeDir(folderPath + "/" + subFolder);
		OutputStream out = new BufferedOutputStream(new FileOutputStream(
				folderPath + "/" + subFolder + name));

		for (int b; (b = in.read()) != -1;) {
			out.write(b);
		}
		out.close();
		in.close();

	}

	private static void getImages(String src) throws IOException {

		String folder = null;

		// Exctract the name of the image from the src attribute
		int indexname = src.lastIndexOf("/");

		if (indexname == src.length()) {
			src = src.substring(1, indexname);
		}

		indexname = src.lastIndexOf("/");
		String name = src.substring(indexname, src.length());

		System.out.println(name);

		// Open a URL Stream
		URL url = new URL(src);
		InputStream in = url.openStream();

		OutputStream out = new BufferedOutputStream(new FileOutputStream(
				folderPath + name));

		for (int b; (b = in.read()) != -1;) {
			out.write(b);
		}
		out.close();
		in.close();

	}

	private static void makeDir(String directory) {
		File theDir = new File(directory);

		// if the directory does not exist, create it
		if (!theDir.exists()) {
			System.out.println("creating directory: " + directory);
			boolean result = false;

			try {
				theDir.mkdir();
				result = true;
			} catch (SecurityException se) {
				// handle it
			}
			if (result) {
				System.out.println("DIR created");
			}
		}
	}

}
