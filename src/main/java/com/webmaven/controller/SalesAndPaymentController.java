package com.webmaven.controller;

import static com.webmaven.util.BmsConstants.LOGOUT_VIEW;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.webmaven.bean.AddSales;
import com.webmaven.bean.Customer;
import com.webmaven.bean.Payment;
import com.webmaven.bean.Product;
import com.webmaven.bean.SalesAndPayment;
import com.webmaven.bean.SalesDetails;
import com.webmaven.bean.User;
import com.webmaven.dao.CustomerDAO;
import com.webmaven.dao.ProductDAO;
import com.webmaven.dao.SalesAndPaymentDAO;
import com.webmaven.dao.SalesDetailsDAO;
import com.webmaven.util.BmsConstants;
import com.webmaven.util.Utility;

@Controller
public class SalesAndPaymentController {
	
	private static final Logger logger = Logger.getLogger(SalesAndPaymentController.class);
	private static final Utility utils = Utility.getInstance();
	
	@Autowired
    private ProductDAO productDao;
	
	@Autowired
    private CustomerDAO customerDao;
	
	@Autowired
    private SalesAndPaymentDAO salesAndPaymentDao;
	
	@Autowired
    private SalesDetailsDAO salesDetailsDao;
	
	public void setProductDao(ProductDAO productDao) {
		this.productDao = productDao;
	}
	
	public void setCustomerDao(CustomerDAO customerDao) {
		this.customerDao = customerDao;
	}
	
	public SalesAndPaymentDAO getSalesAndPaymentDao() {
		return salesAndPaymentDao;
	}

	public void setSalesAndPaymentDao(SalesAndPaymentDAO salesAndPaymentDao) {
		this.salesAndPaymentDao = salesAndPaymentDao;
	}

	public SalesDetailsDAO getSalesDetailsDao() {
		return salesDetailsDao;
	}

	public void setSalesDetailsDao(SalesDetailsDAO salesDetailsDao) {
		this.salesDetailsDao = salesDetailsDao;
	}

	@RequestMapping(value="/addSales", method=RequestMethod.GET)
	public ModelAndView addSales(HttpSession session){
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		List<Product> productList = productDao.selectAll();
		List<Customer> customerList = customerDao.selectAll();

		Map<String, List<?>> models = new HashMap<String, List<?>>();
		models.put("productList", productList);
		models.put("customerList", customerList);
		logger.info("Going to add Sales View:");
		return new ModelAndView("addSales", models);
	}
	
	@RequestMapping(value="/insertSales", method=RequestMethod.POST)
	public ModelAndView insertSales(@RequestBody AddSales[] addSales, HttpSession session) {
		if (!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		int salesId = -1;
		List<SalesDetails> salesDetails = null;
		logger.info(addSales.toString());
		SalesAndPayment sales = getSalesObj(addSales);
		User uDetails = (User) session.getAttribute(BmsConstants.USERDETAILS);
		if (uDetails != null) {
			sales.setUpdatedBy(uDetails.getUsername());
		}else {
			logger.warn("Got User Obj as Null hence setting updatedBy as Null");
		}
		
		salesId = salesAndPaymentDao.insert(sales);
		logger.info(sales.toString());

		if(salesId != -1) {
			salesDetails = getSalesDetailsObj(salesId, addSales);
			salesDetailsDao.insert(salesDetails);
		}
		
		logger.info(salesDetails);
		return new ModelAndView("redirect:/viewUsers");
	}
	
	
	@RequestMapping(value="/insertPayment", method=RequestMethod.POST)
	public ModelAndView insertPayment(@ModelAttribute("SalesAndPayment") SalesAndPayment payments, HttpSession session){
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		User uDetails = (User) session.getAttribute(BmsConstants.USERDETAILS);
		if (uDetails != null) {
			payments.setUpdatedBy(uDetails.getUsername());
		}else {
			logger.warn("Got User Obj as Null hence setting updatedBy as Null");
		}
		salesAndPaymentDao.insert(payments);
		return new ModelAndView("redirect:/addPayment");
	}
	
	@RequestMapping(value="/viewLedger/{id}/", method=RequestMethod.GET)
	public ModelAndView insertSales(@PathVariable("id") int id, HttpSession session) {
		if (!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		List<SalesAndPayment> salesPaymenet = salesAndPaymentDao.getSalesByCustomerId(id);
		return new ModelAndView("viewLedger", "salesPaymenet", salesPaymenet);
	}

	private SalesAndPayment getSalesObj(AddSales[] addSalesObj) {
		if(addSalesObj != null) {
			AddSales sales = addSalesObj[addSalesObj.length - 1];
			return new SalesAndPayment(sales.getCustomerId(), sales.getDate(), sales.getTotalAmount(), sales.getComment(), sales.getType(), sales.getMode(), sales.getPayment());
		}
		return null;
	}
	
	private List<SalesDetails> getSalesDetailsObj(int salesId, AddSales[] addSalesObj) {
		if(addSalesObj != null) {
			AddSales[] newSalesArr = Arrays.copyOf(addSalesObj, addSalesObj.length-1);
			List<SalesDetails> salesDetails = new ArrayList<SalesDetails>();
			
			for(AddSales addSales : newSalesArr) {
				salesDetails.add(new SalesDetails(salesId, addSales));
			}
			return salesDetails;
		}
		return null;
	}
}
