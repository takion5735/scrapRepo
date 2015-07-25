package com.eugenefe.mvfeed.dart;

import java.io.IOException;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eugenefe.mvfeed.util.FileUtil;

public class DartTableUtil {
	private final static Logger logger = LoggerFactory.getLogger(DartTableUtil.class);
	
	
	public static String[] getTableHeader(Elements theads, int colSize) {
		int headRows = theads.size();
		String[] header = new String[colSize];
		String[][] cellName = new String[headRows][colSize];
		int[] colRowspan = new int[colSize];
		for (int i = 0; i < colSize; i++) {
			colRowspan[i] = 0;
		}

		int rowIndex = 0;
		int colIndex = 0;
		int colspan = 0;
		int rowspan = 0;

		int idx = 1;
		for (Element tr : theads) {
			colIndex = 0;

			for (Element th : tr.children()) {
				for (int m = colIndex; m < colSize; m++) {
					if (rowIndex >= colRowspan[m]) {
						colIndex = m;
						break;
					}
				}

				if (th.hasAttr("rowspan")) {
					rowspan = Integer.valueOf(th.attr("rowspan"));
				} else {
					rowspan = 1;
				}

				if (th.hasAttr("colspan")) {
					colspan = Integer.valueOf(th.attr("colspan"));

				} else {
					colspan = 1;
				}
				// logger.info("span : {}, {}", rowspan, colspan);
//				logger.info("cell Index : {}, {}", rowIndex, colIndex);

				for (int k = colIndex; k < colIndex + colspan; k++) {
					// logger.info("column row: {}, {}", k, colIndex);
					
					cellName[rowIndex][k] = th.text();
					colRowspan[k] = Math.max(colRowspan[k], rowIndex + rowspan);
				}
				colIndex = colIndex + colspan;
			}
			rowIndex = rowIndex + 1;
		}
		
		for(int k =0 ; k < colSize; k++){
			String temp = new String();
			for (int l = 0; l < headRows; l++) {
				if(cellName[l][k] != null){
					temp = temp + cellName[l][k]+";";
				}
//				else{
//					temp = temp + ";";
//				}
			}
			header[k] =temp;
		}
//		for (int m = 0; m < colSize; m++) {
//				logger.info("colNumqq : {}, {}", header[m]);
//		}
		return header;
	}
	
	public static void getTableBody(Elements tbodys, int colSize) {
		int rowIndex = 0;
		int colIndex = 0;
		int colspan = 0;
		int rowspan = 0;
		int rowSize = tbodys.size();
		
		int[] colRowspan = new int[colSize];
		for (int i = 0; i < colSize; i++) {
			colRowspan[i] = 0;
		}
		
		for (Element tr : tbodys) {
			logger.info("Tbody tr: {},{}", tbodys.size(), tr.text());
			for (Element td : tr.children()) {
				if (td.hasAttr("rowspan")) {
					rowspan = Integer.valueOf(td.attr("rowspan"));
				} else {
					rowspan = 1;
				}
				if (td.hasAttr("colspan")) {
					colspan = Integer.valueOf(td.attr("colspan"));

				} else {
					colspan = 1;
				}
				
				for (int k = colIndex; k < colIndex + colspan; k++) {
					// logger.info("column row: {}, {}", k, colIndex);
					
					
					colRowspan[k] = Math.max(colRowspan[k], rowIndex + rowspan);
				}
				colIndex = colIndex + colspan;
			}
			rowIndex = rowIndex + 1;
		}
		
//		return null;
	}
}
