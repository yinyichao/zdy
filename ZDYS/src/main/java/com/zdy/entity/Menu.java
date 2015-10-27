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
}
