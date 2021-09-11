package com.poly.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poly.DAO.AccountDAO;
import com.poly.DAO.CategoryDAO;
import com.poly.entity.Account;
import com.poly.entity.Category;


@Controller
public class DangKyController {
	@Autowired
	AccountDAO daoAccount;
	@Autowired
	HttpServletRequest app;
	
	
	
	
	@GetMapping("/dangky")
	public String getDangKy(Model model) {	
		Account item = new Account();
		model.addAttribute("item", item);
		return "/home/dangky";
	}
	@PostMapping("/dangky")
	public String postDangKy(Model model,Account item, @RequestParam("photo1") MultipartFile photo1) {	
		if (!photo1.isEmpty()) {
			String fileName = photo1.getOriginalFilename();
			File file = new File(app.getServletContext().getRealPath("assets/img"));
			if (!file.exists()) {
				file.mkdirs();
			}
			try {
				File saveFile = new File(file, fileName);
				photo1.transferTo(saveFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			item.setPhoto(fileName);
		} else {
			item.setPhoto("không có ảnh");
		}

		daoAccount.save(item);
		model.addAttribute("messenger", "Đăng ký thành công!");
		item=new Account();
		model.addAttribute("item", item);
		return "/home/dangky";
	}
	
	
	@Autowired
	CategoryDAO daoCategory;
	@ModelAttribute("danhmuc")
	public List<Category> getDanhMuc(){
		return  daoCategory.findAll();
	}
}
