package com.jpro.studentsmeetbackend.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.jpro.studentsmeetbackend.dao.JobApplicationDAO;
import com.jpro.studentsmeetbackend.model.JobApplication;

@Repository("jobApplicationDAO")
@Transactional
public class JobApplicationDAOImpl implements JobApplicationDAO {
	
	private static final Logger log=LoggerFactory.getLogger(JobApplicationDAOImpl.class);
	
	private SessionFactory sessionFactory;
	
	JobApplicationDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}


	public boolean createJobApplication(JobApplication jobApplication) {
		try{
			jobApplication.setApplicationStatus("Registered");
			sessionFactory.getCurrentSession().save(jobApplication);
			return true;
		}
		catch(Exception e){
			System.err.println(e);
			return false;
		}
	}

	public boolean removeJobApplication(JobApplication jobApplication) {
		try{
			sessionFactory.getCurrentSession().delete(jobApplication);
			return true;
		}
		catch(Exception e){
			System.err.println(e);
			return false;
		}
	}

	public boolean updateJobApplication(JobApplication jobApplication) {
		try{
			sessionFactory.getCurrentSession().update(jobApplication);
			return true;
		}
		catch(Exception e){
			System.err.println(e);
			return false;
		}
	}

	public JobApplication getJobApplicationById(long jobApplicationId) {
		return sessionFactory.getCurrentSession().get(JobApplication.class, jobApplicationId);
	}

	public List<JobApplication> getAllJobApplication() {
		return sessionFactory.getCurrentSession().createQuery("from JobApplication").list();
	}

	public List<JobApplication> getUserApplications(String userID){
		return sessionFactory.getCurrentSession().createQuery("from JobApplication where jobApplicantId='"+userID+"'").list();
	}
}
