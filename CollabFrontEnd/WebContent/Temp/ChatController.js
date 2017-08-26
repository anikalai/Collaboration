'user strict';
app.controller('ChatController',['$scope','ChatService','$location','$rootScope','$cookieStore','$http',
	function($scope, ChatService, $location, $rootScope,$cookieStore, $http) {
	console.log("Inside chatController")
	$scope.messages = [];
	  $scope.message = "";
	  $scope.addMessage = function() {
		    ChatService.send($scope.message);
		    $scope.message = "";
		  };

		  ChatService.receive().then(null, null, function(message) {
		    $scope.messages.push(message);
		  });
}])