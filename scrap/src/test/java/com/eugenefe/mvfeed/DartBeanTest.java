package com.eugenefe.mvfeed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eugenefe.mvfeed.dart.DartCompany;
import com.eugenefe.mvfeed.dart.DartCompanyMasterBean;
import com.eugenefe.mvfeed.dart.DartFinancialReportBean;
import com.eugenefe.mvfeed.dart.DartReport;
import com.eugenefe.mvfeed.dart.DartReportMasterBean;
import com.eugenefe.mvfeed.util.FileUtil;

public class DartBeanTest {
	private final static Logger logger = LoggerFactory.getLogger(DartBeanTest.class);
	private static String companyFile = "/home/takion77/isincode/bankName";
//	private static String reportFile = "/home/takion77/isincode/dartCompany";
	private static String reportFile = "/home/takion77/isincode/dartReport";
	private static String appendReportFile = "/home/takion77/isincode/dartReportAppend";
	
	private static Set<DartCompany> companyRst = new HashSet<DartCompany>();
	private static Set<DartReport> reportRst = new HashSet<DartReport>();

	public static void main(String[] args) {
//		String stDate = "20130101";
//		String endDate = "20131231";
//		String compName = "";
//		String pubType = "A001";
		
		try {
//			testDartCompany();
//			testDartReportByName();
//			TODO : test online please!!;
//			writeReportListByName(reportFile, companyFile);
			
//			testDartReportByType();
//			TODO : test please;
//			writeReportListByType(reportFile);
			
//			TODO : test please;
//			appendDcmNoTo(appendReportFile, reportFile);
			
			test();
			
		} catch (Exception e) {

		}
		
		for (DartCompany aa : companyRst) {
			// logger.info("AAA:{},{}", aa.getComCode(), aa.getBizNum());
		}
		for (DartReport aa : reportRst) {
			logger.info("AAA:{},{}", aa.getComCode(), aa.getUrl());
		}
	}
	private static void writeReportListByType(String writeFile) throws Exception {
		
		String stDate = "20130101";
		String endDate = "20131231";
		String compName = "";
		String pubType = "A001";
		DartReportMasterBean bb = new DartReportMasterBean(stDate, endDate, null, pubType);
		
		reportRst= bb.getDartReportSet();
//		reportRst= bb.getDartReportSet(1);
		logger.info("AAA:{},{}", bb.getCurrentPage(), bb.getArgMap());
		
		FileUtil.writeFile(writeFile, reportRst);
	}
	
	
	/* TODO :remove after test writeReportListByType....
	   private static void testDartReportByType() throws Exception {
		
		String stDate = "20130101";
		String endDate = "20131231";
		String compName = "";
		String pubType = "A001";
		DartReportMasterBean bb = new DartReportMasterBean(stDate, endDate, null, pubType);
		
		reportRst= bb.getDartReportSet();
//		reportRst= bb.getDartReportSet(1);
		logger.info("AAA:{},{}", bb.getCurrentPage(), bb.getArgMap());
		
		FileUtil.writeFile(rptFilePath, reportRst);
	}*/

	private static void writeReportListByName(String writFile, String companyFile) throws IOException {
		Set<DartReport> temp = new HashSet<DartReport>();

		String stDate = "19980101";
		String endDate = "20141231";
		String compName ;
		String pubType ="A003";

//		comments : Company Info contains company name
		List<String> compList = FileUtil.readFileToList(companyFile);
//		logger.info("File Size : {},{} ", compList.size(),rptFilePath);
		
		DartReportMasterBean rptBean = new DartReportMasterBean(stDate, endDate, null, pubType);
		logger.info("AAA:{},{}", rptBean.getCurrentPage(), rptBean.getArgMap());
		
		for(String aa : compList){
			logger.info("aa : {},{}",aa );
//			TODO waring : check the file to get company name 
			String[] arr = aa.split(";");
			String comName = arr[2];

			rptBean.setCompanyName(comName);
			
			temp = rptBean.getDartReportSet();
			logger.info("Report : {},{}", temp.size(), rptBean.getArgMap() );
			
//			reportRst.addAll(temp);
		}
//		logger.info("ready");
		 FileUtil.writeFile(writFile, temp);
	}
	
	/*  TODO : remove after test writeReportListByName
	private static void testDartReportByName() throws IOException {
		Set<DartReport> temp = new HashSet<DartReport>();

		String stDate = "19980101";
		String endDate = "20141231";
		String compName ;
		String pubType ="A003";

//		comments : Company Info contains company name
		List<String> compList = FileUtil.readFileToList(filePath);
//		logger.info("File Size : {},{} ", compList.size(),rptFilePath);
		
		DartReportMasterBean rptBean = new DartReportMasterBean(stDate, endDate, null, pubType);
		logger.info("AAA:{},{}", rptBean.getCurrentPage(), rptBean.getArgMap());
		
		for(String aa : compList){
			logger.info("aa : {},{}",aa );
			String[] arr = aa.split(";");
//			TODO waring : check the file to get company name 
			String comName = arr[2];
			rptBean.setCompanyName(comName);
			
			temp = rptBean.getDartReportSet();
			logger.info("Report : {},{}", temp.size(), rptBean.getArgMap() );
			reportRst.addAll(temp);
		}
//		logger.info("ready");
		 FileUtil.writeFile(rptFilePath, reportRst);
	}*/
	
	private static void testDartCompany(){
		DartCompanyMasterBean compBean = new DartCompanyMasterBean(1);
		
		companyRst = compBean.getDartCompanySet(2);
		for( DartCompany aa : companyRst){
			logger.info("Company : {} ", aa);
		}
	}
	
	
	private static void test(){
		DartFinancialReportBean finBean = new DartFinancialReportBean();
		logger.info("AAA");
//		Map<String, List<String>> aaa =
		finBean.getAAA();
		
	}
	
	private static void appendDcmNoTo(String writeFile, String readFile){
		List<String> appendList = new ArrayList<String>();
		String appendReport;
		
		DartFinancialReportBean finBean = new DartFinancialReportBean();
		
		//dartReporetUrl example : /dsaf001/main.do?rcpNo=20131231000048";
		String rcpNo ="";
		String dcmNo ;
		String tempName ="";
		try {
			List<String> list = FileUtil.readFileToList(readFile);
			for(String aa : list){
//				logger.info("list :{},{}", aa);
//				logger.info("list :{},{}", aa.split(";")[1]);
				tempName =aa.split(";")[0];
				rcpNo = aa.split("=")[1];
				
				dcmNo=finBean.getDcmNo(rcpNo);
				appendReport =aa + ";" +rcpNo+ ";" +dcmNo;
				appendList.add(appendReport);
			}
			FileUtil.writeFile(writeFile, appendList);
		} catch (IOException e) {
			logger.info("error");
			e.printStackTrace();
		}
	}
}
