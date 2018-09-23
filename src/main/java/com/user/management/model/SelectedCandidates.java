package com.user.management.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="selected_candidates")
public class SelectedCandidates implements Serializable{
	
	private static final long serialVersionUID = -1900599805788350557L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "BIGINT")
    private long selid;
	
	@NotNull
	@JoinColumn(name = "recruitment_requests", referencedColumnName = "reqid")
	@Column(name = "request_id", nullable = false, columnDefinition = "BIGINT")
	private long request_id;
	
	@NotNull
	@JoinColumn(name = "users", referencedColumnName = "userid")
	@Column(name = "user_id", nullable = false, columnDefinition = "BIGINT")
	private long user_id;
	
	@NotNull
	@JoinColumn(name = "interview_shedules", referencedColumnName = "sheid")
	@Column(name = "interview_id", nullable = false, columnDefinition = "BIGINT")
	private long interview_id;
	
	@NotNull
	@Type(type="java.util.Date")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "JOINING_DATE", nullable = false)
    private Date joiningDate;

	public SelectedCandidates() {
		super();
	}

	public SelectedCandidates(long request_id, long user_id, long interview_id, Date joiningDate) {
		super();
		this.request_id = request_id;
		this.user_id = user_id;
		this.interview_id = interview_id;
		this.joiningDate = joiningDate;
	}

	public SelectedCandidates(long selid, long request_id, long user_id, long interview_id, Date joiningDate) {
		super();
		this.selid = selid;
		this.request_id = request_id;
		this.user_id = user_id;
		this.interview_id = interview_id;
		this.joiningDate = joiningDate;
	}

	public long getSelid() {
		return selid;
	}

	public void setSelid(long selid) {
		this.selid = selid;
	}

	public long getRequest_id() {
		return request_id;
	}

	public void setRequest_id(long request_id) {
		this.request_id = request_id;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public long getInterview_id() {
		return interview_id;
	}

	public void setInterview_id(long interview_id) {
		this.interview_id = interview_id;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (interview_id ^ (interview_id >>> 32));
		result = prime * result + ((joiningDate == null) ? 0 : joiningDate.hashCode());
		result = prime * result + (int) (request_id ^ (request_id >>> 32));
		result = prime * result + (int) (selid ^ (selid >>> 32));
		result = prime * result + (int) (user_id ^ (user_id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SelectedCandidates other = (SelectedCandidates) obj;
		if (interview_id != other.interview_id)
			return false;
		if (joiningDate == null) {
			if (other.joiningDate != null)
				return false;
		} else if (!joiningDate.equals(other.joiningDate))
			return false;
		if (request_id != other.request_id)
			return false;
		if (selid != other.selid)
			return false;
		if (user_id != other.user_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SelectedCandidates [selid=" + selid + ", request_id=" + request_id + ", user_id=" + user_id
				+ ", interview_id=" + interview_id + ", joiningDate=" + joiningDate + "]";
	}  
		     
}
