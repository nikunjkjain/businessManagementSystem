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

import com.webmaven.bean.Balance;
import com.webmaven.bean.SalesAndPayment;
import com.webmaven.bean.User;
import com.webmaven.dao.MasterDAO;
import com.webmaven.dao.SalesAndPaymentDAO;
import com.webmaven.dao.UserDAO;
import com.webmaven.dao.ViewsDAO;
import com.webmaven.util.BmsConstants;
import com.webmaven.util.EncryptDecrypt;
import com.webmaven.util.Utility;

@Controller
public class UserController {
	
	private static final Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private Utility utils;
	
	public void setUtils(Utility utils) {
		this.utils = utils;
	}

	@Autowired
    private UserDAO userDao;
	
	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}
	
	@Autowired
    private ViewsDAO viewsDao;
	
	public void setViewDao(ViewsDAO viewsDao) {
		this.viewsDao = viewsDao;
	}
	
	@Autowired
    private MasterDAO masterDao;
	
	public void setMasterDao(MasterDAO masterDao) {
		this.masterDao = masterDao;
	}
	
	@Autowired
    private SalesAndPaymentDAO salesAndPaymentDao;
	
	public SalesAndPaymentDAO getSalesAndPaymentDao() {
		return salesAndPaymentDao;
	}

	public void setSalesAndPaymentDao(SalesAndPaymentDAO salesAndPaymentDao) {
		this.salesAndPaymentDao = salesAndPaymentDao;
	}
	
	@RequestMapping(value="/viewUsers", method=RequestMethod.GET)
	public ModelAndView getAllUsers(HttpSession session) {
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		List<User>userList = userDao.selectAll();
		return new ModelAndView("viewUsers", "userList", userList);
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView welcome(HttpSession session){
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		return new ModelAndView("login");
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView login(HttpSession session){
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
		List<Balance> balance;
		List<SalesAndPayment> reminder;
		balance = viewsDao.selectAll();
		reminder = salesAndPaymentDao.selectReminder();
		Map<String, Object> models = new HashMap<String, Object>();
		models.put("balance", balance);
		models.put("reminder", reminder);
		return new ModelAndView("index", models);
	}
	
	@RequestMapping(value="/addUser", method=RequestMethod.GET)
	public ModelAndView addUser(HttpSession session){
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		return new ModelAndView("addUser");
	}
	
	@RequestMapping(value="/insertUser", method=RequestMethod.POST)
	public ModelAndView insertUser(@ModelAttribute("User") User user, HttpSession session) throws Exception{
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		user.setUpdatedBy(utils.getUserIdFromSession(session));
		String password = EncryptDecrypt.getHexEncryptText(user.getPassword(), BmsConstants.AES_KEY);
		user.setPassword(password);
		userDao.insert(user);
		return new ModelAndView("redirect:/viewUsers");
	}
	
	@RequestMapping(value="/updateUser", method=RequestMethod.POST)
	public ModelAndView updateUser(@ModelAttribute("User") User user, HttpSession session){
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		user.setUpdatedBy(utils.getUserIdFromSession(session));
		userDao.update(user);
		return new ModelAndView("redirect:/viewUsers");
	}
	
	@RequestMapping(value="/updateUserPassword", method=RequestMethod.POST)
	public ModelAndView updateUserPassword(@ModelAttribute("User") User user, HttpSession session) throws Exception{
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		user.setUpdatedBy(utils.getUserIdFromSession(session));
		String password = EncryptDecrypt.getHexEncryptText(user.getPassword(), BmsConstants.AES_KEY);
		user.setPassword(password);
		userDao.updateUserPassword(user);
		return new ModelAndView("redirect:/viewUsers");
	}
	
	@RequestMapping(value="/editUser/{id}/", method=RequestMethod.GET)
	public ModelAndView editUser(@PathVariable("id") int id, HttpSession session){
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		User userDetails = userDao.getUserById(id);
		return new ModelAndView("editUser", "userDetails", userDetails);
	}
	
	@RequestMapping(value="/changeUserPassword/{id}/", method=RequestMethod.GET)
	public ModelAndView changeUserPassword(@PathVariable("id") int id, HttpSession session){
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		User userDetails = userDao.getUserById(id);
		return new ModelAndView("changeUserPassword", "userDetails", userDetails);
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
			userDao.delete(id);
		return new ModelAndView("redirect:/viewUsers");
	}
	
	@RequestMapping(value="/validateUser", method=RequestMethod.POST)
	public ModelAndView validateUser(@ModelAttribute("User") User user, HttpSession session) throws Exception{
		if(user != null){
			String password = EncryptDecrypt.getHexEncryptText(user.getPassword(), BmsConstants.AES_KEY);
			user.setPassword(password);
		}
		User userDetails = userDao.validateUser(user);
		List<Balance> balance;
		List<SalesAndPayment> reminder;
		Map<String, Object> models = new HashMap<String, Object>();
		if(userDetails == null) {
			return new ModelAndView(LOGOUT_VIEW);
		}else {
			if(user != null && userDetails != null) {
				if(user.getUsername().equals(userDetails.getUsername())){
				session.setAttribute(BmsConstants.ISVALID, true);
				session.setAttribute(BmsConstants.USERDETAILS, userDetails);
				session.setAttribute(BmsConstants.USERNAME, userDetails.getName());
				session.setAttribute(BmsConstants.ID, userDetails.getId());
				utils.init();
				session.setAttribute(BmsConstants.MKEYVAL, utils.getMasterIdAndKeyVal());
				session.setAttribute(BmsConstants.MVALKEY, utils.getMasterIdAndValKey());
				session.setAttribute(BmsConstants.PVALKEY, utils.getProductIdKeyMap());
				session.setAttribute(BmsConstants.CVALKEY, utils.getCustomerIdKeyMap());
				balance = viewsDao.selectAll();
				reminder = salesAndPaymentDao.selectReminder();
				models.put("balance", balance);
				models.put("reminder", reminder);
				}else {
					return new ModelAndView(LOGOUT_VIEW);
				}
			}else {
				return new ModelAndView(LOGOUT_VIEW);
			}
		}
		return new ModelAndView("index", models);
	}
	
}
