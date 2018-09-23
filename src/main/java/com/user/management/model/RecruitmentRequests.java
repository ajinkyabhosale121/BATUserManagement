package com.user.management.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="recruitment_requests")
public class RecruitmentRequests implements Serializable{
	
	private static final long serialVersionUID = 4986644192644172749L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "BIGINT")
    private long reqid;
	
	@NotNull
    @Size(min=1, max=200)
	@Column(name = "Language")
    private String Language;
	
	@NotNull
	@Min(0)
	@Max(100)
	@Column(name = "Years_Of_Experience", columnDefinition = "BIGINT")
    private String Years_Of_Experience;
	
	
    @Size(max=100)
	@Column(name = "Job_Desc")
    private String Job_Desc;
	
	@NotNull
	@Min(1)
	@Max(1000)
	@Column(name = "Vacancies", columnDefinition = "BIGINT")
    private String Vacancies;
		
	public RecruitmentRequests() {
		super();
	}

	public RecruitmentRequests(String language, String years_Of_Experience, String job_Desc, String vacancies) {
		super();
		Language = language;
		Years_Of_Experience = years_Of_Experience;
		Job_Desc = job_Desc;
		Vacancies = vacancies;
	}

	public RecruitmentRequests(long reqid, String language, String years_Of_Experience, String job_Desc, String vacancies) {
		super();
		this.reqid = reqid;
		Language = language;
		Years_Of_Experience = years_Of_Experience;
		Job_Desc = job_Desc;
		Vacancies = vacancies;
	}

	public long getReqid() {
		return reqid;
	}

	public void setReqid(long reqid) {
		this.reqid = reqid;
	}

	public String getLanguage() {
		return Language;
	}

	public void setLanguage(String language) {
		Language = language;
	}

	public String getYears_Of_Experience() {
		return Years_Of_Experience;
	}

	public void setYears_Of_Experience(String years_Of_Experience) {
		Years_Of_Experience = years_Of_Experience;
	}

	public String getJob_Desc() {
		return Job_Desc;
	}

	public void setJob_Desc(String job_Desc) {
		Job_Desc = job_Desc;
	}

	public String getVacancies() {
		return Vacancies;
	}

	public void setVacancies(String vacancies) {
		Vacancies = vacancies;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Job_Desc == null) ? 0 : Job_Desc.hashCode());
		result = prime * result + ((Language == null) ? 0 : Language.hashCode());
		result = prime * result + ((Vacancies == null) ? 0 : Vacancies.hashCode());
		result = prime * result + ((Years_Of_Experience == null) ? 0 : Years_Of_Experience.hashCode());
		result = prime * result + (int) (reqid ^ (reqid >>> 32));
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
		RecruitmentRequests other = (RecruitmentRequests) obj;
		if (Job_Desc == null) {
			if (other.Job_Desc != null)
				return false;
		} else if (!Job_Desc.equals(other.Job_Desc))
			return false;
		if (Language == null) {
			if (other.Language != null)
				return false;
		} else if (!Language.equals(other.Language))
			return false;
		if (Vacancies == null) {
			if (other.Vacancies != null)
				return false;
		} else if (!Vacancies.equals(other.Vacancies))
			return false;
		if (Years_Of_Experience == null) {
			if (other.Years_Of_Experience != null)
				return false;
		} else if (!Years_Of_Experience.equals(other.Years_Of_Experience))
			return false;
		if (reqid != other.reqid)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RecruitmentRequests [reqid=" + reqid + ", Language=" + Language + ", Years_Of_Experience="
				+ Years_Of_Experience + ", Job_Desc=" + Job_Desc + ", Vacancies=" + Vacancies + "]";
	}
		
}
