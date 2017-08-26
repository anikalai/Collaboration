'use strict';
app.service('JobService', ['$http', '$q','$rootScope', function($http, $q,$rootScope){
	console.log("JobService...")
	var BASE_URL='http://localhost:8080/StudentsMeetControllerRest/';
		return {
			createJob : function(job){
				console.log("Creating job..JobService")
				return $http.post(BASE_URL+'createjob/',job)
				.then(
						function(response){
	                        return response.data;
	                    }, 
	                    function(errResponse){
	                        console.error('Error while creating job..JobService');
	                        return $q.reject(errResponse);
	                    });
			},
			getJobById : function(jobID){
				return $http.get(BASE_URL+'getjobById/'+jobID)
				.then(
						function(response){
	                        return response.data;
	                    }, 
	                    function(errResponse){
	                        console.error('Error while getting job..JobService');
	                        return $q.reject(errResponse);
	                    });
			},
			getAllJobs : function(){
				return $http.get(BASE_URL+'getAlljob')
				.then(
						function(response){
	                        return response.data;
	                    }, 
	                    function(errResponse){
	                        console.error('Error while geting jobs..JobService');
	                        return $q.reject(errResponse);
	                    });
			},
			getAllJobApplications : function(){
				return $http.get(BASE_URL+'getAllJobApplication')
				.then(
						function(response){
	                        return response.data;
	                    }, 
	                    function(errResponse){
	                        console.error('Error while geting jobs..JobService');
	                        return $q.reject(errResponse);
	                    });
			},
			submitJobApplication : function(jobApplication){
				return $http.post(BASE_URL+'createJobApplication/',jobApplication)
				.then(
						function(response){
	                        return response.data;
	                    }, 
	                    function(errResponse){
	                        console.error('Error while applying for job..JobService');
	                        return $q.reject(errResponse);
	                    });
			},
			acceptApplication : function(jobid){
				return $http.get(BASE_URL+'acceptapplication/'+jobid)
				.then(
						function(response){
	                        return response.data;
	                    }, 
	                    function(errResponse){
	                        console.error('Error while applying for job..JobService');
	                        return $q.reject(errResponse);
	                    });
			},
			rejectApplication : function(jobid){
				return $http.get(BASE_URL+'rejectapplication/'+jobid)
				.then(
						function(response){
	                        return response.data;
	                    }, 
	                    function(errResponse){
	                        console.error('Error while applying for job..JobService');
	                        return $q.reject(errResponse);
	                    });
			},
			getApplicOfUser : function(userID){
				return $http.get(BASE_URL+'getUserApplications/'+userID)
				.then(
						function(response){
	                        return response.data;
	                    }, 
	                    function(errResponse){
	                        console.error('Error while applying for job..JobService');
	                        return $q.reject(errResponse);
	                    });
			},
			closejob : function(jobid){
				return $http.get(BASE_URL+'closejob/'+jobid)
				.then(
						function(response){
	                        return response.data;
	                    }, 
	                    function(errResponse){
	                        console.error('Error while geting jobs..JobService');
	                        return $q.reject(errResponse);
	                    });
			}
		}
}])