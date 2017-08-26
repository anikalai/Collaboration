'user strict';
app.controller('ChatMsgController',['$scope','ChatMsgService','$location','$rootScope','$cookieStore','$http',
	function($scope, ChatMsgService, $location, $rootScope,$cookieStore, $http) {
	console.log("Inside chatController")
	$scope.messages = [];
	  $scope.message ={
			  userID : "",
			  chatID : "",
			  message : ""			  
	  };
	  $scope.addSocketMessage = function() {
		  user=$cookieStore.get('currentUser');
		  $scope.message.userID=user.userID;
		  chat=$rootScope.chat;
		  $scope.message.chatID=chat.chatId;
		  ChatMsgService.send($scope.message);
		    $scope.message = {
					  userID : "",
					  chatID : "",
					  message : ""			  
			  };
		  };

		  ChatMsgService.receive().then(null, null, function(message) {
			  chat=$rootScope.chat;
			  if(message.chatID === chat.chatId){
		    $scope.messages.push(message);
			  }
		  });
}])