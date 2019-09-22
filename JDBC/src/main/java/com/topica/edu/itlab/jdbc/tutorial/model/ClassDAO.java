package com.topica.edu.itlab.jdbc.tutorial.model;

import java.sql.SQLException;

import com.topica.edu.itlab.jdbc.tutorial.entity.ClassEntity;
import com.topica.edu.itlab.jdbc.tutorial.model.interfaces.IDAO;

public class ClassDAO extends IDAO<ClassEntity>{

	public ClassDAO() throws ClassNotFoundException, SQLException {
		super(ClassEntity.class);
		// TODO Auto-generated constructor stub
	}

}
