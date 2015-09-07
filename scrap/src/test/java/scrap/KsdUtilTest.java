package scrap;


import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eugenefe.scrap.util.KsdUtil;


public class KsdUtilTest {
	private final static Logger logger = LoggerFactory.getLogger(KsdUtilTest.class);
//	private static String url = "http://www.seibro.or.kr/websquare/engine/proworks/callServletService.jsp";

//	private static String _action = "<reqParam action=\"";
//	private static String _task = "\" task=\"";
//	private static String _menuNo = "\"><MENU_NO value=\"";
//	private static String _xpath = "\"/><W2XPATH value=\"";
//	private static String _isin = "\"/><ISIN value=\"";
//	private static String _issuerCode = "\"/><ISSUCO_CUSTNO value=\"";
//	private static String _prodType = "\"/><SECN_TPCD value=\"";	
//	private static String _endArg ="\"/></reqParam>";
	
	private static StringBuffer strBuffer = new StringBuffer();
	 
	public static void main(String[] args) {
//		String isin ="KR6693321335";
//		String isin ="KR6709346573";
		String isin ="KR66823123A6";
		
		Map<ElsHeader, String> rst = new HashMap<ElsHeader, String>();
		
//		String temp = KsdUtil.getElsInfo(isin);
//		String temp = KsdUtil.getElsStrikePrice(isin);
//		String temp = KsdUtil.getElsSchedule(isin);
//		String temp = KsdUtil.getElsBaseInfo(isin);
		
//		logger.info("xml : {} ", temp);
		
//		KsdUtil.getElsInfoMap(isin);
//		KsdUtil.getElsStrikePriceMap(isin);
//		KsdUtil.getElsScheduleMap(isin);
//		KsdUtil.getElsBaseInfoMap(isin);
//		KsdUtil.getElsSchedule(isin);
//		KsdUtil.getElsBaseInfo(isin);
//		KsdUtil.getElsDataDoc(EelsDataType.BASIC, isin);
		
		rst = KsdUtil.getElsDataMap(EKsdElsDataGroup.BASIC, isin);
//		rst = KsdUtil.getElsDataMap(EKsdElsDataGroup.PAYOFF, isin);
//		rst = KsdUtil.getElsDataMap(EKsdElsDataGroup.UNDERLYING, isin);
//		rst = KsdUtil.getElsDataMap(EKsdElsDataGroup.STRIKE, isin);
		
		for(Map.Entry<ElsHeader,String> entry : rst.entrySet()){
			logger.info("ddd : {},{}", entry.getKey().toString(), entry.getValue());
		}
	}
	

}
