package com.in28mintues.restfulwebservices.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

//import io.swagger.annotations.ApiModel;

//@ApiModel(description = "all details about the user.")
@Entity(name="account")
public class Account{

	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@Size(min=2, message = "--> name should have atleast 2 charcters")
	@Column(name="name")
	private String name;
	
//	@Past
	@Column(name="birth_date")
	private String birthDate;
	
	@OneToMany(mappedBy = "account")
	private List<Post> posts;
	
	public Account() {
		
	}
	
	public Account(Integer id, String name, String birthDate) {
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	
}
