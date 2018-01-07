package com.webmaven.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.webmaven.bean.User;

public class UserDAO {
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	
	/**
	 * Returns the list of all users instances from the database.
	 * 
	 * @return the list of all users instances from the database.
	 */
	public List<User> selectAll() {
		List<User> list = null;
		SqlSession session = sqlSessionFactory.openSession();

		try {
			list = session.selectList("User.selectAll");
		} finally {
			session.close();
		}
		System.out.println("selectAll() --> " + list);
		return list;

	}

	/**
	 * Select instance of Person from the database.
	 * 
	 * @param person
	 *            the instance to be persisted.
	 */
	public User validateUser(User user) {
		User validatedLogin = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			validatedLogin = (User) session.selectOne("User.validateUser", user);

		} finally {
			session.close();
		}
		System.out.println("validateUser(" + user + ") --> " + validatedLogin);
		return validatedLogin;
	}
	
	
	/**
	 * Select instance of User from the database.
	 * 
	 * @param userId
	 */
	public User getUserById(Integer userId) {
		User userDetails = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			userDetails = (User) session.selectOne("User.getUserById", userId);

		} finally {
			session.close();
		}
		return userDetails;
	}

	/**
	 * Insert an instance of Person into the database.
	 * 
	 * @param login
	 *            the instance to be persisted.
	 */
	public int insert(User login) {
		int id = -1;
		SqlSession session = sqlSessionFactory.openSession();

		try {
			id = session.insert("User.insert", login);
		} finally {
			session.commit();
			session.close();
		}
		System.out.println("insert(" + login + ") --> " + login.getId());
		return id;
	}

	/**
	 * Update an instance of Login into the database.
	 * 
	 * @param login
	 *            the instance to be persisted.
	 */
	public void update(User login) {
		int id = -1;
		SqlSession session = sqlSessionFactory.openSession();

		try {
			id = session.update("User.update", login);

		} finally {
			session.commit();
			session.close();
		}
		System.out.println("update(" + login + ") --> updated");
	}

	/**
	 * Delete an instance of Login from the database.
	 * 
	 * @param id
	 *            value of the instance to be deleted.
	 */
	public int delete(int id) {

		SqlSession session = sqlSessionFactory.openSession();
		int rows = 0;
		try {
			rows = session.delete("User.delete", id);
		} finally {
			session.commit();
			session.close();
		}
		System.out.println("deleted User(" + id + ")");
		return rows;
	}
}
