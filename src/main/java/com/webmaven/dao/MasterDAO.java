package com.webmaven.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.webmaven.bean.Master;
import com.webmaven.util.Utility;

public class MasterDAO {
	
	private static final Logger logger = Logger.getLogger(MasterDAO.class);

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
	 * Returns the list of all Masters instances from the database.
	 * 
	 * @return the list of all Masters instances from the database.
	 */
	public List<Master> selectAll() {
		List<Master> list = null;
		SqlSession session = sqlSessionFactory.openSession();

		try {
			list = session.selectList("Master.selectAll");
		} catch(Exception e){
			logger.error("Msg:" + utils.getExceptionStackString(e));
		}finally {
			session.close();
		}
		logger.info("selectAll() --> " + list);
		return list;

	}

	/**
	 * Select instance of Master from the database.
	 * 
	 * @param MasterId
	 */
	public List<Master> getMasterDetailsByMasterId(String masterId) {
		List<Master> MasterDetails = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			MasterDetails =  session.selectList("Master.selectAllFromMasterId", masterId);

		} catch(Exception e){
			logger.error("Msg:" + utils.getExceptionStackString(e));
		}finally {
			session.close();
		}
		return MasterDetails;
	}
	
	/**
	 * Select instance of Master from the database.
	 * 
	 * @param MasterId
	 */
	public List<Master> getDetailsFromMasterIdAndKey(String masterId, String masterKey) {
		List<Master> MasterDetails = null;
		
		Master master = new Master();
		master.setMasterId(masterId);
		master.setMasterKey(masterKey);
		SqlSession session = sqlSessionFactory.openSession();
		try {
			MasterDetails =  session.selectList("Master.selectAllFromMasterIdAndKey", master);

		} catch(Exception e){
			logger.error("Msg:" + utils.getExceptionStackString(e));
		}finally {
			session.close();
		}
		return MasterDetails;
	}

	/**
	 * Select instance of Master from the database.
	 * 
	 * @param MasterId
	 */
	public List<String> selectDistinctMasterId() {
		List<String> masterIdList = null;
		
		SqlSession session = sqlSessionFactory.openSession();
		try {
			masterIdList =  session.selectList("Master.selectDistinctMasterId");

		} catch(Exception e){
			logger.error("Msg:" + utils.getExceptionStackString(e));
		}finally {
			session.close();
		}
		return masterIdList;
	}
	
}
