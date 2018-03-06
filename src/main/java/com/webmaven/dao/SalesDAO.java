package com.webmaven.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.webmaven.bean.Sales;
import com.webmaven.controller.SalesController;

public class SalesDAO {
	
	private static final Logger logger = Logger.getLogger(SalesDAO.class);
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	
	/**
	 * Returns the list of all sales instances from the database.
	 * 
	 * @return the list of all sales instances from the database.
	 */
	public List<Sales> selectAll() {
		List<Sales> list = null;
		SqlSession session = sqlSessionFactory.openSession();

		try {
			list = session.selectList("Sales.selectAll");
		} finally {
			session.close();
		}
		logger.info("selectAll() --> " + list);
		return list;

	}
	
	
	/**
	 * Select instance of Sales from the database.
	 * 
	 * @param SalesId
	 */
	public Sales getSalesById(Integer SalesId) {
		Sales SalesDetails = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			SalesDetails = (Sales) session.selectOne("Sales.getSalesById", SalesId);

		} finally {
			session.close();
		}
		return SalesDetails;
	}

	/**
	 * Insert an instance of Person into the database.
	 * 
	 * @param sales
	 *            the instance to be persisted.
	 */
	public int insert(Sales sales) {
		SqlSession session = sqlSessionFactory.openSession();

		try {
			session.insert("Sales.insert", sales);
		} finally {
			session.commit();
			session.close();
		}
		logger.info("insert(" + sales + ")");
		return sales.getId();
	}

	/**
	 * Update an instance of Sales into the database.
	 * 
	 * @param sales
	 *            the instance to be persisted.
	 */
	public void update(Sales sales) {
		int id = -1;
		SqlSession session = sqlSessionFactory.openSession();

		try {
			id = session.update("Sales.update", sales);

		} finally {
			session.commit();
			session.close();
		}
		logger.info("update(" + sales + ") --> updated");
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
			rows = session.delete("Sales.delete", id);
		} finally {
			session.commit();
			session.close();
		}
		logger.info("deleted Sales(" + id + ")");
		return rows;
	}
}
