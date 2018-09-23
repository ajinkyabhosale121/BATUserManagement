package com.user.management.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="qualification")
public class Qualification implements Serializable{
	
	private static final long serialVersionUID = -5376385085731989593L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "BIGINT")
    private long id;
	
	@NotNull
    @Size(min=2, max=50)
	@Column(name = "Qualification", nullable = false, length=200)
    private String Qualification;

	public Qualification() {
		super();
	}

	public Qualification(String qualification) {
		super();
		Qualification = qualification;
	}

	public Qualification(long id, String qualification) {
		super();
		this.id = id;
		Qualification = qualification;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getQualification() {
		return Qualification;
	}

	public void setQualification(String qualification) {
		this.Qualification = qualification;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Qualification == null) ? 0 : Qualification.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Qualification other = (Qualification) obj;
		if (Qualification == null) {
			if (other.Qualification != null)
				return false;
		} else if (!Qualification.equals(other.Qualification))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Qualification [id=" + id + ", Qualification=" + Qualification + "]";
	}
	
}
