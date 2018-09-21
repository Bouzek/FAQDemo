package faq.demo.boup.model;

public class SearchResult {
	private Topic[] topics;
	private Question[] questions;

	public SearchResult(Topic[] topics, Question[] questions) {
		this.topics = topics;
		this.questions = questions;
	}
	
	public Topic[] getTopics() {
		return topics;
	}
	
	public Question[] getQuestions() {
		return questions;
	}
}
