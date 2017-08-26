package com.jpro.studentsmeetcontrollerrest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jpro.studentsmeetbackend.dao.JobApplicationDAO;
import com.jpro.studentsmeetbackend.dao.JobDAO;
import com.jpro.studentsmeetbackend.model.Job;
import com.jpro.studentsmeetbackend.model.JobApplication;

@RestController
public class JobApplicationController {
	
	private static final Logger log=LoggerFactory.getLogger(JobApplicationController.class);
	
	@Autowired
	private JobApplication jobApplication;
	
	@Autowired
	private JobDAO jobDAO;
	
	@Autowired
	private JobApplicationDAO jobApplicationDAO;
	
	//Test
	@PostMapping("/createJobApplication")
	public ResponseEntity<List<Job>> createJobApplication(@RequestBody JobApplication newJobApplication){
		log.debug("Creating JobApplication");
		boolean valid=jobApplicationDAO.createJobApplication(newJobApplication);
		if(valid){
			log.debug("JobApplication created");
			return new ResponseEntity<List<Job>>(jobDAO.getAllJobs(),HttpStatus.OK);
		}
		else{
			log.debug("JobApplication not created");
			return null;
		}
	}

	@GetMapping("/removeJobApplication/{jobApplicationID}")
	public String removeJobApplication(@PathVariable("jobApplicationID") long jobApplicationID){
		log.debug("Creating JobApplication");
		jobApplication=jobApplicationDAO.getJobApplicationById(jobApplicationID);
		boolean valid=jobApplicationDAO.removeJobApplication(jobApplication);
		if(valid){
			log.debug("JobApplication removed");
			return "Success";
		}
		else{
			log.debug("JobApplication not removed");
			return "Error";
		}
	}

	@PostMapping("/updateJobApplication")
	public ResponseEntity<List<Job>> updateJobApplication(@RequestBody JobApplication updateJobApplication){
		log.debug("Creating JobApplication");
		boolean valid=jobApplicationDAO.updateJobApplication(updateJobApplication);
		if(valid){
			log.debug("JobApplication updated");
			return new ResponseEntity<List<Job>>(jobDAO.getAllJobs(),HttpStatus.OK);
		}
		else{
			log.debug("JobApplication not updated");
			return null;
		}
	}
	
	@GetMapping("/getJobApplicationById/{jobApplicationID}")
	public ResponseEntity<JobApplication> getJobApplicationById(@PathVariable("jobApplicationID") long jobApplicationID){
		log.debug("Getting JobApplication with id:"+jobApplicationID);
		jobApplication=jobApplicationDAO.getJobApplicationById(jobApplicationID);
		if(jobApplication!=null){
			log.debug("JobApplication acquired");
			return new ResponseEntity<JobApplication>(jobApplication,HttpStatus.OK);
		}
		else{
			log.debug("JobApplication not acquired");
			return null;
		}
	}
	
	@GetMapping("/getAllJobApplication")
	public ResponseEntity<List<JobApplication>> getAllJobApplication(){
		log.debug("Getting all JobApplication");
		return new ResponseEntity<List<JobApplication>>(jobApplicationDAO.getAllJobApplication(),HttpStatus.OK);
	}
	
	@GetMapping("/rejectapplication/{jobAppId}")
	public ResponseEntity<List<JobApplication>> reject(@PathVariable("jobAppId") long jobAppID){
		JobApplication jobApp=jobApplicationDAO.getJobApplicationById(jobAppID);
		jobApp.setApplicationStatus("Rejected");
		boolean valid=jobApplicationDAO.updateJobApplication(jobApp);
		if(valid){
			return new ResponseEntity<List<JobApplication>>(jobApplicationDAO.getAllJobApplication(),HttpStatus.OK);
		}
		else{
			return null;
		}
	}
	
	@GetMapping("/acceptapplication/{jobAppId}")
	public ResponseEntity<List<JobApplication>> accept(@PathVariable("jobAppId") long jobAppID){
		JobApplication jobApp=jobApplicationDAO.getJobApplicationById(jobAppID);
		jobApp.setApplicationStatus("Selected");
		boolean valid=jobApplicationDAO.updateJobApplication(jobApp);
		if(valid){
			return new ResponseEntity<List<JobApplication>>(jobApplicationDAO.getAllJobApplication(),HttpStatus.OK);
		}
		else{
			return null;
		}
	}
	
	@GetMapping("/getUserApplications/{userID}")
	public ResponseEntity<List<JobApplication>> getApplicationUser(@PathVariable("userID") String userID){
		return new ResponseEntity<List<JobApplication>>(jobApplicationDAO.getUserApplications(userID),HttpStatus.OK);
	}

}
