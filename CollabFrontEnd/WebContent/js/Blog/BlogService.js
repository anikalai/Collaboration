'use strict';
app.service('BlogService', ['$http', '$q','$rootScope', function($http, $q,$rootScope){
	console.log("BlogService...")
	var BASE_URL='http://localhost:8080/StudentsMeetControllerRest/';
		return {
		createBlog : function(blog){
			console.log("Creating blog in BlogService..BlogService")
			return $http.post(BASE_URL+'createBlog/',blog)
			.then(
					function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while creating blog..BlogService');
                        return $q.reject(errResponse);
                    });
		},
		getAllBlog : function(){
			console.log("Retrieve all blog..BlogService")
			return $http.get(BASE_URL+'getAllBlog')
			.then(
					function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while getting all blog..BlogService');
                        return $q.reject(errResponse);
                    });
		},
		getBlogByID : function(blogID){
			console.log("Getting blog with id..BlogService")
			return $http.get(BASE_URL+'getBlogById/'+blogID)
			.then(
					function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while getting blog..BlogService');
                        return $q.reject(errResponse);
                    });
		},
		getBlogOfUser : function(userID){
			console.log("Getting blogs of user..BlogService")
			return $http.get(BASE_URL+'getBlogOfUser/'+userID)
			.then(
					function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while getting blogs of user..BlogService');
                        return $q.reject(errResponse);
                    });
		},
		updateBlog : function(blog){
			console.log("Updating blog..BlogService")
			return $http.post(BASE_URL+'updateBlog/',blog)
			.then(
					function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while updating blog..BlogService');
                        return $q.reject(errResponse);
                    });
		},
		addComment : function(comment){
			console.log("Posting comment..BLogService")
			return $http.post(BASE_URL+'addComment/',comment)
			.then(
					function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while posting comment..BlogService');
                        return $q.reject(errResponse);
                    });
		},
		getComments : function(blogID){
			console.log("Getting comments..BlogService")
			return $http.get(BASE_URL+'getAllComments/'+blogID)
			.then(
					function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while getting comment..BlogService');
                        return $q.reject(errResponse);
                    });
		},
		reportCommentByID : function(commentID){
			console.log("Reporting comment..BlogService")
			return $http.get(BASE_URL+'reportComment/'+commentID)
			.then(
					function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while reporting comment..BlogService');
                        return $q.reject(errResponse);
                    });
		},
		deleteBlog : function(blogID){
			console.log("REmoving blog..BlogService")
			return $http.get(BASE_URL+'removeBlog/'+blogID)
			.then(
					function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while removing blog..BlogService');
                        return $q.reject(errResponse);
                    });
		},
		getReportedComments : function(){
			return $http.get(BASE_URL+'getReportedComments')
			.then(
					function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while getting reported comments in blog..BlogService');
                        return $q.reject(errResponse);
                    });
		},
		removeComment : function(commentID){
			return $http.get(BASE_URL+'removeComment/'+commentID)
			.then(
					function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while removing comment..BlogService');
                        return $q.reject(errResponse);
                    });
		},
		likeBlog:function(blogId){
			return $http.get(BASE_URL+'likeBlog/'+blogId)
			.then(
					function(response){
                        return response.data;
                    }, 
                    function(errResponse){
                        console.error('Error while like the blog..BlogService');
                        return $q.reject(errResponse);
                    });
		}
	}
}])