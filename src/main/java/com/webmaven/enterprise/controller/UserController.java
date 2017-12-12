package com.webmaven.enterprise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.webmaven.enterprise.bean.User;
import com.webmaven.enterprise.dao.UserDAO;

@Controller
public class UserController {
	
	@Autowired
    private UserDAO userDao;
	
	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	@RequestMapping(value="/viewUsers", method=RequestMethod.GET)
	public ModelAndView getAllUsers() {
		List<User>userList = userDao.selectAll();
		return new ModelAndView("viewUsers", "userList", userList);
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView login(){
		return new ModelAndView("login");
	}
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public ModelAndView index(){
		return new ModelAndView("index");
	}
	
	@RequestMapping(value="/addUser", method=RequestMethod.GET)
	public ModelAndView addUser(){
		return new ModelAndView("addUser");
	}
	
	@RequestMapping(value="/insertUser", method=RequestMethod.POST)
	public ModelAndView insertUser(@ModelAttribute("User") User user){
		userDao.insert(user);
		return new ModelAndView("redirect:/viewUsers");
	}
	
	@RequestMapping(value="/updateUser", method=RequestMethod.POST)
	public ModelAndView updateUser(@ModelAttribute("User") User user){
		userDao.update(user);
		return new ModelAndView("redirect:/viewUsers");
	}
	
	@RequestMapping(value="/editUser/{id}/", method=RequestMethod.GET)
	public ModelAndView editUser(@PathVariable("id") int id){
		User userDetails = userDao.getUserById(id);
		return new ModelAndView("editUser", "userDetails", userDetails);
	}
	
	@RequestMapping(value="/viewUser/{id}/", method=RequestMethod.GET)
	public ModelAndView viewUser(@PathVariable("id") int id){
		User userDetails = userDao.getUserById(id);
		return new ModelAndView("viewUser", "userDetails", userDetails);
	}
	
	@RequestMapping(value="/deleteUser/{id}/", method=RequestMethod.GET)
	public ModelAndView deleteUser(@PathVariable("id") int id){
		int userDetails = userDao.delete(id);
		return new ModelAndView("redirect:/viewUsers");
	}
	
	@RequestMapping(value="/validateUser", method=RequestMethod.POST)
	public ModelAndView validateUser(@ModelAttribute("User") User user){
		userDao.validateUser(user);
		return new ModelAndView("redirect:/viewUsers");
	}
	
}
