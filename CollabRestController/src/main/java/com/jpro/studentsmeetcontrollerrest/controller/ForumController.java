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

import com.jpro.studentsmeetbackend.dao.ForumDAO;
import com.jpro.studentsmeetbackend.model.Forum;

@RestController
public class ForumController {
	
private static final Logger log=LoggerFactory.getLogger(ForumController.class);
	
	@Autowired
	private Forum forum;
	
	@Autowired
	private ForumDAO forumDAO;
	
	@PostMapping("/createForum")
	public ResponseEntity<List<Forum>> createForum(@RequestBody Forum forum){
		boolean valid=forumDAO.createForum(forum);
		if(valid){
			return new ResponseEntity<List<Forum>>(forumDAO.getAllForum(),HttpStatus.OK);
		}
		else{
			return null;
		}
	}
	
	@PostMapping("/updateForum")
	public ResponseEntity<Forum> updateForum(@RequestBody Forum forum){
		boolean valid=forumDAO.updateForum(forum);
		if(valid){
			return new ResponseEntity<Forum>(forum,HttpStatus.OK);
		}
		else{
			return null;
		}
	}
	
	@GetMapping("/getAllForum")
	public ResponseEntity<List<Forum>> getAllForum() {
		return new ResponseEntity<List<Forum>>(forumDAO.getAllForum(),HttpStatus.OK);
	}
	
	@GetMapping("/getForum/{forumId}")
	public ResponseEntity<Forum> getForum(@PathVariable("forumId") long forumId){
		return new ResponseEntity<Forum>(forumDAO.getForumbyID(forumId),HttpStatus.OK);
	}

	@GetMapping("/removeForum/{forumId}")
	public ResponseEntity<List<Forum>> removeForum(@PathVariable("forumId") long forumId) {
		boolean valid=forumDAO.removeForum(forumId);
		if(valid){
		return new ResponseEntity<List<Forum>>(forumDAO.getAllForum(),HttpStatus.OK);
		}
		else{
			return null;
		}
	}
}
