package com.jpro.studentsmeetbackend.dao.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.jpro.studentsmeetbackend.dao.CommentDAO;
import com.jpro.studentsmeetbackend.model.Comment;

@Repository("commentDAO")
@Transactional
public class CommentDAOImpl implements CommentDAO {
	
	private static final Logger log=LoggerFactory.getLogger(CommentDAOImpl.class);
	
	private SessionFactory sessionFactory;
	
	CommentDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}


	public List<Comment> getAllComments(long blogID) {
		return sessionFactory.getCurrentSession().createQuery("from Comment where blogID='"+blogID+"'").list();
	}

	public boolean addComment(long blogID, String comment_UserId, String commentData) {
		try
		{
			Comment comment=new Comment();
			comment.setBlogID(blogID);
			comment.setCommentUserId(comment_UserId);
			comment.setCommentText(commentData);
			comment.setCommentDate(new Date());
			comment.setReportComment('N');
			sessionFactory.getCurrentSession().save(comment);
			return true;
		}
		catch(Exception e){
			System.err.println(e);
			return false;
		}
	}

	public boolean reportComment(long commentID) {
		try{
			Comment comment=(Comment) sessionFactory.getCurrentSession().createQuery("from Comment where commentID='"+commentID+"'").uniqueResult();
			comment.setReportComment('Y');
			sessionFactory.getCurrentSession().update(comment);
			return true;
		}
		catch(Exception e){
			System.err.println(e);
			return false;
		}
	}

	public boolean removeComment(long commentID) {
		try{
			sessionFactory.getCurrentSession().delete(getComment(commentID));
			return true;
		}
		catch(Exception e){
			System.err.println(e);
			return false;
		}
	}

	public List<Comment> getAllReportedComments(long blogID) {
		return sessionFactory.getCurrentSession().createQuery("from Comment where blogID='"+blogID+"' and reportComment='Y'").list();
	}
	
	public Comment getComment(long commentID){
		return sessionFactory.getCurrentSession().get(Comment.class, commentID);
	}


	public List<Comment> getReportedComment() {
		return sessionFactory.getCurrentSession().createQuery("from Comment where reportComment='Y'").list();
	}

}
