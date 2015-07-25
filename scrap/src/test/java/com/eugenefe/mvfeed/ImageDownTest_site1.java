package com.eugenefe.mvfeed;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eugenefe.mvfeed.util.FileUtil;
import com.eugenefe.mvfeed.util.ImageUtil;

public class ImageDownTest_site1 {
	private final static Logger logger = LoggerFactory
			.getLogger(ImageDownTest_site1.class);
	private static final String webSiteURL = "http://www.supercars.net/garage2/119513/galleries.html";

	// The path of the folder that you want to save the images to
//	private static final String folderPath = "/home/takion77/scrapping";
//	private static String rootLinkFile = folderPath+ "/meta.txt";
//	private static String subLinkFile = folderPath +"/meta_out.txt";
//	private static String imageListFile = folderPath+"/meta_img.txt";
	private static final String folderPath = "D:\\scrapping";
	private static String rootLinkFile = folderPath+ "\\meta.txt";
	private static String subLinkFile = folderPath +"\\meta_out.txt";
	private static String imageListFile = folderPath+"\\meta_img.txt";

	private static Map<String, String> rst = new HashMap<String, String>();
	private static Map<String, String> imgRst = new HashMap<String, String>();

	public static void main(String[] args) {
		try {
			// FileUtil.writeFile(folderPath+ fileName, getUpLink(webSiteURL));
			// writeLinkFile();
//			writeImageFile();
			 writeImage();
		} catch (Exception ex){
			System.err.println("There was an error");
			try {
				FileUtil.writeFile(imageListFile, imgRst);
			}catch(IOException e){
				
			}
		}
		
	}

	private static void writeImage() throws IOException {
		Map<String, String> bb = FileUtil.readFileToMap(imageListFile, ";");
		for (Map.Entry<String, String> entry : bb.entrySet()) {
			logger.info("File Content :{},{}", entry.getKey(), entry.getValue());
			ImageUtil.downloadImages(entry.getValue(), entry.getKey());
		}
	}

	private static void writeLinkFile() throws IOException, Exception{
		Map<String, String> aa = FileUtil.readFileToMap(rootLinkFile,";");

		int cnt = 0;
		for (Map.Entry<String, String> entry : aa.entrySet()) {
			cnt = cnt + 1;
			rst.put(entry.getKey(), entry.getValue());
			logger.info("File Content :{},{},{}", cnt, entry.getKey());
			getSubLinks(entry.getKey(), rst);
		}
		
		FileUtil.writeFile(subLinkFile, rst);
	}

	private static void writeImageFile() throws IOException, Exception {
		Map<String, String> aa = FileUtil.readFileToMap(subLinkFile,	";");
		
		for (Map.Entry<String, String> entry : aa.entrySet()) {
			logger.info("File Content :{},{}", entry.getKey(), entry.getValue());
			getImageSourceMap(entry.getKey(), entry.getValue(), imgRst);
		}

		FileUtil.writeFile(imageListFile, imgRst);
	}

	// *********************************util*****************************************

	private static Map<String, String> getLinkMap(String url)	throws Exception {
		Map<String, String> rst = new HashMap<String, String>();
		Document doc = Jsoup.connect(url).get();

		// Elements img = doc.getElementsByTag("div[class =theThumbs]");
		Elements links = doc.select("table[class= tborder]>tbody>tr>td>b>a");

		for (Element el : links) {
			String href = el.attr("href");
			rst.put(href, el.text());
			// logger.info("Element0 :{},{}", href.toString(), el.text());
		}
		return rst;
	}

	private static void getSubLinks(String link, Map<String, String> rst)
			throws Exception {
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

	private static void getImageSourceMap(String link, String subFolder, Map<String, String> imgRst) throws Exception {
		String src = null;

		Document doc = Jsoup.connect(link).get();
		Elements thumbs = doc.select("div[class = theThumbs]>a");

		for (Element el : thumbs) {
			String href = el.attr("href");

			Document imageDoc = Jsoup.connect(href).get();

			Elements img2 = imageDoc
					.select("#contentcolumn>div[class = innertube] img");
			// logger.info("Element1 :{},{}", href.toString(), img2.size());
			for (Element zz : img2) {
				// logger.info("Element3 :{},{}", zz.toString());
				src = zz.absUrl("src");
				imgRst.put(src, subFolder);
			}

			Elements img1 = imageDoc.select("#largeImage>a>img");
			for (Element zz : img1) {
				// logger.info("Element4 :{},{}", zz.toString());
				src = zz.absUrl("src");
				imgRst.put(src, subFolder);
			}
		}

	}
}
