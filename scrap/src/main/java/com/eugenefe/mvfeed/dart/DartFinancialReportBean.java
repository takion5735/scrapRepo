package com.eugenefe.mvfeed.dart;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

import com.eugenefe.mvfeed.util.FileUtil;
import com.eugenefe.mvfeed.util.ScrapException;
import com.eugenefe.mvfeed.util.WebScrappUtil;

/**
 * 1. return dcmNo from rcpNo 2. write doc after get fnReport using rcpNo, dcmNo
 * looping eleId and url; 3. return fnReport Entity using rcpNo, dcmNo looping
 * eleId and url; 4. return fnReport Entity using append report file 5. return
 * fnReport using doc
 * 
 */
public class DartFinancialReportBean {
	private final Logger logger = LoggerFactory.getLogger(DartFinancialReportBean.class);
	private static String fnReportFile = "/home/takion77/isincode/dartFnReport";
	private static String appendRptFile = "/home/takion77/isincode/htm/bank_2013_Dcm.txt";
	// private static String docFile =
	// "/home/takion77/isincode/htm/20140331002097_4112883_25";
	private static String docFile = "/home/takion77/isincode/htm/20140331002097_4112883_26";
	private static String directory = "/home/takion77/isincode/htm/";
	// private String url1
	// ="http://dart.fss.or.kr/dsaf001/main.do?rcpNo=20131231000048&dcmNo=3997747";
	private String url = "http://dart.fss.or.kr/dsaf001/main.do?rcpNo=";
	private String url2 = "http://dart.fss.or.kr//report/viewer.do";

	// private String homepage = "http://dart.fss.or.kr/" ;
	// private String rptUrl ="/dsaf001/main.do?rcpNo=20140331002097";

	// private String fnRptUrl ="/report/viewer.do";
	// private String rcpNo ="20131231000048";
	// private String dcmNo = "3997747";
	private String eleId = "5";
	private String offset = "1";
	private String length = "1";

	public DartFinancialReportBean() {

	}

	public void getAAA() {
		Document doc = readHtmlFrom(docFile);
		Map<String, List<String>> aaa = getContent(doc, "");

	}

