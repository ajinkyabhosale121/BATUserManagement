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
@Table(name="user_role")
public class UserRole implements Serializable{
	
	private static final long serialVersionUID = -2056008475032441922L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
 	@Column(columnDefinition = "BIGINT")
    private long id;
	
	@NotNull
    @Size(min=3, max=100)
    @Column(name = "Role", nullable = false, length=100)
    private String Role;
    
	public UserRole() {
		super();
	}

	public UserRole(String role) {
		super();
		Role = role;
	}

	public UserRole(long id, String role) {
		super();
		this.id = id;
		Role = role;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		this.Role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Role == null) ? 0 : Role.hashCode());
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
		UserRole other = (UserRole) obj;
		if (Role == null) {
			if (other.Role != null)
				return false;
		} else if (!Role.equals(other.Role))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserRole [id=" + id + ", Role=" + Role + "]";
	}
}
