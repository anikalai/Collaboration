package com.jpro.studentsmeetbackend.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.jpro.studentsmeetbackend.dao.JobDAO;
import com.jpro.studentsmeetbackend.model.Job;

@Repository("jobDAO")
@Transactional
public class JobDAOImpl implements JobDAO {
	
	private static final Logger log=LoggerFactory.getLogger(JobDAOImpl.class);
	
	private SessionFactory sessionFactory;
	
	JobDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}

	public boolean createJob(Job job) {
		try{
			sessionFactory.getCurrentSession().save(job);
			return true;
		}
		catch(Exception e){
			System.err.println(e);
			return false;
		}
	}

	public boolean removeJob(Job job) {
		try{
			sessionFactory.getCurrentSession().delete(job);
			return true;
		}
		catch(Exception e){
			System.err.println(e);
			return false;
		}
	}

	public boolean updateJob(Job job) {
		try{
			sessionFactory.getCurrentSession().update(job);
			return true;
		}
		catch(Exception e){
			System.err.println(e);
			return false;
		}
	}

	public Job getJobById(long jobId) {
		return sessionFactory.getCurrentSession().get(Job.class, jobId);
	}

	public List<Job> getAllJobs() {
		return sessionFactory.getCurrentSession().createQuery("from Job").list();
	}
	
	public void closeJob(long jobId){
		Job job=getJobById(jobId);
		job.setJobStatus("Closed");
		updateJob(job);
	}

}
