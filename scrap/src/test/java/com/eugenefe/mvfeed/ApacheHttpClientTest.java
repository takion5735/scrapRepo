package com.eugenefe.mvfeed;


import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApacheHttpClientTest {
	private final static Logger logger = LoggerFactory.getLogger(ApacheHttpClientTest.class);
	
	public static void main(String[] args) {
		multipartPostTest();
  }
	
	private static void multipartPostTest(){
		String url ="http://isin.krx.co.kr/srch/srch.do?method=srchList"; 
		String rst;
		Document doc;
		
		HttpPost httppost = new HttpPost(url);
		HttpEntity entity = buildEntity("1").build();
		httppost.setEntity(entity);
		
		CloseableHttpClient client = HttpClients.createDefault();
		 
		try{
			CloseableHttpResponse response = client.execute(httppost);
			HttpEntity resEntity = response.getEntity();
			doc = Jsoup.parse( EntityUtils.toString(resEntity));
				
			
//			Elements _rows = doc.select("table[class = type-01 detail mt5]>tbody>tr");
			Elements _rows = doc.select("table[id = dataTb]>tbody>tr[name=dataTr]");
			
			Elements sizerows = doc.select("table[class = paging]>tbody>tr>td:not([style^=padding])>a");
			
			for( Element _row : _rows){
				for(Element child : _row.children()){
					logger.info("Row1: {}: {}", child.text());
					
				}
			}
//			logger.info("Html Size : {},{}", _rows.size(), doc.toString());
			logger.info("Html Size : {},{}", _rows.size(), sizerows.size());
			
//			rst = client.execute(httppost, rh);
//			HttpResponse response = client.execute(httppost);
//			logger.info("Apache : {}", EntityUtils.toString(resEntity));
//			logger.info("Apache : {},{}", httppost.getMethod(), response.getAllHeaders());
			
//			for(HttpParams aa : httppost.getParams()){
				logger.info("Apache : {},{}", entity.getContentLength(), response.getParams().getParameter("curSh"));
//			}
		}
		catch(Exception e){
			
		}
	}
	
	private static MultipartEntityBuilder buildEntity(String pageIndex){
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();        
//		boundary=----WebKitFormBoundaryumzVNYwELedulDQZ
		
		/* example for setting a HttpMultipartMode */
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

		/* example for adding an image part */
		
		builder.addTextBody("currentPage","15");
		builder.addTextBody("curSh","1");
		builder.addTextBody("std_cd_grnt_start_dd","20140513");
		builder.addTextBody("std_cd_grnt_end_dd","20140612");
		builder.addTextBody("searchRadio","2");
		builder.addTextBody("pageIndex",pageIndex);
		builder.addTextBody("item_id","");
		builder.addTextBody("item_nm","");
		builder.addTextBody("list_start_dd","");
		builder.addTextBody("list_end_dd","");
		builder.addTextBody("listRadio","");
		builder.addTextBody("isu_start_dd","");
		builder.addTextBody("isu_end_dd","");
		builder.addTextBody("isuRadio","");
		builder.addTextBody("isur_cd","");
		builder.addTextBody("com_nm","");
		builder.addTextBody("isur_nm","");
		builder.addTextBody("list_cd","");
		builder.addTextBody("els_dls_tp_cd","");
		builder.addTextBody("bnd_stkcert_tp_cd","");
		builder.addTextBody("fnc_uly_tp_cd","");
		builder.addTextBody("fnc_prod_fut_tp_cd","");
		builder.addTextBody("std_indic_tp_cd","");
		builder.addTextBody("std_tp_cd","");
		builder.addTextBody("isur_cd1","");
		builder.addTextBody("com_nm1","");
		builder.addTextBody("isur_nm1","");
		builder.addTextBody("list_start_dd1","");
		builder.addTextBody("list_end_dd1","");
		builder.addTextBody("isur_cd2","");
		builder.addTextBody("com_nm2","");
		builder.addTextBody("isur_nm2","");
		builder.addTextBody("list_start_dd2","");
		builder.addTextBody("list_end_dd2","");
		builder.addTextBody("isur_cd3","");
		builder.addTextBody("com_nm3","");
		builder.addTextBody("isur_nm3","");
		builder.addTextBody("isu_start_dd3","");
		builder.addTextBody("isu_end_dd3","");
		builder.addTextBody("list_start_dd3","");
		builder.addTextBody("list_end_dd3","");
		builder.addTextBody("isur_cd4","");
		builder.addTextBody("com_nm4","");
		builder.addTextBody("isur_nm4","");
		builder.addTextBody("isu_start_dd4","");
		builder.addTextBody("isu_end_dd4","");
		builder.addTextBody("list_start_dd4","");
		builder.addTextBody("list_end_dd4","");
		builder.addTextBody("isur_cd5","");
		builder.addTextBody("com_nm5","");
		builder.addTextBody("isur_nm5","");
		builder.addTextBody("isu_start_dd5","");
		builder.addTextBody("isu_end_dd5","");
		builder.addTextBody("list_start_dd5","");
		builder.addTextBody("list_end_dd5","");
		builder.addTextBody("isur_cd6","");
		builder.addTextBody("com_nm6","");
		builder.addTextBody("isur_nm6","");
		builder.addTextBody("isu_start_dd6","");
		builder.addTextBody("isu_end_dd6","");
		builder.addTextBody("isur_cd7","");
		builder.addTextBody("com_nm7","");
		builder.addTextBody("isur_nm7","");
		builder.addTextBody("isu_start_dd7","");
		builder.addTextBody("isu_end_dd7","");
		builder.addTextBody("isur_cd8","");
		builder.addTextBody("com_nm8","");
		builder.addTextBody("isur_nm8","");
		builder.addTextBody("isu_start_dd8","");
		builder.addTextBody("isu_end_dd8","");
		builder.addTextBody("isur_cd9","");
		builder.addTextBody("com_nm9","");
		builder.addTextBody("isur_nm9","");
		builder.addTextBody("isur_cd10","");
		builder.addTextBody("com_nm10","");
		builder.addTextBody("isur_nm10","");
		builder.addTextBody("isu_start_dd10","");
		builder.addTextBody("isu_end_dd10","");
		builder.addTextBody("isur_cd11","");
		builder.addTextBody("com_nm11","");
		builder.addTextBody("isur_nm11","");
		builder.addTextBody("isu_start_dd11","");
		builder.addTextBody("isu_end_dd11","");
		builder.addTextBody("list_start_dd11","");
		builder.addTextBody("list_end_dd11","");
		builder.addTextBody("isur_nm12","");
		builder.addTextBody("list_start_dd12","");
		builder.addTextBody("list_end_dd12","");
		builder.addTextBody("isur_nm13","");
		builder.addTextBody("isur_nm14","");
		builder.addTextBody("list_start_dd14","");
		builder.addTextBody("list_end_dd14","");
		builder.addTextBody("isur_nm15","");
		builder.addTextBody("list_start_dd15","");
		builder.addTextBody("list_end_dd15","");
		
		
		
		return builder;
	}
}
