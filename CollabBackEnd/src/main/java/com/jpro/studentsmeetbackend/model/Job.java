package com.jpro.studentsmeetbackend.model;

//import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

//import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Component
@Table(name="STMT_JOB")
public class Job extends BaseDomain{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long jobId;
	
	@Size(min=4,max=25)
	private String jobName;
	
	private String jobDescription;
	
	/*@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastDateToApply;*/
	
	/*@Embedded
	private Qualification requiredQualification;*/
	
	private float jobSalary;
	
	private String jobStatus;

	public long getJobId() {
		return jobId;
	}

	public void setJobId(long jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	/*public Date getLastDateToApply() {
		return lastDateToApply;
	}

	public void setLastDateToApply(Date lastDateToApply) {
		this.lastDateToApply = lastDateToApply;
	}*/

	/*public Qualification getRequiredQualification() {
		return requiredQualification;
	}

	public void setRequiredQualification(Qualification requiredQualification) {
		this.requiredQualification = requiredQualification;
	}*/

	public float getJobSalary() {
		return jobSalary;
	}

	public void setJobSalary(float jobSalary) {
		this.jobSalary = jobSalary;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
	
}
