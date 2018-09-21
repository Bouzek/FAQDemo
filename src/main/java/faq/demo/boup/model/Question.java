package faq.demo.boup.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "QUESTION")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	@JoinColumn(name = "topic", nullable = false)
	private Topic topic;

	private String question;
	private String answer;

	public Question(String question, String answer) {
		this.question = question;
		this.answer = answer;
	}
	
	public Question(String question, String answer, Topic topic) {
		this.question = question;
		this.answer = answer;
		this.topic = topic;
	}

	public void setTopic(Topic topic){
		this.topic = topic;
	}

	public Question() {
	}

	public String getQuestion() {
		return question;
	}

	public String getAnswer() {
		return answer;
	}
	
	@Override
	public String toString() {
		return "Question [id=" + id + ", topic=" + topic + ", question=" + question + ", answer=" + answer + "]";
	}
}
