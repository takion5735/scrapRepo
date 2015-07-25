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

public class DartCompanyMasterBean {
	private static final Logger logger = LoggerFactory.getLogger(DartCompanyMasterBean.class);
	private static String url ="http://dart.fss.or.kr/corp/searchCorpL.ax";
	
	private int currentPage;
	private Map<String, String> argMap = new HashMap<String, String>();

	public DartCompanyMasterBean() {
	}

	public DartCompanyMasterBean(int currentPage) {
		this.currentPage = currentPage;
	}

	public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		DartCompanyMasterBean.url = url;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.argMap.put("currentPage", String.valueOf(currentPage));
		this.currentPage = currentPage;
	}

	public Map<String, String> getArgMap() {
		return argMap;
	}

	public void setArgMap(Map<String, String> argMap) {
		this.argMap = argMap;
	}
	
	public Set<DartCompany> getDartCompanySet(){
		Set<DartCompany> rst = new HashSet<DartCompany>();
		int size = getPageNum();
		for(int i=0 ; i<size ; i++){
			rst.addAll(getDartCompanySet(i+1));
		}
		return rst;
	}
	
	public Set<DartCompany> getDartCompanySet(int pageIndex)  {
		Set<DartCompany> rst = new HashSet<DartCompany>();
		
		setCurrentPage(pageIndex);
		Document doc = WebScrappUtil.get(url, argMap);
		
		Elements elements = doc.select("div[class =table_scroll]>table>tbody>tr");
		
		rst = writeDartCompanyEntity(elements);
		logger.info("DartCompanySize : {} ", rst.size());
		for(DartCompany comp : rst){
			comp.setPageNum(pageIndex);
		}
		
		return rst;
	}
	
	

	private Set<DartCompany> writeDartCompanyEntity(Elements contents) {
		Set<DartCompany> rst = new HashSet<DartCompany>();
		for (Element el : contents) {
			DartCompany temp = new DartCompany();

			temp.setComCode(el.child(0).child(0).child(2).attr("value"));
			temp.setComName(el.child(0).child(0).child(3).attr("value"));
			temp.setCeoName(el.child(1).text());
			temp.setKrxShortCode(el.child(2).text());
			temp.setIndustry(el.child(3).text());
//			temp.setPageNum(pageIndex);
			
			rst.add(temp);
		}
		return rst;
	}
	
	private int getPageNum(){
		setCurrentPage(1);
		Document doc = WebScrappUtil.post(url, argMap);
		Elements aa = doc.select("p[class=page_info]");
		
		String[] pageString = aa.text().split("]");
		
		logger.info("aa:{}", aa.text());
		int indexname = pageString[0].lastIndexOf("/");
		String pageNum = pageString[0].substring(indexname + 1,	pageString[0].length());
		
		return Integer.valueOf(pageNum);
	}
}
