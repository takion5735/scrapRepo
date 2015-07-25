package com.eugenefe.mvfeed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eugenefe.mvfeed.quandl.EQuandlParam;
import com.eugenefe.mvfeed.quandl.QConnection;
import com.eugenefe.mvfeed.quandl.QDataSource;
import com.eugenefe.mvfeed.quandl.QDataset;
import com.eugenefe.mvfeed.quandl.QDatasetList;
import com.eugenefe.mvfeed.quandl.QuandlConnection;

public class TestQconnection {

//	private final static Logger logger = LoggerFactory.getLogger(TestQconnection.class);
	private final static Logger logger = LoggerFactory.getLogger("TestQconnection");
	private final static String authToken = "W6qxqMz1sZPZcG93SxS4";
//	private final static String authToken = "rN1Kd5yuzLA1fcwjLnFy";
//	private final static String authToken = "ZAjyaEZBfqgqTbFYXDot";
	
	
	public static void main(String[] args) throws JsonParseException,JsonMappingException, IOException {

//		getQuandlDataCount();
		testQuandlData();
//		getQuandlData();
		
//		getQuandlQuery();
	}

	public static void getQuandlData() {
		 QConnection q = new QConnection();
		 
		 String qCode = "OFDP/FUTURE_C1";
//		 String qCode = "OFDP/FUTURE_KCN2013";
		 Map<String, String> param = new HashMap<String, String>();

//		 QDataset data1 = q.getQDataset(authToken, qCode);
		 QDataset data1 = q.getQDataset(authToken, qCode,"2014-02-01","2014-02-07");
//		 param.put(EQuandlParam., value)
//		 QDataset data1 = q.getQDataset(qCode,param);
		 
		logger.info("Quandl : {},{}", data1.getColumn_names(),data1.getDescription());
		logger.info("Quandl : {},{}", data1.getDisplay_url(), data1.getName());
		
		List<Object> printList = new ArrayList<Object>();
		printList.add(data1.getId());
		printList.add(data1.getName());
		printList.add(data1.getSource_code());
		printList.add(data1.getCode());
		printList.add(data1.getType());
		printList.add(data1.getFrequency());
		printList.add(data1.getFrom_date());
		printList.add(data1.getTo_date());
		printList.add(data1.getColumn_names());
		printList.add(data1.getDisplay_url());
		printList.add(data1.getUrlize_name());
		printList.add(data1.getUpdated_at());
		printList.add(data1.getDescription());
//		printList.add(data1.getData());
		logger.info("Quandl list; {};{};{};{};{};{};{};{};{};{};{};{};{};{}", printList.toArray());
		logger.info("azz:{}", data1.toString());
	}
	public static void testQuandlData() {
		 QConnection q = new QConnection();
		 
		 String qCode = "OFDP/FUTURE_C1";
//		 String qCode = "OFDP/FUTURE_KCN2013";
		 Map<String, String> param = new HashMap<String, String>();

		 QDataset data1 = q.getQDataset(authToken, qCode);
//		 param.put(EQuandlParam., value)
//		 QDataset data1 = q.getQDataset(qCode,param);
		 
//		logger.info("Quandl : {},{}", data1.getColumn_names(),data1.getDescription());
//		logger.info("Quandl : {},{}", data1.getDisplay_url(), data1.getName());
		
		List<Object> printList = new ArrayList<Object>();
		printList.add(data1.getId());
		printList.add(data1.getName());
		printList.add(data1.getSource_code());
		printList.add(data1.getCode());
		printList.add(data1.getType());
		printList.add(data1.getFrequency());
		printList.add(data1.getFrom_date());
		printList.add(data1.getTo_date());
		printList.add(data1.getColumn_names());
		printList.add(data1.getDisplay_url());
		printList.add(data1.getUrlize_name());
		printList.add(data1.getUpdated_at());
		printList.add(data1.getDescription());
		printList.add(data1.getData());
		logger.info("Quandl list; {};{};{};{};{};{};{};{};{};{};{};{};{};{}", printList.toArray());
	}
	public static void getQuandlDataCount() {
		 QConnection q = new QConnection();
		 
		 String searchString = "ETF";

			QDatasetList dataList = q.getCodeQuery(authToken,searchString);
//			logger.info("Quandl2 : {},{}", dataList.getCurrent_page(),	dataList.getTotal_count());
//			logger.info("Quandl2 : {},{}", dataList.getPer_page(), dataList.getSources());
		 

	}
	public static void getQuandlQuery() {
		QConnection q = new QConnection();
		 
		String searchString = "ETF";

		QDatasetList dataList = q.getCodeQuery(authToken,searchString,2);
//		logger.info("Quandl2 : {},{}", dataList.getCurrent_page(),	dataList.getTotal_count());
//		logger.info("Quandl2 : {},{}", dataList.getPer_page(), dataList.getSources());
//		if(dataList.getTotal_count()>0){
//			 for (QDataset aa : dataList.getDocs()) {
//				 logger.info("Quandl2 : {}, {}", aa.getSource_code(), aa.getCode());
//			 }
//		}
		 int totalSize = dataList.getTotal_count();
		 int totalPageNo = dataList.getTotal_count() / 20 + 1;
//		int totalPageNo = 3;
//		for (int i = 1; i <= totalPageNo; i++) {
			for (int i = 1; i <= 3; i++) {	
//			logger.info("Quandl1111 : {}, {}");
			dataList = q.getCodeQuery(authToken, searchString, i);
//			logger.info("Quandl1111 : {}, {}", i);
			for (QDataset aa : dataList.getDocs()) {
				 logger.info("Quandl222 :{}/{}", aa.getSource_code(), aa.getCode());
//				 logger.info("Quandl2 : {}, {}", aa.getColumn_names() );
			     logger.info("Quandl2 : {}", aa.getDescription());
			 }

		}
	}
}
