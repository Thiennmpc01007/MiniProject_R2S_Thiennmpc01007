package com.poly.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poly.DAO.AccountDAO;
import com.poly.DAO.CategoryDAO;
import com.poly.entity.Account;
import com.poly.entity.Category;
import com.poly.entity.Product;
import com.poly.service.SessionService;


@Controller
public class TaiKhoanController {
	@Autowired
	SessionService sessionService;
	@Autowired
	AccountDAO daoAccount;
	@Autowired
	HttpServletRequest app;
	

	Account account =new Account();
	
	@GetMapping("/taikhoan")
	public String getTaiKhoan(Model model) {
	    account=sessionService.get("user");
		model.addAttribute("item", account);
		return "/home/taikhoan";
	}
	@PostMapping("/capnhattaikhoan")
	public String postCapNhatUser(Model model,Account item) {			
		item.setUsername(account.getUsername());
		item.setPassword(account.getPassword());
		item.setPhoto(account.getPhoto());	
		item.setAdmin(account.isAdmin());
		daoAccount.save(item);
		sessionService.set("user", item);
		model.addAttribute("messenger", "Cập nhật tài khoản thành công!");
		model.addAttribute("item", item);
		return "/home/taikhoan";
	}
	
	
	@PostMapping("/admin/sanpham/uphinh_user")
	public String postUpHinh(@RequestParam("photo1") MultipartFile photo1) {	
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
				account.setPhoto(fileName);
			}			
			return "redirect:/taikhoan";
	}
	
	@Autowired
	CategoryDAO daoCategory;
	@ModelAttribute("danhmuc")
	public List<Category> getDanhMuc(){
		return  daoCategory.findAll();
	}
}
