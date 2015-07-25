package com.eugenefe.mvfeed;

import java.io.IOException;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import com.eugenefe.mvfeed.others.FlightHop;
import com.eugenefe.mvfeed.others.FlightItinerary;
import com.eugenefe.mvfeed.others.FlightLeg;
import com.eugenefe.mvfeed.others.FlightSegment;
import com.eugenefe.mvfeed.others.GeocodeResponse;
import com.eugenefe.mvfeed.others.Place;
import com.eugenefe.mvfeed.others.Route;
import com.eugenefe.mvfeed.others.SearchResponse;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import com.mashape.unirest.http.HttpResponse;

public class TestJackson {
//	private static Logger logger = Logger.getLogger("MainTest");
//	private static Logger
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		getFlightSearchData();
    	romeToRio();
//    	romeToRioGeo();
//    	romeToRioJackson();
//		getGeoSearchData();
		try {
			jacksonTest();
		} catch (JsonGenerationException e) {
	 
			e.printStackTrace();
	 
		} catch (JsonMappingException e) {
	 
			e.printStackTrace();
	 
		} catch (IOException e) {
	 
			e.printStackTrace();
	 
		}
	  } 
	
	public static String getFlightSearchData() {
	    DefaultHttpClient httpclient = new DefaultHttpClient(); 
	    try
	    { 
	        // Specify values for path parameters (shown as {...}) 
	        URIBuilder builder = new URIBuilder("http://evaluate.rome2rio.com/api/1.2/json/Search/");
	        
	        // Specify your developer key 
	        builder.setParameter("key", "Z2CA71LM"); 
	        // Specify values for the following required parameters 
	        builder.setParameter("oName", "ICN"); 
	        builder.setParameter("dName", "LAX");
//	        builder.setParameter("oPos", "New York Kennedy");
//	        builder.setParameter("dPos", "40.64441,-73.78275");
//	        builder.setParameter("flags", "0x000FFFF0");
//	        builder.setParameter("flags", "0x000FFFFE");
	        builder.setParameter("flags", "0x000FFFFC");
	        
	        URI uri = builder.build(); 
	        	System.out.println("url ;"+  uri.getQuery());
	        HttpGet request = new HttpGet(uri); 
	        HttpResponse response = httpclient.execute(request); 
	        HttpEntity entity = response.getEntity(); 
	        if (entity != null) { 
	            System.out.println("EntityUtil:" + EntityUtils.toString(entity)); 
	        }
	        return EntityUtils.toString(entity);
	    }
	    catch(Exception e) 
	    { 
	        System.out.println("Error:" + e.getMessage()); 
	        return null;
	    } 
	}
	
	
	
	public static String getGeoSearchData(){
		DefaultHttpClient httpclient = new DefaultHttpClient(); 
		  
	    try
	    { 
	        URIBuilder builder = new URIBuilder("http://evaluate.rome2rio.com/api/1.2/json/Geocode/");
	        
	        builder.setParameter("key", "Z2CA71LM"); 
	        builder.setParameter("query", "JFK");
	        builder.setParameter("countryCode", "US");
	        builder.setParameter("languageCode", "EN");
	        

	        URI uri = builder.build(); 
	        HttpGet request = new HttpGet(uri); 
	        System.out.println("uri :" + uri.getQuery());
	        HttpResponse response = httpclient.execute(request); 
	        HttpEntity entity = response.getEntity(); 
	        if (entity != null) { 
//	            System.out.println(EntityUtils.toString(entity)); 
	        	return EntityUtils.toString(entity);
	        }
	        else{
	        	return null;
	        }
	    } 
	    catch(Exception e) 
	    { 
	        System.out.println(e.getMessage());
	        return null;
	    } 
	}
	public static void jacksonTest() throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		try {
			
			GeocodeResponse user = mapper.readValue(getGeoSearchData(), GeocodeResponse.class);
			
			System.out.println(user.getCountryCode());
			System.out.println(user.getQuery());
			for(Place aa : user.getPlaces()){
				System.out.println(aa.getLongName());
				System.out.println(aa.getLat());
				System.out.println(aa.getLng());
			}
	 
		} catch (JsonGenerationException e) {
	 
			e.printStackTrace();
	 
		} catch (JsonMappingException e) {
	 
			e.printStackTrace();
	 
		} catch (IOException e) {
	 
			e.printStackTrace();
	 
		}
	}
	public static void romeToRioJackson(){
		ObjectMapper mapper = new ObjectMapper();
		 
		try {
			
			SearchResponse search = mapper.readValue(getFlightSearchData(), SearchResponse.class);
	 
			for(Route route : search.getRoutes()){
				System.out.println(route.getName());
				for( FlightSegment seg : route.getSegments()){
					for( FlightItinerary itin : seg.getItineraries()){
						for(FlightLeg leg : itin.getLegs()){
//							System.out.println("SEG: " + leg.getDays());
							if(leg.getHops().size()==1){
//								System.out.println("Airline: " + aa.getAirline() +":"+ aa.getsCode()+":"+ aa.gettCode()+":"+ leg.getDays());
								for(FlightHop aa : leg.getHops()){
									System.out.println("Airline: " + aa.getAirline() +":"+ aa.getsCode()+":"+ aa.gettCode()+":"+ leg.getDays());
								}
							}
							
						}
					}
				}
			}
			
	 
		} catch (JsonGenerationException e) {
	 
			e.printStackTrace();
	 
		} catch (JsonMappingException e) {
	 
			e.printStackTrace();
	 
		} catch (IOException e) {
	 
			e.printStackTrace();
	 
		}
	}
	public static void romeToRio() { 

	    DefaultHttpClient httpclient = new DefaultHttpClient(); 
	  
	    try
	    { 
	        // Specify values for path parameters (shown as {...}) 
	        URIBuilder builder = new URIBuilder("http://evaluate.rome2rio.com/api/1.2/json/Search/");
	        
	        // Specify your developer key 
	        builder.setParameter("key", "Z2CA71LM"); 
	        // Specify values for the following required parameters 
	        builder.setParameter("oName", "ICN"); 
	        builder.setParameter("dName", "LAX");
//	        builder.setParameter("oPos", "New York Kennedy");
//	        builder.setParameter("dPos", "40.64441,-73.78275");
//	        builder.setParameter("flags", "0x000FFFF0");
//	        builder.setParameter("flags", "0x000FFFFE");
	        builder.setParameter("flags", "0x000FFFFC");
	        
	        URI uri = builder.build(); 
	        HttpGet request = new HttpGet(uri); 
	        HttpResponse response = httpclient.execute(request); 
	        HttpEntity entity = response.getEntity(); 
	        if (entity != null) { 
	            System.out.println("EntityUtil:" + EntityUtils.toString(entity)); 
	        } 
			
	        JSONObject obj = new JSONObject(EntityUtils.toString(entity));
	        
	        JSONArray routes = obj.getJSONArray("routes");
	        JSONArray airlines = obj.getJSONArray("airlines");
	        JSONArray airports = obj.getJSONArray("airports");
	        JSONArray agencies = obj.getJSONArray("agencies");

	        for (int i = 0; i < airlines.length(); ++i) {
		          JSONObject airline = airlines.getJSONObject(i);
		          
//		          logger.info("Code :{}" , airline.getString("code") );
		          System.out.println("code:" + airline.getString("code"));
		          System.out.println("name:" + airline.getString("name"));
		          System.out.println("url:" + airline.getString("url"));
//		          System.out.println("iconPath:" + airline.getString("iconPath"));
//		          System.out.println("iconSize:" + airline.getString("iconSize") );
//		          System.out.println("iconOffset:" + airline.getString("iconOffset") );
		       
	        }      
	        System.out.println("Airport*************************************************");
	        for (int i = 0; i < airports.length(); ++i) {
		          JSONObject airport = airports.getJSONObject(i);

		          System.out.println("code:" + airport.getString("code"));
		          System.out.println("name:" + airport.getString("name"));
		          System.out.println("pos:" + airport.getString("pos"));
		       
	        }
	        
	        System.out.println("Agency*************************************************");
	        for (int i = 0; i < agencies.length(); ++i) {
		          JSONObject agency = agencies.getJSONObject(i);

		          System.out.println("code:" + agency.getString("code"));
		          System.out.println("name:" + agency.getString("name"));
		          System.out.println("url:" + agency.getString("url"));
//		          System.out.println("iconPath:" + agency.getString("iconPath"));
//		          System.out.println("iconSize:" + agency.getString("iconSize") );
//		          System.out.println("iconOffset:" + agency.getString("iconOffset") );
	        }  
	        
	        System.out.println("Routes*************************************************");
	       
	         for (int i = 0; i < routes.length(); ++i) {
	          JSONObject route = routes.getJSONObject(i);
	          
	          System.out.println("Name:" + route.getString("name"));
	          System.out.println("Distance:" + route.getString("distance"));
	          System.out.println("Duration:" + route.getString("duration"));
	          
	          System.out.println("Routes Stop*************************************************");
	          JSONArray stops = route.getJSONArray("stops");
	          	for ( int j=0 ; j<stops.length(); j++){
	        	  JSONObject stop = stops.getJSONObject(j);
	        	  System.out.println("kind: " + stop.getString("kind"));
	        	  System.out.println("name: " + stop.getString("name"));
	        	  System.out.println("Position: " + stop.getString("pos"));
	        	  System.out.println("code: " + stop.getString("code"));
	        	  
	          	}
	          	
	          	System.out.println("Routes Segment*************************************************");
	          	JSONArray segments = route.getJSONArray("segments");
	          	for ( int k=0 ; k<segments.length(); k++){
	        	  JSONObject seg = segments.getJSONObject(k);
	        	  JSONObject price = seg.getJSONObject("indicativePrice");
	        	  
	        	  System.out.println("kind: "+ seg.getString("kind"));
	        	  System.out.println("isMajor: "+ seg.getString("isMajor"));
	        	  System.out.println("isMajor1: "+ seg.getInt("isMajor"));
	        	  System.out.println("distance: "+ seg.getDouble("distance"));
	        	  System.out.println("duration: "+ seg.getDouble("duration"));
	        	  System.out.println("Start Code: "+ seg.getString("sCode"));
	        	  System.out.println("End Code: "+seg.getString("tCode"));
	        	  
	        	  
	        	  System.out.println("Price:price: "+ price.getDouble("price"));
	        	  System.out.println("Price:curr: "+ price.getString("currency"));
//	        	  System.out.println("Price:nativeprice: "+ price.getDouble("nativePrice"));
//	        	  System.out.println("Price:nativecurr: "+ price.getString("nativeCurrency"));
	        	  System.out.println("Price:isFree : "+ price.getInt("isFreeTransfer"));
	        	  
	        	  
	        	  System.out.println("Routes Segment Itineraries****************************************");
	        	  JSONArray itineraries = seg.getJSONArray("itineraries");
	        	  for(int m =0 ; m< itineraries.length();m++){
	        		  JSONObject itin = itineraries.getJSONObject(m);
	        		  JSONArray legs = itin.getJSONArray("legs");
	        		  for(int n =0 ; n < legs.length(); n++){
	        			  JSONObject leg = itineraries.getJSONObject(n);
//	        			  JSONObject days =leg.getJSONObject("days"); 
//	        			  System.out.println("Leg:days: "+ days.getString("value"));

	        			  JSONArray hops = leg.getJSONArray("hops");
	        			  for(int ii =0; ii< hops.length(); ii++){
	        				  System.out.println("Hop:Start Code: "+ seg.getString("sCode"));
	        				  System.out.println("Hop:End Code: "+seg.getString("tCode"));
//	        				  System.out.println("Hop:Start Time: "+seg.getString("sTime"));
//	        				  System.out.println("Hop:End Time: "+seg.getString("tTime"));
	        				  System.out.println("Hop:airline "+ seg.getString("airline"));
	        	        	  System.out.println("Hop:flight "+ seg.getString("flight"));
	        	        	  System.out.println("duration: "+ seg.getDouble("duration"));
	        	        	  System.out.println("dayChange: "+ seg.getDouble("dayChange"));
	        	        	  System.out.println("lDuration: "+ seg.getDouble("lDuration"));
	        	        	  System.out.println("lDayChange: "+ seg.getDouble("lDayChange"));
	        	        	  
	        			  }
	        		  }
	        	  }	  
	        
	          	}	   
	         } 
	    } 
	    catch(Exception e) 
	    { 
	        System.out.println(e.getMessage()); 
	    } 
}
	
	public static void romeToRioGeo() { 
	    DefaultHttpClient httpclient = new DefaultHttpClient(); 
	  
	    try
	    { 
	        // Specify values for path parameters (shown as {...}) 
	        URIBuilder builder = new URIBuilder("http://evaluate.rome2rio.com/api/1.2/json/Geocode/");
	        
	        // Specify your developer key 
	        builder.setParameter("key", "Z2CA71LM"); 
	        // Specify values for the following required parameters 
	        builder.setParameter("query", "JFK");
//	        builder.setParameter("countryCode", "KR");
//	        builder.setParameter("languageCode", "");
	        

	        URI uri = builder.build(); 
	        HttpGet request = new HttpGet(uri); 
	        HttpResponse response = httpclient.execute(request); 
	        HttpEntity entity = response.getEntity(); 
	        if (entity != null) { 
	            System.out.println(EntityUtils.toString(entity)); 
	        } 
	    } 
	    catch(Exception e) 
	    { 
	        System.out.println(e.getMessage()); 
	    } 
}
}	
