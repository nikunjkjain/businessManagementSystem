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
import com.webmaven.bean.LedgerDetail;
import com.webmaven.bean.Product;
import com.webmaven.bean.SalesAndPayment;
import com.webmaven.bean.SalesDetails;
import com.webmaven.dao.CustomerDAO;
import com.webmaven.dao.ProductDAO;
import com.webmaven.dao.SalesAndPaymentDAO;
import com.webmaven.dao.SalesDetailsDAO;
import com.webmaven.dao.ViewsDAO;
import com.webmaven.util.Utility;

@Controller
public class SalesAndPaymentController {
	
	private static final Logger logger = Logger.getLogger(SalesAndPaymentController.class);

	@Autowired
	private Utility utils;
	
	public void setUtils(Utility utils) {
		this.utils = utils;
	}
	
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
	
	@Autowired
    private ViewsDAO viewsDao;
	
	public void setViewDao(ViewsDAO viewsDao) {
		this.viewsDao = viewsDao;
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
		
		logger.info("Going to Edit Sales View:" + salesAndPayment.toString());
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
	
	@RequestMapping(value="/deleteSales/{id}/", method=RequestMethod.GET)
	public ModelAndView deleteSales(@PathVariable("id") int id, HttpSession session) {
		if (!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
			salesDetailsDao.delete(id);
			salesAndPaymentDao.delete(id);
		return new ModelAndView("redirect:/viewCustomers");
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
		payments.setPayment(Integer.parseInt(utils.getMasterIdAndKeyVal().get("ADDPAY").get(payments.getType())));
		salesAndPaymentDao.insert(payments);
		return new ModelAndView("redirect:/addPayment");
	}
	
	@RequestMapping(value="/editPayment", method=RequestMethod.POST)
	public ModelAndView editPayment(@ModelAttribute("SalesAndPayment") SalesAndPayment payments, HttpSession session){
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		payments.setUpdatedBy(utils.getUserIdFromSession(session));
		payments.setPayment(Integer.parseInt(utils.getMasterIdAndKeyVal().get("ADDPAY").get(payments.getType())));
		salesAndPaymentDao.update(payments);
		return new ModelAndView("redirect:/viewCustomers");
	}
	
	@RequestMapping(value="/viewLedger/{id}/", method=RequestMethod.GET)
	public ModelAndView viewLegder(@PathVariable("id") int id, HttpSession session) {
		if (!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		List<SalesAndPayment> salesPaymenet = salesAndPaymentDao.getSalesByCustomerId(id);
		System.out.println("salesPayemnt toString:" + salesPaymenet.toString());
		Map<String, Object> models = new HashMap<String, Object>();
		models.put("salesPaymenet", salesPaymenet);
		models.put("customerId", id);
		return new ModelAndView("viewLedger", "salesPaymenet", salesPaymenet);
	}
	
	@RequestMapping(value="/viewLedgerDetail/{customerId}/", method=RequestMethod.GET)
	public ModelAndView viewLedgerDetail(@PathVariable("customerId") int customerId, HttpSession session) {
		if (!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		List<LedgerDetail> ledgerDetailList = viewsDao.selectLedgerDetail(customerId);
		Map<String, Object> models = new HashMap<String, Object>();
		models.put("ledgerDetailList", ledgerDetailList);
		models.put("id", customerId);
		return new ModelAndView("viewLedgerDetail", models);
	}
	
	@RequestMapping(value="/viewPaymentDetails/{id}/", method=RequestMethod.GET)
	public ModelAndView viewPaymentDetails(@PathVariable("id") int id, HttpSession session) {
		if (!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		SalesAndPayment salesPaymenet = salesAndPaymentDao.getSalesById(id);
		Map<String, Object> models = new HashMap<String, Object>();
		models.put("salesPaymenet", salesPaymenet);
		return new ModelAndView("viewPaymentDetails", "salesPaymenet", salesPaymenet);
	}
	
	
	@RequestMapping(value="/viewPaymentReminder/", method=RequestMethod.GET)
	public ModelAndView viewPaymentReminder(HttpSession session) {
		if (!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		List<SalesAndPayment> salesPaymenet = salesAndPaymentDao.selectReminder();
		Map<String, Object> models = new HashMap<String, Object>();
		models.put("salesPaymenet", salesPaymenet);
		return new ModelAndView("viewPaymentDetails", "salesPaymenet", salesPaymenet);
	}
	
	
	@RequestMapping(value="/editPaymentDetails/{id}/", method=RequestMethod.GET)
	public ModelAndView editPaymentDetails(@PathVariable("id") int id, HttpSession session) {
		if (!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		SalesAndPayment salesPaymenet = salesAndPaymentDao.getSalesById(id);
		List<Customer> customerList = customerDao.selectAll();
		Map<String, Object> models = new HashMap<String, Object>();
		models.put("salesPaymenet", salesPaymenet);
		models.put("customerList", customerList);
		return new ModelAndView("editPaymentDetails", models);
	}
	
	private SalesAndPayment getSalesObj(AddSales[] addSalesObj, boolean isEditSales) {
		if(addSalesObj != null) {
			AddSales sales = addSalesObj[addSalesObj.length - 1];
			if(!isEditSales)
				return new SalesAndPayment(sales.getCustomerId(), sales.getDate(), sales.getReminder(), sales.getTotalAmount(), sales.getComment(), sales.getType(), sales.getMode(), sales.getPayment());
			return new SalesAndPayment(sales.getId(), sales.getCustomerId(), sales.getDate(), sales.getReminder(), sales.getTotalAmount(), sales.getComment(), sales.getType(), sales.getMode(), sales.getPayment());
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
