package com.example;

import java.beans.XMLDecoder;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.validator.internal.xml.XmlMappingParser;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controller class to provide restful methods for Demo microservice application.
 * 
 * @author sanjayingole
 *
 */
@RestController
public class HomeController {

	Logger logger = Logger.getLogger(HomeController.class);	
	
	/**
	 * Method to return a JSON representation of an XML file.
	 * 
	 * @param file
	 * @return
	 */
	@RequestMapping("/xmltojson/")
	public String getJSONFromXML(@RequestParam("file") MultipartFile file){
		
		logger.debug("Convert xml file to JSON object");
		
	    JSONObject jsonObj = new JSONObject("{}");

		if (file == null || file.isEmpty()) {
			jsonObj = new JSONObject("{ERROR : Could not convert the xml into JSON as empty file provided.}");
		}
			    
	    try {
		    BufferedReader bufferedReader = 
		    		new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
	        StringBuilder xmlStrBuilder = new StringBuilder();

	        String inputStr;
	        while ((inputStr = bufferedReader.readLine()) != null) {
	        	xmlStrBuilder.append(inputStr);
	        }
	       
	    	logger.debug("Return JSON object for xml" + xmlStrBuilder.toString());
	    	jsonObj = XML.toJSONObject(xmlStrBuilder.toString());
	    	
	    } catch (IOException ioexp) {
	    	logger.error("Error reading the xml" + ioexp.getMessage());
	    } catch (NullPointerException npe) {
	    	logger.error("Error Occured while parsing xml to JSON due to null XML : " + npe.getMessage());
	    } catch(JSONException jsonExp) {
	    	logger.error("Error Occured while parsing xml to JSON : " + jsonExp.getMessage());
	    } catch(Exception exp) {
	    	logger.error("Unexpected error occured while parsing xml to JSON : " + exp.getMessage());
	    }
	    
	    logger.debug("Return JSON object for xml" + jsonObj.toString());
		return jsonObj.toString();		
	}
}
