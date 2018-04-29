package com.webmaven.controller;

import static com.webmaven.util.BmsConstants.LOGOUT_VIEW;

import java.util.List;

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
import com.webmaven.bean.User;
import com.webmaven.dao.CustomerDAO;
import com.webmaven.util.BmsConstants;
import com.webmaven.util.Utility;

@Controller
public class CustomerController {
	
	private static final Logger logger = Logger.getLogger(CustomerController.class);
	
	@Autowired
	private Utility utils;
	
	public void setUtils(Utility utils) {
		this.utils = utils;
	}
	@Autowired
    private CustomerDAO customerDao;
	
	public void setCustomerDao(CustomerDAO customerDao) {
		this.customerDao = customerDao;
	}

	@RequestMapping(value="/viewCustomers", method=RequestMethod.GET)
	public ModelAndView getAllCustomers(HttpSession session) {
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		List<Customer>CustomerList = customerDao.selectAll();
		return new ModelAndView("viewCustomers", "CustomerList", CustomerList);
	}
	
	@RequestMapping(value="/addCustomer", method=RequestMethod.GET)
	public ModelAndView addCustomer(HttpSession session){
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		return new ModelAndView("addCustomer");
	}
	
	@RequestMapping(value="/insertCustomer", method=RequestMethod.POST)
	public ModelAndView insertCustomer(@ModelAttribute("Customer") Customer customer, HttpSession session){
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		customer.setUpdatedBy(utils.getUserIdFromSession(session));
		customerDao.insert(customer);
		return new ModelAndView("redirect:/viewCustomers");
	}
	
	@RequestMapping(value="/updateCustomer", method=RequestMethod.POST)
	public ModelAndView updateCustomer(@ModelAttribute("Customer") Customer customer, HttpSession session){
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		customer.setUpdatedBy(utils.getUserIdFromSession(session));
		customerDao.update(customer);
		return new ModelAndView("redirect:/viewCustomers");
	}
	
	@RequestMapping(value="/editCustomer/{id}/", method=RequestMethod.GET)
	public ModelAndView editCustomer(@PathVariable("id") int id, HttpSession session){
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		Customer CustomerDetails = customerDao.getCustomerById(id);
		return new ModelAndView("editCustomer", "CustomerDetails", CustomerDetails);
	}
	
	@RequestMapping(value="/viewCustomer/{id}/", method=RequestMethod.GET)
	public ModelAndView viewCustomer(@PathVariable("id") int id, HttpSession session){
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		Customer CustomerDetails = customerDao.getCustomerById(id);
		return new ModelAndView("viewCustomer", "CustomerDetails", CustomerDetails);
	}
	
	@RequestMapping(value="/deleteCustomer/{id}/", method=RequestMethod.GET)
	public ModelAndView deleteCustomer(@PathVariable("id") int id, HttpSession session){
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
			customerDao.delete(id);
		return new ModelAndView("redirect:/viewCustomers");
	}
	
}
