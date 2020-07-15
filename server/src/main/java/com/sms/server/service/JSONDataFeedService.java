package com.sms.server.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.sms.server.model.City;
import com.sms.server.model.CityVO;

/**
 * @author Supriya Shelar
 * 
 * Service to Parse Json feed 
 * and Insert that data into database
 * 
 */
@Service
public class JSONDataFeedService {
	
	private static final Logger logger = LoggerFactory.getLogger(JSONDataFeedService.class);
	
	@Autowired
	CityService cityService;
	
	/**
	 * Get List of Objects and insert that into Database
	 */
	public void performFeedDataInsertion() {
		logger.info("----------PERFORM JSON FEED PARSING AND DATA INSERTION-----");
		List<City> cityList = parseJsonFile();
		logger.info("------- PERFORM FEED DATA INSERTION ----------");
		cityService.addAll(cityList);
		logger.info("------- DATA INSERTION COMPLETED SUCCESSFULLY----------");

	}

	/**
	 * @return
	 * 
	 * Parse JSON file 
	 * Create City Object for each record
	 * And Return list of City objects 
	 * 
	 */
	private List<City> parseJsonFile() {
		logger.info("------------ PARSING JSON FEED-----------");
		LinkedList<City> cityList = new LinkedList<City>();

		try {
			File file = ResourceUtils.getFile("classpath:data.json");
			String content = new String(Files.readAllBytes(file.toPath()));
			JsonParser springParser = JsonParserFactory.getJsonParser();

			List<Object> list = springParser.parseList(content);
			
			for(Object o : list) {
				if(o instanceof Map) {
					Map<String,Object> map = (Map<String,Object>) o;
					City city = new City();
					int i = 0;
					for (Map.Entry<String, Object> entry : map.entrySet()) {
						if(entry.getKey().equalsIgnoreCase("id"))
							city.setId((Integer)entry.getValue());
						if(entry.getKey().equalsIgnoreCase("city"))
							city.setName(entry.getValue().toString());
						if(entry.getKey().equalsIgnoreCase("start_date"))
							city.setStartDate(new SimpleDateFormat("MM/dd/yyyy").parse(entry.getValue().toString()));
						if(entry.getKey().equalsIgnoreCase("end_date"))
							city.setEndDate(new SimpleDateFormat("MM/dd/yyyy").parse(entry.getValue().toString()));
						if(entry.getKey().equalsIgnoreCase("price"))
							city.setPrice(Double.parseDouble(entry.getValue().toString()));
						if(entry.getKey().equalsIgnoreCase("status"))
							city.setStatus(entry.getValue().toString());
						if(entry.getKey().equalsIgnoreCase("color"))
							city.setColor(entry.getValue().toString());
						i++;
					}
					cityList.add(city);
				}
			}
			logger.info("==========NUMBER OF CITIES FOUND : " + cityList.size()+"===============");
			
			
		} catch (FileNotFoundException e) {
			logger.error("[ERROR] :: JSON FEED FILE NOT FOUND AT SPECIFIED LOCATION", e);
		} catch (IOException e) {
			logger.error("[ERROR] :: IO EXCEPTION OCCURRED WHILE READING JSON FILE", e);
		} catch (ParseException e) {
			logger.error("[ERROR] :: EXCEPTION OCCURRED WHILE PARSING JSON FILE", e);
		}
		return cityList;
	}
}
