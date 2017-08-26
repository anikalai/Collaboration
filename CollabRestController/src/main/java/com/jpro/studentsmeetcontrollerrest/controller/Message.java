package com.jpro.studentsmeetcontrollerrest.controller;

public class Message {
	
	private int id;
	
	private String userID;
	
	private long chatID;
	
	private String message;
	
	public Message(){	
	}
	
	public Message(int id,String userid,long chatID,String message){
		this.id=id;
		this.chatID=chatID;
		this.userID=userid;
		this.message=message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public long getChatID() {
		return chatID;
	}

	public void setChatID(long chatID) {
		this.chatID = chatID;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
