package com.webmaven.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.webmaven.bean.Payment;

public class PaymentDAO {
	
	private static final Logger logger = Logger.getLogger(PaymentDAO.class);
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	
	/**
	 * Returns the list of all Payment instances from the database.
	 * 
	 * @return the list of all Payment instances from the database.
	 */
	public List<Payment> selectAll() {
		List<Payment> list = null;
		SqlSession session = sqlSessionFactory.openSession();

		try {
			list = session.selectList("Payment.selectAll");
		} finally {
			session.close();
		}
		logger.info("selectAll() --> " + list);
		return list;

	}
	
	
	/**
	 * Select instance of Payment from the database.
	 * 
	 * @param PaymentId
	 */
	public Payment getPaymentById(Integer PaymentId) {
		Payment PaymentDetails = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			PaymentDetails = (Payment) session.selectOne("Payment.getPaymentById", PaymentId);

		} finally {
			session.close();
		}
		return PaymentDetails;
	}

	/**
	 * Insert an instance of Person into the database.
	 * 
	 * @param Payment
	 *            the instance to be persisted.
	 */
	public int insert(Payment Payment) {
		SqlSession session = sqlSessionFactory.openSession();

		try {
			session.insert("Payment.insert", Payment);
		} finally {
			session.commit();
			session.close();
		}
		logger.info("insert(" + Payment + ")");
		return Payment.getId();
	}

	/**
	 * Update an instance of Payment into the database.
	 * 
	 * @param Payment
	 *            the instance to be persisted.
	 */
	public void update(Payment Payment) {
		int id = -1;
		SqlSession session = sqlSessionFactory.openSession();

		try {
			id = session.update("Payment.update", Payment);

		} finally {
			session.commit();
			session.close();
		}
		logger.info("update(" + Payment + ") --> updated");
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
			rows = session.delete("Payment.delete", id);
		} finally {
			session.commit();
			session.close();
		}
		logger.info("deleted Payment(" + id + ")");
		return rows;
	}
}
