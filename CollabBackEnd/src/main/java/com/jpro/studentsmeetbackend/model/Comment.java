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
@Table(name="STMT_BLOG_COMMENTS")
public class Comment extends BaseDomain {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long commentID;
	
	private long blogID;
	
	private String commentUserId;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date commentDate;
	
	private String commentText;
	
	private char reportComment;
	
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="blogID", scope=Blog.class)
	@ManyToOne
	@JoinColumn(name = "blogID", nullable = false, updatable = false, insertable = false)
	private Blog blog;
	
	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	public long getCommentID() {
		return commentID;
	}

	public void setCommentID(long commentID) {
		this.commentID = commentID;
	}

	public long getBlogID() {
		return blogID;
	}

	public void setBlogID(long blogID) {
		this.blogID = blogID;
	}

	public String getCommentUserId() {
		return commentUserId;
	}

	public void setCommentUserId(String commentUserId) {
		this.commentUserId = commentUserId;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public char getReportComment() {
		return reportComment;
	}

	public void setReportComment(char reportComment) {
		this.reportComment = reportComment;
	}
}
