package com.rt.springboot.app.models.entity;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "users")
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, length = 30)
	private String username;

	@Column(length = 60)
	private String password;
	
	@Transient
	private String confirmPassword;

	private Boolean enabled;

	private static final long serialVersionUID = -4639312987403040257L;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private java.util.List<Role> authorities;

	/*----- Getters & Setters -----*/
	public Long getId() { return id; }
	
	public void setId(Long id) { this.id = id; }
	
	public String getUsername() {  return username; }
	
	public void setUsername(String username) { this.username = username; }
	
	public String getPassword() { return password; }
	
	public void setPassword(String password) { this.password = password; }
	
	public String getConfirmPassword() { return confirmPassword; }

	public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }

	public Boolean getEnabled() { return enabled; }
	
	public void setEnabled(Boolean enabled) { this.enabled = enabled; }
	
	public java.util.List<Role> getAuthorities() { return authorities; }
	
	public void setAuthorities(java.util.List<Role> authorities) { this.authorities = authorities; }
}
