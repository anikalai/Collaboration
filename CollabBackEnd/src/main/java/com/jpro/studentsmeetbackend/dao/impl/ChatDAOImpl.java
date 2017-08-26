package com.jpro.studentsmeetbackend.dao.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.jpro.studentsmeetbackend.dao.ChatDAO;
import com.jpro.studentsmeetbackend.model.Chat;
import com.jpro.studentsmeetbackend.model.ChatMessage;

@Repository("chatDAO")
@Transactional
public class ChatDAOImpl implements ChatDAO {
	
	private static final Logger log=LoggerFactory.getLogger(ChatDAOImpl.class);
	
	private SessionFactory sessionFactory;
	
	ChatDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}

	public boolean createChat(Chat chat) {
		try{
			if(chat.getIsPrivateChat()=='Y'){
				chat.setChatTopic("Private Chat");
			}
			if(chat.getIsPrivateChat()=='N'){
				chat.setFriendID("Open Chat");
				}			
			sessionFactory.getCurrentSession().save(chat);
			return true;
		}
		catch(Exception e){
			System.err.println(e);
			return false;
		}
	}

	public List<Chat> getAllChat() {
		return sessionFactory.getCurrentSession().createQuery("from Chat").list();
	}

	public List<ChatMessage> getAllChatMessage(long chatID) {
		Chat chat=(Chat) sessionFactory.getCurrentSession().createQuery("from Chat where chatId='"+chatID+"'").uniqueResult();
		return chat.getChat_Messages();
	}

	public boolean removeChat(Chat chat) {
		try{
			sessionFactory.getCurrentSession().delete(chat);
			return true;
		}
		catch(Exception e){
			System.err.println(e);
			return false;
		}
	}

	public boolean removeChat(long chatId) {
		try{
			sessionFactory.getCurrentSession().delete(getChatById(chatId));
			return true;
		}
		catch(Exception e){
			System.err.println(e);
			return false;
		}
	}

	public Chat getChatById(long chatID) {
		return sessionFactory.getCurrentSession().get(Chat.class,chatID);
	}

	public boolean addChatMessage(long chatID,ChatMessage chatMessage) {
		try{
			chatMessage.setMsgDate(new Date());
			Chat chat=getChatById(chatID);
			chat.getChat_Messages().add(chatMessage);
			sessionFactory.getCurrentSession().update(chat);
			return true;
		}
		catch(Exception e){
			System.err.println(e);
			return false;
		}
	}

	public boolean reportChat(long chatID) {
		try{
			Chat chat=getChatById(chatID);
			chat.setReportChat('Y');
			sessionFactory.getCurrentSession().update(chat);
			return true;
		}
		catch(Exception e){
			System.err.println(e);
			return false;
		}
	}

	public List<Chat> getAllReportedChats() {
		return sessionFactory.getCurrentSession().createQuery("from Chat where reportChat='Y'").list();
	}

	public List<Chat> getPrivateChatsOfUser(String userID){
		List<Chat> privateChats=sessionFactory.getCurrentSession().createQuery("from Chat where creatorID='"+userID+"' or friendID='"+userID+"'").list();
		for(int i=0;i<privateChats.size();i++)
		{
			Chat chat=privateChats.get(i);
			if(chat.getIsPrivateChat()=='N'){
				privateChats.remove(chat);
			}
		}
		return privateChats;
	}
	
	public List<Chat> getOpenChats(){
		return sessionFactory.getCurrentSession().createQuery("from Chat where isPrivateChat='N'").list();
	}
	
}
