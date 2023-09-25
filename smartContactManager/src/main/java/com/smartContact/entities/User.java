package com.smartContact.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;
	
	@NotBlank(message = "Name Field is required")
	@Size(min = 2,max = 20,message = "Name must be between 2 - 20 character")
	private String Name;
	
	@Pattern(regexp = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$",message = "invalid email address")
	@Column(unique = true)
	private String Email;
	
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",message ="password must contain Upper case letter lower case letter number and a symbol" )
	private String Password;
	
	private String role;
	
	private boolean enabled;
	
	private String imageUrl;
	
	@Column(length = 500)
	@Size(min=2,max = 500,message = "between 2 to 500 char")
	private String About;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Contact> contact=new ArrayList<>();
	
	
	@Override
	public String toString() {
		return "User [Id=" + Id + ", Name=" + Name + ", Email=" + Email + ", Password=" + Password + ", role=" + role
				+ ", enabled=" + enabled + ", imageUrl=" + imageUrl + ", About=" + About + "]";
	}
	public User(String name,String email,String about) {
		this.Name=name;
		this.Email=email;
		this.About=about;
	}
	public User(int id, String name, String email, String password, String role, boolean enabled, String imageUrl,
			String about) {
		super();
		Id = id;
		Name = name;
		Email = email;
		Password = password;
		this.role = role;
		this.enabled = enabled;
		this.imageUrl = imageUrl;
		About = about;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getAbout() {
		return About;
	}

	public void setAbout(String about) {
		About = about;
	}

	public List<Contact> getContact() {
		return contact;
	}

	public void setContact(List<Contact> contact) {
		this.contact = contact;
	}	
	
	
	
}
