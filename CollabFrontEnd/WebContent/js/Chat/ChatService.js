'use strict';
app.service('ChatService', ['$http', '$q','$rootScope', function($http, $q,$rootScope){
	console.log("ChatService...")
	var BASE_URL='http://localhost:8080/StudentsMeetControllerRest/';
	
		return {
			createChat : function(chat){
				console.log("Creating chat..ChatService");
				return $http.post(BASE_URL+'createChat/',chat)
				.then(
						function(response){
	                        return response.data;
	                    }, 
	                    function(errResponse){
	                        console.error('Error while creating chat..ChatService');
	                        return $q.reject(errResponse);
	                    });
			},
			getUserFriends : function(userId){
				console.log("Getting friends..ChatService");
				return $http.get(BASE_URL+'getFriendsOfUser/'+userId)
				.then(
						function(response){
	                        return response.data;
	                    }, 
	                    function(errResponse){
	                        console.error('Error while getting friends in chat..ChatService');
	                        return $q.reject(errResponse);
	                    });
			},
			getOpenChats : function(){
				console.log("getting open chats..ChatService")
				return $http.get(BASE_URL+'getOpenChat/')
				.then(
						function(response){
	                        return response.data;
	                    }, 
	                    function(errResponse){
	                        console.error('Error while getting open chat..ChatService');
	                        return $q.reject(errResponse);
	                    });
			},
			addChatMessage : function(msg){
				console.log("Adding msg to chat..ChatService")
				return $http.post(BASE_URL+'addChatMessage/',msg)
				.then(
						function(response){
	                        return response.data;
	                    }, 
	                    function(errResponse){
	                        console.error('Error while adding msg to chat..ChatService');
	                        return $q.reject(errResponse);
	                    });
			},
			getChatById : function(chatID){
				console.log("Getting chat by id ..ChatService")
				return $http.get(BASE_URL+'getChatById/'+chatID)
				.then(
						function(response){
	                        return response.data;
	                    }, 
	                    function(errResponse){
	                        console.error('Error while getting chat..ChatService');
	                        return $q.reject(errResponse);
	                    });
			},
			getPrivateChats : function(userID){
				return $http.get(BASE_URL+'getChatsOfUser/'+userID)
				.then(
						function(response){
	                        return response.data;
	                    }, 
	                    function(errResponse){
	                        console.error('Error while getting chat..ChatService');
	                        return $q.reject(errResponse);
	                    });
			},
			reportChat : function(chatid,userid){
				return $http.get(BASE_URL+'reportchat/'+chatid+'/'+userid)
				.then(
						function(response){
	                        return response.data;
	                    }, 
	                    function(errResponse){
	                        console.error('Error while reporting chat..ChatService');
	                        return $q.reject(errResponse);
	                    });
			},
			getReportedChats : function(){
				return $http.get(BASE_URL+'getChatReps')
				.then(
						function(response){
	                        return response.data;
	                    }, 
	                    function(errResponse){
	                        console.error('Error while getting reported chat..ChatService');
	                        return $q.reject(errResponse);
	                    });
			},
			removeChat : function(chatid){
				return $http.get(BASE_URL+'removechat/'+chatid)
				.then(
						function(response){
	                        return response.data;
	                    }, 
	                    function(errResponse){
	                        console.error('Error while removing chat..ChatService');
	                        return $q.reject(errResponse);
	                    });
			}
		}
}])