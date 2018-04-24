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
import com.webmaven.bean.Product;
import com.webmaven.bean.SalesAndPayment;
import com.webmaven.bean.SalesDetails;
import com.webmaven.dao.CustomerDAO;
import com.webmaven.dao.ProductDAO;
import com.webmaven.dao.SalesAndPaymentDAO;
import com.webmaven.dao.SalesDetailsDAO;
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
	
	@RequestMapping(value="/editSales/{id}/", method=RequestMethod.GET)
	public ModelAndView editSales(@PathVariable("id") int id, HttpSession session){
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		List<Product> productList = productDao.selectAll();
		List<Customer> customerList = customerDao.selectAll();
		List<SalesDetails> salesDetailsList = salesDetailsDao.getSalesDetailsById(id);
		SalesAndPayment salesAndPayment = salesAndPaymentDao.getSalesById(id);

		Map<String, Object> models = new HashMap<String, Object>();
		models.put("productList", productList);
		models.put("customerList", customerList);
		models.put("salesDetailsList", salesDetailsList);
		models.put("salesAndPayment", salesAndPayment);
		
		logger.info("Going to Edit Sales View:");
		return new ModelAndView("editSales", models);
	}
	
	@RequestMapping(value="/insertSales", method=RequestMethod.POST)
	public ModelAndView insertSales(@RequestBody AddSales[] addSales, HttpSession session) {
		if (!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		int salesId = -1;
		List<SalesDetails> salesDetails = null;
		logger.info(addSales.toString());
		SalesAndPayment sales = getSalesObj(addSales);
		sales.setUpdatedBy(utils.getUserIdFromSession(session));
		salesId = salesAndPaymentDao.insert(sales);
		logger.info(sales.toString());

		if(salesId != -1) {
			salesDetails = getSalesDetailsObj(salesId, addSales, session);
			salesDetailsDao.insert(salesDetails);
		}
		
		logger.info(salesDetails);
		return new ModelAndView("redirect:/viewUsers");
	}
	
	@RequestMapping(value="/editSalesDetails", method=RequestMethod.POST)
	public ModelAndView editSalesDetails(@RequestBody AddSales[] addSales, HttpSession session) {
		if (!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		int salesId = -1;
		int updateCount = 0;
		List<SalesDetails> salesDetails = null;
		logger.info(addSales.toString());
		SalesAndPayment sales = getSalesObj(addSales, true);
		sales.setUpdatedBy(utils.getUserIdFromSession(session));
		updateCount = salesAndPaymentDao.update(sales);
		logger.info(sales.toString());

		if (updateCount == 1) {
			salesId = sales.getId();
			salesDetails = getSalesDetailsObj(salesId, addSales, session);
			salesId = salesDetailsDao.update(salesDetails);
		}

		logger.info(salesDetails);
		return new ModelAndView("redirect:/viewUsers");
	}
	
	
	@RequestMapping(value="/insertPayment", method=RequestMethod.POST)
	public ModelAndView insertPayment(@ModelAttribute("SalesAndPayment") SalesAndPayment payments, HttpSession session){
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		payments.setUpdatedBy(utils.getUserIdFromSession(session));
		salesAndPaymentDao.insert(payments);
		return new ModelAndView("redirect:/addPayment");
	}
	
	@RequestMapping(value="/viewLedger/{id}/", method=RequestMethod.GET)
	public ModelAndView viewLegder(@PathVariable("id") int id, HttpSession session) {
		if (!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		List<SalesAndPayment> salesPaymenet = salesAndPaymentDao.getSalesByCustomerId(id);
		return new ModelAndView("viewLedger", "salesPaymenet", salesPaymenet);
	}

	private SalesAndPayment getSalesObj(AddSales[] addSalesObj, boolean isEditSales) {
		if(addSalesObj != null) {
			AddSales sales = addSalesObj[addSalesObj.length - 1];
			if(!isEditSales)
				return new SalesAndPayment(sales.getCustomerId(), sales.getDate(), sales.getTotalAmount(), sales.getComment(), sales.getType(), sales.getMode(), sales.getPayment());
			return new SalesAndPayment(sales.getId(), sales.getCustomerId(), sales.getDate(), sales.getTotalAmount(), sales.getComment(), sales.getType(), sales.getMode(), sales.getPayment());
		}
		return null;
	}
	
	private SalesAndPayment getSalesObj(AddSales[] addSalesObj) {
		return getSalesObj(addSalesObj, false);
	}
	
	private List<SalesDetails> getSalesDetailsObj(int salesId, AddSales[] addSalesObj, HttpSession session) {
		if(addSalesObj != null) {
			String userId = utils.getUserIdFromSession(session);
			AddSales[] newSalesArr = Arrays.copyOf(addSalesObj, addSalesObj.length-1);
			List<SalesDetails> salesDetails = new ArrayList<SalesDetails>();
			
			for(AddSales addSales : newSalesArr) {
				addSales.setUpdatedBy(userId);
				salesDetails.add(new SalesDetails(salesId, addSales));
			}
			return salesDetails;
		}
		return null;
	}
}
