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
import com.jpro.studentsmeetbackend.dao.ForumMessageDAO;
import com.jpro.studentsmeetbackend.model.Forum;
import com.jpro.studentsmeetbackend.model.ForumMessage;

@RestController
public class FourmMessageController {
	
	private static final Logger log=LoggerFactory.getLogger(FourmMessageController.class);
	
	@Autowired
	private ForumMessage fourmMessage;
	
	@Autowired
	private ForumDAO forumDAO;
	
	@Autowired
	private ForumMessageDAO forumMessageDAO;
	
	@PostMapping("/createForumMessage")
	public ResponseEntity<List<ForumMessage>> createForumMessage(@RequestBody ForumMessage msg){
		boolean valid=forumMessageDAO.createForumMessage(msg);
		if(valid){
			return new ResponseEntity<List<ForumMessage>>(forumMessageDAO.getAllForumMessage(msg.getForumId()),HttpStatus.OK);
		}
		else{
			return null;
		}
	}

	@GetMapping("/removeForumMessage/{forumMsgID}")
	public ResponseEntity<List<ForumMessage>> removeForumMessage(@PathVariable("forumMsgID") long forumMsgID){
		fourmMessage=forumMessageDAO.getForumMessage(forumMsgID);
		boolean valid=forumMessageDAO.removeForumMessage(fourmMessage);
		if(valid)
		{
			return new ResponseEntity<List<ForumMessage>>(forumMessageDAO.getAllReportedMsg(),HttpStatus.OK);
		}
		else{
			return null;
		}
	}
	
	@GetMapping("/getForumMessage/{fmmsgId}")
	public ResponseEntity<ForumMessage> getForumMessage(@PathVariable("fmmsgId") long fmmsgId){
		return new ResponseEntity<ForumMessage>(forumMessageDAO.getForumMessage(fmmsgId),HttpStatus.OK);
	}
	
	@GetMapping("reportForumMessage/{fmID}")
	public ResponseEntity<Forum> reportForumMessage(@PathVariable("fmID") long fmID){
		ForumMessage forummsg=forumMessageDAO.getForumMessage(fmID);
		boolean valid=forumMessageDAO.reportForumMessage(forummsg);
		if(valid){
			return new ResponseEntity<Forum>(forumDAO.getForumbyID(forummsg.getForumId()),HttpStatus.OK);
		}
		else{
			return null;
		}
	}
	
	@GetMapping("/getAllForumMessage/{forumId}")
	public ResponseEntity<List<ForumMessage>> getAllForumMessage(@PathVariable("forumId") long forumId){
		return new ResponseEntity<List<ForumMessage>>(forumMessageDAO.getAllForumMessage(forumId),HttpStatus.OK);
	}
	
	@GetMapping("/getForumRepMsg")
	public ResponseEntity<List<ForumMessage>> getAllREpMsg(){
		return new ResponseEntity<List<ForumMessage>>(forumMessageDAO.getAllReportedMsg(),HttpStatus.OK);
	}
	
	@GetMapping("/getAllReportedForumMessage/{forumId}")
	public ResponseEntity<List<ForumMessage>> getAllReportedForumMessage(@PathVariable("forumId") long forumId){
		return new ResponseEntity<List<ForumMessage>>(forumMessageDAO.getAllReportedMessage(forumId),HttpStatus.OK);
	}
}
