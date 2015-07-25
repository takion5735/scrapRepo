package scrap;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator.MatchesOwn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import antlr.Parser;


public class KsdInfoHttpUrlTest {
	private final static Logger logger = LoggerFactory.getLogger(KsdInfoHttpUrlTest.class);
	private static String url = "http://www.seibro.or.kr/websquare/engine/proworks/callServletService.jsp";

	private static String _action = "<reqParam action=\"";
	private static String _task = "\" task=\"";
	private static String _menuNo = "\"><MENU_NO value=\"";
	private static String _xpath = "\"/><W2XPATH value=\"";
	private static String _isin = "\"/><ISIN value=\"";
	private static String _endArg ="\"/></reqParam>";

	private static String _issuerCode = "\"/><ISSUCO_CUSTNO value=\"";
	private static String issuerCode = "594";
	
	private static String _prodType = "\"/><SECN_TPCD value=\"";
	private static String prodType = "41";
	
	
	

	
	private static StringBuffer strBuffer = new StringBuffer();
	 
	public static void main(String[] args) {
			String elsReferer = "http://www.seibro.or.kr/websquare/control.jsp?w2xPath=/IPORTAL/user/derivCombi/BIP_CNTS07016V.xml&menuNo=200";

			String issueInfoAction = "issuInfoList";
			String exerciseAction = "bassetXrcList";
			String scheduleAction = "midValatSkedulRedCondiList";
			String baseInfoAction = "bassetInfoList";
			
			
			String task = "ksd.safe.bip.cnts.DerivCombi.process.DeriELSPTask";
			
			String menuNo = "200";
			String xpath ="/IPORTAL/user/derivCombi/BIP_CNTS07016V.xml";
			String isin ="KR6723307577"; 
			
			
			
			StringBuffer strBuffer = new StringBuffer();  
			String tempStr = strBuffer.append(_task).append(task)
									  .append(_menuNo).append(menuNo)
									  .append(_xpath).append(xpath)
									  .append(_isin).append(isin)
									  .append(_endArg)
									  .toString();

			String issueInfoPayload = _action + issueInfoAction+ tempStr;									  
			String exerPayload = _action + exerciseAction+ tempStr;
			String schedulePayload = _action + scheduleAction+ tempStr;
			String baseInfoPayload = _action + baseInfoAction+ tempStr;
			
//			StringBuffer strBuffer = new StringBuffer();  
//			String payload = strBuffer.append(_action).append(exerciseAction)
//									  .append(_task).append(task)
//									  .append(_menuNo).append(menuNo)
//									  .append(_xpath).append(xpath)
//									  .append(_isin).append(isin)
//									  .append(_endArg)
//									  .toString();					
//			issueRemainAmt();
//			issueInfoList();
//			bassetXrcList();
			StringBuffer rst = new StringBuffer();
			rst.append(callSeveltService(elsReferer, issueInfoPayload)).append("cn")
				.append(callSeveltService(elsReferer, exerPayload)).append("\n")
				.append(callSeveltService(elsReferer, schedulePayload)).append("\n")
				.append(callSeveltService(elsReferer, baseInfoPayload)).append("\n")
				;
//			String xml1 = callSeveltService(elsReferer, issueInfoPayload);
//			String xml2 = callSeveltService(elsReferer, exerPayload);
//			String xml3 = callSeveltService(elsReferer, schedulePayload);
//			String xml = callSeveltService(elsReferer, baseInfoPayload);
//			logger.info("xml : {} ", rst.toString());
			
			searchProduct();
	}

	
	private static void issueRemainAmt(){
		Document doc;
		
		try {
			URL obj = new URL(url);
			HttpURLConnection  conn = (HttpURLConnection)obj.openConnection();
			
			conn.addRequestProperty("User-Agent", "Mozilla");
			conn.addRequestProperty("Accept-Language", "ko,en-US;q=0.8,en;q=0.6" );
			conn.addRequestProperty("Referer", "http://www.seibro.or.kr/websquare/control.jsp?w2xPath=/IPORTAL/user/derivCombi/BIP_CNTS07001V.xml&menuNo=191" );
			conn.addRequestProperty("Content-Type", "application/xml");
			conn.setDoOutput(true);
			
//			String payload = "<reqParam action=\"issuRemaStatList\" task=\"ksd.safe.bip.cnts.DerivCombi.process.DeriCommPTask\"><SECN_TPNM value=\"ELS\"/></reqParam>";
			String payload = "<reqParam action=\"issuRemaList\" task=\"ksd.safe.bip.cnts.DerivCombi.process.DeriCommPTask\"><SECN_TPNM value=\"ELS\"/><STD_DT value=\"20150703\"/></reqParam>";
			
			OutputStreamWriter w  = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			w.write(payload);
			
			w.close();
			
			logger.info("Request Url : {}", url);
			
			int status = conn.getResponseCode();
			logger.info("Response : {}", status);
			
			BufferedReader in = new BufferedReader( new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer html = new StringBuffer();
			
			while ((inputLine = in.readLine())!= null){
				html.append(inputLine);
			}
			
			in.close();
			conn.disconnect();
			
			logger.info("htm : {} ", html.toString());
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}	
	
	private static void issueInfoList(){
		Document doc;
		
		try {
			URL obj = new URL(url);
			HttpURLConnection  conn = (HttpURLConnection)obj.openConnection();
			String referer = "http://www.seibro.or.kr/websquare/control.jsp?w2xPath=/IPORTAL/user/derivCombi/BIP_CNTS07016V.xml&menuNo=200";
//			String payload = "<reqParam action=\"issuInfoList\" task=\"ksd.safe.bip.cnts.DerivCombi.process.DeriELSPTask\"><MENU_NO value=\"200\"/><CMM_BTN_ABBR_NM value=\"allview,allview,print,hwp,word,pdf,searchIcon,seach,link,link,wide,wide,top,\"/><W2XPATH value=\"/IPORTAL/user/derivCombi/BIP_CNTS07016V.xml\"/><ISIN value=\"KR6693312326\"/></reqParam>";
//			String payload = "<reqParam action=\"issuInfoList\" task=\"ksd.safe.bip.cnts.DerivCombi.process.DeriELSPTask\"><MENU_NO value=\"200\"/><W2XPATH value=\"/IPORTAL/user/derivCombi/BIP_CNTS07016V.xml\"/><ISIN value=\"KR6693312326\"/></reqParam>";
//			String payload = "<reqParam action=\"issuInfoList\" task=\"ksd.safe.bip.cnts.DerivCombi.process.DeriELSPTask\"><MENU_NO value=\"200\"/><W2XPATH value=\"/IPORTAL/user/derivCombi/BIP_CNTS07016V.xml\"/><ISIN value=\"KR6693312326\"/></reqParam>";
			String payload = "<reqParam action=\"issuInfoList\" task=\"ksd.safe.bip.cnts.DerivCombi.process.DeriELSPTask\"><MENU_NO value=\"200\"/><W2XPATH value=\"/IPORTAL/user/derivCombi/BIP_CNTS07016V.xml\"/><ISIN value=\"KR6723307577\"/></reqParam>";
			
//			conn.addRequestProperty("User-Agent", "Mozilla");
//			conn.addRequestProperty("Accept-Language", "ko,en-US;q=0.8,en;q=0.6" );
			conn.addRequestProperty("Referer", referer );
//			conn.addRequestProperty("Content-Type", "application/xml");
			
			conn.setDoOutput(true);
			conn.setDoInput(true);
			
			
			OutputStreamWriter w  = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			w.write(payload);
			
			w.close();
			
			logger.info("Request Url : {}", url);
			
			int status = conn.getResponseCode();
			logger.info("Response : {}", status);
			
//			BufferedReader in = new BufferedReader( new InputStreamReader(conn.getInputStream()));
			BufferedReader in = new BufferedReader( new InputStreamReader(conn.getInputStream(),"UTF-8"));
			String inputLine;
			StringBuffer html = new StringBuffer();
			
			while ((inputLine = in.readLine())!= null){
				html.append(inputLine);
//				logger.info("line : {} ", in.readLine().toString());
			}
			
			in.close();
			conn.disconnect();
			
			logger.info("htm : {} ", html.toString());
			
			Document xml = Jsoup.parse(html.toString());
//			Elements links = xml.body().children();
			Elements links = xml.select("result");
			
			for (Element link : links) {
				Elements aa = link.children();
				for(Element bb : aa){
//					logger.info("children :{}", bb);
					logger.info("children element :{},{}", bb.nodeName(),bb.attr("value"));
					
				}
				// get the value from href attribute
//				System.out.println("\nlink : " + link.attr("href"));
//				System.out.println("text : " + link.text());
//	 
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	private static void searchProduct(){
		Document doc;
		
		try {
			URL obj = new URL(url);
			HttpURLConnection  conn = (HttpURLConnection)obj.openConnection();
			
			conn.addRequestProperty("User-Agent", "Mozilla");
			conn.addRequestProperty("Accept-Language", "ko,en-US;q=0.8,en;q=0.6" );
			conn.addRequestProperty("Referer", "http://www.seibro.or.kr/websquare/control.jsp?w2xPath=/IPORTAL/user/etc/BIP_CMUC01054P.xml&secn_tpcd=41" );
			conn.addRequestProperty("Content-Type", "application/xml");
			conn.setDoOutput(true);
			
//			String payload = "<reqParam action=\"issuRemaStatList\" task=\"ksd.safe.bip.cnts.DerivCombi.process.DeriCommPTask\"><SECN_TPNM value=\"ELS\"/></reqParam>";
			String payload = "<reqParam action=\"searchIsscoBySecnList\" task=\"ksd.safe.bip.cmuc.User.process.SearchPTask\"><ISSUCO_CUSTNO value=\"594\"/><KOR_SECN_NM value=\"\"/><SECN_TPCD value=\"41\"/></reqParam>";
			
			OutputStreamWriter w  = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			w.write(payload);
			
			w.close();
			
			logger.info("Request Url : {}", url);
			
			int status = conn.getResponseCode();
			logger.info("Response : {}", status);
			
//			BufferedReader in = new BufferedReader( new InputStreamReader(conn.getInputStream()));
			BufferedReader in = new BufferedReader( new InputStreamReader(conn.getInputStream(),"UTF-8"));
			String inputLine;
			StringBuffer html = new StringBuffer();
			
			while ((inputLine = in.readLine())!= null){
				html.append(inputLine).append("\n");
			}
			
			in.close();
			conn.disconnect();
			
			logger.info("htm : {} ", html.toString());
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}	
	
	private static void callSeveltServiceAll(String referer, String payload){
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
			
			logger.info("Request Url : {}, {}", url, payload);
			
			int status = conn.getResponseCode();
			logger.info("Response : {}", status);
			
//			BufferedReader in = new BufferedReader( new InputStreamReader(conn.getInputStream()));
			BufferedReader in = new BufferedReader( new InputStreamReader(conn.getInputStream(),"UTF-8"));
			String inputLine;
			StringBuffer html = new StringBuffer();
			
			while ((inputLine = in.readLine())!= null){
				html.append(inputLine);
//				logger.info("line : {} ", in.readLine().toString());
			}
			
			in.close();
			conn.disconnect();
			
			logger.info("htm : {} ", html.toString());
			
			Document xml = Jsoup.parse(html.toString());
//			Elements links = xml.body().children();
			Elements links = xml.select("result");
			
			for (Element link : links) {
				Elements aa = link.children();
				for(Element bb : aa){
//					logger.info("children :{}", bb);
					logger.info("children element :{},{}", bb.nodeName(),bb.attr("value"));
					
				}
				// get the value from href attribute
//				System.out.println("\nlink : " + link.attr("href"));
//				System.out.println("text : " + link.text());
//	 
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
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
	private static void aaa(String html){
		Document xml = Jsoup.parse(html.toString());
	//	Elements links = xml.body().children();
		Elements links = xml.select("result");
		
		for (Element link : links) {
			Elements aa = link.children();
			for(Element bb : aa){
	//			logger.info("children :{}", bb);
				logger.info("children element :{},{}", bb.nodeName(),bb.attr("value"));
				
			}
			// get the value from href attribute
	//		System.out.println("\nlink : " + link.attr("href"));
	//		System.out.println("text : " + link.text());
	//
	}	
	}
}
