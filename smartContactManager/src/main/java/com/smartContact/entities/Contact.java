package com.smartContact.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cid;
	private String Name;
	@Column(name = "nick_name")
	private String nick_name;
	@Column(length = 1000)
	private String Description;
	private String Work;
	private String Email;
	@Column(unique = true)
	private String Phone;
	private String image;
	
	@ManyToOne
	private User user;
	
	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Contact(int cid, String name, String nickName, String description, String work, String email, String phone,
			String image) {
		super();
		this.cid = cid;
		Name = name;
		nick_name = nickName;
		Description = description;
		Work = work;
		Email = email;
		Phone = phone;
		this.image = image;
	}
	@Override
	public String toString() {
		return "Contact [cid=" + cid + ", Name=" + Name + ", NickName=" + nick_name + ", Description=" + Description
				+ ", Work=" + Work + ", Email=" + Email + ", Phone=" + Phone + ", image=" + image + "]";
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getNickName() {
		return nick_name;
	}
	public void setNickName(String nickName) {
		nick_name = nickName;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getWork() {
		return Work;
	}
	public void setWork(String work) {
		Work = work;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
