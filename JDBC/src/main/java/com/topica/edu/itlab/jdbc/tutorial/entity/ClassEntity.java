package com.topica.edu.itlab.jdbc.tutorial.entity;

import java.util.ArrayList;
import java.util.List;

import com.topica.edu.itlab.jdbc.tutorial.annotation.Column;
import com.topica.edu.itlab.jdbc.tutorial.annotation.OneToMany;
import com.topica.edu.itlab.jdbc.tutorial.annotation.Table;
import com.topica.edu.itlab.jdbc.tutorial.model.StudentDAO;
/**
 * extended Entity abstract class
 * @author Hi
 *
 */
@Table(name = "class")
public class ClassEntity extends Entity{
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "student")
	private List<StudentEntity> listStudent =new ArrayList<StudentEntity>();

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

	public List<StudentEntity> getListStudent() {
		return this.listStudent;
	}
	
	public List<StudentEntity> getListStudentLazyLoad() throws Exception{
		if (this.listStudent.size()==0) {
			 this.setListStudent();
		}
		return listStudent;
	}

	public void setListStudent(List<StudentEntity> listStudent)  {
		this.listStudent =listStudent;
	}
	public void addListStudent(StudentEntity student)  {
		this.listStudent.add(student);
	}
	
	/**
	 * set list student with condition class id	
	 * @throws Exception
	 */
	public void setListStudent()  throws Exception {
		StudentDAO dao = new StudentDAO();
		this.listStudent = dao.setCondition(this.getClass().getAnnotation(Table.class).name()+"_id", "=", String.valueOf(this.id)).get();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.id +"---"+this.name;
	}
}
