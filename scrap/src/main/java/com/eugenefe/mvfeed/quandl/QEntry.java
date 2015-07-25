package com.eugenefe.mvfeed.quandl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

public class QEntry {

	private String date;
	private List<String> row;

	public QEntry(JSONArray entry) {
		row = new ArrayList<String>();
		try {
			this.date = entry.get(0).toString();
			for (int i = 0; i < entry.length(); ++i) {
				row.add(entry.getJSONObject(i).toString());
			}
			// for(Object eachValue : entry) {
			// row.add(eachValue.toString());
			// }

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public List<String> getRow() {
		return row;
	}

	public String getDate() {
		return date;
	}
}
