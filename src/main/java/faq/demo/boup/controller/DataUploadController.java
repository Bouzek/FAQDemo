package faq.demo.boup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import faq.demo.boup.model.TopicsData;
import faq.demo.boup.service.FAQService;

@RestController
public class DataUploadController {

	@Autowired
	private FAQService dataService;
	
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST, headers = "Content-Type=application/json")
	public void uploadCsv(@RequestBody TopicsData topics) {
		System.out.println("Uploading FAQ data");

		dataService.saveData(topics);
	}
}
