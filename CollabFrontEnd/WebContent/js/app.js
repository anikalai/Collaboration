var app = angular.module("myApp",['ngRoute','ngCookies']);
app.config(function($routeProvider) {
	$routeProvider
	.when('/', {
		templateUrl : 'Common/Common.html'	
	})
	.when('/goRegister', {
		templateUrl : 'User/Register.html',
		controller : 'UserController'
	})
	.when('/goLogin',{
		templateUrl: 'User/Login.html',
		controller : 'UserController'
	})
	.when('/goUserHome',{
		templateUrl:'User/UserHome.html',
		controller:'UserController'
	})
	.when('/goBlogHome',{
		templateUrl:'Blog/BlogHome.html',
		controller:'BlogController'
	})
	.when('/goBlogCreate',{
		templateUrl: 'Blog/BlogCreate.html',
		controller : 'BlogController'
	})
	.when('/goBlogView',{
		templateUrl: 'Blog/BlogView.html',
		controller : 'BlogController'
	})
	.when('/goBlogViewAll',{
		templateUrl : 'Blog/BlogViewAll.html',
		controller : 'BlogController'
	})
	.when('/goBlogsUser',{
		templateUrl:'Blog/BlogUpdate.html',
		controller : 'BlogController'
	})
	.when('/goBlogEdit',{
		templateUrl : 'Blog/BlogEdit.html',
		controller : 'BlogController'
	})
	.when('/goBlogManage',{
		templateUrl:'Admin/ManageBlog.html',
		controller:'BlogController'
	})
	.when('/goForumHome',{
		templateUrl: 'Forum/ForumHome.html',
		controller : 'ForumController'
	})
	.when('/goForumCreate',{
		templateUrl: 'Forum/ForumCreate.html',
		controller : 'ForumController'
	})
	.when('/goForumViewAll',{
		templateUrl: 'Forum/ForumViewAll.html',
		controller : 'ForumController'
	})
	.when('/goForumManage',{
		templateUrl:'Admin/ManageForum.html',
		controller:'ForumController'
	})
	.when('/goForumView',{
		templateUrl : 'Forum/ForumView.html',
		controller : 'ForumController'
	})
	.when('/goFriendHome',{
		templateUrl:'User/FriendHome.html',
		controller:'UserController'
	})
	.when('/goFriendsView',{
		templateUrl:'User/UserViewFriends.html',
			controller:'UserController'
	})
	.when('/goViewUsers',{
		templateUrl:'User/UserViewAll.html',
		controller:'UserController'
	})
	.when('/goFriendsAccept',{
		templateUrl:'User/UserAcceptFriends.html',
		controller:'UserController'
	})
	.when('/goAdminHome',{
		templateUrl : 'Admin/AdminHome.html',
		controller : 'UserController'
	})
	.when('/goBlogManage',{
		templateUrl:'Admin/ManageBlog.html',
		controller:'UserController'
	})
	.when('/goForumManage',{
		templateUrl:'Admin/ManageForum.html',
		controller:'UserController'
	})
	.when('/goManageJob',{
		templateUrl:'Admin/ManageJob.html',
		controller:'UserController'
	})
	.when('/goChatHome',{
		templateUrl:'Chat/ChatHome.html',
		controller:'ChatController'
	})
	.when('/goChaCreate',{
		templateUrl:'Chat/ChatCreate.html',
		controller:'ChatController'
	})
	.when('/goChatList',{
		templateUrl :'Chat/ChatList.html',
		controller : 'ChatController'
	})
	.when('/goChatViewOpen',{
		templateUrl:'Chat/ChatViewOpen.html',
		controller:'ChatController'
	})
	.when('/goChatPage',{
		templateUrl:'Chat/ChatPagePrime.html',
		controller:'ChatController'
	})
	.when('/goChatsPrivate',{
		templateUrl: 'Chat/ChatViewPrivate.html',
		controller:'ChatController'
	})
	.when('/goBlogReportView',{
		templateUrl : 'Blog/BlogCommentReport.html',
		controller : 'BlogController'
	})
	.when('/goJobHome',{
		templateUrl : 'Job/JobHome.html',
		controller : 'JobController'
	})
	.when('/goPostJob',{
		templateUrl:'Job/JobPost.html',
		controller:'JobController'
	})
		.when('/goJobViewAll',{
		templateUrl:'Job/JobViewAll.html',
		controller:'JobController'
	})
	.when('/goUserQualification',{
		templateUrl:'User/UserQualification.html',
		controller:'UserController'
	})
	.when('/goJobApply',{
		templateUrl:'Job/JobApply.html',
		controller:'JobController'
	})
	.when('/goJobAppViewAll',{
		templateUrl:'Job/JobApplicationViewAll.html',
		controller:'JobController'
	})
	.when('/goJobAdminViewAll',{
		templateUrl:'Job/JobAdminViewAll.html',
		controller:'JobController'
	})
	.when('/goMyApplicationView',{
		templateUrl:'Job/JobUserApplications.html',
		controller:'JobController'
	})
	.when('/goChatReportUSer',{
		templateUrl:'Chat/ChatViewReport.html',
		controller:'ChatController'
	})
	.when('/goManageChats',{
		templateUrl:'Admin/ManageChat.html',
		controller:'ChatController'
	})
	.when('/goSocketChat',{
		templateUrl:'SocketChat/ChatHome.html',
		controller:'ChatMsgController'
	})
	.otherwise({
		redirectTo : '/'
	});
});
app.run( function ($rootScope, $location, $cookies, $cookieStore, $http, UserService) {
	
	$rootScope.$on('$locationChangeStart', function (event, next, current) {
		console.log("$locationChangeStart")		
		$rootScope.currentUser= $cookieStore.get('currentUser') || {};
		var userPage=['/goUserHome','/goBlogHome','/goBlogCreate','/goBlogView','/goBlogViewAll','/goBlogsUser','/goBlogEdit','/goForumHome','/goForumCreate','/goForumViewAll','/goForumView','/goFriendHome']
		var adminPage=['/goBlogManage','/goJobAdminViewAll','/goForumManage','/goJobAppViewAll','/goPostJob','/goBlogReportView']
		var currentPage=$location.path()
		var isUserPage = $.inArray(currentPage, userPage)
		var isAdminPage = $.inArray(currentPage, adminPage)
		var isLoggedIn=$rootScope.currentUser.userID;
		console.log("isLoggedIn:" +isLoggedIn)
	    console.log("isUserPage:" +isUserPage)
	    console.log("isAdminPage:" +isAdminPage)
	    if(angular.isUndefined(isLoggedIn))
	        	{
	    			  $rootScope.notLogged=true;
	    			  $rootScope.isUser=false;
	    			  $rootScope.isAdmin=false;
	    			  if(isUserPage >=0 || isAdminPage >=0 )
	    				  {
		        	  console.log("Navigating to login page:")
		        	  alert("You need to loggin to do this operation")
		        	  $rootScope.notLogged=true;
					  $location.path('/goLogin');
	    				  }
	        	}
		if(isLoggedIn===""){
			$rootScope.notLogged=true;
			$rootScope.isUser=false;
			$rootScope.isAdmin=false;
			if(isUserPage>=0 || isAdminPage >=0 )
			  {
  	  console.log("Navigating to login page:")
  	  alert("You need to loggin to do this operation")
		  $location.path('/goLogin');
			  }
		}
	    else{
	    	var role=$rootScope.currentUser.userRole;
	    	if(role==='ROLE_USER'){
	    	$rootScope.isUser=true;
	    	$rootScope.notLogged=false;
	    	$rootScope.isAdmin=false;
	    	$rootScope.userrole="USER";
	    	if(isAdminPage===0){
	    		alert("You do not have access to this operation")
	    		$location.path('/goUserHome');
	    	}
	    	}
	    	if(role==='ROLE_ADMIN'){
	    		$rootScope.notLogged=false;
				$rootScope.isUser=false;
				$rootScope.isAdmin=true;
				$rootScope.userrole="ADMIN";
	    	}
	    }
	});
});