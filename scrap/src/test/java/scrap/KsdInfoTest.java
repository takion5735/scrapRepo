package scrap;


import java.io.IOException;
import java.util.Properties;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator.MatchesOwn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class KsdInfoTest {
	private final static Logger logger = LoggerFactory.getLogger(KsdInfoTest.class);
	private  static Properties properties = new Properties();
	
	
	public static void main(String[] args) {
		bondInfoTest();
  }
	
	private static void bondInfoTest(){
		Document doc;
		
		try {
			properties.load(KsdInfoTest.class.getResourceAsStream("/url.properties"));
			logger.info("AAA: {}", properties.getProperty("ksdInfo1"));
						  
			String url = "http://www.seibro.or.kr/websquare/engine/proworks/callServletService.jsp";
			String agent = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36";
//			String referer ="http://www.seibro.or.kr/websquare/control.jsp?w2xPath=/IPORTAL/user/derivCombi/BIP_CNTS07001V.xml&menuNo=191";
//			String payload = "<reqParam action=\"issuInfoList\" task=\"ksd.safe.bip.cnts.DerivCombi.process.DeriELSPTask\"><MENU_NO value=\"200\"/><W2XPATH value=\"/IPORTAL/user/derivCombi/BIP_CNTS07016V.xml\"/><ISIN value=\"KR6723307577\"/></reqParam>";
			
			String referer = "http://www.seibro.or.kr/websquare/control.jsp?w2xPath=/IPORTAL/user/derivCombi/BIP_CNTS07016V.xml&menuNo=200";
			String payload = "<reqParam action=\"issuInfoList\" task=\"ksd.safe.bip.cnts.DerivCombi.process.DeriELSPTask\"><MENU_NO value=\"200\"/><W2XPATH value=\"/IPORTAL/user/derivCombi/BIP_CNTS07016V.xml\"/><ISIN value=\"KR6723307577\"/></reqParam>";
			String path = "/IPORTAL/user/derivCombi/BIP_CNTS07016V.xml"; 
			
			
			
			doc = Jsoup.connect(url)
					.userAgent(agent)
					.referrer(referer)
//					.header("Content-Type", "application/xml")
//					.header("submissionid" , "submission_issuRemaList")
//					.header("payload", payload)
					.data("action", "issuInfoList")
					.data("task", "ksd.safe.bip.cnts.DerivCombi.process.DeriELSPTask")
					.data("MENU_NO", "200")
					.data("W2XPATH", path)
					.data("ISIN", "KR6723307577")
					.data("STD_DT", "20150703")
					.post()
					
					;
					
//			doc = Jsoup.connect("http://www.seibro.or.kr/websquare/control.jsp")
//						.data("w2xPath", "/IPORTAL/user/derivCombi/BIP_CNTS07001V.xml")
//						.data("menuNo", "191")
//						.data("action", "locationInfoList")
//						.data("action", "menuAllList")
//						.data("ISIN", "KR6693302335")
//						.data( "task" ,"ksd.safe.bip.cnts.DerivCombi.process.DeriELSPTask")
//						.data("action", "issuInfoList")
//						.post();
//					.get();
//			doc.outputSettings().escapeMode(Entities.EscapeMode.xhtml);
//			doc.outputSettings().escapeMode(Entities.EscapeMode.base);
//			doc.select("th:matchesOwn((?is) )").remove();
//			doc.select("td:matchesOwn((?is) )").remove();
			
//			Elements _rows = doc.select("table[id = tbl1]> tbody>tr");
//			System.out.println("Table : "+ _rows);
			
//			for( Element _row : _rows){
//				System.out.println("Row11: "+ _row.childNodeSize());
//				for(Node  _node : _row.childNodes()){
//					System.out.println("Row : "+ _node.nodeName());
//				}
				
//				logger.info("Row1: {}, {}", _row, _row.children().size());
//				if( _row.children().size()== 4){
//					for(int i=0; i<_row.children().size(); i=i+2){
//						_row.children().get(i).select("th:matchesOwn(?is)").remove();
//						_row.children().get(i+1).select("td:matchesOwn(?is)").remove();
//						if( !_row.children().get(i).text().contains("\u00a0")){;
//						if( _row.children().get(i).text().replace("&nbsp;","")!=""){;
//							logger.info("Row1: {}: {}", _row.children().get(i).text(), _row.children().get(i+1).text());
//						}
//					}
//					for( Element _rowCont : _row.children()){
////						logger.info("Row1: {}, {}", _rowCont.text());
//					}	
//				}
					
				
//					System.out.println("Row : "+ _rowCont.nodeName());
//			}
				
					
//					System.out.println("Row : "+ aa.children() );
//				Elements colName = aa.select("th[class != tac]");
//				if( colName.size()>0){
//					System.out.println("Row : "+ colName+ colValue );
//				}
//				Elements colValue = aa.select("td:eq(0)");
//				System.out.println("Row : "+  colValue);
//				System.out.println("Row : "+ colName +"_" + colValue);
				
//			}
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
			System.out.println("text : 1");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
