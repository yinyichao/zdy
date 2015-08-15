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
@Table(name = "Z_USER")
public class User implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7445061241411845839L;

	@Id	
	@Column(name="id", unique = true, nullable = false, precision = 10, scale = 0)	
	@SequenceGenerator(name="my_seq",allocationSize=1,initialValue=1, sequenceName="SQ_ZUSER_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="my_seq")
    private Long id;

	@Column(length = 20)
	private String username;

	@Column(length = 30)
	private String mm;
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

	public String getMm() {
		return mm;
	}

	public void setMm(String mm) {
		this.mm = mm;
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

}
