package com.topica.edu.itlab.jdbc.tutorial.main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.topica.edu.itlab.jdbc.tutorial.connection.Connect;
import com.topica.edu.itlab.jdbc.tutorial.entity.ClassEntity;
import com.topica.edu.itlab.jdbc.tutorial.entity.StudentEntity;
import com.topica.edu.itlab.jdbc.tutorial.model.ClassDAO;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ex02();
	}
	
	/**
	 * method for lazy loading
	 * @throws Exception
	 */
	
	static void ex01() throws Exception {
		List<ClassEntity> classes = new ArrayList<ClassEntity>();
		ClassDAO classDao = new ClassDAO();
		classes= classDao.get();
		for (int i = 0; i < classes.size(); i++) {
			System.out.print("Class: ");
			System.out.println(classes.get(i).toString());
			List<StudentEntity> students = classes.get(i).getListStudentLazyLoad();
			System.out.println("Have list student");
			for (int j = 0; j < students.size(); j++) {
				System.out.println(students.get(j).toString());
			}
		}
	}
	
	/**
	 * method for eager loading
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	static void ex02() throws ClassNotFoundException, SQLException, IllegalArgumentException, IllegalAccessException {
		Map<Long, ClassEntity> classes= new HashMap<Long, ClassEntity>();
		String query = "Select c.id as class_id,c.name as class_name,s.id as student_id, s.name as student_name from Class c, Student s where c.id = s.class_id";
		Statement stmt = Connect.getIntanse().getConnection().createStatement();
		ResultSet res = stmt.executeQuery(query);
//		process result of mysql
		while (res.next()) {
			ClassEntity clazz = new ClassEntity();
			StudentEntity students = new StudentEntity();
//			parse to object with prefix because result have multi table data
			clazz.parseToObject(res, true);
			students.parseToObject(res, true);
			if (!classes.containsKey(clazz.getId())) {
				classes.put(clazz.getId(), clazz);
			}
			classes.get(clazz.getId()).addListStudent(students);
		}
		for (Map.Entry<Long, ClassEntity> entry : classes.entrySet()) {
			List<StudentEntity> students = entry.getValue().getListStudent();
			System.out.print("Class: ");
			System.out.println(entry.getValue().toString());
			System.out.println("Have list student");
			for (int j = 0; j < students.size(); j++) {
				System.out.println(students.get(j).toString());
			}
		}
	}
}
