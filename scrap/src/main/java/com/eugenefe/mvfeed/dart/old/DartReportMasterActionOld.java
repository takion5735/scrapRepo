package com.eugenefe.mvfeed.dart.old;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eugenefe.mvfeed.dart.DartReport;
import com.eugenefe.mvfeed.util.ScrapException;
import com.eugenefe.mvfeed.util.WebScrappUtil;

public class DartReportMasterActionOld {
	private static final Logger logger = LoggerFactory.getLogger(DartReportMasterActionOld.class);
	
	private static String url ="http://dart.fss.or.kr/dsab001/search.ax";
	private static Set<DartReport> reportSet = new HashSet<DartReport>();
	
	public DartReportMasterActionOld() {
	}

	public static String getUrl() {
		return url;
	}

	
	public static Set<DartReport> getDartReportSet(String compName, String stDate, String endDate,int pageIndex)  {
		Map<String, String> argMap = new HashMap<String, String>();
		argMap.put("startDate", stDate);
		argMap.put("endDate", endDate);
		argMap.put("currentPage", String.valueOf(pageIndex));
		argMap.put("textCrpNm", compName);
		argMap.put("finalReport", "recent");
//		argMap.put("publicType", pubType);
		
		Document doc = WebScrappUtil.post(url, argMap);
		Elements contents = doc.select("div[class=table_list]>table>tbody>tr");

		for (Element el : contents) {
			DartReport temp = new DartReport();
//				logger.info("doc : {},{}", el.text(), el.child(2).child(0).attr("href"));
			
			temp.setComCode(el.child(1).child(0).child(0).attr("onclick").substring(14, 22));
			temp.setComName(el.child(1).text());
			temp.setReportName(el.child(2).text());
			temp.setUrl(el.child(2).child(0).attr("href"));
			temp.setReporterName(el.child(3).text());
			temp.setSubmitDate(el.child(4).text());
			temp.setDesc(el.child(5).text());
			
			reportSet.add(temp);
		}
		return reportSet;

	}
	
/*public static Set<DartReport> getDartReportSet(String comName, String stDate, String endDate,int pageIndex)  {
		
		Document doc = getDocument(comName, stDate, endDate, pageIndex);
		Elements contents = doc.select("div[class=table_list]>table>tbody>tr");

		for (Element el : contents) {
			DartReport temp = new DartReport();
//				logger.info("doc : {},{}", el.text(), el.child(2).child(0).attr("href"));
			
			temp.setComCode(el.child(1).child(0).child(0).attr("onclick").substring(14, 22));
			temp.setComName(el.child(1).text());
			temp.setReportName(el.child(2).text());
			temp.setUrl(el.child(2).child(0).attr("href"));
			temp.setReporterName(el.child(3).text());
			temp.setSubmitDate(el.child(4).text());
			temp.setDesc(el.child(5).text());
			
			reportSet.add(temp);
		}
		return reportSet;

	}*/
	
	public static Set<DartReport> getDartReportSetByType(String pubType, String stDate, String endDate,int pageIndex)  {
		Map<String, String> argMap = new HashMap<String, String>();
		argMap.put("startDate", stDate);
		argMap.put("endDate", endDate);
		argMap.put("finalReport", "recent");
		argMap.put("currentPage", String.valueOf(pageIndex));
		argMap.put("publicType", pubType);
		
		Document doc = WebScrappUtil.post(url, argMap);

		Elements contents = doc.select("div[class=table_list]>table>tbody>tr");

		for (Element el : contents) {
			DartReport temp = new DartReport();
//				logger.info("doc : {},{}", el.text(), el.child(2).child(0).attr("href"));
			
			temp.setComCode(el.child(1).child(0).child(0).attr("onclick").substring(14, 22));
			temp.setComName(el.child(1).text());
			temp.setReportName(el.child(2).text());
			temp.setUrl(el.child(2).child(0).attr("href"));
			temp.setReporterName(el.child(3).text());
			temp.setSubmitDate(el.child(4).text());
			temp.setDesc(el.child(5).text());
			
			reportSet.add(temp);
		}
		return reportSet;

	}

	public static int getPageNum(String comName, String stDate, String endDate)  {
		Map<String, String> argMap = new HashMap<String, String>();
		argMap.put("startDate", stDate);
		argMap.put("endDate", endDate);
		argMap.put("textCrpNm", comName);
		argMap.put("finalReport", "recent");
//		argMap.put("currentPage", "1");
//		argMap.put("publicType", pubType);
		
		Document doc = WebScrappUtil.post(url, argMap);
		
			
		Elements aa = doc.select("div[class=page_list]>p[class=page_info]");
		return getNumber(aa);
		
		/*String[] pageString = aa.text().split("]");
//		logger.info("comName:{},{}", comName, pageString.length);
		
		if( pageString.length>1){
//			logger.info("aa:{}", aa.text());
			int indexname = pageString[0].lastIndexOf("/");
			String pageNum = pageString[0].substring(indexname + 1,	pageString[0].length());
			
			return Integer.valueOf(pageNum);
		}	
		return 0;*/
	}
	
	public static int getPageNumByType(String pubType, String stDate, String endDate)  {
		Map<String, String> argMap = new HashMap<String, String>();
		argMap.put("startDate", stDate);
		argMap.put("endDate", endDate);
		argMap.put("finalReport", "recent");
		argMap.put("currentPage", "1");
//		argMap.put("textCrpNm", comName);
		argMap.put("publicType", pubType);
		
		Document doc = WebScrappUtil.post(url, argMap);
			
		Elements aa = doc.select("div[class=page_list]>p[class=page_info]");
		return getNumber(aa);
		
		/*String[] pageString = aa.text().split("]");
//		logger.info("comName:{},{}", comName, pageString.length);
		
		if( pageString.length>1){
//			logger.info("aa:{}", aa.text());
			int indexname = pageString[0].lastIndexOf("/");
			String pageNum = pageString[0].substring(indexname + 1,	pageString[0].length());
			
			return Integer.valueOf(pageNum);
		}	
		return 0;*/
	}
	
	private static int getNumber(Elements element){
		String[] pageString = element.text().split("]");
//		logger.info("comName:{},{}", comName, pageString.length);
		
		if( pageString.length>1){
//			logger.info("aa:{}", aa.text());
			int indexname = pageString[0].lastIndexOf("/");
			String pageNum = pageString[0].substring(indexname + 1,	pageString[0].length());
			
			return Integer.valueOf(pageNum);
		}	
		return 0;
	}
}
