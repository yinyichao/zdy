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
@Table(name = "Z_USER_ROLE")
public class UserRole implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -966363591870364820L;
	@Id	
	@Column(name="id", unique = true, nullable = false, precision = 10, scale = 0)	
	@SequenceGenerator(name="my_seq",allocationSize=1,initialValue=1, sequenceName="SQ_USER_ROLE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="my_seq")
    private Long id;
	@Column(name="userid")
    private Long userid;

	@Column(name="roleid")
	private Long roleid;

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getRoleid() {
		return roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

}
