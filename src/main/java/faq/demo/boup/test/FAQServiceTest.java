package faq.demo.boup.test;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import faq.demo.boup.model.Question;
import faq.demo.boup.model.Topic;
import faq.demo.boup.service.FAQService;

@RunWith(SpringJUnit4ClassRunner.class)
public class FAQServiceTest {

	@Autowired
    @Qualifier("faq")
	private FAQService faqService;

	@Test
	public void testFaqSearch() {
		List<Topic> topics = generateRandomTopics(10);
		populateTopicQuestions(topics);
		//TODO assert search results
	}

	private List<Topic> generateRandomTopics(int count) {

		List<Topic> generatedTopics = new ArrayList<Topic>();
		generatedTopics.add(faqService.saveTopic(new Topic(getRandomName(), null)));
		for (int i = 0; i < count; i++) {

			if (new Random().nextFloat() > 0.25f)// create main topic - 25%
				generatedTopics.add(faqService.saveTopic(new Topic(getRandomName(), null)));
			else // create subtopic - 75%
				generatedTopics.add(faqService.saveTopic(
						new Topic(getRandomName(), generatedTopics.get(new Random().nextInt(generatedTopics.size())))));
		}

		return generatedTopics;
	}

	private void populateTopicQuestions(List<Topic> topics) {
		for (Topic topic : topics) {
			if (topic.getSubtopics().size() > 0)
				continue;
			for (int i = 0; i < new Random().nextInt(4); i++) {
				topic.getQuestions().add(faqService.saveQuestion(new Question(getRandomName(), getRandomName())));
			}
		}
	}

	private String getRandomName() {
		byte[] array = new byte[7];
		new Random().nextBytes(array);
		return new String(array, Charset.forName("UTF-8"));
	}
}
