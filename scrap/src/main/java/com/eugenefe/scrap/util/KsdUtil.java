package com.eugenefe.scrap.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KsdUtil {
	private final static Logger logger = LoggerFactory.getLogger(KsdUtil.class);	
	private static String url = "http://www.seibro.or.kr/websquare/engine/proworks/callServletService.jsp";
	
	private static String elsUrl = "http://www.seibro.or.kr/websquare/engine/proworks/callServletService.jsp";
	
	private static String _action = "<reqParam action=\"";
	private static String _task = "\" task=\"";
	private static String _menuNo = "\"><MENU_NO value=\"";
	private static String _cmm_btn = "\"/><CMM_BTN_ABBR_NM value=\"allview,allview,print,hwp,word,pdf,searchIcon,seach,link,link,wide,wide,top,";
	private static String _xpath = "\"/><W2XPATH value=\"";
	private static String _isin = "\"/><ISIN value=\"";
	private static String _issuerCode = "\"/><ISSUCO_CUSTNO value=\"";
	private static String _prodType = "\"/><SECN_TPCD value=\"";	
	private static String _endArg ="\"/></reqParam>";
	
	private static String elsReferer = "http://www.seibro.or.kr/websquare/control.jsp?w2xPath=/IPORTAL/user/derivCombi/BIP_CNTS07016V.xml&menuNo=200";
	private static String elsTask = "ksd.safe.bip.cnts.DerivCombi.process.DeriELSPTask";
	private static String elsMenuNo = "200";
	private static String elsXpath ="/IPORTAL/user/derivCombi/BIP_CNTS07016V.xml";
	
	private static String getElsPayloadSurfix(String isin){
		StringBuffer strBuffer = new StringBuffer();  
		String tempStr = strBuffer.append(_task).append(elsTask)
								  .append(_menuNo).append(elsMenuNo)
								  .append(_cmm_btn)
								  .append(_xpath).append(elsXpath)
								  .append(_isin).append(isin)
								  .append(_endArg)
								  .toString();
		return tempStr;
	}
	public static Map<String, String> getElsInfoMap(String isin){
		Map<String, String> rstMap = new HashMap<String, String>();
		Document doc = getElsInfo(isin);
		logger.info("xml : {}", doc.toString());
		
		for(Element e : doc.select("result").first().children()){
			logger.info("aaa:{}, {}", e.tagName(), e.attr("value"));
		}
		
		return rstMap;
	}
	
	
	
	
	public static Document getElsInfo(String isin){
		String tempStr = getElsPayloadSurfix(isin);
		String issueInfoAction = "issuInfoList";
		String issueInfoPayload = _action + issueInfoAction+ tempStr;
		
		Document doc = Jsoup.parse(KsdUtil.callSeveltService(elsReferer, issueInfoPayload), "",Parser.xmlParser());
		
		return doc;
	}
	
	public static Map<String, String> getElsStrikePriceMap(String isin){
		Map<String, String> rstMap = new HashMap<String, String>();
		Document doc = getElsStrikePrice(isin);
		logger.info("xml : {}", doc.toString());
		int cnt =0;
		StringBuffer rst= new StringBuffer();
		for(Element e : doc.select("data>result")){
//		for(Element e : doc.select("data")){	
			cnt = cnt +1;
//			logger.info("aaa:{}, {}", cnt, e.toString());
			for( Element bb : e.children()){
				rst.append(isin).append(";")
					.append(cnt).append(";")
					.append(bb.tagName()).append(";")
				   .append(bb.attr("value")).append("\n");
				logger.info("bbb:{}, {}", bb.tagName(), bb.attr("value"));
			}
			
		}
		logger.info("ccc:{}, {}", rst.toString());
		return rstMap;
	}
	
	public static Document getElsStrikePrice(String isin){
		String exerciseAction = "bassetXrcList";
		
		String tempStr = getElsPayloadSurfix(isin);
		String exerPayload = _action + exerciseAction+ tempStr;
		
		Document doc = Jsoup.parse(KsdUtil.callSeveltService(elsReferer, exerPayload),"", Parser.xmlParser());
		return doc;
	}
	
	public static Map<String, String> getElsScheduleMap(String isin){
		Map<String, String> rstMap = new HashMap<String, String>();
		
		for(Element e : getElsInfo(isin).select("result").first().children()){
			logger.info("aaa:{}, {}", e.tagName(), e.attr("value"));
			
		}
		
		return rstMap;
	}
	
	public static Document getElsSchedule(String isin){
		String scheduleAction = "midValatSkedulRedCondiList";
		String tempStr = getElsPayloadSurfix(isin);
		String schedulePayload = _action + scheduleAction+ tempStr;
		Document doc = Jsoup.parse(KsdUtil.callSeveltService(elsReferer, schedulePayload),"", Parser.xmlParser());
		return doc;
	}
	
	public static Map<String, String> getElsBaseInfoMap(String isin){
		Map<String, String> rstMap = new HashMap<String, String>();
		
		for(Element e : getElsInfo(isin).select("result").first().children()){
			logger.info("aaa:{}, {}", e.tagName(), e.attr("value"));
			
		}
		
		return rstMap;
	}
	
	public static Document getElsBaseInfo(String isin){
		String baseInfoAction = "bassetInfoList";
	
		String tempStr = getElsPayloadSurfix(isin);
		String baseInfoPayload = _action + baseInfoAction+ tempStr;
		Document doc = Jsoup.parse(KsdUtil.callSeveltService(elsReferer, baseInfoPayload),"", Parser.xmlParser());
		return doc;
	}
		
	
//	public static String callSeveltService(String referer, String payload){
//		return callSeveltServiceDoc(referer, payload).toString();
//		
//		
//	}
	
	
	private static String callSeveltService(String referer, String payload){
		Document doc;
		
		try {
			URL obj = new URL(url);
			HttpURLConnection  conn = (HttpURLConnection)obj.openConnection();
			
//			conn.addRequestProperty("User-Agent", "Mozilla");
//			conn.addRequestProperty("Accept-Language", "ko,en-US;q=0.8,en;q=0.6" );
//			conn.addRequestProperty("Content-Type", "application/xml");
			conn.addRequestProperty("Referer", referer );
			
			conn.setDoOutput(true);
			conn.setDoInput(true);
			
			
			OutputStreamWriter w  = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			w.write(payload);
			w.close();
			
//			logger.info("Request Url : {}, {}", url, payload);
			
			int status = conn.getResponseCode();
//			logger.info("Response : {}", status);
			
			BufferedReader in = new BufferedReader( new InputStreamReader(conn.getInputStream(),"UTF-8"));
			String inputLine;
			StringBuffer html = new StringBuffer();
			
			while ((inputLine = in.readLine())!= null){
				html.append(inputLine).append("\n");
//				logger.info("line : {} ", in.readLine().toString());
			}
			
			in.close();
			conn.disconnect();
//			logger.info("htm : {} ", html.toString());
			return html.toString();
		}
		catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}
	
	public static String searchProduct(String referer, String payload){
		Document doc;
		
		try {
			URL obj = new URL(url);
			HttpURLConnection  conn = (HttpURLConnection)obj.openConnection();
			
//			conn.addRequestProperty("User-Agent", "Mozilla");
//			conn.addRequestProperty("Accept-Language", "ko,en-US;q=0.8,en;q=0.6" );
//			conn.addRequestProperty("Content-Type", "application/xml");
			conn.addRequestProperty("Referer", referer );
			
			conn.setDoOutput(true);
			conn.setDoInput(true);
			
			
			OutputStreamWriter w  = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			w.write(payload);
			w.close();
			
//			logger.info("Request Url : {}, {}", url, payload);
			
			int status = conn.getResponseCode();
//			logger.info("Response : {}", status);
			
			BufferedReader in = new BufferedReader( new InputStreamReader(conn.getInputStream(),"UTF-8"));
			String inputLine;
			StringBuffer html = new StringBuffer();
			
			while ((inputLine = in.readLine())!= null){
				html.append(inputLine).append("\n");
//				logger.info("line : {} ", in.readLine().toString());
			}
			
			in.close();
			conn.disconnect();
			logger.info("htm : {} ", html.toString());
			return html.toString();
			

		}
		catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}
}
