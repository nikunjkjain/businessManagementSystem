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

import com.webmaven.bean.Product;
import com.webmaven.bean.User;
import com.webmaven.dao.ProductDAO;
import com.webmaven.util.BmsConstants;
import com.webmaven.util.Utility;

@Controller
public class ProductController {
	
	private static final Logger logger = Logger.getLogger(ProductController.class);
	private static final Utility utils = Utility.getInstance();
	
	@Autowired
    private ProductDAO productDao;
	
	public void setProductDao(ProductDAO productDao) {
		this.productDao = productDao;
	}
	
	@RequestMapping(value="/viewProducts", method=RequestMethod.GET)
	public ModelAndView getAllProducts(HttpSession session) {
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		List<Product> productList = productDao.selectAll();
		logger.info("Going to view Products:");
		return new ModelAndView("viewProducts", "productList", productList);
	}
	
	@RequestMapping(value="/addProduct", method=RequestMethod.GET)
	public ModelAndView addProduct(HttpSession session){
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		return new ModelAndView("addProduct");
	}
	
	@RequestMapping(value="/insertProduct", method=RequestMethod.POST)
	public ModelAndView insertProduct(@ModelAttribute("Product") Product product, HttpSession session){
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		User uDetails = (User) session.getAttribute(BmsConstants.USERDETAILS);
		if (uDetails != null) {
			product.setUpdatedBy(uDetails.getUsername());
		}else {
			logger.warn("Got User Obj as Null hence setting updatedBy as Null");
		}
		productDao.insert(product);
		return new ModelAndView("redirect:/viewProducts");
	}
	
	@RequestMapping(value="/updateProduct", method=RequestMethod.POST)
	public ModelAndView updateProduct(@ModelAttribute("Product") Product product, HttpSession session){
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		User uDetails = (User) session.getAttribute(BmsConstants.USERDETAILS);
		if (uDetails != null) {
			product.setUpdatedBy(uDetails.getUsername());
		}else {
			logger.warn("Got User Obj as Null hence setting updatedBy as Null");
		}
		productDao.update(product);
		return new ModelAndView("redirect:/viewProducts");
	}
	
	@RequestMapping(value="/editProduct/{id}/", method=RequestMethod.GET)
	public ModelAndView editProduct(@PathVariable("id") int id, HttpSession session){
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		Product productDetails = productDao.getProductById(id);
		return new ModelAndView("editProduct", "productDetails", productDetails);
	}
	
	@RequestMapping(value="/viewProduct/{id}/", method=RequestMethod.GET)
	public ModelAndView viewProduct(@PathVariable("id") int id, HttpSession session){
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
		Product productDetails = productDao.getProductById(id);
		return new ModelAndView("viewProduct", "productDetails", productDetails);
	}
	
	@RequestMapping(value="/deleteProduct/{id}/", method=RequestMethod.GET)
	public ModelAndView deleteProduct(@PathVariable("id") int id, HttpSession session){
		if(!utils.isValidSession(session))
			return new ModelAndView(LOGOUT_VIEW);
			productDao.delete(id);
		return new ModelAndView("redirect:/viewProducts");
	}
}
