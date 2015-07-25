package com.eugenefe.mvfeed;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eugenefe.mvfeed.krx.KrxBondType;


public class IsinSearchResultTest {
	private final static Logger logger = LoggerFactory.getLogger(IsinSearchResultTest.class);
	private  static Properties properties = new Properties();
	
	
	public static void main(String[] args) {
		
//		stockMasterTest();
		
		bondMasterTest();
//		elsMasterTest();
//		isinSearchResult("stockSearchRst", "2", "KR7192090009");
//		isinSearchResult("stockSearchRst", "2", "KR7200640001");
//		isinSearchResult("bondSearchRst", "3", "KR310204G471");
//		isinSearchResult("fundSearchRst", "4", "KR57420170C9"); 
//		isinSearchResult("cdSearchRst", "6", "KRE827021223"); 
//		isinSearchResult("drSearchRst", "8", "KR8036020004"); 
//		isinSearchResult("stripSearchRst", "9", "KRC0355P1936"); 
//		isinSearchResult("elsSearchRst", "10", "KR65033AL466"); 
//		isinSearchResult("elwSearchRst", "11", "KRA591115456");
//		isinSearchResult("deriSearchRst", "12", "KR4221J70606"); 
//		isinSearchResult("indexSearchRst", "13", "KRD020020065"); 
//		isinSearchResult("bwSearchRst", "55", "KRA001230143");  
  }
	
	
		
	
	private static void stockMasterTest(){
		Document doc;
		try {
			properties.load(IsinSearchResultTest.class.getResourceAsStream("/url.properties"));
			
			// need http protocol
			doc = Jsoup.connect(properties.getProperty("stockSearchRst"))		
						.data("type","2")
						.data("stdcd_type", "2")
						.data("std_cd", "KR7192090009")
//						.data("isu_nm", "미래에셋 TIGER차이나A300 증권상장지수자투자신탁(주식-파생형)")
						.timeout(100000000)
//						.userAgent("Chrome")
						.post();
			
			Elements _rows = doc.select("table[class = type-01 detail mt5]>tbody>tr");
			
//			logger.info("Html Size : {},{}", _rows.size(), doc.toString());
			for( Element _row : _rows){
				for(Element child : _row.children()){
					logger.info("Row1: {}: {}", child.text());
					
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static void bondMasterTest(){
		Document doc;
		
		try {
			properties.load(IsinSearchResultTest.class.getResourceAsStream("/url.properties"));
			
			// need http protocol
			doc = Jsoup.connect(properties.getProperty("bondSearchRst"))		
						.data("stdcd_type", "3")
						.data("std_cd", "KR356202G454")
						.timeout(100000000)
//						.userAgent("Chrome")
						.post();
			
			Elements _rows = doc.select("table[class = type-01 detail mt5]>tbody>tr");
			
			for( Element _row : _rows){
				for(Element child : _row.children()){
					logger.info("Row1: {}: {}", child.text());
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static void elsMasterTest(){
		Document doc;
		try {
			properties.load(IsinSearchResultTest.class.getResourceAsStream("/url.properties"));
			
			// need http protocol
			doc = Jsoup.connect(properties.getProperty("elsSearchRst"))		
						.data("stdcd_type", "2")
						.data("std_cd", "KR65033AL466")
//						.data("isu_nm", "미래에셋 TIGER차이나A300 증권상장지수자투자신탁(주식-파생형)")
						.timeout(100000000)
//						.userAgent("Chrome")
						.post();
			
			Elements _rows = doc.select("table[class = type-01 detail mt5]>tbody>tr");
			
//			logger.info("Html Size : {},{}", _rows.size(), doc.toString());
			for( Element _row : _rows){
				for(Element child : _row.children()){
					logger.info("Row1: {}: {}", child.text());
					
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void isinSearchResult(String url, String type, String code){
		Document doc;
		try {
			properties.load(IsinSearchResultTest.class.getResourceAsStream("/url.properties"));
			
			// need http protocol
			doc = Jsoup.connect(properties.getProperty(url))		
						.data("stdcd_type", type)
						.data("std_cd", code)
//						.data("isu_nm", "미래에셋증권(주) 주식워런트증권 제4234호")
//						.data("pershr_isu_prc", "200")
//						.data("isu_shrs", "5000000")
						.timeout(100000000)
						.userAgent("Chrome")
						.post();
			
//			Elements _rows = doc.select("table[class = type-01 detail mt5]>tbody>tr, table:not(table[id])");
//			Elements _rows = new Elements();
			Elements _rows = doc.select("table:not([id^=id])>tbody>tr");
			Elements specialRows = doc.select("table[id^=id]>tbody>tr");
//			Elements _table = doc.select("table");
//			for( Element aa : _table){
//				for( Element bb : aa.children()){
//					logger.info("element Size : {},{}", aa,bb);
//					if(bb.tagName().equals("tbody")){
//						_rows.add(bb);		
//					}
//				}
//			}
			
//			logger.info("Html Size : {},{}", _rows.size(), doc.toString());
			logger.info("Html Size : {},{}", _rows.size(),specialRows.size());
			for( Element _row : _rows){
				for(Element child : _row.children()){
//					logger.info("Row1: {}: {}", child.text(), child.tagName());
					if(child.tagName().equals("th")){
						if( child.nextElementSibling() !=null 
								&& child.nextElementSibling().tagName().equals("td")){
							logger.info("Row TH: {}: {}", child.text(), child.nextElementSibling().text());
						}
					}
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
