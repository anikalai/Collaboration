package com.jpro.studentsmeetbackend.dao;

import java.util.List;
import com.jpro.studentsmeetbackend.model.JobApplication;

public interface JobApplicationDAO {
	
	public boolean createJobApplication(JobApplication jobApplication);
	
	public boolean removeJobApplication(JobApplication jobApplication);
	
	public boolean updateJobApplication(JobApplication jobApplication);
	
	public JobApplication getJobApplicationById(long jobApplicationId);
	
	public List<JobApplication> getAllJobApplication();

	public List<JobApplication> getUserApplications(String userID);
}
