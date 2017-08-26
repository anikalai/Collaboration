'user strict';
app.controller('ChatMsgController',['$scope','ChatMsgService','$location','$rootScope','$cookieStore','$http',
	function($scope, ChatMsgService, $location, $rootScope,$cookieStore, $http) {
	console.log("Inside chatmsgController")
	$scope.messages = [];
	  $scope.message = "";
	  $scope.addMessage = function() {
		  ChatMsgService.send($scope.message);
		    $scope.message = "";
		  };

		  ChatMsgService.receive().then(null, null, function(message) {
		    $scope.messages.push(message);
		  });
}])