package scrap;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eugenefe.scrap.util.KsdUtil;


public class KofiaUtilTest {
	private final static Logger logger = LoggerFactory.getLogger(KofiaUtilTest.class);
	
	private static StringBuffer strBuffer = new StringBuffer();
	private static String url = "http://dis.kofia.or.kr/proframeWeb/XMLSERVICES/";
	 
	public static void main(String[] args) {
//		String isin ="KR6693321335";
//		String isin ="KR6709346573";
		String isin ="KR66823123A6";
		
		
//		String url = "http://dis.kofia.or.kr/proframeWeb/XMLSERVICES/";
		String agent = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36";
		
		String referer = "http://dis.kofia.or.kr/websquare/index.jsp?w2xPath=/wq/etcann/DISFundStandardCD.xml&divisionId=MDIS04003000000000&serviceId=SDIS04003000000";
		
		String payload = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
				+ "<message>"
				+ "<proframeHeader>"
				+ 	"<pfmAppName>FS-DIS2</pfmAppName>"
				+ 	"<pfmSvcName>DISFundStandardCdSO</pfmSvcName>"
				+ 	"<pfmFnName>selectExcel</pfmFnName>"
				+ "</proframeHeader>"
				+ "<systemHeader></systemHeader>"
				+ "<DISStdCdPageDTO>"
				+ 	"<companyCd>A01013</companyCd>"
				+ 	"<fundNm>산은장기사모채권제7</fundNm>"
				+ 	"<shortCd></shortCd>"
				+ 	"<businessGb></businessGb>"
				+ "</DISStdCdPageDTO>"
				+ "</message>"
				;
		
		String payload1 = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
				+ "<message>  "
				+ 	"<proframeHeader>    "
				+ 		"<pfmAppName>FS-COM</pfmAppName>    "
				+ 		"<pfmSvcName>COMFundUnityBasInfoSO</pfmSvcName>    "
				+ 		"<pfmFnName>fundBasInfoSrch</pfmFnName>  </proframeHeader>  "
				+ 	"<systemHeader></systemHeader>    "
				+ 	"<COMFundUnityInfoInputDTO>    "
				+ 		"<standardCd>KR5205544988</standardCd>    "
				+ 		"<companyCd>A01013</companyCd>    "
				+ 		"<standardDt>20060208</standardDt>"
				+ 	"</COMFundUnityInfoInputDTO>"
				+ "</message>"
				;
		Map<ElsHeader, String> rst = new HashMap<ElsHeader, String>();
		
		for(Map.Entry<ElsHeader,String> entry : rst.entrySet()){
			logger.info("ddd : {},{}", entry.getKey().toString(), entry.getValue());
		}
		
		String rst1 = callSeveltService(referer, payload1);
		logger.info("xml : {} ", rst1.toString());
		bondInfoTest(referer,payload);
		
		
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

	
	private static void bondInfoTest(String referer, String payload){
		Document doc;
		String agent = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36";
		try {
						  
			doc = Jsoup.connect(url)
//					.userAgent(agent)
//					.referrer(referer)
					.header("referer", referer)
//					.header("Content-Type", "application/xml")
//					.header("submissionid" , "submission_issuRemaList")
					.header("payload", payload)
//					.data("action", "issuInfoList")
//					.data("task", "ksd.safe.bip.cnts.DerivCombi.process.DeriELSPTask")
//					.data("MENU_NO", "200")
//					.data("W2XPATH", path)
//					.data("ISIN", "KR6723307577")
//					.data("STD_DT", "20150703")
					.post()
					
					;
					
			// get page title
			String title = doc.title();
			System.out.println("title : " + title);
			logger.info("Row1: {}, {}", doc);
	 
			// get all links
			Elements links = doc.select("a[href]");
			for (Element link : links) {
	 
				// get the value from href attribute
//				System.out.println("\nlink : " + link.attr("href"));
//				System.out.println("text : " + link.text());
//	 
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
