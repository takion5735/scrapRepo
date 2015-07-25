package com.eugenefe.mvfeed;

import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eugenefe.mvfeed.dart.DartReport;

public class DartTest {
	private final static Logger logger = LoggerFactory.getLogger(DartTest.class);
	private static Properties properties = new Properties();
//	private static String filePath = "/home/takion77/isincode/dartCompany";
	private static String filePath = "D:\\Dev\\dartCompany\\";

	private static Set<DartReport> rst = new HashSet<DartReport>();

	public static void main(String[] args) {
		int index =0;
		try {
			properties.load(DartTest.class.getResourceAsStream("/url.properties"));
//			 String url = properties.getProperty("dart");
			String url = properties.getProperty("dartCorp");
//			int pageNum = getPageNum(url);
			 
			Document doc = getDocument(url);
			logger.info("doc1 : {}", doc);
			Elements contents = doc.select("div[class=table_list]>table>tbody>tr");
			
			for(Element el : contents){
				logger.info("doc : {},{}", el.text(), el.child(2).child(0).attr("href"));
				DartReport temp = new DartReport();
				temp.setComCode(el.child(1).child(0).child(0).attr("onclick").substring(14, 22));
				temp.setComName(el.child(1).text());
				temp.setReportName(el.child(2).text());
				temp.setUrl(el.child(2).child(0).attr("href"));
				temp.setReporterName(el.child(3).text());
				temp.setSubmitDate(el.child(4).text());
				temp.setDesc(el.child(5).text());
				rst.add(temp);
			}
			
			logger.info("size:{}", getPageNum(url));
			
		} catch (Exception e) {
		}
		for (DartReport aa : rst) {
			logger.info("DartReport : {}, {}", aa.getComCode(), aa.getDesc());
		}
		
		
		

	}

	/*private static void writeDartCompayList(int i){
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
	}*/
	

	private static int getPageNum(String url) {
		Document doc;
		try {
			doc = Jsoup.connect(url).timeout(10000000)
					.data("currentPage", "2")
					// .data("searchIndex","2")
					 .data("textCrpNm", "")
					 .data("textCrpCik", "00386937")
					 .data("startDate", "20140103")
					 .data("endDate", "20140703")
					 .data("finalReport","recent")
					 .data("sort","date")
					 .data("series","desc")
					.post();
			
//			Elements aa = doc.select("a[href=#next]");
			Elements aa = doc.select("div[class=page_list]>p[class=page_info]");
			
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

	private static Document getDocument(String url) {
		Document doc;
		try {
			doc = Jsoup.connect(url).timeout(1000000000)
					.data("currentPage", "1")
					// .data("searchIndex","2")
//					 .data("textCrpNm", "21세기조선")
//					 .data("textCrpCik", "00386937")
					 .data("startDate", "20140103")
					 .data("endDate", "20140703")
					 .data("finalReport","recent")
					 .data("sort","date")
					 .data("series","desc")
				
					 .post();
//					.get();
			return doc;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
