package com.zdy.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "Z_ROLE")
public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4477964240136525397L;

	@Id	
	@Column(name="id", unique = true, nullable = false, precision = 10, scale = 0)	
	@SequenceGenerator(name="role_seq",allocationSize=1,initialValue=1, sequenceName="SQ_ZROLE_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="role_seq")
    private Long id;
	
	@Column(name="name")	
    private String name;
	@ManyToMany  
    @JoinTable(name="Z_USER_ROLE",joinColumns={@JoinColumn(name="roleid")},inverseJoinColumns={@JoinColumn(name="userid")})
	private Set<User> userSet;  
	@ManyToMany(mappedBy="roleSet",fetch = FetchType.EAGER)
	private Set<Resources> resourcesSet;  
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	  
    //注意：joinColumns是指自己表在关系表（Z_USER_ROLE）中的外键，inverseJoinColumns 是对方表即z_user表在关系表中的外键  
	public Set<User> getUserSet() {
		return userSet;
	}

	public void setUserSet(Set<User> userSet) {
		this.userSet = userSet;
	}
	 
	
	public Set<Resources> getResourcesSet() {
		return resourcesSet;
	}

	public void setResourcesSet(Set<Resources> resourcesSet) {
		this.resourcesSet = resourcesSet;
	} 
}