	public Map<String, List<String>> getAAAzzz() {
		String writeFile = directory + "bank_2103_Dcm.txt";
		String readFile = directory + "bank_2013.txt";
		// writeFnReportTo(writeFile, readFile, homepage);

		Map<String, String> arg = new HashMap<String, String>();
		for (int i = 6; i < 25; i++) {
			eleId = String.valueOf(i);

			try {
				List<String> list = FileUtil.readFileToList(writeFile);
				for (String aa : list) {
					logger.info("list :{},{}", aa);
					logger.info("list :{},{}", aa.split(";")[3]);
					arg.put("rcpNo", aa.split(";")[2]);
					arg.put("dcmNo", aa.split(";")[3]);
					arg.put("eleId", eleId);
					arg.put("offset", offset);
					arg.put("length", length);
					arg.put("dtd", "dart3.xsd");
					String writeTo = directory + arg.get("rcpNo") + "_" + arg.get("dcmNo") + "_" + arg.get("eleId");

					Document doc = WebScrappUtil.get(url2, arg);
					writeHtmTo(writeTo, doc.toString());

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}

	/**
	 * TODO :append dcmNo to DartReport and write to file depend on
	 * getFinancialReportArg to get DcmNo; ==> change getDcmNo from get
	 * FinancialReportArg to get DcmNo ==> change owner of this method ??? ==>
	 * remove after test
	 */
	private void appendDcmNoTo(String writeFile, String readFile) {
		List<String> appendList = new ArrayList<String>();
		String appendReport;

		// dartReporetUrl example : /dsaf001/main.do?rcpNo=20131231000048";
		String rcpNo = "";
		String dcmNo;
		String tempName = "";
		try {
			List<String> list = FileUtil.readFileToList(readFile);
			for (String aa : list) {
				// logger.info("list :{},{}", aa);
				// logger.info("list :{},{}", aa.split(";")[1]);
				tempName = aa.split(";")[0];
				rcpNo = aa.split("=")[1];

				dcmNo = getDcmNo(rcpNo);
				appendReport = aa + ";" + rcpNo + ";" + dcmNo;
				appendList.add(appendReport);
			}
			FileUtil.writeFile(writeFile, appendList);
		} catch (IOException e) {
			logger.info("error");
			e.printStackTrace();
		}
	}

	/*
	 * remove...
	 * 
	 * public void doWriteJob(String url){ logger.info("AAA1 : {} ");
	 * Map<String, String> arg = getFinancialReportArg(url); String writeTo =
	 * directory + arg.get("rcpNo") +"_"+ arg.get("dcmNo") +"_" +
	 * arg.get("eleId"); Document doc = WebScrappUtil.get(url2,arg);
	 * writeHtmTo(writeTo, doc.toString()); }
	 */

	// TODO remove???: request url is like
	// http://dart.fss.or.kr/dsaf001/main.do?rcpNo=xxxx";

	// TODO :request url is like
	// http://dart.fss.or.kr/dsaf001/main.do?rcpNo=xxxx";
	public String getDcmNo(String rcpNo) {
		String dcmNo = "";
		Document doc = WebScrappUtil.get(url + rcpNo);

		Elements elements = doc.select("ul>li>a[href=#download");
		for (Element aa : elements) {
			// logger.info("arg : {},{}", aa.attr("onclick"));
			// if(aa.attr("onclick").contains("openPdfDownlaod")){
			// }
			// onclick attr is like
			// onclick="openPdfDownload('20131231000048', '3997747'); return false;"
			dcmNo = aa.attr("onclick").split("', '")[1].split("'")[0];
		}
		logger.info("arg : {},{}", rcpNo, dcmNo);
		return dcmNo;
	}

	// TODO : ..ing
	public void writeFinancialReport(String writeFile, String rcpNo, String dcmNo, String eleId) {
		Set<DartFnReport> rst = new HashSet<DartFnReport>();
		Map<String, String> arg = new HashMap<String, String>();
		arg.put("eleId", eleId);
		arg.put("dcmNo", dcmNo);
		arg.put("offset", offset);
		arg.put("length", length);
		arg.put("dtd", "dart3.xsd");
		arg.put("rcpNo", rcpNo);

		Document doc = WebScrappUtil.get(url2, arg);

		/*
		 * // Elements elements = doc.select("body"); Elements elements =
		 * doc.select("a[name=toc1]"); logger.info("elesize:{}",
		 * elements.size());
		 * 
		 * int i =1; // logger.info("elesize:{}", elements);
		 * while(elements.size()>0){ for(Element aa : elements){ eleName =
		 * aa.toString(); logger.info("eleName:{}", eleName); } rst.add(new
		 * DartFnReport
		 * (arg.get("rcpNo"),arg.get("dcmNo"),arg.get("dcmNo"),eleName)); String
		 * eleId = String.valueOf(i+1); arg.put("eleId", eleId); doc =
		 * WebScrappUtil.get(url,arg); elements = doc.select("a[name=toc1]"); }
		 * 
		 * try { FileUtil.writeFile(writeFile, rst); } catch (IOException e) {
		 * // TODO Auto-generated catch block e.printStackTrace(); }
		 */

	}

	private Map<String, List<String>> writeAAA(Elements contents) {
		Map<String, List<String>> rst = new HashMap<String, List<String>>();
		List<String> header = new ArrayList<String>();

		for (Element el : contents) {
			logger.info("PP : {},{} ", el.text(), el.children().size());
			for (Element child : el.children()) {
				// logger.info("PP : {},{} ", child.text());
				//TODO : check string
				if (child.text().contains("")) {					
					logger.info("PP : {} ", child.text(), el.text());
				}
			}
		}
		return rst;
	}

	// TODO : Read DocumentFile to Document
	private Document readHtmlFrom(String file) {
		try {
			File input = new File(docFile);
			Document doc = Jsoup.parse(input, "UTF-8", "http://dart.fss.or.kr/");
			// logger.info("ZZ: {}", doc);
			return doc;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// TODO:
	private void writeHtmTo(String fileName, String doc) {

		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
			out.write(doc);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Map<String, List<String>> getContent(Document doc, String fnReportName) {

		Element body = doc.body();
		// logger.info("body : {},{} ", doc.nodeName(), doc.val());

		int index = 1;
		for (Element bb : body.children()) {
			// logger.info("zzz : {},{} ", bb, bb.tagName());
			// logger.info("zzzzzz : {},{} ", bb.tagName());

			// if(bb.text().contains("?∞Í≤∞?¨Î¨¥?ÅÌÉú??)){
			//TODO : check string
			if (bb.text().contains("")) {					
				index = 10;
				// logger.info("zzzzzz : {},{} ", bb.text());
			}
			if (bb.tagName().contains("table")) {
//				index = index - 1;
//				 logger.info("table : {},{} ",bb);
				// getTableName(bb);
				getTableContent(bb);
			}
		}
		// logger.info("zzz : {},{} ", aa, aa.text());
		return null;
	}

	private String getTableName(Element table) {
		// TODO : check table for content table
		if (table.attr("class").equals("nb")) {
			return table.text();
		}
		return "";
	}

	private void getTableContent(Element table) {
		// logger.info("In the Table : {}", table);
		List<String> tableHeader = new ArrayList<String>();
		
		int colSize = table.select("colgroup").size();
		
//		logger.info("In the Table : {}, {}", table.attr("border"), table);
		if (table.hasAttr("border")) {
//			logger.info("In the Table : {}, {}", table.attr("border"), table);
//			Elements theads = table.select("thead > tr ");
//			tableHeader = Arrays.asList(DartTableUtil.getTableHeader(theads, colSize));
//			logger.info("Table: {}", tableHeader);
			
			Elements tbodys = table.select("tbody > tr ");
			DartTableUtil.getTableBody(tbodys, colSize);
		}

	}

	

}
