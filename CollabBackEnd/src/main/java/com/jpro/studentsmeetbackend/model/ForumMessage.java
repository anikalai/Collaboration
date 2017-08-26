package com.jpro.studentsmeetbackend.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Component
@Table(name="STMT_FORUM_MESSAGE")
public class ForumMessage extends BaseDomain {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long forumMessageID;
	
	private long forumId;
	
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="forumId", scope=Forum.class)
	@ManyToOne
	@JoinColumn(name = "forumId", nullable = false, updatable = false, insertable = false)
	private Forum forum;
	
	private String userId;
	
	private String msgData;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date msgTime;
	
	private char reportMessage;

	public long getForumMessageID() {
		return forumMessageID;
	}

	public void setForumMessageID(long forumMessageID) {
		this.forumMessageID = forumMessageID;
	}

	public Forum getForum() {
		return forum;
	}

	public void setForum(Forum forum) {
		this.forum = forum;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMsgData() {
		return msgData;
	}

	public void setMsgData(String msgData) {
		this.msgData = msgData;
	}

	public Date getMsgTime() {
		return msgTime;
	}

	public void setMsgTime(Date msgTime) {
		this.msgTime = msgTime;
	}

	public char getReportMessage() {
		return reportMessage;
	}

	public void setReportMessage(char reportMessage) {
		this.reportMessage = reportMessage;
	}

	public long getForumId() {
		return forumId;
	}

	public void setForumId(long forumId) {
		this.forumId = forumId;
	}
	
}
