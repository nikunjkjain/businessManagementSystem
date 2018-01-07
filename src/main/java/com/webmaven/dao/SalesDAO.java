package com.webmaven.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.webmaven.bean.Sales;

public class SalesDAO {
	
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
		System.out.println("selectAll() --> " + list);
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
	 * @param login
	 *            the instance to be persisted.
	 */
	public int insert(Sales login) {
		int id = -1;
		SqlSession session = sqlSessionFactory.openSession();

		try {
			id = session.insert("Sales.insert", login);
		} finally {
			session.commit();
			session.close();
		}
		//System.out.println("insert(" + login + ") --> " + login.getId());
		return id;
	}

	/**
	 * Update an instance of Login into the database.
	 * 
	 * @param login
	 *            the instance to be persisted.
	 */
	public void update(Sales login) {
		int id = -1;
		SqlSession session = sqlSessionFactory.openSession();

		try {
			id = session.update("Sales.update", login);

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
			rows = session.delete("Sales.delete", id);
		} finally {
			session.commit();
			session.close();
		}
		System.out.println("deleted Sales(" + id + ")");
		return rows;
	}
}
