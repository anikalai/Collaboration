package com.jpro.studentsmeetbackend.dao.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.jpro.studentsmeetbackend.dao.ForumMessageDAO;
import com.jpro.studentsmeetbackend.model.ForumMessage;

@Repository("forumMessageDAO")
@Transactional
public class ForumMessageDAOImpl implements ForumMessageDAO {
	
	private static final Logger log=LoggerFactory.getLogger(ForumMessageDAOImpl.class);
	
	private SessionFactory sessionFactory;
	
	ForumMessageDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}

	public boolean createForumMessage(ForumMessage forumMessgae) {
		try{
			forumMessgae.setMsgTime(new Date());
			forumMessgae.setReportMessage('N');
			sessionFactory.getCurrentSession().save(forumMessgae);
			return true;
		}
		catch(Exception e){
			System.err.println(e);
			return false;
		}
	}

	public boolean removeForumMessage(ForumMessage forumMessage) {
		try{
			sessionFactory.getCurrentSession().delete(forumMessage);
			return true;
		}
		catch(Exception e){
			System.err.println(e);
			return false;
		}
	}

	public ForumMessage getForumMessage(long messageId) {
		return sessionFactory.getCurrentSession().get(ForumMessage.class, messageId);
	}

	public boolean reportForumMessage(ForumMessage forumMessage) {
		try{
			ForumMessage msg=sessionFactory.getCurrentSession().get(ForumMessage.class, forumMessage.getForumMessageID());
			msg.setReportMessage('Y');
			sessionFactory.getCurrentSession().update(msg);
			return true;
		}
		catch(Exception e){
			System.err.println(e);
			return false;
		}
	}

	public List<ForumMessage> getAllForumMessage(long forumId) {
		return sessionFactory.getCurrentSession().createQuery("from ForumMessage where forumId='"+forumId+"'").list();
	}

	public List<ForumMessage> getAllReportedMessage(long forumId) {
		return sessionFactory.getCurrentSession().createQuery("from ForumMessage where forumId='"+forumId+"' and reportMessage='Y'").list();

	}

	public List<ForumMessage> getAllReportedMsg(){
		return sessionFactory.getCurrentSession().createQuery("from ForumMessage where reportMessage='Y'").list();
	}
}
