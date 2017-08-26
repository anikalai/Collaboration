package com.jpro.studentsmeetbackend.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "STMT_FORUM")
public class Forum extends BaseDomain {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long forumId;

	private String forumName;

	private String forumData;

	@OneToMany(mappedBy = "forum", fetch = FetchType.EAGER)
	private List<ForumMessage> messages = new LinkedList<ForumMessage>();

	public List<ForumMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<ForumMessage> messages) {
		this.messages = messages;
	}

	public long getForumId() {
		return forumId;
	}

	public String getForumData() {
		return forumData;
	}

	public void setForumData(String forumData) {
		this.forumData = forumData;
	}

	public void setForumId(long forumId) {
		this.forumId = forumId;
	}

	public String getForumName() {
		return forumName;
	}

	public void setForumName(String forumName) {
		this.forumName = forumName;
	}

}
