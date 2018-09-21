package faq.demo.boup.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TOPIC")
public class Topic {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;

	@OneToMany(mappedBy = "parent")
	private Set<Topic> subtopics;

	@ManyToOne
	@JoinColumn(name = "parent", nullable = true)
	private Topic parent;

	@OneToMany(mappedBy = "topic")
	private Set<Question> questions;
	
	public Topic(String name, Topic parent) {
		this.name = name;
		this.parent = parent;
	}


	public Topic() {
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Set<Topic> getSubtopics() {
		return subtopics;
	}

	public Set<Question> getQuestions() {
		return questions;
	}


	public void setSubtopics(Set<Topic> subtopics) {
		this.subtopics = subtopics;
	}
	
	@Override
	public String toString() {
		return "Topic [id=" + id + ", name=" + name + "]";
	}

}
