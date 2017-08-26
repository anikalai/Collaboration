package com.jpro.studentsmeetbackend.dao.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.jpro.studentsmeetbackend.dao.BlogDAO;
import com.jpro.studentsmeetbackend.model.Blog;

@Repository("blogDAO")
@Transactional
public class BlogDAOImpl implements BlogDAO {
	
	private static final Logger log=LoggerFactory.getLogger(BlogDAOImpl.class);
	
	private SessionFactory sessionFactory;
	
	BlogDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}


	public boolean createBlog(Blog blog) {
		log.debug("Start of method createBlog");
		try
		{
			blog.setLastUpdateDate(new Date());
			sessionFactory.getCurrentSession().save(blog);
			log.debug("Blog saved to database");
			return true;
		}
		catch(Exception e)
		{
			log.debug("Error creating blog");
			e.printStackTrace();
			return false;
		}

	}

	public boolean updateBlog(Blog blog) {
		log.debug("Start of method updateBlog");
		try
		{
			blog.setLastUpdateDate(new Date());
			sessionFactory.getCurrentSession().update(blog);
			log.debug("Blog updated");
			return true;
		}
		catch(Exception e)
		{
			log.debug("Error occured while updating blog");
			e.printStackTrace();
			return false;
		}

	}

	public boolean removeBlog(long blogId) {
		log.debug("Start of method removeBlog");
		boolean value=removeCommentForBlog(blogId);
		if(value)
		{
		Blog blog=(Blog) getBlogById(blogId);
		try
		{
			sessionFactory.getCurrentSession().delete(blog);
			log.debug("Blog succesfully removed");
			System.out.println("Blog deleted successfully");
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		}
		else
		{
			return false;
		}
	}

	 
	public boolean removeCommentForBlog(long blogId) {
		try
		{
			String sql="delete from STMT_BLOG_COMMENTS  where BLOGID='"+blogId+"'";
			SQLQuery query=sessionFactory.getCurrentSession().createSQLQuery(sql);
			query.executeUpdate();
		log.debug("Comment deleted");
		System.out.println("Blog comments are deleted");
		return true;
		}
		catch(Exception e){
			log.debug("Error occured while rejecting request");
			return false;
		}
	}
	public boolean removeBlog(Blog blog) {
		try
		{
			sessionFactory.getCurrentSession().delete(blog);
			log.debug("Blog succesfully removed");
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public Blog getBlogById(long blogID) {
		log.debug("Getting blog with blogId:"+blogID);
		return (Blog) sessionFactory.getCurrentSession().createQuery("from Blog where blogID='"+blogID+"'").uniqueResult();
	}

	public List<Blog> getAllBlogs() {
		log.debug("Retreiving all blogs");
		return sessionFactory.getCurrentSession().createQuery("from Blog").list();
	}
	
	public List<Blog> getBlogsOfUser(String userID){
		return sessionFactory.getCurrentSession().createQuery("from Blog where blogCreatorId='"+userID+"'").list();
	}


	public boolean likeTheBlog(Blog blog) {
		log.debug("---starting likes ");
		
		try
		{
		blog.setBlogLikes(blog.getBlogLikes() + 1);
		
		sessionFactory.getCurrentSession().update(blog);
	
		System.out.println("Blog likes updated successfully"+ blog.getBlogLikes());
		return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		return false;
		}
	}

}
