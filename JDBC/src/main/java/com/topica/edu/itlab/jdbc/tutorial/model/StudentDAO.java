package com.topica.edu.itlab.jdbc.tutorial.model;

import java.sql.SQLException;

import com.topica.edu.itlab.jdbc.tutorial.entity.StudentEntity;
import com.topica.edu.itlab.jdbc.tutorial.model.interfaces.IDAO;

public class StudentDAO extends IDAO<StudentEntity>{

	public StudentDAO() throws ClassNotFoundException, SQLException {
		super(StudentEntity.class);
		// TODO Auto-generated constructor stub
	}
	
}
