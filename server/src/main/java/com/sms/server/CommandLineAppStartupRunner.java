package com.sms.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sms.server.service.JSONDataFeedService;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

	@Autowired
	JSONDataFeedService jsonDataFeedService;

	@Override
	public void run(String... args) throws Exception {
		jsonDataFeedService.performFeedDataInsertion();
	}

}
