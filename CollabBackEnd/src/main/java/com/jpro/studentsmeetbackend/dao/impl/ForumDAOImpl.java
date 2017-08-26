package com.jpro.studentsmeetbackend.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.jpro.studentsmeetbackend.dao.ForumDAO;
import com.jpro.studentsmeetbackend.model.Forum;

@Repository("forumDAO")
@Transactional
public class ForumDAOImpl implements ForumDAO {

	private static final Logger log = LoggerFactory.getLogger(ForumDAOImpl.class);

	private SessionFactory sessionFactory;

	ForumDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public boolean createForum(Forum forum) {
		try {
			sessionFactory.getCurrentSession().save(forum);
			return true;
		} catch (Exception e) {
			System.err.println(e);
			return false;
		}
	}

	public boolean updateForum(Forum forum) {
		try {
			sessionFactory.getCurrentSession().update(forum);
			return true;
		} catch (Exception e) {
			System.err.println(e);
			return false;
		}
	}

	public List<Forum> getAllForum() {
		return sessionFactory.getCurrentSession().createQuery("from Forum").list();
	}

	public Forum getForumbyID(long forumID) {
		return sessionFactory.getCurrentSession().get(Forum.class, forumID);
	}

	public boolean removeForum(Forum forum) {
		try {
			sessionFactory.getCurrentSession().delete(forum);
			return true;
		} catch (Exception e) {
			System.err.println(e);
			return false;
		}
	}

	public boolean removeCommentForForum(long forumId) {
		try {
			String sql = "delete from STMT_FORUM_MESSAGE  where FORUMID='" + forumId + "'";
			SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
			query.executeUpdate();
			log.debug("Comment deleted");
			System.out.println("Forum comments are deleted");
			return true;
		} catch (Exception e) {
			log.debug("Error occured while rejecting request");
			return false;
		}
	}

	public boolean removeForum(long forumId) {
		boolean value=removeCommentForForum(forumId);
		if(value)
		{
		try {
			sessionFactory.getCurrentSession().delete(getForumbyID(forumId));
			System.out.println("Forum deleted successfully");
			return true;
		} catch (Exception e) {
			System.err.println(e);
			return false;
		}
		}
		else
		{
			return false;
		}
	}

}
