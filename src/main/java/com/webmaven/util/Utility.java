package com.webmaven.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.webmaven.bean.Customer;
import com.webmaven.bean.Master;
import com.webmaven.bean.Product;
import com.webmaven.bean.User;
import com.webmaven.dao.CustomerDAO;
import com.webmaven.dao.MasterDAO;
import com.webmaven.dao.ProductDAO;

public class Utility {

	private static final Logger logger = Logger.getLogger(Utility.class);

	@Autowired
	private MasterDAO masterDao;

	public void setMasterDao(MasterDAO masterDao) {
		this.masterDao = masterDao;
	}

	@Autowired
    private ProductDAO productDao;
	
	public void setProductDao(ProductDAO productDao) {
		this.productDao = productDao;
	}
	
	@Autowired
    private CustomerDAO customerDao;
	
	public void setCustomerDao(CustomerDAO customerDao) {
		this.customerDao = customerDao;
	}
	
	Map<String, Map<String, String>> masterIdAndKeyVal = new HashMap<String, Map<String, String>>();
	Map<String, Map<String, String>> masterIdAndValKey = new HashMap<String, Map<String, String>>();
	Map<String, String> productIdKeyMap = new HashMap<String, String>();
	Map<String, String> customerIdKeyMap = new HashMap<String, String>();
	
	public Map<String, Map<String, String>> getMasterIdAndKeyVal() {
		return masterIdAndKeyVal;
	}

	public Map<String, String> getCustomerIdKeyMap() {
		return customerIdKeyMap;
	}

	public Map<String, Map<String, String>> getMasterIdAndValKey() {
		return masterIdAndValKey;
	}
	
	public Map<String, String> getProductIdKeyMap() {
		return productIdKeyMap;
	}
	
	private Utility() {
	}

	public boolean isValidSession(HttpSession session) {
		Boolean sessVar = (Boolean) session.getAttribute(BmsConstants.ISVALID);
		User uDetails = (User) session.getAttribute(BmsConstants.USERDETAILS);
		logger.info("===> isValidSession :sessVar:<" + sessVar + "> uDetails:<" + uDetails + ">");
		boolean isValid = false;
		if (sessVar != null && sessVar.booleanValue() && uDetails != null) {
			isValid = true;
		}
		return isValid;
	}

	public String getUserIdFromSession(HttpSession session) {

		User uDetails = (User) session.getAttribute(BmsConstants.USERDETAILS);

		if (uDetails != null)
			return uDetails.getUsername();

		logger.warn("Got User Obj as Null hence retruning userName as Null");
		return null;
	}

	public String getExceptionStackString(Exception e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		String exceptionAsString = sw.toString();
		return exceptionAsString;
	}

	public void init() {
		generateMasterDBMappings();
		generateProductIdNameMapping();
		generateCustomerIdNameMapping();
	}
	
	private void generateMasterDBMappings() {
		List<String> masterIdList = masterDao.selectDistinctMasterId();
		Map<String, String> masterKeyVal;
		Map<String, String> masterValKey;
		
		for (String masterId : masterIdList) {
			List<Master> masterDetails = masterDao.getMasterDetailsByMasterId(masterId);
			masterKeyVal = new HashMap<String, String>();
			masterValKey = new HashMap<String, String>();
			for(Master Mdetails : masterDetails) {
				String key = Mdetails.getMasterKey();
				String value = Mdetails.getMasterValue();
				masterKeyVal.put(key, value);
				masterValKey.put(value, key);
			}
			masterIdAndKeyVal.put(masterId, masterKeyVal);
			masterIdAndValKey.put(masterId, masterValKey);
		}
		
		logger.info("masterIdAndKeyVal-->"+masterIdAndKeyVal);
		logger.info("masterIdAndValKey-->"+masterIdAndValKey);
	}
	
	private void generateProductIdNameMapping() {
		List<Product> productList = productDao.selectAll();
		for(Product product : productList) {
			productIdKeyMap.put(product.getId()+"", product.getName());
		}
		logger.info("productIdKeyMap-->"+productIdKeyMap);
	}
	
	private void generateCustomerIdNameMapping() {
		List<Customer> customerList = customerDao.selectAll();
		for(Customer customer : customerList) {
			customerIdKeyMap.put(customer.getId()+"", customer.getName());
		}
		logger.info("customerIdKeyMap-->"+customerIdKeyMap);
	}
}
