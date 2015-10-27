package com.zdy.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Z_Menu")
public class Menu implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -5155551737664317803L;

	@Id	
	@Column(name="id")	
    private Long id;

	@Column(length = 20)
	private String name;

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

	public static List<Menu> pojoList2DtoLists(List<Object[]> pojoList) {
		List<Menu> dtoList = new ArrayList<Menu>();
		Menu dto = null;
		for (Object[] object : pojoList) {
			dto = pojo2Dtos(object);
			dtoList.add(dto);
		}
		return dtoList;
	}
	public static Menu pojo2Dtos(Object[] pojo) {
		Menu dto = new Menu();
		dto.setId(Long.parseLong(pojo[0].toString()));
		dto.setName((String) pojo[1]);
		return dto;
	}
}
