package com.webmaven.enterprise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.webmaven.enterprise.bean.Product;
import com.webmaven.enterprise.dao.ProductDAO;

@Controller
public class ProductController {
	
	@Autowired
    private ProductDAO productDao;
	
	public void setProductDao(ProductDAO productDao) {
		this.productDao = productDao;
	}

	@RequestMapping(value="/viewProducts", method=RequestMethod.GET)
	public ModelAndView getAllProducts() {
		List<Product> productList = productDao.selectAll();
		return new ModelAndView("viewProducts", "productList", productList);
	}
	
	@RequestMapping(value="/addProduct", method=RequestMethod.GET)
	public ModelAndView addProduct(){
		return new ModelAndView("addProduct");
	}
	
	@RequestMapping(value="/insertProduct", method=RequestMethod.POST)
	public ModelAndView insertProduct(@ModelAttribute("Product") Product product){
		productDao.insert(product);
		return new ModelAndView("redirect:/viewProducts");
	}
	
	@RequestMapping(value="/updateProduct", method=RequestMethod.POST)
	public ModelAndView updateProduct(@ModelAttribute("Product") Product product){
		productDao.update(product);
		return new ModelAndView("redirect:/viewProducts");
	}
	
	@RequestMapping(value="/editProduct/{id}/", method=RequestMethod.GET)
	public ModelAndView editProduct(@PathVariable("id") int id){
		Product productDetails = productDao.getProductById(id);
		return new ModelAndView("editProduct", "productDetails", productDetails);
	}
	
	@RequestMapping(value="/viewProduct/{id}/", method=RequestMethod.GET)
	public ModelAndView viewProduct(@PathVariable("id") int id){
		Product productDetails = productDao.getProductById(id);
		return new ModelAndView("viewProduct", "productDetails", productDetails);
	}
	
	@RequestMapping(value="/deleteProduct/{id}/", method=RequestMethod.GET)
	public ModelAndView deleteProduct(@PathVariable("id") int id){
		int productDetails = productDao.delete(id);
		return new ModelAndView("redirect:/viewProducts");
	}
}
