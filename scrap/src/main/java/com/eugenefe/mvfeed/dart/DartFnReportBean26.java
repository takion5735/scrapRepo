package com.eugenefe.mvfeed.dart;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DartFnReportBean26 {
	private final Logger logger = LoggerFactory.getLogger(DartFnReportBean26.class);

	private String url = "http://dart.fss.or.kr/dsab001/search.ax";
	private String stDate;
	
	public DartFnReportBean26() {
	}
	

	public String getTableName(Element table) {
		// TODO : check table for content table
		if (table.attr("class").equals("nb")) {
			return table.text();
		}
		return "";
	}

	public void getTableContent(Element table) {
		// logger.info("In the Table : {}", table);
		if (table.hasAttr("border")) {
			logger.info("In the Table : {}, {}", table.attr("border"));
			Elements theads = table.select("thead > tr ");
			int headRows = theads.size();
			for (Element aa : theads) {
				logger.info("In the Table : {}, {}", aa, headRows);
			}

		}
	}
	
}
