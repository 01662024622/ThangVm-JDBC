package com.topica.edu.itlab.jdbc.tutorial.entity;

import com.topica.edu.itlab.jdbc.tutorial.annotation.Column;
import com.topica.edu.itlab.jdbc.tutorial.annotation.Table;
/**
 * extended Entity abstract class
 * @author Hi
 *
 */
@Table(name = "student")
public class StudentEntity extends Entity {
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
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
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.id+"---"+this.name;
	}
}
