package com.webmaven.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.webmaven.bean.User;
import com.webmaven.util.Utility;

public class UserDAO {
	
	private static final Logger logger = Logger.getLogger(UserDAO.class);

	@Autowired
	private Utility utils;
	
	public void setUtils(Utility utils) {
		this.utils = utils;
	}
	
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
		}catch(Exception e){
			logger.info("Msg:" + utils.getExceptionStackString(e));
		} finally {
			session.close();
		}
		logger.info("selectAll() --> " + list);
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

		}catch(Exception e){
			logger.info("Msg:" + utils.getExceptionStackString(e));
		} finally {
			session.close();
		}
		logger.info("validateUser(" + user + ") --> " + validatedLogin);
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

		}catch(Exception e){
			logger.info("Msg:" + utils.getExceptionStackString(e));
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
		}catch(Exception e){
			logger.info("Msg:" + utils.getExceptionStackString(e));
		} finally {
			session.commit();
			session.close();
		}
		logger.info("insert(" + login + ") --> " + login.getId());
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

		}catch(Exception e){
			logger.info("Msg:" + utils.getExceptionStackString(e));
		} finally {
			session.commit();
			session.close();
		}
		logger.info("update(" + login + ") --> updated");
	}
	
	public void updateUserPassword(User user) {
		int id = -1;
		SqlSession session = sqlSessionFactory.openSession();

		try {
			id = session.update("User.updatePassword", user);

		}catch(Exception e){
			logger.info("Msg:" + utils.getExceptionStackString(e));
		} finally {
			session.commit();
			session.close();
		}
		logger.info("updateUserPassword(" + user + ") --> updated");
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
		}catch(Exception e){
			logger.info("Msg:" + utils.getExceptionStackString(e));
		} finally {
			session.commit();
			session.close();
		}
		logger.info("deleted User(" + id + ")");
		return rows;
	}
}
