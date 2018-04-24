package com.webmaven.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.webmaven.bean.Balance;
import com.webmaven.bean.Product;
import com.webmaven.controller.UserController;
import com.webmaven.util.Utility;

public class ViewsDAO {
	
	private static final Logger logger = Logger.getLogger(ViewsDAO.class);
	private static final Utility utils = Utility.getInstance();
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	/**
	 * 
	 * @return the list of all Balances instances from the database.
	 */
	public List<Balance> selectAll() {
		List<Balance> list = null;
		SqlSession session = sqlSessionFactory.openSession();

		try {
			list = session.selectList("Balance.selectAll");
		}catch(Exception e){
			logger.info("Msg:" + utils.getExceptionStackString(e));
		} finally {
			session.close();
		}
		logger.info("selectAll() --> " + list);
		return list;

	}
}
