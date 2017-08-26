package com.jpro.studentsmeetbackend.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="STMT_CHAT")
public class Chat extends BaseDomain {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long chatId;
	
	private String creatorID;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="STMT_CHAT_MESSAGES",joinColumns=@JoinColumn(name="chatId"))
	private List<ChatMessage> chat_Messages=new LinkedList<ChatMessage>();
	
	private char isPrivateChat;
	
	private char reportChat;
	
	private String chatTopic;
	
	private String friendID;

	public long getChatId() {
		return chatId;
	}

	public void setChatId(long chatId) {
		this.chatId = chatId;
	}

	public String getCreatorID() {
		return creatorID;
	}

	public void setCreatorID(String creatorID) {
		this.creatorID = creatorID;
	}

	public List<ChatMessage> getChat_Messages() {
		return chat_Messages;
	}

	public void setChat_Messages(List<ChatMessage> chat_Messages) {
		this.chat_Messages = chat_Messages;
	}

	public char getIsPrivateChat() {
		return isPrivateChat;
	}

	public void setIsPrivateChat(char isPrivateChat) {
		this.isPrivateChat = isPrivateChat;
	}

	public char getReportChat() {
		return reportChat;
	}

	public void setReportChat(char reportChat) {
		this.reportChat = reportChat;
	}

	public String getChatTopic() {
		return chatTopic;
	}

	public void setChatTopic(String chatTopic) {
		this.chatTopic = chatTopic;
	}

	public String getFriendID() {
		return friendID;
	}

	public void setFriendID(String friendID) {
		this.friendID = friendID;
	}
	
}
