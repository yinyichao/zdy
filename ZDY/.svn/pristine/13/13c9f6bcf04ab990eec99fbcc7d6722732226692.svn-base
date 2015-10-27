package com.zdy.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "Z_Resource")
public class Resources implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7425594037527886435L;

	@Id	
	@Column(name="id", unique = true, nullable = false, precision = 10, scale = 0)	
	@SequenceGenerator(name="my_seq",allocationSize=1,initialValue=1, sequenceName="SQ_ZRESOURCE_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="my_seq")
    private Long id;

	@Column(length = 20)
	private String name;

	@Column(length = 40)
	private String url;
	
	@Column(length = 40)
	private Long pid;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public static List<Resources> pojoList2DtoLists(List<Object[]> pojoList) {
		List<Resources> dtoList = new ArrayList<Resources>();
		Resources dto = null;
		for (Object[] object : pojoList) {
			dto = pojo2Dtos(object);
			dtoList.add(dto);
		}
		return dtoList;
	}
	public static Resources pojo2Dtos(Object[] pojo) {
		Resources dto = new Resources();
		dto.setId(Long.parseLong(pojo[0].toString()));
		dto.setName((String) pojo[1]);
		dto.setUrl((String) pojo[2]);
		dto.setPid(Long.parseLong(pojo[3].toString()));
		return dto;
	}
}
