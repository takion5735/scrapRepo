package com.eugenefe.mvfeed.dart;

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

import com.eugenefe.mvfeed.util.ScrapException;
import com.eugenefe.mvfeed.util.WebScrappUtil;

public class DartReportMasterBean {
	private final Logger logger = LoggerFactory.getLogger(DartReportMasterBean.class);

	private String url = "http://dart.fss.or.kr/dsab001/search.ax";
	private String stDate;
	private String endDate;
	private String companyName;
	private String finalReport = "recent";
	private String publicType;
	private int currentPage;

	private Map<String, String> argMap = new HashMap<String, String>();
	private Set<DartReport> reportSet = new HashSet<DartReport>();

	public DartReportMasterBean() {
	}

	public DartReportMasterBean(String stDate, String endDate) {
		this.stDate = stDate;
		this.endDate = endDate;
		this.currentPage = 1;
		argMap.put("startDate", stDate);
		argMap.put("endDate", endDate);
		argMap.put("currentPage", String.valueOf(currentPage));
		argMap.put("finalReport", finalReport);
	}
	
	public DartReportMasterBean(String stDate, String endDate, String companyName, String publicType) {
		this.stDate = stDate;
		this.endDate = endDate;
		this.currentPage = 1;
		this.companyName = companyName;
		this.publicType = publicType;

		argMap.put("startDate", stDate);
		argMap.put("endDate", endDate);
		argMap.put("currentPage", String.valueOf(currentPage));
		argMap.put("finalReport", finalReport);

		if (companyName != null) {
			argMap.put("textCrpNm", companyName);
		}
		if (publicType != null) {
			argMap.put("publicType", publicType);
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getStDate() {
		return stDate;
	}

	public void setStDate(String stDate) {
		this.stDate = stDate;
	}


	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.argMap.put("currentPage", String.valueOf(currentPage));
		this.currentPage = currentPage;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.argMap.put("textCrpNm", companyName);
		this.companyName = companyName;
	}

	public String getFinalReport() {
		return finalReport;
	}

	public void setFinalReport(String finalReport) {
		this.finalReport = finalReport;
	}

	public String getPublicType() {
		return publicType;
	}

	public void setPublicType(String publicType) {
		this.argMap.put("publicType", publicType);
		this.publicType = publicType;
	}

	public Map<String, String> getArgMap() {
		return argMap;
	}

	public void setArgMap(Map<String, String> argMap) {
		this.argMap = argMap;
	}

	public Set<DartReport> getDartReportSet() {
		Set<DartReport> rst = new HashSet<DartReport>();
		int size = getPageNum();
//		logger.info("AAA2:{},{}", getCurrentPage(), size);
		
		for(int i=0 ; i< size; i++){
//			Set<DartReport> temp = getDartReportSet(i+1);
//			logger.info("RPT:{},{}", i, temp.size());
			rst.addAll(getDartReportSet(i+1));
		}
		return rst;
	}
	
	public Set<DartReport> getDartReportSet(int pageIndex) {
		Set<DartReport> rst = new HashSet<DartReport>();
//		logger.info("Map : {}", argMap);
		setCurrentPage(pageIndex);
		
		Document doc = WebScrappUtil.post(url, getArgMap());
		Elements contents = doc.select("div[class=table_list]>table>tbody>tr");
		rst = writeDartReportEntity(contents);
		return rst;
	}

	public int getPageNum() {
		setCurrentPage(1);
		Document doc = WebScrappUtil.post(url, argMap);

		Elements aa = doc.select("div[class=page_list]>p[class=page_info]");
//		logger.info("AAA1:{},{}", getCurrentPage(), getArgMap());
		return getNumber(aa);
	}

	private  int getNumber(Elements element) {
		String[] pageString = element.text().split("]");

		if (pageString.length > 1) {
			// logger.info("aa:{}", aa.text());
			int indexname = pageString[0].lastIndexOf("/");
			String pageNum = pageString[0].substring(indexname + 1, pageString[0].length());

			return Integer.valueOf(pageNum);
		}
		return 0;
	}

	private Set<DartReport> writeDartReportEntity(Elements contents) {
		Set<DartReport> rst = new HashSet<DartReport>();
		for (Element el : contents) {
			DartReport temp = new DartReport();
			// logger.info("doc : {},{}", el.text(),
			// el.child(2).child(0).attr("href"));

			temp.setComCode(el.child(1).child(0).child(0).attr("onclick").substring(14, 22));
			temp.setComName(el.child(1).text());
			temp.setReportName(el.child(2).text());
			temp.setUrl(el.child(2).child(0).attr("href"));
			temp.setReporterName(el.child(3).text());
			temp.setSubmitDate(el.child(4).text());
			temp.setDesc(el.child(5).text());

			rst.add(temp);
		}
		return rst;
	}
}
