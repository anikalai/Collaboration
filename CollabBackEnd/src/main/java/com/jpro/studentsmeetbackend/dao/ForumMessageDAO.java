package com.jpro.studentsmeetbackend.dao;

import java.util.List;

import com.jpro.studentsmeetbackend.model.ForumMessage;

public interface ForumMessageDAO {
	
	public boolean createForumMessage(ForumMessage forumMessgae);
	
	public boolean removeForumMessage(ForumMessage forumMessage);
	
	public ForumMessage getForumMessage(long messageId);
	
	public boolean reportForumMessage(ForumMessage forumMessage);
	
	public List<ForumMessage> getAllForumMessage(long forumId);
	
	public List<ForumMessage> getAllReportedMessage(long forumId);
	
	public List<ForumMessage> getAllReportedMsg();

}
