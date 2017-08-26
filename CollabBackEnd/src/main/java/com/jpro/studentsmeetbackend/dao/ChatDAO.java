package com.jpro.studentsmeetbackend.dao;

import java.util.List;

import com.jpro.studentsmeetbackend.model.Chat;
import com.jpro.studentsmeetbackend.model.ChatMessage;

public interface ChatDAO {
	
	public boolean createChat(Chat chat);
	
	public List<Chat> getAllChat();
	
	public List<ChatMessage> getAllChatMessage(long chatID);
	
	public boolean removeChat(Chat chat);
	
	public boolean removeChat(long chatId);
	
	public Chat getChatById(long chatID);
	
	public boolean addChatMessage(long chatID,ChatMessage chatMessage);
	
	public boolean reportChat(long chatID);
	
	public List<Chat> getAllReportedChats();
	
	public List<Chat> getPrivateChatsOfUser(String userID);
	
	public List<Chat> getOpenChats();

}
