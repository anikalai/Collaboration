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

import com.jpro.studentsmeetbackend.dao.UserDAO;
import com.jpro.studentsmeetbackend.model.Friend;
import com.jpro.studentsmeetbackend.model.User;

@RestController
public class UserController {
	
	private static final Logger log=LoggerFactory.getLogger(BlogController.class);
	
	@Autowired
	private User user;
	
	@Autowired
	private UserDAO userDAO;
	
	@PostMapping("/createUser")
	public ResponseEntity<User> createUser(@RequestBody User newUser)
	{
		boolean valid=userDAO.createUser(newUser);
		if(valid){
			return new ResponseEntity<User>(newUser,HttpStatus.OK);
		}
		else{
			return null;
		}
	}
	
	@PostMapping("/updateUser")
	public ResponseEntity<User> updateUser(@RequestBody User newUser)
	{
		boolean valid=userDAO.updateUser(newUser);
		if(valid){
			return new ResponseEntity<User>(newUser,HttpStatus.OK);
		}
		else{
			return null;
		}
	}
	
	@GetMapping("/getUserById/{userID}")
	public ResponseEntity<User> getUSerByID(@PathVariable("userID") String userID){
		return new ResponseEntity<User>(userDAO.getUserById(userID),HttpStatus.OK);
	}
	
	@GetMapping("/getAllFriendsOfUser/{userID}")
	public ResponseEntity<List<Friend>> getAllFriends(@PathVariable("userID") String userID){
		return new ResponseEntity<List<Friend>>(userDAO.getAllFriendsOfUser(userID),HttpStatus.OK);
	}
	
	@GetMapping("/getFriendsOfUser/{userID}")
	public ResponseEntity<List<Friend>> getFriendsOfUser(@PathVariable("userID") String userID){
		return new ResponseEntity<List<Friend>>(userDAO.getFriendsOfUser(userID),HttpStatus.OK);
	}
	
	@GetMapping("/getFriendsReqOfUser/{userID}")
	public ResponseEntity<List<Friend>> getFriendsReqOfUser(@PathVariable("userID") String userID){
		return new ResponseEntity<List<Friend>>(userDAO.getFriendsReqOfUser(userID),HttpStatus.OK);
	}
	
	@PostMapping("/validateUser")
	public  ResponseEntity<User> validateUser(@RequestBody User validateUser){
		if(userDAO.userValidate(validateUser.getUserID(), validateUser.getUserPassword())){
			return new ResponseEntity<User>(userDAO.getUserById(validateUser.getUserID()),HttpStatus.OK);
		}
		else{
			return null;
		}
	}
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<User>> getAllUSer(){
		System.out.println("Getting all users");
		return new ResponseEntity<List<User>>(userDAO.getAllUsers(),HttpStatus.OK);
	}
	
	@GetMapping("/userLogout/{userID}")
	public void userLogout(@PathVariable("userID") String userID){
		userDAO.setUserOffline(userID);
	}

	@PostMapping("/sendFriendRequest")
	public ResponseEntity<List<Friend>> sendFriendRequest(@RequestBody Friend friend){
		boolean valid=userDAO.sendFriendRequest(friend.getUserID(), friend.getFriendId());
		if(valid){
			return new ResponseEntity<List<Friend>>(userDAO.getAllFriendsOfUser(friend.getUserID()),HttpStatus.OK);
		}
		else{
			return null;
		}
	}
	
	@PostMapping("/acceptFriendRequest")
	public ResponseEntity<List<Friend>> acceptRequest(@RequestBody Friend friend){
		boolean valid=userDAO.acceptFriendRequest(friend.getUserID(), friend.getFriendId());
		if(valid){
			return new ResponseEntity<List<Friend>>(userDAO.getAllFriendsOfUser(friend.getUserID()),HttpStatus.OK);
		}
		else{
			return null;
		}
	}
	
	@PostMapping("/rejectRequest")
	public ResponseEntity<List<Friend>> rejectRequest(@RequestBody Friend friend){
		boolean valid=userDAO.rejectRequest( friend.getUserID(),friend.getFriendId());
		if(valid){
			return new ResponseEntity<List<Friend>>(userDAO.getAllFriendsOfUser(friend.getUserID()),HttpStatus.OK);
		}
		else{
			return null;
		}
	}
}
