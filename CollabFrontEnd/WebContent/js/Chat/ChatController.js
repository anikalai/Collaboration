'use strict';
app.controller('ChatController',['$scope','ChatService','$location','$rootScope','$cookieStore','$http',
	function($scope, ChatService, $location, $rootScope,$cookieStore, $http){
	console.log("Inside ChatController")
	this.chat={
		chatId :'',
		creatorID : '',
		isPrivateChat :'',
		reportChat :'',
		chatTopic :'',
		chat_Messages : [],
		friendID : ''
	};
	this.friend={
			friendId :'',
			isOnline :'',
			friendStatus :''
	};
	this.chatreport={
			chat_reportId:'',
			chatID:'',
			userID:''
	}
	this.chatID='';
	this.user={
			errorCode : '',
			errorMsg : '',
			userID : '',
			userName : '',
			userPassword : '',
			userQualification : {
				tenthPercentage : '',
				twelfthPercentage : '',
				bachelorCGPAPercentage : ''
			},
			isOnline : '' ,
			lastSeenOnline : '',
			userRole : '',
			userFriends : [],
			userImage : ''
		};
	this.chatMsg={
			chatID :'',
			userID :'',
			msgDate :'',
			chatMsg :''
	}
	this.createChat=function(){
		console.log("Creating chat")
		this.user=$cookieStore.get('currentUser');
		this.chat.creatorID=this.user.userID;
		this.getFriends();
		ChatService.createChat(this.chat)
		.then(
				function(d){
					$rootScope.chat=d;
					$location.path("/goChatList")
				},
				function(errResponse){
					console.error('Error while creating chat');
				});
	};
	this.getFriends=function(){
		console.log("Getting friends");
		this.user=$cookieStore.get('currentUser');
		this.chat.creatorID=this.user.userID;
		ChatService.getUserFriends(this.user.userID)
		.then(
				function(d){
					$rootScope.friends=d;
				},
				function(errResponse){
					console.error('Error while creating chat');
				});
	};
	this.getAllAvailableChat=function(){
		console.log("getting all open chats")
		ChatService.getOpenChats()
			.then(
					function(d){
						$rootScope.chats=d;
						$location.path("/goChatViewOpen")
					},
					function(errResponse){
						console.error('Error while creating chat');
					});
	};
	this.joinOpenChat=function(chatID){
		console.log("user entering public chat")
		this.getChatByID(chatID);
		$location.path("/goChatPage")
	};
	this.getChatByID=function(chatID){
		console.log("Getting chat by id")
		ChatService.getChatById(chatID)
		.then(
				function(d){
					$rootScope.chat=d;	
					$location.path("/goSocketChat")
				},
				function(errResponse){
					console.error('Error while getting chat');
				});
	};
	this.addMessage=function(){
		console.log("Adding Message")
		this.chatMsg.chatID=$rootScope.chat.chatId;
		this.user=$cookieStore.get('currentUser');
		this.chatMsg.userID=this.user.userID;
		ChatService.addChatMessage(this.chatMsg)
		.then(
				function(d){
					$rootScope.chat=d;					
				},
				function(errResponse){
					console.error('Error while adding msg chat');
				});
	};
	this.startChat=function(friendID){
		console.log("Creating private chat")
		this.chat.isPrivateChat='Y';
		this.user=$cookieStore.get('currentUser');
		this.chat.creatorID=this.user.userID;
		this.chat.friendID=friendID;
		ChatService.createChat(this.chat)
		.then(
				function(d){
					$rootScope.chat=d;
					$location.path("/goChatPage")
				},
				function(errResponse){
					console.error('Error while starting chat');
				});
	};
	this.getPrivateChats=function(){
		this.user=$cookieStore.get('currentUser');
		$rootScope.curUser=$cookieStore.get('currentUser');
		ChatService.getPrivateChats(this.user.userID)
		.then(
				function(d){
					$rootScope.chats=d;
					$location.path("/goChatsPrivate")
				},
				function(errResponse){
					console.error('Error while getting private chats')
				});
	};
	this.gotoChat=function(chatID){
		this.getChatByID(chatID);
	};
	this.repChat=function(chatid){
		this.user=$cookieStore.get('currentUser');
		ChatService.reportChat(chatid,this.user.userID)
		.then(
				function(d){
					$rootScope.report=d;
					$location.path("/goChatReportUSer")
				});
	};
	this.manageChat=function(){
		ChatService.getReportedChats()
		.then(
				function(d){
					$rootScope.reports=d;
					$location.path("/goManageChats")
				});
	};
	this.removeChat=function(chatid){
		ChatService.removeChat(chatid)
		.then(
				function(d){
					$rootScope.reports=d;
					$location.path("/goManageChats")
				})
	}
}])