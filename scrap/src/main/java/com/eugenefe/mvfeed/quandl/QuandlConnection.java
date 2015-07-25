package com.eugenefe.mvfeed.quandl;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;


public class QuandlConnection {
	private final static Logger logger = LoggerFactory.getLogger(QuandlConnection.class);

	private String token;
	private boolean isToken = false;
	private final String baseUrl = "http://www.quandl.com/api/v1/datasets/";
	private final String collectionUrl = "http://www.quandl.com/api/v1/current_user/collections/datasets/favourites.json?auth_token=";
	private final String queryUrl = "http://www.quandl.com/api/v1/datasets.json?auth_token=";
	private final String multiSetUrl = "http://quandl.com/api/v1/multisets.json?auth_token=";

	// private QDataset rst;

	public QuandlConnection() {
		System.out
				.println("No token... you are connected through the public api and will be rate limited accordingly.");
	}

	public QuandlConnection(String token) {

		if (connectedWithGoodToken(token)) {
			this.token = token;
			isToken = true;
		} else {
			System.out
					.println("Bad token... you are connected through the public api and will be rate limited accordingly.");
		}
	}

	public QDataset getQDataset(String qCode) {
		String url;
		if (isToken) {
			url = baseUrl + qCode + ".json?auth_token=" + token;

		} else {
			url = baseUrl + qCode + ".json";
		}

		return requestQDataset(url);
		// return rst;
	}

	public QDataset getQDatasetBetweenDates(String qCode, String start, String end) {
		String url;
		if (isToken) {
			url = baseUrl + qCode + ".json?trim_start=" + start + "&trim_end=" + end + "&auth_token=" + token;

		} else {
			url = baseUrl + qCode + ".json?trim_start=" + start + "&trim_end=" + end;
		}
		return requestQDataset(url);
		// return rst;

	}

	public QDataset getQDatasetWithParams(Map<String, String> params) {
		String url;
		String paramString = "?";

		for (String eachParam : params.keySet()) {
			if (!eachParam.contains("code") && !eachParam.contains("Code")) {
				paramString = paramString + eachParam + "=" + params.get(eachParam) + "&";
			}
		}

		if (isToken) {
			paramString = paramString + "auth_token=" + token + "&";
		}

		url = baseUrl + params.get("source_code") + "/" + params.get("code") + ".json?"
				+ paramString.substring(0, paramString.length() - 2);
		return requestQDataset(url);

	}

	public QDatasetList getQDatasetList() {
		String url;
		if (isToken) {
			url = collectionUrl + token;
			return requestQDatasetList(url);
		}
		return null;
	}

	public QDatasetList getQDatasetListBetweenDates(String start, String end) {
		String url;
		if (isToken) {
			url = collectionUrl + token + "&trim_start=" + start + "&trim_end=" + end;
			return requestQDatasetList(url);
		}
		return null;

	}

	public QDatasetList getCodeQuery(String query) {
		String url;
		if (isToken) {
			url = queryUrl + token + "&query=" + query ;
//			url = queryUrl + token + "&query=" + query + "&page=40";
			return requestQDatasetList(url);
		}
		return null;
	}
	public QDatasetList getCodeQuery(String query, int pageNo) {
		String url;
		if (isToken) {
			url = queryUrl + token + "&query=" + query+ "&page=" + String.valueOf(pageNo);
			return requestQDatasetList(url);
		}
		return null;
	}
	

	public QMultiset getMultiData(String multiset) {
		String url;
		if (isToken) {
			url = multiSetUrl + token + "&columns=" + multiset;
			return requestQMultiset(url);
		}
		return null;
	}

	public QMultiset getMultiDataBetweenDates(String multiset, String start, String end) {
		String url;
		if (isToken) {
			url = multiSetUrl + token + "&columns=" + multiset + "&trim_start=" + start + "&trim_end=" + end;
			return requestQMultiset(url);
		}
		return null;

	}

