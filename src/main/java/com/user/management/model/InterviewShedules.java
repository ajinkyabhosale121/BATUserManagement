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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="interview_shedules")
public class InterviewShedules implements Serializable{
	
	private static final long serialVersionUID = 7908958338791690093L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "BIGINT")
    private long sheid;
	
	@NotNull
	@JoinColumn(name = "recruitment_requests", referencedColumnName = "reqid")
	@Column(name = "request_id", nullable = false, columnDefinition = "BIGINT")
	private long request_id;
	
	@NotNull
	@JoinColumn(name = "users", referencedColumnName = "userid")
	@Column(name = "user_id", nullable = false, columnDefinition = "BIGINT")
	private long user_id;
	
	@Pattern(regexp = "^\\pL+[\\pL\\pZ\\pP]{0,}$", 
			message="Please Enter Valid Name Format! Allowed Chars Ascii/Unicode [A-Z][a-z][Space]!")
	@NotNull
    @Size(min=3, max=200)
	@Column(name = "Name", nullable = false, length=200)
    private String Name;
    
	@NotNull
    @Size(min=8, max=20)
	@Pattern(
			regexp = "((\\d{10})|(0\\d{10})|((\\+\\d{2}|\\d{3})(\\s|-)(\\d{10}))|((\\d{4}|\\d{3})(-|\\s)(\\d{7})))",
			message="No shoud be 9057128909 | 09057128909 | +91 9057128909 | +91-9057128909 | 0217-2301333 | 0217 2301333 | 022-2301333 | 022 2301333"
			)
    @Column(name = "Number", nullable = false, length=20)
    private String Number;
    
	@Email
	@Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])",
	 message= "Emaid ID Format Should be a[*]@b[*].[*]")
	@NotNull
    @Size(min=5, max=100)
    @Column(name = "EmailID", nullable = false, length=100)
    private String EmailID;
    
	@Pattern(regexp = "([A-Za-z0-9'\\\\\\-\\s\\,]*,*)", 
			message="Please Enter Valid Address! Allowed Chars Ascii/Unicode [A-Z][a-z][0-9][\\,-,/,] !")
    @Size(min=2, max=500)
    @Column(name = "Address", nullable = true, length=500)
    private String Address;
    
    @JoinColumn(name = "qualification", referencedColumnName = "id")
    @Column(name = "Qualification_Id", nullable = false, columnDefinition = "BIGINT")
    private long Qualification_Id;	    
	
    //@Future
    @NotNull
	@Type(type="java.util.Date")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Column(name = "interview_datetime", nullable = false)
	private Date interview_datetime;
	
    @NotNull
    @JoinColumn(name = "result_status", referencedColumnName = "id")
	@Column(name = "Result_Status_Id", nullable = false, columnDefinition = "BIGINT")
	private long Result_Status_Id;

	public InterviewShedules() {
		super();
	}

	public InterviewShedules(long request_id, long user_id, String name, String number, String emailID, String address,
			long qualification_Id, Date interview_datetime, long result_Status_Id) {
		super();
		this.request_id = request_id;
		this.user_id = user_id;
		Name = name;
		Number = number;
		EmailID = emailID;
		Address = address;
		Qualification_Id = qualification_Id;
		this.interview_datetime = interview_datetime;
		Result_Status_Id = result_Status_Id;
	}

	public InterviewShedules(long sheid, long request_id, long user_id, String name, String number, String emailID,
			String address, long qualification_Id, Date interview_datetime, long result_Status_Id) {
		super();
		this.sheid = sheid;
		this.request_id = request_id;
		this.user_id = user_id;
		Name = name;
		Number = number;
		EmailID = emailID;
		Address = address;
		Qualification_Id = qualification_Id;
		this.interview_datetime = interview_datetime;
		Result_Status_Id = result_Status_Id;
	}

	public long getSheid() {
		return sheid;
	}

	public void setSheid(long sheid) {
		this.sheid = sheid;
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

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getNumber() {
		return Number;
	}

	public void setNumber(String number) {
		Number = number;
	}

	public String getEmailID() {
		return EmailID;
	}

	public void setEmailID(String emailID) {
		EmailID = emailID;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public long getQualification_Id() {
		return Qualification_Id;
	}

	public void setQualification_Id(long qualification_Id) {
		Qualification_Id = qualification_Id;
	}

	public Date getInterview_datetime() {
		return interview_datetime;
	}

	public void setInterview_datetime(Date interview_datetime) {
		this.interview_datetime = interview_datetime;
	}

	public long getResult_Status_Id() {
		return Result_Status_Id;
	}

	public void setResult_Status_Id(long result_Status_Id) {
		Result_Status_Id = result_Status_Id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Address == null) ? 0 : Address.hashCode());
		result = prime * result + ((EmailID == null) ? 0 : EmailID.hashCode());
		result = prime * result + ((Name == null) ? 0 : Name.hashCode());
		result = prime * result + ((Number == null) ? 0 : Number.hashCode());
		result = prime * result + (int) (Qualification_Id ^ (Qualification_Id >>> 32));
		result = prime * result + (int) (Result_Status_Id ^ (Result_Status_Id >>> 32));
		result = prime * result + ((interview_datetime == null) ? 0 : interview_datetime.hashCode());
		result = prime * result + (int) (request_id ^ (request_id >>> 32));
		result = prime * result + (int) (sheid ^ (sheid >>> 32));
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
		InterviewShedules other = (InterviewShedules) obj;
		if (Address == null) {
			if (other.Address != null)
				return false;
		} else if (!Address.equals(other.Address))
			return false;
		if (EmailID == null) {
			if (other.EmailID != null)
				return false;
		} else if (!EmailID.equals(other.EmailID))
			return false;
		if (Name == null) {
			if (other.Name != null)
				return false;
		} else if (!Name.equals(other.Name))
			return false;
		if (Number == null) {
			if (other.Number != null)
				return false;
		} else if (!Number.equals(other.Number))
			return false;
		if (Qualification_Id != other.Qualification_Id)
			return false;
		if (Result_Status_Id != other.Result_Status_Id)
			return false;
		if (interview_datetime == null) {
			if (other.interview_datetime != null)
				return false;
		} else if (!interview_datetime.equals(other.interview_datetime))
			return false;
		if (request_id != other.request_id)
			return false;
		if (sheid != other.sheid)
			return false;
		if (user_id != other.user_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\"InterviewShedules\":{\"sheid\":\"" + sheid + "\", \"request_id\":\"" + request_id + "\", \"user_id\":\"" + user_id + "\", \"Name\":\""
				+ Name + "\", \"Number\":\"" + Number + "\", \"EmailID\":\"" + EmailID + "\", \"Address\":\"" + Address + "\", \"Qualification_Id\":\""
				+ Qualification_Id + "\", \"interview_datetime\":\"" + interview_datetime + "\", \"Result_Status_Id\":\""
				+ Result_Status_Id + "\"}";
	}
}
