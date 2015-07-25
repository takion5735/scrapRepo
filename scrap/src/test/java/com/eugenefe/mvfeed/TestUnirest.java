package com.eugenefe.mvfeed;

import java.io.IOException;
import java.util.List;

import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eugenefe.mvfeed.others.CurrencyConverter;
import com.eugenefe.mvfeed.quandl.QDataset;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import com.mashape.unirest.http.HttpResponse;

public class TestUnirest {
//	private static Loggerr logger = Logger.getLogger("MainTest");
//	private static Logger logger = Logger.getLogger(TestUnirest.class);
	
	private final static Logger logger = LoggerFactory.getLogger(TestUnirest.class);
	
//	private static Logger
	/**
	 * @param args
	 */
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException{
		
		
//		currencyConverterTest();
		
//		getSpotPricesData();

//		getQuandlData();
		quandlTest();
//		getQuandlMeta();
	}
	

	
	public static void currencyConverterTest() throws JsonParseException, JsonMappingException, IOException{
		
		ObjectMapper mapper = new ObjectMapper();
		 
		try {
			CurrencyConverter search = mapper.readValue(getCurrencyConverterData(), CurrencyConverter.class);
//			System.out.println(search.getFrom()+":"+ search.getTo()+":"+search.getTo_amount() );
			logger.info("AAA:{}, {}",  search.getFrom(), search.getFrom_amount());
			logger.info("BBB:{}, {}", search.getTo(),search.getTo_amount());
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	public static String getCurrencyConverterData() {
	    DefaultHttpClient httpclient = new DefaultHttpClient(); 
	    try
	    { 
	    	String url = "https://currencyconverter.p.mashape.com/?from_amount=1";
	    	String from = "&from=USD";
	    	String to = "&to=JPY";
	    	StringBuffer buffer = new StringBuffer();
	    	buffer.append(url).append(from).append("&").append(to);
	    	
	    	
	    	String queryString = url + from  + to;
	    	logger.info("CC:{}", queryString);
	    	logger.info("CC:{}", url);
	    	HttpResponse<JsonNode> response =  	Unirest.get(queryString)
//	    			HttpResponse<JsonNode> response =  	Unirest.get(buffer.toString())
	    			.header("X-Mashape-Authorization", "FKYLS3ZQvTQl1QW16GelltYv4OpwJhmw").asJson();

//	    	for(Map.Entry<String, String> entry : response.getHeaders().entrySet()){
//	    		System.out.println("AAA:"+ entry.getKey()+ ":"+ entry.getValue());
//	    	}
//	    	logger.info("AAA:{}", response.getBody());
	            return response.getBody().toString();
	    } 
	    catch(Exception e) 
	    { 
	        System.out.println(e.getMessage()); 
	        return null;
	    } 	    
	}
	
	public static void spotPricesTest() throws JsonParseException, JsonMappingException, IOException{
		
		ObjectMapper mapper = new ObjectMapper();
		 
		try {
			CurrencyConverter search = mapper.readValue(getCurrencyConverterData(), CurrencyConverter.class);
//			System.out.println(search.getFrom()+":"+ search.getTo()+":"+search.getTo_amount() );
			logger.info("AAA:{}, {}", search.getTo(), search.getFrom());
			logger.info("BBB:{}, {}", search.getTo_amount(), search.getFrom_amount());
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	public static String getSpotPricesData() {
	    DefaultHttpClient httpclient = new DefaultHttpClient(); 
	    try
	    { 
	    	String url = "https://prendario.p.mashape.com/api_get.php?access_token=EBGH3_71AbccB0_d02ba&service_id=EB0lXml2hyhs";
	    	HttpResponse<JsonNode> response = Unirest.get(url)
	    				.header("X-Mashape-Authorization", "FKYLS3ZQvTQl1QW16GelltYv4OpwJhmw").asJson();

//	    	for(Map.Entry<String, String> entry : response.getHeaders().entrySet()){
//	    		System.out.println("AAA:"+ entry.getKey()+ ":"+ entry.getValue());
//	    	}
	    	logger.info("AAA:{}", response.getBody());
	            return response.getBody().toString();
	    } 
	    catch(Exception e) 
	    { 
	        System.out.println(e.getMessage()); 
	        return null;
	    } 	    
	}
	public static String getStockTwitsData() {
	    DefaultHttpClient httpclient = new DefaultHttpClient(); 
	    try
	    { 
	    	String url = "https://stocktwits.p.mashape.com/oauth_url";
	    	HttpResponse<JsonNode> response = Unirest.post(url)
	  			  .header("X-Mashape-Authorization", "FKYLS3ZQvTQl1QW16GelltYv4OpwJhmw")
	  			  .field("clientId", "takion77")
	  			  .field("clientSecret", "<client_secret>")
	  			  .field("username", "undefined")
	  			  .field("password", "undefined")
	  			  .field("scope", "<scope>")
	  			  .field("callback", "http://guardian.mashape.com/callback")
	  			  .asJson();

//	    	for(Map.Entry<String, String> entry : response.getHeaders().entrySet()){
//	    		System.out.println("AAA:"+ entry.getKey()+ ":"+ entry.getValue());
//	    	}
	    	logger.info("AAA:{}", response.getBody());
	            return response.getBody().toString();
	    } 
	    catch(Exception e) 
	    { 
	        System.out.println(e.getMessage()); 
	        return null;
	    } 	    
	}
	
	
	
public static void quandlTest() throws JsonParseException, JsonMappingException, IOException{
		
		ObjectMapper mapper = new ObjectMapper();
		 
		try {
			QDataset search = mapper.readValue(getQuandlData(), QDataset.class);
//			System.out.println(search.getFrom()+":"+ search.getTo()+":"+search.getTo_amount() );
			logger.info("AAA:{}, {}",  search.getName(), search.getColumn_names());
			logger.info("AAA:{}, {}",  search.getColumn_names());
			for(List<String> li: search.getData()){
				logger.info("AAA:{}, {}",  li);
//				for( String con: li){
//					logger.info("AAA:{}, {}",  li, con);
//				}
			}
//			logger.info("BBB:{}, {}", search.getTo(),search.getTo_amount());
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	public static String getQuandlData() {
	    DefaultHttpClient httpclient = new DefaultHttpClient(); 
	    try
	    { 
//	    	String url = "http://www.quandl.com/api/v1/datasets/PRAGUESE/PX.json?trim_start=2012-11-01&trim_end=2012-11-30";
	    	String url = "http://www.quandl.com/api/v1/datasets/OFDP/FUTURE_B1.json?trim_start=2012-11-01&trim_end=2012-11-30&auth_token=W6qxqMz1sZPZcG93SxS4";
	    	HttpResponse<JsonNode> response = Unirest.get(url) .asJson();

//	    	for(Map.Entry<String, String> entry : response.getHeaders().entrySet()){
//	    		System.out.println("AAA:"+ entry.getKey()+ ":"+ entry.getValue());
//	    	}
//	    	logger.info("AAA:{}", response.getBody());
	            return response.getBody().toString();
	    } 
	    catch(Exception e) 
	    { 
	        System.out.println(e.getMessage()); 
	        return null;
	    } 	    
	}
	
	public static String getQuandlMeta() {
	    DefaultHttpClient httpclient = new DefaultHttpClient(); 
	    try
	    { 
//	    	String url = "http://www.quandl.com/api/v1/datasets/PRAGUESE/PX.json?trim_start=2012-11-01&trim_end=2012-11-30";
//	    	String url = "http://www.quandl.com/api/v1/datasets/OFDP/FUTURE_B1.json?trim_start=2012-11-01&trim_end=2012-11-30";
//	    	String url = "http://www.quandl.com/api/v1/datasets/NSE/OIL.json?exclude_data=true&auth_token=W6qxqMz1sZPZcG93SxS4";
//	    	String url = "http://www.quandl.com/api/v1/datasets.json/?query=crude&auth_token=W6qxqMz1sZPZcG93SxS4";
//	    	String url = "http://www.quandl.com/api/v1/current_user/collections/datasets/favourites.json?auth_token=W6qxqMz1sZPZcG93SxS4";
	    	String url = "http://quandl.com/api/v1/multisets.json?columns=GOOG.NASDAQ_GOOG.4,GOOG.NASDAQ_AAPL.4&auth_token=W6qxqMz1sZPZcG93SxS4";
	    	
	    	HttpResponse<JsonNode> response = Unirest.get(url) .asJson();

//	    	for(Map.Entry<String, String> entry : response.getHeaders().entrySet()){
//	    		System.out.println("AAA:"+ entry.getKey()+ ":"+ entry.getValue());
//	    	}
//	    	logger.info("AAA:{}", response.getHeaders());
	    	logger.info("AAA:{}", response.getBody());
	            return response.getBody().toString();
	    } 
	    catch(Exception e) 
	    { 
	        System.out.println(e.getMessage()); 
	        return null;
	    } 	    
	}
	
	

}	
