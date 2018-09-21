package faq.demo.boup.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import faq.demo.boup.dao.QuestionDAO;
import faq.demo.boup.dao.TopicDAO;
import faq.demo.boup.model.Question;
import faq.demo.boup.model.SearchResult;
import faq.demo.boup.model.Topic;
import faq.demo.boup.model.TopicsData;

@Service("faq")
public class FAQService {
	@Autowired
	private TopicDAO topicRepository;
	@Autowired
	private QuestionDAO questionRepository;

	public void saveData(TopicsData data) {
		for (TopicsData.TopicData topicData : data.getTopics()) {
			createTopic(topicData, null);
		}

	}

	private void createTopic(TopicsData.TopicData topicData, Topic parent) {
		Topic topic = new Topic(topicData.getName(), parent);

		topic = topicRepository.save(topic);
		System.out.println("saving topic" + topic);

		for (TopicsData.TopicData subtopicData : topicData.getSubtopics()) {
			createTopic(subtopicData, topic);
		}

		for (Question question : topicData.getQuestions()) {
			question.setTopic(topic);
			question = questionRepository.save(question);
			System.out.println("saving question" + question);
		}
	}

	public Topic saveTopic(Topic topic) {
		return topicRepository.save(topic);
	}
	

	public Question saveQuestion(Question question) {
		return questionRepository.save(question);
	}

	public Topic[] getTopics(int parentTopicId) {
		List<Topic> tops;
		if (parentTopicId == 0)
			tops = topicRepository.findByParent(null);
		else {
			Optional<Topic> parent = topicRepository.findById(parentTopicId);
			if (!parent.isPresent())
				return new Topic[0];

			tops = topicRepository.findByParent(parent.get());
		}

		if (tops == null)
			return new Topic[0];

		Topic[] t = new Topic[tops.size()];
		t = tops.toArray(t);
		return t;
	}

	public Topic[] getMainTopics() {
		return getTopics(0);
	}

	public Question[] getQuestions(int topicId) {
		Optional<Topic> topic = topicRepository.findById(topicId);

		if (!topic.isPresent())
			return new Question[0];

		return questionRepository.findByTopic(topic.get());
	}

	public SearchResult search(String query) {
		List<Topic> topicResults = new ArrayList<Topic>();
		List<Question> questionResults = new ArrayList<Question>();

		for (Topic topic : topicRepository.findAll()) {
			if (topic.getName().contains((query))) {
				topicResults.add(topic);
			}
		}

		for (Question question : questionRepository.findAll()) {
			if (question.getQuestion().contains(query) || question.getAnswer().contains(query)) {
				questionResults.add(question);
			}
		}

		Topic[] t = new Topic[topicResults.size()];
		Question[] q = new Question[questionResults.size()];
		return new SearchResult(topicResults.toArray(t), questionResults.toArray(q));
	}
}