	/*
	 * public QDataset getQDatasetListWithParams(Map<String, String> params) {
	 * String url; String paramString = "?";
	 * 
	 * for (String eachParam : params.keySet()) { if
	 * (!eachParam.contains("code") && !eachParam.contains("Code")) {
	 * paramString = paramString + eachParam + "=" + params.get(eachParam) +
	 * "&"; } }
	 * 
	 * if(isToken) { paramString = paramString + "auth_token=" + token + "&"; }
	 * 
	 * url =baseUrl + params.get("source_code") + "/" + params.get("code") +
	 * ".json?" + paramString.substring(0, paramString.length() - 2); return
	 * requestQDataset(url);
	 * 
	 * }
	 */

	/**
	 * This method uses the "favorites" url to check that the provided token is
	 * valid.
	 * 
	 * @param token
	 *            this is the security token for your quandl account.
	 * @return true or false... depending on whether or not the token is valid.
	 */
	private boolean connectedWithGoodToken(String token) {
		String output = this
				.curl("http://www.quandl.com/api/v1/current_user/collections/datasets/favourites.json?auth_token="
						+ token);
		System.out.println("connection:" + output);
		if (output.contains("Unauthorized")) {
			System.out
					.println("BAD TOKEN!!! Check your token under http://www.quandl.com/users/edit Click \"API\" and use the token specified");
			return false;
		}
		return true;

	}

	/**
	 * This method just executes HTTP requests... putting the boilerplate code
	 * in one place.
	 * 
	 * @param url
	 *            this is the url for the http request... it assumes "http://"
	 *            is already included.
	 * @return it returns the response from the url in string form... or the
	 *         message of the exception if one is thrown.
	 */
	
//unirest..	
	private String curl(String url) {

		String output = "";

		HttpClient httpclient = new DefaultHttpClient();
		try {
			HttpGet httpget = new HttpGet(url);

			System.out.println("Executing Request: " + httpget.getURI());

			ResponseHandler<String> responseHandler = new BasicResponseHandler();

			output = httpclient.execute(httpget, responseHandler);

		} catch (IOException e) {
			e.printStackTrace();
			return e.getMessage();
		} finally {

			httpclient.getConnectionManager().shutdown();
		}
		return output;

	}

	/*
	 * private void requestQDataset(String url){
	 * 
	 * ObjectMapper mapper = new ObjectMapper();
	 * 
	 * try { HttpResponse<JsonNode> response = Unirest.get(url).asJson(); rst =
	 * mapper.readValue(response.getBody().toString(), QDataset.class);
	 * 
	 * // logger.info("AAA:{}, {}", rst.getName(), rst.getColumn_names()); //
	 * logger.info("AAA:{}, {}", rst.getColumn_names()); // for(List<String> li:
	 * rst.getData()){ // logger.info("AAA:{}, {}", li); // } } catch
	 * (JsonGenerationException e) { e.printStackTrace(); } catch
	 * (JsonMappingException e) { e.printStackTrace(); } catch (IOException e) {
	 * e.printStackTrace(); } catch(Exception e) {
	 * System.out.println(e.getMessage()); } }
	 */
	// private QDataset requestQDataset(String url) throws JsonParseException,
	// JsonMappingException, IOException{
	private QDataset requestQDataset(String url) {

		ObjectMapper mapper = new ObjectMapper();
		QDataset rst = new QDataset();

		try {
			HttpResponse<JsonNode> response = Unirest.get(url).asJson();
			rst = mapper.readValue(response.getBody().toString(), QDataset.class);

			return rst;

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return rst;
	}

	private QDatasetList requestQDatasetList(String url) {

		ObjectMapper mapper = new ObjectMapper();
		QDatasetList rst = new QDatasetList();

		try {
			HttpResponse<JsonNode> response = Unirest.get(url).asJson();
			System.out.println(":::::" + url);
			rst = mapper.readValue(response.getBody().toString(), QDatasetList.class);

			return rst;

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return rst;
	}

	private QMultiset requestQMultiset(String url) {

		ObjectMapper mapper = new ObjectMapper();
		QMultiset rst = new QMultiset();

		try {
			HttpResponse<JsonNode> response = Unirest.get(url).asJson();
			System.out.println(":::::" + url);
			rst = mapper.readValue(response.getBody().toString(), QMultiset.class);

			return rst;

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return rst;
	}
}