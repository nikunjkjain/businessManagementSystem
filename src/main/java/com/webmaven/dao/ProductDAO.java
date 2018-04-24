package com.webmaven.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.webmaven.bean.Product;
import com.webmaven.controller.UserController;
import com.webmaven.util.Utility;

public class ProductDAO {
	
	private static final Logger logger = Logger.getLogger(ProductDAO.class);
	private static final Utility utils = Utility.getInstance();
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	
	/**
	 * Returns the list of all Products instances from the database.
	 * 
	 * @return the list of all Products instances from the database.
	 */
	public List<Product> selectAll() {
		List<Product> list = null;
		SqlSession session = sqlSessionFactory.openSession();

		try {
			list = session.selectList("Product.selectAll");
		}catch(Exception e){
			logger.info("Msg:" + utils.getExceptionStackString(e));
		} finally {
			session.close();
		}
		logger.info("selectAll() --> " + list);
		return list;

	}

	
	/**
	 * Select instance of Product from the database.
	 * 
	 * @param ProductId
	 */
	public Product getProductById(Integer ProductId) {
		Product ProductDetails = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ProductDetails = (Product) session.selectOne("Product.getProductById", ProductId);

		}catch(Exception e){
			logger.info("Msg:" + utils.getExceptionStackString(e));
		} finally {
			session.close();
		}
		return ProductDetails;
	}

	/**
	 * Insert an instance of Person into the database.
	 * 
	 * @param login
	 *            the instance to be persisted.
	 */
	public int insert(Product login) {
		int id = -1;
		SqlSession session = sqlSessionFactory.openSession();

		try {
			id = session.insert("Product.insert", login);
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
	public void update(Product login) {
		int id = -1;
		SqlSession session = sqlSessionFactory.openSession();

		try {
			id = session.update("Product.update", login);

		}catch(Exception e){
			logger.info("Msg:" + utils.getExceptionStackString(e));
		} finally {
			session.commit();
			session.close();
		}
		logger.info("update(" + login + ") --> updated");
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
			rows = session.delete("Product.delete", id);
		}catch(Exception e){
			logger.info("Msg:" + utils.getExceptionStackString(e));
		} finally {
			session.commit();
			session.close();
		}
		logger.info("deleted Product(" + id + ")");
		return rows;
	}
}
