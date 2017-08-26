package com.jpro.studentsmeetbackend.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


@Entity
@Component
@Table(name="STMT_JOBAPPLICATION")
public class JobApplication extends BaseDomain {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long jobApplicationId;
	
	private String jobApplicantId;
	
	private long jobAppliedId;
	
	private String applicantDetails;
	
	/*@Embedded
	private Qualification applicantQualification;
	*/
	private String applicationStatus;

	public long getJobApplicationId() {
		return jobApplicationId;
	}

	public void setJobApplicationId(long jobApplicationId) {
		this.jobApplicationId = jobApplicationId;
	}

	public String getJobApplicantId() {
		return jobApplicantId;
	}

	public void setJobApplicantId(String jobApplicantId) {
		this.jobApplicantId = jobApplicantId;
	}

	public long getJobAppliedId() {
		return jobAppliedId;
	}

	public void setJobAppliedId(long jobAppliedId) {
		this.jobAppliedId = jobAppliedId;
	}

	/*public Qualification getApplicantQualification() {
		return applicantQualification;
	}

	public void setApplicantQualification(Qualification applicantQualification) {
		this.applicantQualification = applicantQualification;
	}*/

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public String getApplicantDetails() {
		return applicantDetails;
	}

	public void setApplicantDetails(String applicantDetails) {
		this.applicantDetails = applicantDetails;
	}

}
