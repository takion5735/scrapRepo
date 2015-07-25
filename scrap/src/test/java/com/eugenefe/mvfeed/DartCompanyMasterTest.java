package com.eugenefe.mvfeed;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eugenefe.mvfeed.dart.DartCompany;

public class DartCompanyMasterTest {
	private final static Logger logger = LoggerFactory.getLogger(DartCompanyMasterTest.class);
	private static Properties properties = new Properties();
//	private static String filePath = "/home/takion77/isincode/dartCompany";
	private static String filePath = "D:\\Dev\\DartCompany\\dartCompany";

	private static List<DartCompany> rst = new ArrayList<DartCompany>();

	public static void main(String[] args) {
		int index =0;
		try {
			properties.load(DartCompanyMasterTest.class.getResourceAsStream("/url.properties"));
			// String url = properties.getProperty("dart");
			String url = properties.getProperty("dartCorp");
//			int pageNum = getPageNum(url);
			int pageNum =2;
			
			for (int i = 1; i <= pageNum; i++) {
				index = i;
				logger.info("pageNum : {}", i);
				getDartCompanyList(url, i);
			}
			writeDartCompayList(index);
		} catch (Exception e) {
			writeDartCompayList(index);
		}
		for (DartCompany aa : rst) {
			logger.info("AAA:{},{}", aa.getComCode(), aa.getBizNum());
		}
		
		
		

	}

	private static void writeDartCompayList(int i){
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(filePath+ i+ ".txt"));
			for (DartCompany aa : rst) {
//				logger.info("RST: {},{}", aa.getEprodType(), aa.getProdName());
				out.write(aa.toString());
				out.newLine();
			}
			out.close();
		} catch (IOException ex) {

		}
	}
	private static void getDartCompanyList(String url, int index)
			throws Exception {
		Document doc = getDocument(url, index);
		Elements elements = doc.select("div[class =table_scroll]>table>tbody>tr");

		for (Element el : elements) {
			DartCompany temp = new DartCompany();
//			logger.info("Size:{}, {}",el.child(0).attr("title"));

			temp.setComCode(el.child(0).child(0).child(2).attr("value"));
			temp.setComName(el.child(0).child(0).child(3).attr("value"));
			temp.setCeoName(el.child(1).text());
			temp.setKrxShortCode(el.child(2).text());
			temp.setIndustry(el.child(3).text());
			temp.setPageNum(index);

			
			String[] arr =el.child(0).attr("title").split(":");
			if(arr.length==6){
				String zz = el.child(0).attr("title").split(":")[4];
//TODO :String check
				int idx = zz.lastIndexOf("2");
				temp.setBizNum(zz.substring(0, idx));
			}
			
			rst.add(temp);
		}

		// for( DartCompany aa : rst){
		// logger.info("AAA:{},{}", aa.getComCode(), aa.getBizNum());
		// }
	}

	private static int getPageNum(String url) {
		Document doc;
		try {
			doc = Jsoup.connect(url).timeout(10000000)
			// .data("currentPage","2")
			// .data("searchIndex","2")
			// .data("textCrpNm", "援����)
			// .data("textCrpCik", "00386937")
			// .post();
					.get();
			
			Elements aa = doc.select("p[class=page_info]");
			String[] pageString = aa.text().split("]");
			logger.info("aa:{}", aa.text());
			int indexname = pageString[0].lastIndexOf("/");
			String pageNum = pageString[0].substring(indexname + 1,	pageString[0].length());
			
			return Integer.valueOf(pageNum);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return 1;
	}

	private static Document getDocument(String url, int pageIndex) {
		Document doc;
		try {
			doc = Jsoup.connect(url).timeout(1000000000)
					.data("currentPage", String.valueOf(pageIndex))
					// .data("searchIndex","2")
					// .data("textCrpNm", "援����)
					// .data("textCrpCik", "00386937")
					// .post();
					.get();
			return doc;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
