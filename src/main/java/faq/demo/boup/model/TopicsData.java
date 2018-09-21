package faq.demo.boup.model;

public class TopicsData {

	private TopicData[] topics;

	public TopicData[] getTopics() {
		return topics;
	}

	public static class TopicData {
		private String name;

		private Question[] questions;
		private TopicData[] subtopics;


		public String getName() {
			return name;
		}

		public Question[] getQuestions() {
			return questions;
		}

		public TopicData[] getSubtopics() {
			return subtopics;
		}
	}
}
