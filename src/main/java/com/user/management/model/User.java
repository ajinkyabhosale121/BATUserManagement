package com.user.management.model;


import java.io.Serializable;

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

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name="users")
public class User implements Serializable{

	private static final long serialVersionUID = -6207231409958503655L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "BIGINT")
	private long userid;

	@Pattern(regexp = "^\\pL+[\\pL\\pZ\\pP]{0,}$", 
			message="Please Enter Valid Name Format! Allowed Chars Ascii/Unicode [A-Z][a-z][Space]!")
	@NotNull
	@Size(min=3, max=200)
	@Column(name = "Name", nullable = false, length=200)
	private String Name;
	
	@Pattern(
			regexp = "((\\d{10})|(0\\d{10})|((\\+\\d{2}|\\d{3})(\\s|-)(\\d{10}))|((\\d{4}|\\d{3})(-|\\s)(\\d{7})))",
			message="No shoud be 9057128909 | 09057128909 | +91 9057128909 | +91-9057128909 | 0217-2301333 | 0217 2301333 | 022-2301333 | 022 2301333"
			)
	@NotNull
    @Size(min=8, max=20)
    @Column(name = "Number", nullable = false, length=20)
    private String Number;
	
	@Pattern(regexp = "([A-Za-z0-9'\\\\\\-\\s\\,]*,*)", 
			message="Please Enter Valid Address! Allowed Chars Ascii/Unicode [A-Z][a-z][0-9][\\,-,/,] !")
	@Size(min=2, max=500)
	@Column(name = "Address", nullable = true, length=500)
	private String Address;

	@NotNull
	@Email
	@Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])",
			 message= "Emaid ID Format Should be a[*]@b[*].[*]")
	@Size(min=5, max=100)
	@Column(name = "EmailID", nullable = false, length=100)
	private String EmailID;
	
	@NotNull
	@JoinColumn(name = "qualification", referencedColumnName = "id")
	@Column(columnDefinition = "BIGINT")
	private long Qualification_ID;
	
	@Size(min=2, max=50)
	@Pattern(regexp = "([A-Za-z0-9-@$&*/\\\\\\s]{2,30}$)",
			message="Not Valid! Allowed Char [A-Z,a-z,0-9,-,@,$,&,*,/,\\,Space]!")
	@Column(name = "Designation", nullable = true, length=50)
	private String Designation;
	
	@Pattern(regexp = "([A-Za-z0-9\\-\\!\\@#\\$%\\^&*]{5,20}$)",
			message="Username not Valid! Username Valid Char: [a-z][A-z][0-9][-!@#$%^&*]!")
	@NotNull
	@Size(min=5, max=20)
	@Column(name = "Username", nullable = false, length=20)
	private String Username;
	
	@Pattern(regexp = "([A-Za-z0-9\\-\\!\\@#\\$%\\^&*\\=]{5,30}$)",
			 message="Password not Valid! Password Valid Char: [a-z][A-z][0-9][-!@#$%^&*]!")
	@NotNull
	@Size(min=5, max=30)
	@Column(name = "Password", nullable = false, length=30)
	private String Password;
	
	@NotNull
	@JoinColumn(name = "user_role", referencedColumnName = "id")
	@Column(name = "Role_Id", columnDefinition = "BIGINT")
	private long role_id;
	
	public User() {
		super();
	}
	
	public User(String name, String number, String address, String emailID, long qualification_ID, String designation,
			String username, String password, long role_id) {
		super();
		Name = name;
		Number = number;
		Address = address;
		EmailID = emailID;
		Qualification_ID = qualification_ID;
		Designation = designation;
		Username = username;
		Password = password;
		this.role_id = role_id;
	}

	public User(long userid, String name, String number, String address, String emailID, long qualification_ID,
			String designation, String username, String password, long role_id) {
		super();
		this.userid = userid;
		Name = name;
		Number = number;
		Address = address;
		EmailID = emailID;
		Qualification_ID = qualification_ID;
		Designation = designation;
		Username = username;
		Password = password;
		this.role_id = role_id;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
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

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getEmailID() {
		return EmailID;
	}

	public void setEmailID(String emailID) {
		EmailID = emailID;
	}

	public long getQualification_ID() {
		return Qualification_ID;
	}

	public void setQualification_ID(long qualification_ID) {
		Qualification_ID = qualification_ID;
	}

	public String getDesignation() {
		return Designation;
	}

	public void setDesignation(String designation) {
		Designation = designation;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public long getRole_id() {
		return role_id;
	}

	public void setRole_id(long role_id) {
		this.role_id = role_id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Address == null) ? 0 : Address.hashCode());
		result = prime * result + ((Designation == null) ? 0 : Designation.hashCode());
		result = prime * result + ((EmailID == null) ? 0 : EmailID.hashCode());
		result = prime * result + ((Name == null) ? 0 : Name.hashCode());
		result = prime * result + ((Number == null) ? 0 : Number.hashCode());
		result = prime * result + ((Password == null) ? 0 : Password.hashCode());
		result = prime * result + (int) (Qualification_ID ^ (Qualification_ID >>> 32));
		result = prime * result + ((Username == null) ? 0 : Username.hashCode());
		result = prime * result + (int) (role_id ^ (role_id >>> 32));
		result = prime * result + (int) (userid ^ (userid >>> 32));
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
		User other = (User) obj;
		if (Address == null) {
			if (other.Address != null)
				return false;
		} else if (!Address.equals(other.Address))
			return false;
		if (Designation == null) {
			if (other.Designation != null)
				return false;
		} else if (!Designation.equals(other.Designation))
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
		if (Password == null) {
			if (other.Password != null)
				return false;
		} else if (!Password.equals(other.Password))
			return false;
		if (Qualification_ID != other.Qualification_ID)
			return false;
		if (Username == null) {
			if (other.Username != null)
				return false;
		} else if (!Username.equals(other.Username))
			return false;
		if (role_id != other.role_id)
			return false;
		if (userid != other.userid)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", Name=" + Name + ", Number=" + Number + ", Address=" + Address
				+ ", EmailID=" + EmailID + ", Qualification_ID=" + Qualification_ID + ", Designation=" + Designation
				+ ", Username=" + Username + ", Password=" + Password + ", role_id=" + role_id + "]";
	}
}
