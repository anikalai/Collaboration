'use strict';
app.controller('JobController',['$scope','JobService','$location','$rootScope','$cookieStore','$http',
	function($scope, JobService, $location, $rootScope,$cookieStore, $http){
	console.log("Inside JobController")
	this.job={
		jobId:'',
		jobName:'',
		jobDescription:'',
		/*lastDateToApply:'',
		requiredQualification:{
			tenthPercentage : '',
			twelfthPercentage : '',
			bachelorCGPAPercentage : ''
		},*/
		jobSalary:'',
		jobStatus:''
	};
	this.jobid='';
	this.jobApplication={
		jobApplicationId:'',
		jobApplicantId:'',
		jobAppliedId:'',
		applicantDetails:'',
		//applicantQualification:[],
		applicationStatus:''
	};
	this.isUser='';
	this.user={
			errorCode : '',
			errorMsg : '',
			userID : '',
			userName : '',
			userPassword : '',
			/*userQualification : {
				tenthPercentage : '',
				twelfthPercentage : '',
				bachelorCGPAPercentage : ''
			},*/
			isOnline : '' ,
			lastSeenOnline : '',
			userRole : '',
			userFriends : [],
			userImage : ''
		};
	this.putJob=function(){
		$location.path("/goPostJob")
	};
	this.createJob=function(){
		console.log("Creating job")
		this.isUser=false;
		JobService.createJob(this.job)
		.then(
				function(d){
					$rootScope.jobs=d;
					$location.path("/goJobAdminViewAll")
				},
				function(errResponse){
					console.error('Error creating job')
				});
	};
	this.applyJob=function(jobid){
		this.user=$cookieStore.get('currentUser');
		/*var qualCheckTenth=this.user.userQualification.tenthPercentage;
		var qualCheckTwelfth=this.user.userQualification.twelfthPercentage;
		var qualCheckDegree=this.user.userQualification.bachelorCGPAPercentage;*/
		this.getJobByID(jobid);
		/*var elTenth=$rootScope.job.requiredQualification.tenthPercentage;
		var elTwelth=$rootScope.job.requiredQualification.twelfthPercentage;
		var elDegree=$rootScope.job.requiredQualification.bachelorCGPAPercentage;
		if(qualCheckTenth==='' || qualCheckTwelfth===''|| qualCheckDegree===''){
			$location.path("/goUserQualification")
		}
		if(qualCheckTenth < elTenth || qualCheckTwelfth < elTwelth || qualCheckDegree < elDegree)
		{
			alert("Not Eligible")
		}
		else{
			//$location.path("/goJobApply")
		}*/
		$location.path("/goJobApply")
	};
	this.getJobByID=function(jobid){
		JobService.getJobById(jobid)
		.then(
				function(d){
					$rootScope.job=d;					
				},
				function(errResponse){
					console.error('Error applying job')
				});
	}
	this.getJobs=function(){
		JobService.getAllJobs()
			.then(
					function(d){
						$rootScope.jobs=d;
						$location.path("/goJobViewAll")
					},
					function(errResponse){
						console.error('Error getting jobs')
					});
	};
	this.getJobApplications=function(){
		JobService.getAllJobApplications()
		.then(
				function(d){
					$rootScope.jobsApplications=d;
					$location.path("/goJobAppViewAll")
				},
				function(errResponse){
					console.error('Error getting jobs')
				});
	};
	this.submitJobApplication=function(){
		this.user=$cookieStore.get('currentUser');
		this.jobApplication.jobApplicantId=this.user.userID;
		this.jobApplication.jobAppliedId=$rootScope.job.jobId;
		//this.jobApplication.applicantQualification=this.user.userQualification;
		JobService.submitJobApplication(this.jobApplication)
		.then(
				function(d){
					$rootScope.jobs=d;
					$location.path("/goJobViewAll")
				},
				function(errResponse){
					console.error('Error getting jobs')
				});
	};
	this.acceptJobAppl=function(jobAppID){
		JobService.acceptApplication(jobAppID)
		.then(
				function(d){
					$rootScope.jobsApplications=d;
					$location.path("/goJobAppViewAll")
				},
				function(errResponse){
					console.error('Error getting jobs')
				});
	};
	this.rejectJobAppl=function(jobAppID){
		JobService.rejectApplication(jobAppID)
		.then(
				function(d){
					$rootScope.jobsApplications=d;
					$location.path("/goJobAppViewAll")
				},
				function(errResponse){
					console.error('Error getting jobs')
				});
	}
	this.getJobsAdmin=function(){
		JobService.getAllJobs()
		.then(
				function(d){
					$rootScope.jobs=d;
					$location.path("/goJobAdminViewAll")
				},
				function(errResponse){
					console.error('Error getting jobs')
				});
	};
	this.getUserAppl=function(){
		this.user=$cookieStore.get('currentUser');
		JobService.getApplicOfUser(this.user.userID)
		.then(
				function(d){
					$rootScope.myjobs=d;
					$location.path("/goMyApplicationView")
				},
				function(errResponse){
					console.error('Error getting jobs')
				});
	};
	this.closejob=function(jobid){
		JobService.closejob(jobid)
		.then(
				function(d){
					$rootScope.jobs=d;
					$location.path("/goJobAdminViewAll")
				},
				function(errResponse){
					console.error('Error getting jobs')
				});
	}
}])