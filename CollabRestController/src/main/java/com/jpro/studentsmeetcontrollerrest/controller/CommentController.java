package com.jpro.studentsmeetcontrollerrest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jpro.studentsmeetbackend.dao.CommentDAO;
import com.jpro.studentsmeetbackend.model.Comment;

@RestController
public class CommentController {
	
	private static final Logger log=LoggerFactory.getLogger(CommentController.class);
	
	@Autowired
	private Comment comment;
	
	@Autowired
	private CommentDAO commentDAO;
	
	@GetMapping("/getAllComments/{blogID}")
	public ResponseEntity<List<Comment>> getAllComment(@PathVariable("blogID") long blogID){
		return new ResponseEntity<List<Comment>>(commentDAO.getAllComments(blogID),HttpStatus.OK);
	}
	
	@PostMapping("/addComment/")
	public ResponseEntity<List<Comment>> addComment(@RequestBody Comment comment){
		boolean valid=commentDAO.addComment(comment.getBlogID(), comment.getCommentUserId(), comment.getCommentText());
		if(valid){
			return new ResponseEntity<List<Comment>>(commentDAO.getAllComments(comment.getBlogID()),HttpStatus.OK);
		}
		else{
			return null;
		}
	}
	
	@GetMapping("/reportComment/{commentID}")
	public ResponseEntity<Comment> reportComment(@PathVariable("commentID") long commentID){
		boolean valid=commentDAO.reportComment(commentID);
		if(valid){
			return new ResponseEntity<Comment>(commentDAO.getComment(commentID),HttpStatus.OK);
		}
		else{
			return null;
		}
	}
	
	@GetMapping("/removeComment/{commentID}")
	public ResponseEntity<List<Comment>> removeComment(@PathVariable("commentID") long commentID){
		Comment comment=commentDAO.getComment(commentID);
		boolean valid=commentDAO.removeComment(commentID);
		if(valid){
			return new ResponseEntity<List<Comment>>(commentDAO.getReportedComment(),HttpStatus.OK);
		}
		else{
			return null;
		}
	}
	
	@GetMapping("/getAllReportedComments/{blogID}")
	public ResponseEntity<List<Comment>> getAllReportedComments(@PathVariable("blogID") long blogID){
		return new ResponseEntity<List<Comment>>(commentDAO.getAllReportedComments(blogID),HttpStatus.OK);
	}
	
	@GetMapping("/getReportedComments")
	public ResponseEntity<List<Comment>> getReportedComments(){
		return new ResponseEntity<List<Comment>>(commentDAO.getReportedComment(),HttpStatus.OK);
	}
	
	@PostMapping("/getComment/{commentID}")
	public ResponseEntity<Comment> getComment(@PathVariable("commentID") long commentID){
		return new ResponseEntity<Comment>(commentDAO.getComment(commentID),HttpStatus.OK);
	}

}
