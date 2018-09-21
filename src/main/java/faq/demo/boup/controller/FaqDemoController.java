package faq.demo.boup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import faq.demo.boup.model.Question;
import faq.demo.boup.model.SearchResult;
import faq.demo.boup.model.Topic;
import faq.demo.boup.service.FAQService;

@RestController
public class FaqDemoController {


	@Autowired
	private FAQService dataService;
	
	
	@RequestMapping(value = "/topics", method = RequestMethod.GET)
	public Topic[] getTopics() {
		return dataService.getMainTopics();
	}

	@RequestMapping(value = "/subtopics", method = RequestMethod.GET)
	public Topic[] getSubtopics(@RequestParam(value = "topic") int topicId) {
		return dataService.getTopics(topicId);
	}

	@RequestMapping(value = "/questions", method = RequestMethod.GET)
	public Question[] getQuestions(@RequestParam(value = "topic") int topicId) {
		return dataService.getQuestions(topicId);
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public SearchResult search(@RequestParam(value = "query") String query) {
		return dataService.search(query);
	}
}
