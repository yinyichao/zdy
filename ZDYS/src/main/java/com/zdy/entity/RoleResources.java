package com.zdy.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "Z_ROLE_RESOURCE")
public class RoleResources implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -243870878884361672L;
	@Id	
	@Column(name="id", unique = true, nullable = false, precision = 10, scale = 0)	
	@SequenceGenerator(name="my_seq",allocationSize=1,initialValue=1, sequenceName="SQ_ROLE_RESOURCE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="my_seq")
    private Long id;
	
	@Column(name="roleid")	

    private Long roleid;

	@Column(name="resourceid")
	private Long resourceid;

	public Long getRoleid() {
		return roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}

	public Long getResourceid() {
		return resourceid;
	}

	public void setResourceid(Long resourceid) {
		this.resourceid = resourceid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
}
