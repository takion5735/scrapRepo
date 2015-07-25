package com.eugenefe.mvfeed;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eugenefe.mvfeed.dart.DartCompany;
import com.eugenefe.mvfeed.dart.DartReport;
import com.eugenefe.mvfeed.dart.DartReportMasterBean;
import com.eugenefe.mvfeed.dart.old.DartCompanyMasterAction;
import com.eugenefe.mvfeed.dart.old.DartReportMasterAction;
import com.eugenefe.mvfeed.util.FileUtil;

public class DartActionTest {
	private final static Logger logger = LoggerFactory.getLogger(DartActionTest.class);
	private static String filePath = "/home/takion77/isincode/dartCompany";
	private static String rptFilePath = "/home/takion77/isincode/dartReport";
	private static Set<DartCompany> companyRst = new HashSet<DartCompany>();
	private static Set<DartReport> reportRst = new HashSet<DartReport>();

	public static void main(String[] args) {
		String stDate = "20130101";
		String endDate = "20131231";
		String compName = "";
		String pubType = "A001";
		try {
			
			DartReportMasterBean bb = new DartReportMasterBean("20130101", "20131231", null, "A001");
			
			reportRst= bb.getDartReportSet();
			logger.info("AAA:{},{}", bb.getCurrentPage(), bb.getArgMap());
			
			FileUtil.writeFile(rptFilePath, reportRst);
			// testDartCompanyMasterAction();
//			 testDartReportMasterAction();
//			 testDartReportMasterActionFromFile();
//			testDartReportMasterActionByType();

		} catch (Exception e) {

		}
		for (DartCompany aa : companyRst) {
			// logger.info("AAA:{},{}", aa.getComCode(), aa.getBizNum());
		}
		for (DartReport aa : reportRst) {
			logger.info("AAA:{},{}", aa.getComCode(), aa.getUrl());
		}
	}

	private static void testDartCompanyMasterAction() throws Exception {
		// rst = DartCompanyMasterAction.getMasterSet();
		// rst = DartCompanyMasterAction.getDartCompanySet(2);
		FileUtil.writeFile(filePath, DartCompanyMasterAction.getDartCompanySet(2));
	}

	private static void testDartReportMasterAction() throws Exception {
		// String comName = "�댐옙占쏙옙占쎈뻬";
		String comName = "占쎌꼶援방묾�됱몪筌욑옙竊�";
		String stDate = "20140103";
		String endDate = "20140703";
//		reportRst = DartReportMasterAction.getDartReportSet(comName, stDate, endDate, 2);
//		reportRst = DartReportMasterAction.getDartReportSet(comName, stDate, endDate, 2);
		FileUtil.writeFile(rptFilePath, reportRst);

	}

	private static void testDartReportMasterActionFromFile() throws IOException {
		Set<DartReport> temp = new HashSet<DartReport>();
		 for(String aa : FileUtil.readFileToList(filePath)){
//		for (String aa : FileUtil.readFileToList(filePath + "_20140703.txt")) {
			String[] arr = aa.split(";");
			String comName = arr[2];
			String stDate = "20130101";
			String endDate = "20131231";
			// int size = DartReportMasterAction.getPageNum(comName, stDate,
			// endDate);
			int size = DartReportMasterAction.getPageNumByType("A001", stDate, endDate);
			logger.info("Size : {} ", size);
			for (int i = 0; i < size; i++) {
				// temp = DartReportMasterAction.getDartReportSet(comName,
				// stDate,endDate, i+1);
				temp = DartReportMasterAction.getDartReportSetByType("A001", stDate, endDate, i + 1);

//				logger.info("St: {},{}", arr[1], arr[2]);
				reportRst.addAll(temp);
				temp.clear();
			}
			FileUtil.writeFile(rptFilePath, reportRst);
		}

	}

	private static void testDartReportMasterActionByType() throws IOException {
		Set<DartReport> temp = new HashSet<DartReport>();
		String stDate = "20130101";
		String endDate = "20131231";

		int size = DartReportMasterAction.getPageNumByType("A001", stDate, endDate);
		for (int i = 0; i < size; i++) {
			temp = DartReportMasterAction.getDartReportSetByType("A001", stDate, endDate, i + 1);

			reportRst.addAll(temp);
			temp.clear();
		}
		FileUtil.writeFile(rptFilePath, reportRst);

	}
}
