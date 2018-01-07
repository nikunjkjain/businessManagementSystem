package com.webmaven.controller;

import static com.webmaven.util.BmsConstants.LOGOUT_VIEW;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.webmaven.bean.Sales;
import com.webmaven.util.Utility;

@Controller
public class SalesController {
	
	private static final Logger logger = Logger.getLogger(CustomerController.class);
	private static final Utility utils = Utility.getInstance();
	
	@RequestMapping(value="/insertSales", method=RequestMethod.POST)
	public ModelAndView insertSales(@RequestBody Sales[] customers, HttpSession session){
		System.out.println("SerialNo:"+customers[0].getSrNo());
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		System.out.println("Inside insertSales");
		System.out.println(customers);
		return new ModelAndView("redirect:/viewUsers");
	}

}
