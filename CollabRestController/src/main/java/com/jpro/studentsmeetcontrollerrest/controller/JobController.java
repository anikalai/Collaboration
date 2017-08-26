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

import com.jpro.studentsmeetbackend.dao.JobDAO;
import com.jpro.studentsmeetbackend.model.Job;

@RestController
public class JobController {
	
private static final Logger log=LoggerFactory.getLogger(JobController.class);
	
	@Autowired
	private Job job;
	
	@Autowired
	private JobDAO jobDAO;
	
	@PostMapping("/createjob")
	public ResponseEntity<List<Job>> createjob(@RequestBody Job newjob){
		log.debug("Creating job");
		boolean valid=jobDAO.createJob(newjob);
		if(valid){
			log.debug("job created");
			return new ResponseEntity<List<Job>>(jobDAO.getAllJobs(),HttpStatus.OK);
		}
		else{
			log.debug("job not created");
			return null;
		}
	}

	@GetMapping("/removejob/{jobID}")
	public ResponseEntity<List<Job>> removejob(@PathVariable("jobID") long jobID){
		log.debug("Creating job");
		job=jobDAO.getJobById(jobID);
		boolean valid=jobDAO.removeJob(job);
		if(valid){
			log.debug("job removed");
			return new ResponseEntity<List<Job>>(jobDAO.getAllJobs(),HttpStatus.OK);
		}
		else{
			log.debug("job not removed");
			return null;
		}
	}

	@PostMapping("/updatejob")
	public ResponseEntity<List<Job>> updatejob(@RequestBody Job updatejob){
		log.debug("Creating job");
		boolean valid=jobDAO.updateJob(updatejob);
		if(valid){
			log.debug("job updated");
			return new ResponseEntity<List<Job>>(jobDAO.getAllJobs(),HttpStatus.OK);
		}
		else{
			log.debug("job not updated");
			return null;
		}
	}
	
	@GetMapping("/getjobById/{jobID}")
	public ResponseEntity<Job> getjobById(@PathVariable("jobID") long jobID){
		log.debug("Getting job with id:"+jobID);
		job=jobDAO.getJobById(jobID);
		if(job!=null){
			log.debug("job acquired");
			return new ResponseEntity<Job>(job,HttpStatus.OK);
		}
		else{
			log.debug("job not acquired");
			return null;
		}
	}
	
	@GetMapping("/getAlljob")
	public ResponseEntity<List<Job>> getAlljob(){
		log.debug("Getting all job");
		return new ResponseEntity<List<Job>>(jobDAO.getAllJobs(),HttpStatus.OK);
	}
	
	@GetMapping("/closejob/{jobID}")
	public ResponseEntity<List<Job>> closeJob(@PathVariable("jobID") long jobId){
		jobDAO.closeJob(jobId);
		return new ResponseEntity<List<Job>>(jobDAO.getAllJobs(),HttpStatus.OK);
	}


}
