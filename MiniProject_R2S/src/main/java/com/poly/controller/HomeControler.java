package com.poly.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.DAO.CategoryDAO;
import com.poly.DAO.ProductDAO;
import com.poly.entity.Account;
import com.poly.entity.Category;
import com.poly.entity.Product;
import com.poly.service.SessionService;


@Controller
public class HomeControler {
	@Autowired
	ProductDAO daoProduct;
	@Autowired
	SessionService sessionService;
	
	
	@ModelAttribute("user")
	public Account getUser() {
		return sessionService.get("user");
	}
	String sxTheo = "name";
	@RequestMapping("/index")
	public String getIndex(Model model, Optional<Integer> p) {
		Sort s = Sort.by(Direction.ASC, sxTheo);
		Pageable pa = PageRequest.of(p.orElse(0), 12, s);
		Page<Product> page = daoProduct.findAll(pa);
		model.addAttribute("page", page);
		return "/home/index";
	}

	@RequestMapping("/lienhe")
	public String getLienHe() {
		return "/home/lienhe";
	}

	@RequestMapping("/gioithieu")
	public String getGioiThieu() {
		return "home/gioithieu";
	}
	
	
	@RequestMapping("/giohang")
	public String getGioHang() {
		return "giohang";
	}
	@RequestMapping("/layout")
	public String ga() {
		return "/layout/index";
	}
	
	@Autowired
	CategoryDAO daoCategory;
	@ModelAttribute("danhmuc")
	public List<Category> getDanhMuc(){
		return  daoCategory.findAll();
	}

}
