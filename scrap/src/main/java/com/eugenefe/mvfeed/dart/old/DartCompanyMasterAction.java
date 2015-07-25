package com.eugenefe.mvfeed.dart.old;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eugenefe.mvfeed.dart.DartCompany;
import com.eugenefe.mvfeed.util.ScrapException;

public class DartCompanyMasterAction {
	private static final Logger logger = LoggerFactory.getLogger(DartCompanyMasterAction.class);
	private static String url ="http://dart.fss.or.kr/corp/searchCorpL.ax";

	private static Set<DartCompany> masterSet = new HashSet<DartCompany>();
	
	
	public DartCompanyMasterAction() {
	}

	public static String getUrl() {
		return url;
	}


	public static Set<DartCompany> getMasterSet() {
		for(int i=1; i<=getPageNum(); i++){
			getDartCompanySet(i);
		}
		return masterSet;
	}
	
	public static Set<DartCompany> getDartCompanySet(int pageIndex)  {
		Document doc = getDocument(pageIndex);
		Elements elements = doc.select("div[class =table_scroll]>table>tbody>tr");

		for (Element el : elements) {
			DartCompany temp = new DartCompany();
//			logger.info("Size:{}, {}",el.child(0).attr("title"));

			temp.setComCode(el.child(0).child(0).child(2).attr("value"));
			temp.setComName(el.child(0).child(0).child(3).attr("value"));
			temp.setCeoName(el.child(1).text());
			temp.setKrxShortCode(el.child(2).text());
			temp.setIndustry(el.child(3).text());
			temp.setPageNum(pageIndex);

			
			String[] arr =el.child(0).attr("title").split(":");
			if(arr.length==6){
				String zz = el.child(0).attr("title").split(":")[4];
				//TODO : check string
				int idx = zz.lastIndexOf("");
				temp.setBizNum(zz.substring(0, idx));
			}
			
			masterSet.add(temp);
		}
		return masterSet;

	}

	public static int getPageNum() {
		Document doc;
		try {
			doc = Jsoup.connect(url).timeout(10000000).get();
			
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

	private static Document getDocument(int pageIndex) {
		Document doc;
		try {
			doc = Jsoup.connect(url).timeout(1000000000)
					.data("currentPage", String.valueOf(pageIndex))
					// .data("searchIndex","2")
					// .data("textCrpNm", "êµ????)
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
