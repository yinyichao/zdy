package com.zdy.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "Z_USER")
public class User implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7445061241411845839L;

	@Id	
	@Column(name="id", unique = true, nullable = false, precision = 10, scale = 0)	
	@SequenceGenerator(name="user_seq",allocationSize=1,initialValue=1, sequenceName="SQ_ZUSER_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="user_seq")
    private Long id;

	@Column(length = 20)
	private String username;

	@Column(length = 30)
	private String password;
	@Column(length = 20)
	private String xm;
	@Column(length = 20)
	private Integer qx;
	@Column(length = 20)
	private Integer zt;
	@Column(length = 20)
	private String bm;
	@Column(length = 20)
	private String sj;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public Integer getQx() {
		return qx;
	}

	public void setQx(Integer qx) {
		this.qx = qx;
	}

	public Integer getZt() {
		return zt;
	}

	public void setZt(Integer zt) {
		this.zt = zt;
	}

	public String getBm() {
		return bm;
	}

	public void setBm(String bm) {
		this.bm = bm;
	}

	public String getSj() {
		return sj;
	}

	public void setSj(String sj) {
		this.sj = sj;
	}
	@ManyToMany(mappedBy="userSet",fetch = FetchType.EAGER)
	private Set<Role> roleSet;  
	//表Z_USER放弃维护，userSet是Role类里的属性名，一定要使一方放弃维护  
    public Set<Role> getRoleSet() {  
        return roleSet;  
    }  
    public void setStudentSet(Set<Role> roleSet) {  
        this.roleSet = roleSet;  
    }  
}
