package com.poly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.DAO.AccountDAO;
import com.poly.DAO.CategoryDAO;
import com.poly.entity.Account;
import com.poly.entity.Category;
import com.poly.service.CookieService;
import com.poly.service.ParamService;
import com.poly.service.SessionService;





@Controller
public class DangNhapController {
	@Autowired
	ParamService paramService;
	@Autowired
	AccountDAO daoAccount;
	@Autowired
	SessionService sessionService;
	@Autowired
	CookieService cookieService;
	
	@GetMapping("/dangnhap")
	public String getDangNhap(Model model) {		
		model.addAttribute("username", cookieService.getValue("username"));
		model.addAttribute("password", cookieService.getValue("password"));
		if(cookieService.getValue("username")!=null && cookieService.getValue("password")!=null){
			model.addAttribute("rmchecked", "checked");
		}else {
			model.addAttribute("rmchecked", "unchecked");
		}
		return "/home/dangnhap";
	}
	@PostMapping("/dangnhap")
	public String getLogin(Model model) {		
		String us = paramService.getString("username", "");
		String pw = paramService.getString("password", "");
		boolean rm = paramService.getBoolean("remember", false);
		
		Account account=null;
		try {
			account = daoAccount.findById(us).get();
		} catch (Exception e) {
			
		}
		
		if (account == null) {
			model.addAttribute("messenge", "Tài khoản không tồn tại!");
			return "/home/dangnhap";
		} else {
			if (!pw.equals(account.getPassword())) {
				model.addAttribute("messenge", "Mật khẩu không chính xác!");
				return "/home/dangnhap";
			} else {
				sessionService.set("user", account);
				String uri = sessionService.get("security-uri");
				if(rm==true) {
		    		cookieService.add("username", us, 10);
		    		cookieService.add("password", pw, 10);
		    	}else {
		    		cookieService.remove("user");
		    		cookieService.remove("password");
		    	}
				
				if (uri != null) {
					return "redirect:" + uri;
				}else {
					return "redirect:/index";
				}
			}
		}

	}
	
	@RequestMapping("/dangxuat")
	public String getLogout() {
				sessionService.remove("user");
				return "redirect:/index";
	}
	
	@Autowired
	CategoryDAO daoCategory;
	@ModelAttribute("danhmuc")
	public List<Category> getDanhMuc(){
		return  daoCategory.findAll();
	}
}
