package com.jpro.studentsmeetbackend.dao;

import java.util.List;

import com.jpro.studentsmeetbackend.model.Friend;
import com.jpro.studentsmeetbackend.model.User;

public interface UserDAO {
	
	public boolean createUser(User user);
	
	public boolean updateUser(User user);
	
	public User getUserById(String userID);
	
	public List<User> getAllUsers();
	
	public List<Friend> getAllFriendsOfUser(String userID);
	
	public List<Friend> getFriendsOfUser(String userID);
	
	public List<Friend> getFriendsReqOfUser(String userID);
	
	public boolean userValidate(String userID,String userPassword);
	
	public boolean sendFriendRequest(String senderID,String receiverId);
	
	public boolean acceptFriendRequest(String receiverId,String senderID);
	
	public boolean rejectRequest(String senderId,String receiverID);
	
	public boolean setUserOffline(String userID);
	
	public boolean setUserPhoto(String userID,String filePath);

	
}
