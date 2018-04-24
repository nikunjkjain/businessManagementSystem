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
import com.webmaven.bean.SalesDetails;
import com.webmaven.bean.User;
import com.webmaven.dao.CustomerDAO;
import com.webmaven.dao.SalesDetailsDAO;
import com.webmaven.util.BmsConstants;
import com.webmaven.util.Utility;

@Controller
public class SalesDetailsController {
	
	private static final Logger logger = Logger.getLogger(SalesDetailsController.class);
	private static final Utility utils = Utility.getInstance();
	
	@Autowired
    private SalesDetailsDAO salesDetailsDao;
	
	public void setSalesDetailsDao(SalesDetailsDAO salesDetailsDao) {
		this.salesDetailsDao = salesDetailsDao;
	}
	
	@RequestMapping(value="/editSalesDetails/{id}/", method=RequestMethod.GET)
	public ModelAndView editSalesDetailsById(@PathVariable("id") int id, HttpSession session){
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		List<SalesDetails> salesDetails = salesDetailsDao.getSalesDetailsById(id);
		return new ModelAndView("editSalesDetails", "salesDetails", salesDetails);
	}
	
	@RequestMapping(value="/viewSalesDetails/{id}/", method=RequestMethod.GET)
	public ModelAndView viewSalesDetailsById(@PathVariable("id") int id, HttpSession session){
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		List<SalesDetails> salesDetails = salesDetailsDao.getSalesDetailsById(id);
		System.out.println("salesDetails==>"+salesDetails);
		return new ModelAndView("viewSalesDetails", "salesDetails", salesDetails);
	}
	
	@RequestMapping(value="/deleteSalesDetails/{id}/", method=RequestMethod.GET)
	public ModelAndView deleteSalesDetailsById(@PathVariable("id") int id, HttpSession session){
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
			salesDetailsDao.delete(id);
		return new ModelAndView("redirect:/viewCustomers");
	}
	
}
