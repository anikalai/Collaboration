'use strict';
app.service('UserService', ['$http', '$q','$rootScope', function($http, $q,$rootScope){
	console.log("UserService...")
	var BASE_URL='http://localhost:8080/StudentsMeetControllerRest/'
		return {
		createUser : function(user) {
			console.log("Creating user in UserService")
			return $http.post(BASE_URL+'createUser',user)
			.then(
					function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while creating user');
                        return $q.reject(errResponse);
                    });
		},
		login : function(user){
			console.log("Login user")
			return $http.post(BASE_URL+'validateUser',user)
			.then(
					function(response){
						return response.data;
					},
					function(errResponse){
						console.error('Error while login');
						return $q.reject(errResponse);
					});
		},
		logout : function(user){
			console.log("Logout user")
			return $http.get(BASE_URL+'userLogout/'+user.userID)
		},
		getUserFriends:function(userID){
			console.log("Getting Friends of particular user...")
			return $http.get(BASE_URL+'getFriendsOfUser/'+userID)
			.then(
					function(response){
						return response.data;
					},
					function(errResponse){
						console.error('Error while getting freinds..UserService');
						return $q.reject(errResponse);
					});
		},
		getFriends : function(userID){
			console.log("Getting user Friends..UserService")
			return $http.get(BASE_URL+'getAllFriendsOfUser/'+userID)
			.then(
					function(response){
						return response.data;
					},
					function(errResponse){
						console.error('Error while getting freinds..UserService');
						return $q.reject(errResponse);
					});
		},
		getAllUsers : function(){
			console.log("Gettting all users")
			return $http.get(BASE_URL+'getAllUsers')
			.then(
					function(response){
						return response.data;
					},
					function(errResponse){
						console.error('Error while getting users..UserService');
						return $q.reject(errResponse);
					});
		},
		acceptRequest : function(friend){
			console.log("Accepting request")
			return $http.post(BASE_URL+'acceptFriendRequest',friend)
			.then(
					function(response){
						return response.data;
					},
					function(errResponse){
						console.error('Error while accepting request..UserService');
						return $q.reject(errResponse);
					});
		},
		submitQualification : function(user){
			console.log("Updating user")
			return $http.post(BASE_URL+'updateUser',user)
			.then(
					function(response){
						return response.data;
					},
					function(errResponse){
						console.error('Error while updating user..UserService');
						return $q.reject(errResponse);
					});
		},
		addFriend : function(firend){
			return $http.post(BASE_URL+'sendFriendRequest',firend)
			.then(
					function(response){
						return response.data;
					},
					function(errResponse){
						console.error('Error while ADDING user as friend..UserService');
						return $q.reject(errResponse);
					});
		},
		rejectFriend : function(firend){
			return $http.post(BASE_URL+'rejectRequest',firend)
			.then(
					function(response){
						return response.data;
					},
					function(errResponse){
						console.error('Error while ADDING user as friend..UserService');
						return $q.reject(errResponse);
					});
		},
		acceptFriend : function(firend){
			return $http.post(BASE_URL+'acceptFriendRequest',firend)
			.then(
					function(response){
						return response.data;
					},
					function(errResponse){
						console.error('Error while ADDING user as friend..UserService');
						return $q.reject(errResponse);
					});
		},
		getFriendsReq : function(userID){
			console.log("Getting user Friends..UserService")
			return $http.get(BASE_URL+'getFriendsReqOfUser/'+userID)
			.then(
					function(response){
						return response.data;
					},
					function(errResponse){
						console.error('Error while getting freinds..UserService');
						return $q.reject(errResponse);
					});
		},
		createChat : function(chat){
			console.log("Creating chat..UserService");
			return $http.post(BASE_URL+'createChat',chat)
			.then(
					function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while creating chat..UserService');
                        return $q.reject(errResponse);
                    });
		}
	}
	}])