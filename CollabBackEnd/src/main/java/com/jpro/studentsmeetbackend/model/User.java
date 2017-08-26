package com.jpro.studentsmeetbackend.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Component
@Table(name="STMT_USER")
public class User extends BaseDomain {
	
	@Id
	@Size(min=4,max=25)
	private String userID;
	
	@Size(min=4,max=25)
	private String userName;
	
	@Size(min=7,max=20)
	private String userPassword;
	
	private char isOnline;
	
	/*@Embedded
	private Qualification userQualification;*/
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastSeenOnline;
	
	private String userRole;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="STMT_USER_FRIENDS",joinColumns=@JoinColumn(name="userID"))
	private List<Friend> userFriends=new LinkedList<Friend>();
	
	private byte[] userImage;

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public char getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(char isOnline) {
		this.isOnline = isOnline;
	}

	/*public Qualification getUserQualification() {
		return userQualification;
	}

	public void setUserQualification(Qualification userQualification) {
		this.userQualification = userQualification;
	}*/

	public Date getLastSeenOnline() {
		return lastSeenOnline;
	}

	public void setLastSeenOnline(Date lastSeenOnline) {
		this.lastSeenOnline = lastSeenOnline;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public List<Friend> getUserFriends() {
		return userFriends;
	}

	public void setUserFriends(List<Friend> userFriends) {
		this.userFriends = userFriends;
	}

	public byte[] getUserImage() {
		return userImage;
	}

	public void setUserImage(byte[] userImage) {
		this.userImage = userImage;
	}

}
