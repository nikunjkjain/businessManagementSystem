package com.webmaven.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.webmaven.bean.SalesAndPayment;
import com.webmaven.controller.SalesAndPaymentController;

public class SalesAndPaymentDAO {
	
	private static final Logger logger = Logger.getLogger(SalesAndPaymentDAO.class);
	
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
	public List<SalesAndPayment> selectAll() {
		List<SalesAndPayment> list = null;
		SqlSession session = sqlSessionFactory.openSession();

		try {
			list = session.selectList("SalesAndPayment.selectAll");
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
	public SalesAndPayment getSalesById(Integer SalesId) {
		SalesAndPayment SalesDetails = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			SalesDetails = (SalesAndPayment) session.selectOne("SalesAndPayment.getSalesById", SalesId);

		} finally {
			session.close();
		}
		return SalesDetails;
	}
	
	/**
	 * Select instance of Sales from the database.
	 * 
	 * @param customerId
	 */
	public List<SalesAndPayment> getSalesByCustomerId(Integer customerId) {
		List<SalesAndPayment> SalesDetails = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			SalesDetails = session.selectList("SalesAndPayment.getSalesByCustomerId", customerId);

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
	public int insert(SalesAndPayment sales) {
		SqlSession session = sqlSessionFactory.openSession();

		try {
			session.insert("SalesAndPayment.insert", sales);
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
	public void update(SalesAndPayment sales) {
		int id = -1;
		SqlSession session = sqlSessionFactory.openSession();

		try {
			id = session.update("SalesAndPayment.update", sales);

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
			rows = session.delete("SalesAndPayment.delete", id);
		} finally {
			session.commit();
			session.close();
		}
		logger.info("deleted Sales(" + id + ")");
		return rows;
	}
}
