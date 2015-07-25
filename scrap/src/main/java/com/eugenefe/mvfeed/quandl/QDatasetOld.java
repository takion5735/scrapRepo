package com.eugenefe.mvfeed.quandl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class QDatasetOld {

    private String id;
    private String sourceCode;
    private String code;
    private String name;
    private String urlize_name;
    private String description;
    private String updatedAt;
    private String frequency;
    private String fromDate;
    private String toDate;
    private List<String> columnNames = new ArrayList<String>();
    private boolean isPrivate;
    private String errors;
    private String rawData;
    private List<QEntry> dataset = new ArrayList<QEntry>();

    public QDatasetOld() {
    }
    
    public QDatasetOld getData(String input, String type){
    	ObjectMapper mapper = new ObjectMapper();
    	try {
			QDatasetOld qDataset = mapper.readValue(input, QDatasetOld.class);
			System.out.println(qDataset.getId());
			return qDataset;
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return new QDatasetOld();
    }

    public String getId() {
        return id;

    }

    public String getSourceCode() {
        return sourceCode;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getUrlizeName() {
        return urlize_name;
    }

    public String getDescription() {
        return description;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getFrequency() {
        return frequency;
    }

    public String getFromDate() {
        return fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }
    
    
    public boolean isPrivate() {
        return isPrivate;
    }

    public String getErrors() {
        return errors;
    }




    public String getRawJSON() {
        return rawData;
    }

    public List<QEntry> getDataset() {
        return dataset;
    }

    public List<List<String>> getArrayMatrix() {
        List<List<String>> arrayMatrix = new ArrayList<List<String>>();

        for (QEntry eachEntry : dataset) {
            arrayMatrix.add(eachEntry.getRow());
        }

        return arrayMatrix;
    }

    public String[][] getStringMatrix() {
        String stringMatrix[][] = new String[dataset.size()][dataset.get(0).getRow().size()];

        for (int i = 0; i < dataset.size(); i++) {
            for (int j = 0; j < dataset.get(i).getRow().size(); j++) {
                stringMatrix[i][j] = dataset.get(i).getRow().get(j);
            }
        }
        return stringMatrix;
    }

    public void print() {

        println("ID:" + id);
        println("SOURCE CODE:" + sourceCode);
        println("CODE:" + code);
        println("NAME:" + name);
        println("URLIZE NAME:" + urlize_name);
        println("DESCRIPTION:" + description);
        println("UPDATED AT:" + updatedAt);
        println("FREQUENCY:" + frequency);
        println("FROM DATE:" + fromDate);
        println("TO DATE:" + toDate);
        println("IS PRIVATE:" + isPrivate);
        println("ERRORS:" + errors);
        println("RAW DATA:" + rawData);


    }

    public void println(String string) {
        System.out.println(string);
    }


}
