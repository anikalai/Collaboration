'user strict';
app.controller('BlogController', [
		'$scope',
		'BlogService',
		'$cookies',
		'$location',
		'$rootScope',
		'$cookieStore',
		'$http',
		function($scope, BlogService, $cookies, $location, $rootScope,
				$cookieStore, $http) {
			console.log("Inside BlogController")
			this.blog = {
				blogID : '',
				blogName : '',
				blogCreatorId : '',
				blogData : '',
				blogDescription : '',
				lastUpdateDate : '',
				blogLikes : '',
				comments : []
			};
			this.comment = {
				commentID : '',
				blogID : '',
				commentUserId : '',
				commentDate : '',
				commentText : '',
				reportComment : ''
			};
			this.comments = [];
			this.editblog = {
				blogID : '',
				blogName : '',
				blogCreatorId : '',
				blogData : '',
				blogDescription : '',
				lastUpdateDate : '',
				blogLikes : '',
				comments : []
			};
			this.blogID = '';
			this.user = {
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
				isOnline : '',
				lastSeenOnline : '',
				userRole : '',
				userFriends : [],
				userImage : ''
			};
			this.blogs = [];
			this.createBlog = function() {
				console.log("Creating Blog");
				this.user = $cookieStore.get('currentUser');
				this.blog.blogCreatorId = this.user.userID;
				BlogService.createBlog(this.blog).then(function(d) {
					$rootScope.blog = d;
					alert("Blog created successfully...")
					// $location.path("/goBlogViewAll")
				}, function(errResponse) {
					console.error('Error while creating blog');
				});
				this.getBlogs();
			};
			this.getBlogs = function() {
				console.log("Getting all blogs");
				BlogService.getAllBlog().then(function(d) {
					$rootScope.blogs = d;
					// this.blogs=d;
					$location.path("/goBlogViewAll")
				}, function(errResponse) {
					console.error('Error while getting all blogs');
				});
			}
			this.getAllBlog = function() {
				console.log("Getting all blogs");
				BlogService.getAllBlog().then(function(d) {
					this.blogs = d;
				}, function(errResponse) {
					console.error('Error while getting all blogs');
				});
			};
			this.viewBlog = function(blogID) {
				console.log("Getting this blog");
				this.getCommentsofBlog(blogID);
				BlogService.getBlogByID(blogID).then(function(d) {
					$rootScope.blog = d;
					$location.path("/goBlogView")
				}, function(errResponse) {
					console.error('Error while getting blog');
				});
			};
			this.getMyBlogs = function() {
				console.log("getting blogs of user");
				this.user = $cookieStore.get('currentUser');
				BlogService.getBlogOfUser(this.user.userID).then(function(d) {
					$rootScope.blogs = d;
					$location.path("/goBlogsUser")
				}, function(errResponse) {
					console.error('Error while getting blogs of User');
				});
			}
			this.editBlog = function(blogID) {
				console.log("Getting this blog");
				$rootScope.editblog = {
					blogID : '',
					blogName : '',
					blogCreatorId : '',
					blogData : '',
					blogDescription : '',
					lastUpdateDate : '',
					blogLikes : '',
					comments : []
				};
				BlogService.getBlogByID(blogID).then(function(d) {
					$rootScope.editblog = d;
					this.editblog = d;
					$location.path("/goBlogEdit")
				}, function(errResponse) {
					console.error('Error while editing blog');
				});
			};
			this.updateBlog = function(blogId) {
				console.log("Updating blog");
				this.editblog.blogID = blogId;
				this.user = $cookieStore.get('currentUser');
				this.editblog.blogCreatorId = this.user.userID;
				console.log("creator Id" + this.editblog.blogCreatorId);
				BlogService.updateBlog(this.editblog).then(function(d) {
					$rootScope.blog = d;
					// $location.path("/goBlogView")
					$location.path("/goBlogViewAll")
				}, function(errResponse) {
					console.error('Error while updating blog');
				});
			}
			this.submitComment = function() {
				{
					console.log("Adding comment");
					this.submittedComment();
				}
				this.reset();
			};
			this.submittedComment = function() {
				this.comment.blogID = $rootScope.blog.blogID;
				this.user = $cookieStore.get('currentUser');
				this.comment.commentUserId = this.user.userID;
				BlogService.addComment(this.comment).then(function(d) {
					$rootScope.comments = d;
					$location.path("/goBlogView")
					// $location.path("/goBlogViewAll")
				}, function(errResponse) {
					console.error('Error while posting comment in blog');
				});
				// this.comment.commentText='';
			}
			this.reset = function() {
				this.comment = {
					commentID : '',
					blogID : '',
					commentUserId : '',
					commentDate : '',
					commentText : '',
					reportComment : ''
				};
				$scope.cmtForm.$setPristine();
			};
			this.getCommentsofBlog = function(blogID) {
				console.log("Getting comments of blog")
				BlogService.getComments(blogID).then(function(d) {
					$rootScope.comments = d;
				}, function(errResponse) {
					console.error('Error while getting comment in blog');
				});
			};
			this.reportComment = function(commentID) {
				console.log("reporting comment")
				BlogService.reportCommentByID(commentID).then(function(d) {
					$rootScope.comment = d;
					$location.path("/goBlogReportView")
				}, function(errResponse) {
					console.error('Error while reporting comment in blog');
				});
			};
			this.continueBlog = function() {
				this.viewBlog($rootScope.blog.blogID);
			};
			this.deleteBlog = function(blogID) {
				console.log("Deleting blog")
				BlogService.deleteBlog(blogID).then(function(d) {
					$rootScope.blogs = d;
					$location.path("/goBlogViewAll")
				}, function(errResponse) {
					console.error('Error while deleting blog');
				});
			};
			this.getReportedInBlogs = function() {
				BlogService.getReportedComments().then(function(d) {
					$rootScope.repcom = d;
					$location.path("/goBlogManage")
				}, function(errResponse) {
					console.error('Error while deleting blog');
				});
			};
			this.removeComment = function(commentID) {
				BlogService.removeComment(commentID).then(function(d) {
					$rootScope.repcom = d;
					$location.path("/goBlogManage")
				}, function(errResponse) {
					console.error('Error while deleting blog');
				});
			};
			this.likeBlog = function(blogId) {
				console.log('entering upvote controller')
				BlogService.likeBlog(blogId).then(function(d) {
					$rootScope.blog = d;
					// $location.path("/goBlogView")
					$location.path("/goBlogViewAll")
				}, function(errResponse) {
					console.error('Error while updating blog');
				});
			};
		} ])