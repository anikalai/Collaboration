'user strict';
app.controller('UserController',['$scope','UserService','$cookies','$location','$rootScope','$cookieStore','$http',
	function($scope, UserService,$cookies, $location, $rootScope,$cookieStore, $http) {
	console.log("Inside UserController")
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
			userID:'',
			friendId:'',
			friendStatus:'',
			isOnline:''
	};
	this.friends=[];
	$rootScope.currentUser ={
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
	this.qualification={
			tenthPercentage:'',
			twelfthPercentage:'',
			bachelorCGPAPercentage:''
	}
	this.users = [];
	$scope.orderByMe = function(x) {
		$scope.myOrderBy = x;
	};
	this.createUser = function(user) {
		console.log("Creating user");
		UserService.createUser(user)
		.then(
				function(d) {
					$location.path("/")
					this.user=d;
					alert("Please wait for the admin approval");
				},
				function(errResponse) {
					console.error('Error while creating user');
				});
	};
	this.reset = function() {
		this.user = {
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
				userFriends : []
		};
		$scope.regForm.$setPristine(); 
	};
	this.submit = function() {
		{
			console.log('Saving New User');
			this.createUser(this.user);
		}
		this.reset();
	};
	this.login =function(){
		console.log('Performing Login operations');		
		UserService.login(this.user)
		.then(
				function(d){
					$cookieStore.put('currentUser',d);
					$rootScope.currentUser=d;
					$rootScope.myUser=$rootScope.currentUser;
					var role=$rootScope.currentUser.userRole;
			    	if(role==='ROLE_USER'){
					$location.path("/goUserHome")
			    	}
			    	if(role==='ROLE_ADMIN'){
			    		$location.path("/goAdminHome")
			    	}
				},
				function(errResponse) {
					console.error('Error while login');
				});
	};
	this.logout=function(){
		console.log("Logout User");
		user=$cookieStore.get('currentUser');
		UserService.logout(user)
		.then(
				function(){
					$cookieStore.remove('currentUser');
					$location.path("/")
				},
				function(errResponse) {
					console.error('Error while logout');
				});
	};
	this.getFriends=function(){
		console.log("Getting friends of user")
		this.user=$cookieStore.get('currentUser');
		UserService.getFriends(this.user.userID)
		.then(
				function(d){
					$rootScope.friends=d;
					$location.path("/goFriendsView")
				},
				function(errResponse){
					console.error('Error while retreiving friends');
				});
	};
	this.getUserFriends=function(){
		console.log("Getting friends of user")
		this.user=$cookieStore.get('currentUser');
		UserService.getFriends(this.user.userID)
		.then(
				function(d){
					$rootScope.friends=d;
				},
				function(errResponse){
					console.error('Error while retreiving friends');
				});
	};
	
	this.getCurrentUserFriend=function(){
		console.log("Getting friends of particular user")
		this.user=$cookieStore.get('currentUser');
		UserService.getUserFriends(this.user.userID)
		.then(
				function(d){
					$rootScope.friends=d;
					console.log($rootScope.friends)
					$location.path("/goFriendsView")
				},
				function(errResponse){
					console.error('Error while retreiving friends');
				});
	};
	this.getAllUsers=function(){
		console.log("Getting all users")
		this.getUserFriends()
		UserService.getAllUsers()
		.then(
				function(d){
					this.user=$cookieStore.get('currentUser');
					var userid=this.user.userID;
					var index=d.findIndex(x => x.userID==userid);
					d.splice(index,1);
					this.users=d;
					this.friends=$rootScope.friends;
					for(i=0;i<friends.length;i++){
						for(j=0;j<users.length;j++){
						if(friends[i].friendId===users[j].userID){
							d.splice(j,1);
						}
						}
					}
					$rootScope.users=d;					
					$location.path("/goViewUsers")
				},
				function(errResponse){
					console.error('Error while getting users')
				});
	};
	this.acceptRequest=function(senderID){
		console.log("Accepting request")
		this.user=$cookieStore.get('currentUser');
		this.friend.userID=this.user.userID;
		this.friend.friendId=senderID;
		UserService.acceptRequest(this.friend)
		.then(
				function(d){
					$rootScope.friends=d;
					$location.path("/goFriendsView")
				},
				function(errResponse){
					console.error('Error while accpting friends');
				});
	};
	this.submitQualification=function(){
		console.log("Submitting Qualification of user")
		this.user=$cookieStore.get('currentUser');
		this.user.userQualification=this.qualification;
		UserService.submitQualification(this.user)
		.then(
				function(d){
					$rootScope.user=d;
					$cookieStore.remove('currentUser');
					$cookieStore.put('currentUser',d);
					$location.path("/goJobViewAll")
				},
				function(errResponse){
					console.error('Error while accpting friends');
				});
	};
	this.addFriend=function(userid){
		this.user=$cookieStore.get('currentUser');
		this.friend.userID=this.user.userID;
		this.friend.friendId=userid;
		UserService.addFriend(this.friend)
		.then(
				function(d){
					$rootScope.friends=d;
					$location.path("/goFriendsView")
				},
				function(errResponse){
					console.error('Error while retreiving friends');
				});

	}
	this.rejFriend=function(userid){
		this.user=$cookieStore.get('currentUser');
		this.friend.userID=this.user.userID;
		this.friend.friendId=userid;
		console.log(" user id "+this.friend.userID+" friend id "+userid)
		UserService.rejectFriend(this.friend)
		.then(
				function(d){
					$rootScope.friends=d;
					$location.path("/goFriendsView")
				},
				function(errResponse){
					console.error('Error while retreiving friends');
				});

	};
	this.getFriendRequests=function(){
		this.user=$cookieStore.get('currentUser');
		UserService.getFriendsReq(this.user.userID)
		.then(
				function(d){
					$rootScope.friends=d;
					$location.path("/goFriendsAccept");
				},
				function(errResponse){
					console.error('Error while retreiving friends');
				});
	};
	this.startChat=function(friendID){
		console.log("Creating private chat")
		this.chat.isPrivateChat='Y';
		this.user=$cookieStore.get('currentUser');
		this.chat.creatorID=this.user.userID;
		this.chat.friendID=friendID;
		UserService.createChat(this.chat)
		.then(
				function(d){
					$rootScope.chat=d;
					$location.path("/goChatPage")
				},
				function(errResponse){
					console.error('Error while starting chat');
				});
	};
}])