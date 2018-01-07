package com.webmaven.controller;

import static com.webmaven.util.BmsConstants.LOGOUT_VIEW;

import java.util.List;

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

import com.webmaven.bean.Sales;
import com.webmaven.bean.User;
import com.webmaven.dao.UserDAO;
import com.webmaven.util.BmsConstants;
import com.webmaven.util.Utility;

@Controller
public class UserController {
	
	private static final Logger logger = Logger.getLogger(UserController.class);
	private static final Utility utils = Utility.getInstance();
	
	@Autowired
    private UserDAO userDao;
	
	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}
	
	@RequestMapping(value="/addSales", method=RequestMethod.GET)
	public ModelAndView addSales(){
		return new ModelAndView("addSales");
	}
	
	@RequestMapping(value="/viewUsers", method=RequestMethod.GET)
	public ModelAndView getAllUsers(HttpSession session) {
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		List<User>userList = userDao.selectAll();
		return new ModelAndView("viewUsers", "userList", userList);
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView welcome(){
		return new ModelAndView("login");
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView login(){
		return new ModelAndView("login");
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public ModelAndView logout(HttpSession session){
		session.invalidate();
		return new ModelAndView("redirect:/login");
	}
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public ModelAndView index(HttpSession session){
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		return new ModelAndView("index");
	}
	
	@RequestMapping(value="/addUser", method=RequestMethod.GET)
	public ModelAndView addUser(HttpSession session){
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		return new ModelAndView("addUser");
	}
	
	@RequestMapping(value="/insertUser", method=RequestMethod.POST)
	public ModelAndView insertUser(@ModelAttribute("User") User user, HttpSession session){
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		userDao.insert(user);
		return new ModelAndView("redirect:/viewUsers");
	}
	
	@RequestMapping(value="/updateUser", method=RequestMethod.POST)
	public ModelAndView updateUser(@ModelAttribute("User") User user, HttpSession session){
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		userDao.update(user);
		return new ModelAndView("redirect:/viewUsers");
	}
	
	@RequestMapping(value="/editUser/{id}/", method=RequestMethod.GET)
	public ModelAndView editUser(@PathVariable("id") int id, HttpSession session){
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		User userDetails = userDao.getUserById(id);
		return new ModelAndView("editUser", "userDetails", userDetails);
	}
	
	@RequestMapping(value="/viewUser/{id}/", method=RequestMethod.GET)
	public ModelAndView viewUser(@PathVariable("id") int id, HttpSession session){
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		User userDetails = userDao.getUserById(id);
		return new ModelAndView("viewUser", "userDetails", userDetails);
	}
	
	@RequestMapping(value="/deleteUser/{id}/", method=RequestMethod.GET)
	public ModelAndView deleteUser(@PathVariable("id") int id, HttpSession session){
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		int userDetails = userDao.delete(id);
		return new ModelAndView("redirect:/viewUsers");
	}
	
	@RequestMapping(value="/validateUser", method=RequestMethod.POST)
	public ModelAndView validateUser(@ModelAttribute("User") User user, HttpSession session){
		User userDetails = userDao.validateUser(user);
		if(userDetails == null) {
			return new ModelAndView(LOGOUT_VIEW);
		}else {
			if(user != null && userDetails != null) {
				if(user.getUsername().equals(userDetails.getUsername()) &&
						user.getPassword().equals(userDetails.getPassword())){
				session.setAttribute(BmsConstants.ISVALID, true);
				}else {
					return new ModelAndView(LOGOUT_VIEW);
				}
			}else {
				return new ModelAndView(LOGOUT_VIEW);
			}
		}
		return new ModelAndView("redirect:/index");
	}
	
}
