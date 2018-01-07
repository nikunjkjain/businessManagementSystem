package com.webmaven.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.webmaven.bean.Customer;

public class CustomerDAO {
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	
	/**
	 * Returns the list of all Customers instances from the database.
	 * 
	 * @return the list of all Customers instances from the database.
	 */
	public List<Customer> selectAll() {
		List<Customer> list = null;
		SqlSession session = sqlSessionFactory.openSession();

		try {
			list = session.selectList("Customer.selectAll");
		} finally {
			session.close();
		}
		System.out.println("selectAll() --> " + list);
		return list;

	}

	/**
	 * Select instance of Customer from the database.
	 * 
	 * @param CustomerId
	 */
	public Customer getCustomerById(Integer CustomerId) {
		Customer CustomerDetails = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			CustomerDetails = (Customer) session.selectOne("Customer.getCustomerById", CustomerId);

		} finally {
			session.close();
		}
		return CustomerDetails;
	}

	/**
	 * Insert an instance of Person into the database.
	 * 
	 * @param customerDetails
	 *            the instance to be persisted.
	 */
	public int insert(Customer customerDetails) {
		int id = -1;
		SqlSession session = sqlSessionFactory.openSession();

		try {
			id = session.insert("Customer.insert", customerDetails);
		} finally {
			session.commit();
			session.close();
		}
		System.out.println("insert(" + customerDetails + ") --> " + customerDetails.getId());
		return id;
	}

	/**
	 * Update an instance of Login into the database.
	 * 
	 * @param customerDetails
	 *            the instance to be persisted.
	 */
	public void update(Customer customerDetails) {
		int id = -1;
		SqlSession session = sqlSessionFactory.openSession();

		try {
			id = session.update("Customer.update", customerDetails);

		} finally {
			session.commit();
			session.close();
		}
		System.out.println("update(" + customerDetails + ") --> updated");
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
			rows = session.delete("Customer.delete", id);
		} finally {
			session.commit();
			session.close();
		}
		System.out.println("deleted Customer(" + id + ")");
		return rows;
	}
}
