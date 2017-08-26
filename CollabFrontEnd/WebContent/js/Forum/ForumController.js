'user strict';
app.controller('ForumController',['$scope','ForumService','$cookies','$location','$rootScope','$cookieStore',
	function($scope, ForumService, $cookies, $location, $rootScope, $cookieStore) {
	console.log("Inside ForumController")
	this.forum={
		forumId : '',
		forumName : '',
		forumData : '',
		messages : []
	};
	this.message={
			forumMessageID:'',
			forumId : '',
			userId:'',
			msgData :'',
			msgTime :'',
			reportMessage :''
	}
	this.forumId='';
	this.createForum=function(){
		console.log("Creating forum")
		ForumService.createForum(this.forum)
		.then(
				function(d){
					$rootScope.forums=d;
					$location.path("/goForumViewAll")
				},
				function(errResponse){
					console.error('Error while creating Forum');
				});
	};
	this.getForums=function(){
		console.log("Getting all forums")
		ForumService.getForums()
		.then(
				function(d){
					$rootScope.forums=d;
					$location.path("/goForumViewAll")
				},
				function(errResponse){
					console.error('Error while getting all Forum');
				});
	};
	this.viewForum=function(forumID){
		console.log("Getting forum by id");
		ForumService.getForumByID(forumID)
		.then(
				function(d){
					$rootScope.forum=d;
					$location.path("/goForumView")
				},
				function(errResponse){
					console.error('Error while getting Forum');
				});
	};
	
	this.submitMessage=function(){
		{
			console.log("Adding comment for forum");
			this.submitMsg();
		}
		this.reset();
	}
	this.submitMsg=function(){
		console.log("Submitting msg in forum");
		this.message.forumId=$rootScope.forum.forumId;
		this.user=$cookieStore.get('currentUser');
		this.message.userId=this.user.userID;
		ForumService.submitMessage(this.message)
		.then(
				function(d){
					$rootScope.forum.messages=d;
					$location.path("/goForumView")
				},
				function(errResponse){
					console.error('Error while getting Forum');
				});
	};
	this.reset = function() {
		this.message={
				forumMessageID:'',
				forumId : '',
				userId:'',
				msgData :'',
				msgTime :'',
				reportMessage :''
		};
		$scope.msgForm.$setPristine(); 
	};
	this.reportMessage=function(fmId){
		ForumService.reportMsg(fmId)
		.then(
				function(d){
					$rootScope.forum=d;
					$location.path("/goForumView")
				},
				function(errResponse){
					console.error('Error while getting Forum');
				});
	};
	this.manageForum=function(){
		ForumService.getAllReportMsg()
		.then(
				function(d){
					$rootScope.repforum=d;
					$location.path("/goForumManage")
				},
				function(errResponse){
					console.error('Error while getting Forum');
				});
	};
	this.removeMessage=function(fmid){
		console.log("Deleting forum "+fmid)
		ForumService.removeMsg(fmid)
		.then(
				function(d){
					$rootScope.repforum=d;
					alert("Forum deleted successfully...");
					$location.path("/goForumViewAll")
				},
				function(errResponse){
					console.error('Error while getting Forum');
				});
	}
}])