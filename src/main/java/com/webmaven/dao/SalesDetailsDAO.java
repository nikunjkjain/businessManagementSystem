package com.webmaven.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.webmaven.bean.SalesDetails;

public class SalesDetailsDAO {
	
	private static final Logger logger = Logger.getLogger(SalesDetailsDAO.class);
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	
	/**
	 * Returns the list of all SalesDetails instances from the database.
	 * 
	 * @return the list of all SalesDetails instances from the database.
	 */
	public List<SalesDetails> selectAll() {
		List<SalesDetails> list = null;
		SqlSession session = sqlSessionFactory.openSession();

		try {
			list = session.selectList("SalesDetails.selectAll");
		} finally {
			session.close();
		}
		logger.info("selectAll() --> " + list);
		return list;

	}
	
	
	/**
	 * Select instance of SalesDetails from the database.
	 * 
	 * @param SalesDetailsId
	 */
	public List<SalesDetails> getSalesDetailsById(Integer SalesDetailsId) {
		List<SalesDetails> SalesDetailsDetails = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			SalesDetailsDetails = session.selectList("SalesDetails.getSalesDetailsBySalesId", SalesDetailsId);

		} finally {
			session.close();
		}
		return SalesDetailsDetails;
	}

	/**
	 * Insert an instance of Person into the database.
	 * 
	 * @param salesDetails
	 *            the instance to be persisted.
	 */
	public int insert(List<SalesDetails> salesDetails) {
		int id = -1;
		SqlSession session = sqlSessionFactory.openSession();

		try {
			id = session.insert("SalesDetails.insert", salesDetails);
		} finally {
			session.commit();
			session.close();
		}
		logger.info("insert(" + salesDetails + ")");
		return id;
	}

	/**
	 * Update an instance of SalesDetails into the database.
	 * 
	 * @param SalesDetails
	 *            the instance to be persisted.
	 */
	public int update(List<SalesDetails> SalesDetails) {
		int id = 0;
		int count = 0;
		SqlSession session = sqlSessionFactory.openSession();

		try {
			for (SalesDetails sdetails : SalesDetails) {
				count = session.update("SalesDetails.update", sdetails);
				id = id + count;
			}
		} catch (Exception e) {
			logger.info("Got Exception "+e.getStackTrace() +" while updating the details hence rolling back the transaction");
			session.rollback();
		} finally {
			session.commit();
			session.close();
		}
		logger.info("update(" + SalesDetails + ") --> updated");
		return id;
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
			rows = session.delete("SalesDetails.delete", id);
		} finally {
			session.commit();
			session.close();
		}
		logger.info("deleted SalesDetails(" + id + ")");
		return rows;
	}
}
