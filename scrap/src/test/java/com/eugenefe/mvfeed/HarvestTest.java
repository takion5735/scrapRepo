package com.eugenefe.mvfeed;


import java.io.FileNotFoundException;

import javassist.compiler.ast.Variable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HarvestTest {
	private final static Logger logger = LoggerFactory.getLogger(HarvestTest.class);
	
	public static void main(String[] args) {
/*//		DefinitionResolver.registerPlugin("com.my.MyPlugin1");
//        DefinitionResolver.registerPlugin("com.my.MyPlugin2");
//        DefinitionResolver.registerPlugin("com.my.MyPlugin3");

        String srcFile ="c:/AAWork/@Current/WebHarvest/krx_bond.xml";
        ScraperConfiguration config;
//        try {
//        	ScraperConfiguration config= new ScraperConfiguration(srcFile);
//        }catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		try {
			config = new ScraperConfiguration(srcFile);
//			config = new ScraperConfiguration("C:/AAwork/@Current/WebHarvest/webharvest2b1-project/examples/nytimes.xml");
			Scraper scraper = new Scraper(config, "c:/AAWork/@Current/WebHarvest/");
//			scraper.addVariableToContext("username", "web-harvest");
//			scraper.addVariableToContext("password", "web-harvest");
//        scraper.addVariableToContext("myXmlLib", new MyXmlLibrary());
			
			scraper.setDebug(false);
			ScraperConfiguration conf = scraper.getConfiguration();
			
			
			scraper.execute();
			Variable duke = (Variable) scraper.getContext().get("dukej");
			logger.info("Key Set : {}", duke);
			
			
			LoopDef loopDef = (LoopDef)conf.getOperations().get(2);
			logger.info("Configuration : {}",loopDef.getItem());
			logger.info("Configuration : {}",((BaseElementDef)loopDef.getLoopBodyDef()).getOperationDefs()[0]);
			
//			logger.info("Configuration : {}",((BaseElementDef)loopDef.getLoopBodyDef()).getOperationDefs());
//			logger.info("Configuration : {}",((LoopDef)conf.getOperations().get(2)).getLoopBodyDef());
			
			
			// takes variable created during execution
			Variable duke = (Variable) scraper.getContext().get("duke");
			logger.info("Key Set : {}", duke.toArray());
			
			ListVariable listVar = (ListVariable) scraper.getContext().get("list");
			NodeVariable aaa = (NodeVariable) scraper.getContext().get("bondInfo");
			Set<String> kk = scraper.getContext().keySet();
			for(String aa : kk){
				logger.info("Key Set : {}", aa);
				if(!aa.equals("http") && !aa.equals("sys")){
					Variable var1 = (Variable) scraper.getContext().get(aa);
//					logger.info("Key Set : {}", var1.getWrappedObject());
					
				}
			}
//			for(Map.Entry<Object, Object> entry : (Object) scraper.getContext().entrySet()){}
			
//			while(scraper.getContext().keySet().iterator().hasNext()){
//				logger.info("Duke : {}", scraper.getContext().keySet().iterator().next());
//			}
			
//			XmlToJsonPlugin json = new XmlToJsonPlugin();
//			Variable zzz = json.executePlugin(scraper, scraper.getContext());
//			
			
			logger.info("Duke : {}", aaa.getWrappedObject());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        */
	}

}
