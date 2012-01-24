package com.tunisiana.tutorials.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "AUTHORITIES")
public class Authority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1348884670244154024L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Auth_seq")
	@javax.persistence.SequenceGenerator(name = "Auth_seq", sequenceName = "Auth_seq", initialValue = 1, allocationSize = 1)
	private Integer id;

	@Column(unique = true, nullable = false, updatable = false, name = "role")
	private String role;
	
	@OneToMany(fetch = FetchType.EAGER, cascade={javax.persistence.CascadeType.ALL}, mappedBy = "authority") 	
	@Cascade(value= CascadeType.DELETE_ORPHAN)	
	private Set<UserAuthority> userAuthorities = new HashSet<UserAuthority>();

	public Authority() {
		
	}
	
	public Authority(Integer id) {
		this.id = id;
	}

	public Authority(String role) {
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuthority() {
		return role;
	}

	public String toString() {
		return this.role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public Set<UserAuthority> getUserAuthorities() {
		return userAuthorities;
	}
	
	public void setUserAuthorities(Set<UserAuthority> userAuthorities) {
		this.userAuthorities = userAuthorities;
	}

}
