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

import com.eugenefe.mvfeed.enums.EIsinProdType;
import com.eugenefe.mvfeed.isin.IsinMaster;

public class IsinMasterTest {
	private final static Logger logger = LoggerFactory.getLogger(IsinMasterTest.class);
	
	private static Properties properties = new Properties();
//	private static String filePath = "/home/takion77/isincode/isinMaster";
	private static String filePath = "D:\\Dev\\isincode\\isinMaster\\";
	
	private static List<IsinMaster> rst = new ArrayList<IsinMaster>();

	public static void main(String[] args) {
		String bssd = "201403";
		String stDate = bssd+"01";
//		String endDate =bssd +"02";
//		String endDate =bssd +"30";
		String endDate =bssd+ "31";
		
		try{
			properties.load(IsinMasterTest.class.getResourceAsStream("/url.properties"));
			String url = properties.getProperty("isinSearch");
			
			int pageSize =getPageSize(url, stDate, endDate);
			
			logger.info("size: {}", pageSize);
			for(int i =0; i< pageSize; i++){
				isinMaterTest(url, i+1, stDate, endDate);
			}
		}
		catch(Exception e){
			
		}
		
		try{
			
		BufferedWriter out = new BufferedWriter(new FileWriter(filePath+bssd+".txt"));
		for(IsinMaster aa : rst){
			logger.info("RST: {},{}", aa.getEprodType(), aa.getProdName());
			StringBuffer str = new StringBuffer();
				str.append(aa.getProdType()).append(";")
					.append(aa.getProdId()).append(";")
					.append(aa.getProdName()).append(";")
					.append(aa.getIssuer()).append(";")
					.append(aa.getIssueDate()).append(";")
					.append(aa.getMaturityDate()).append(";")
					.append(aa.getListYn()).append(";")
					.append(aa.getListDate()).append(";")
					.append(aa.getGenDate());
					
				out.write(str.toString());
				out.newLine();
			}
			out.close();
		}catch(IOException ex){
			
		}
		
	}
	
	private static void isinMaterTest(String url, int pageIndex, String stDate, String endDate) {
		Document doc = getDocument(url, pageIndex, stDate, endDate);
		
		Elements _rows = doc.select("table[id = dataTb]>tbody>tr[name=dataTr]");
		
//		Elements temp1 = doc.select("table[id = dataTb]>tbody>tr[name=dataTr]>td");
//		logger.info("zza: {}, {}",temp1);
		
		for (Element aa : _rows) {
			IsinMaster temp = new IsinMaster();
			temp.setProdType(aa.child(0).text());
			temp.setEprodType(EIsinProdType.getProdType(aa.child(0).text()));
			temp.setProdId(aa.child(1).text());
			temp.setProdName(aa.child(2).text());
			temp.setIssuer(aa.child(3).text());
			temp.setIssueDate(aa.child(4).text());
			temp.setMaturityDate(aa.child(5).text());
			temp.setListYn(aa.child(6).text());
			temp.setListDate(aa.child(7).text());
			temp.setGenDate(aa.child(8).text());
			rst.add(temp);
//			logger.info("aa: {}", aa.text());
		}
		
	}
	
	private static int getPageSize(String url, String stDate, String endDate) {
		Document doc = getDocument(url, 1, stDate, endDate);
		
//		Elements _rows = doc.select("table[class = paging]>tbody>tr>td:not([style^=padding])>a");
		Element _rows = doc.select("#totPage").first();
		int pageSize = Integer.valueOf(_rows.text())/ 15;
		logger.info("zz: {}, {}", _rows.text(), pageSize);
		
		return pageSize+1;
		
	}
	
	
	private static Document getDocument(String url, int pageIndex, String stDate, String endDate) {
		Document doc;
		try {
			doc = Jsoup	.connect(url)
//					.data("curSh", "2")
					.data("searchRadio", "1")
					.data("pageIndex", String.valueOf(pageIndex))
					.data("std_cd_grnt_start_dd",stDate)
					.data("std_cd_grnt_end_dd",endDate)
//					.data("currentPage", "15")
//					.data("isur_nm","湲�)
					.timeout(100000000)
					.userAgent("Chrome")
					.post();

			return doc;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
