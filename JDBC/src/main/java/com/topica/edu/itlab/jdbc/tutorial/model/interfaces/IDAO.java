package com.topica.edu.itlab.jdbc.tutorial.model.interfaces;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.topica.edu.itlab.jdbc.tutorial.annotation.Table;
import com.topica.edu.itlab.jdbc.tutorial.connection.Connect;
import com.topica.edu.itlab.jdbc.tutorial.entity.Entity;

public abstract class IDAO<T extends Entity> {
	
	private Connection conn;
	private String lent="";
	private String ofset="";
	private Set<String> condition=new HashSet<String>();
	private String table="";
	private Class<T> classes;
	public IDAO(Class<T> clazz) throws ClassNotFoundException, SQLException {
		this.classes=clazz;
		this.table = clazz.getAnnotation(Table.class).name();
		this.conn= Connect.getIntanse().getConnection();
	}
	
	
	
	/**
	 * get student after set condition or ofset or limmit
	 * @param Class generic
	 * @return list generic
	 * @throws Exception
	 */
	public List<T> get() throws Exception {
		List<T> lists = new ArrayList<T>();
		String query = String.join(" ", "SELECT * FROM", table, this.getCondition(), this.ofset, this.lent);
		Statement stmt = this.conn.createStatement();
		ResultSet res = stmt.executeQuery(query);
		while (res.next()) {
			T object = createGenericClass();
			object.parseToObject(res,false);
			lists.add(object);
		}
		this.clear();
		return lists;
	}

	/**
	 * set condition for clause
	 * @param col
	 * @param cond
	 * @param id
	 * @return
	 */
	
	public IDAO<T> setCondition(String col,String cond,String id){
		this.condition.add(String.join("", col,cond,id));
		return this;
	};
	
	/**
	 * set ofset for clause
	 * @param ofset
	 * @return
	 */
	public IDAO<T> setOfset(int ofset){
		this.ofset= String.join(" ", "OFSET",String.valueOf(lent));
		return this;
	};
	
	/**
	 * set limmit for result
	 * @param lent
	 * @return
	 */
	public IDAO<T> setLent(int lent) {
		this.lent= String.join(" ", "LIMIT",String.valueOf(lent));
		return this;
	}
	
	/**
	 * get condition right for clause Query DB
	 * @return
	 */
	private String getCondition() {
		if (this.condition.size()==0) {
			return "";
		}
		return "WHERE " + String.join(" AND ", this.condition);
	}
	
	
	/**
	 * clear all property of clause
	 */
	private void clear() {
		this.lent="";
		this.condition.clear();
		this.ofset="";
	}
	/**
	 * Create new generic class
	 * @param <T>
	 * @return T
	 * @throws Exception
	 */
	
	@SuppressWarnings("unchecked")
	private T createGenericClass() throws Exception {
		@SuppressWarnings("rawtypes")
		Constructor[]  newObject = this.classes.getDeclaredConstructors();
		@SuppressWarnings("rawtypes")
		Constructor ctor = null;
		for (int i = 0; i < newObject.length; i++) {
			ctor = newObject[i];
		    if (ctor.getGenericParameterTypes().length == 0)
			break;
		}
	    ctor.setAccessible(true);
		T object = null;
		object= (T) ctor.newInstance();
 	    return object;
	}
	
}
