package faq.demo.boup.dao;

import org.springframework.data.repository.CrudRepository;

import faq.demo.boup.model.Question;
import faq.demo.boup.model.Topic;


public interface QuestionDAO extends CrudRepository<Question, Integer>  {
	Question[] findByTopic(Topic topic);
}
