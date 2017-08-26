'use strict';
app.service('ForumService', ['$http', '$q','$rootScope', function($http, $q,$rootScope){
	console.log("ForumService...")
	var BASE_URL='http://localhost:8080/StudentsMeetControllerRest/';
		return {
			createForum : function(forum){
				console.log("Creating forum..ForumService")
				return $http.post(BASE_URL+'createForum/',forum)
				.then(
						function(response){
	                        return response.data;
	                    }, 
	                    function(errResponse){
	                        console.error('Error while creating forum..ForumService');
	                        return $q.reject(errResponse);
	                    });
			},
			getForums : function(){
				console.log("Getting all forums ... ForumService")
				return $http.get(BASE_URL+'getAllForum')
				.then(
						function(response){
	                        return response.data;
	                    }, 
	                    function(errResponse){
	                        console.error('Error while getting forums..ForumService');
	                        return $q.reject(errResponse);
	                    });
			},
			getForumByID : function(forumId){
				console.log("Getting forum ... ForumService")
				return $http.get(BASE_URL+'getForum/'+forumId)
				.then(
						function(response){
	                        return response.data;
	                    }, 
	                    function(errResponse){
	                        console.error('Error while getting forum..ForumService');
	                        return $q.reject(errResponse);
	                    });
			},
			submitMessage : function(msg){
				console.log("Submitting msg..ForumService")
				return $http.post(BASE_URL+'createForumMessage/',msg)
				.then(
						function(response){
	                        return response.data;
	                    }, 
	                    function(errResponse){
	                        console.error('Error while submiting msg to forum..ForumService');
	                        return $q.reject(errResponse);
	                    });
			},
			reportMsg : function(fmID){
				return $http.get(BASE_URL+'reportForumMessage/'+fmID)
				.then(
						function(response){
	                        return response.data;
	                    }, 
	                    function(errResponse){
	                        console.error('Error while reporting msg in forum..ForumService');
	                        return $q.reject(errResponse);
	                    });
			},
			getAllReportMsg : function(){
				return $http.get(BASE_URL+'getForumRepMsg')
				.then(
						function(response){
	                        return response.data;
	                    }, 
	                    function(errResponse){
	                        console.error('Error while reporting msg in forum..ForumService');
	                        return $q.reject(errResponse);
	                    });
			},
			removeMsg : function(fmid){
				return $http.get(BASE_URL+'removeForum/'+fmid)
				.then(
						function(response){
	                        return response.data;
	                    }, 
	                    function(errResponse){
	                        console.error('Error while reporting msg in forum..ForumService');
	                        return $q.reject(errResponse);
	                    });
			}
		}
}])