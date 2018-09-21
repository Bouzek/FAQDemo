package faq.demo.boup.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import faq.demo.boup.model.Topic;

public interface TopicDAO extends CrudRepository<Topic, Integer> {
	List<Topic> findByParent(Topic parent);
}
