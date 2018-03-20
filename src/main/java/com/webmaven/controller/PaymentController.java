package com.webmaven.controller;

import static com.webmaven.util.BmsConstants.LOGOUT_VIEW;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.webmaven.bean.Customer;
import com.webmaven.bean.Payment;
import com.webmaven.dao.CustomerDAO;
import com.webmaven.dao.PaymentDAO;
import com.webmaven.util.Utility;

@Controller
public class PaymentController {
	
	private static final Logger logger = Logger.getLogger(PaymentController.class);
	private static final Utility utils = Utility.getInstance();
	
	@Autowired
    private PaymentDAO paymentDao;
	
	public void setPaymentDao(PaymentDAO paymentDao) {
		this.paymentDao = paymentDao;
	}
	
	@Autowired
    private CustomerDAO customerDao;
	
	public void setCustomerDao(CustomerDAO customerDao) {
		this.customerDao = customerDao;
	}

	@RequestMapping(value="/addPayment", method=RequestMethod.GET)
	public ModelAndView addPayment(HttpSession session){
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		List<Customer> customerList = customerDao.selectAll();

		Map<String, List<?>> models = new HashMap<String, List<?>>();
		models.put("customerList", customerList);
		logger.info("Going to add addPayment View:");
		return new ModelAndView("addPayment", models);
	}
	
	@RequestMapping(value="/viewPayments", method=RequestMethod.GET)
	public ModelAndView getAllPayments(HttpSession session) {
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		List<Payment>PaymentList = paymentDao.selectAll();
		return new ModelAndView("viewPayments", "PaymentList", PaymentList);
	}
	
	/*@RequestMapping(value="/insertPayment", method=RequestMethod.POST)
	public ModelAndView insertPayment(@ModelAttribute("Payment") Payment Payment, HttpSession session){
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		paymentDao.insert(Payment);
		return new ModelAndView("redirect:/addPayment");
	}*/
	
	@RequestMapping(value="/updatePayment", method=RequestMethod.POST)
	public ModelAndView updatePayment(@ModelAttribute("Payment") Payment Payment, HttpSession session){
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		paymentDao.update(Payment);
		return new ModelAndView("redirect:/viewPayments");
	}
	
	@RequestMapping(value="/editPayment/{id}/", method=RequestMethod.GET)
	public ModelAndView editPayment(@PathVariable("id") int id, HttpSession session){
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		Payment PaymentDetails = paymentDao.getPaymentById(id);
		return new ModelAndView("editPayment", "PaymentDetails", PaymentDetails);
	}
	
	@RequestMapping(value="/viewPayment/{id}/", method=RequestMethod.GET)
	public ModelAndView viewPayment(@PathVariable("id") int id, HttpSession session){
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		Payment PaymentDetails = paymentDao.getPaymentById(id);
		return new ModelAndView("viewPayment", "PaymentDetails", PaymentDetails);
	}
	
	@RequestMapping(value="/deletePayment/{id}/", method=RequestMethod.GET)
	public ModelAndView deletePayment(@PathVariable("id") int id, HttpSession session){
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		int PaymentDetails = paymentDao.delete(id);
		return new ModelAndView("redirect:/viewPayments");
	}
}
