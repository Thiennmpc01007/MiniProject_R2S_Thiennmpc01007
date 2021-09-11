package com.poly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.poly.DAO.AccountDAO;
import com.poly.DAO.CategoryDAO;
import com.poly.entity.Account;
import com.poly.entity.Category;
import com.poly.service.ParamService;
import com.poly.service.SessionService;



@Controller
public class DoiMatKhauController {
	@Autowired
	ParamService paramService;
	@Autowired
	AccountDAO daoAccount;
	@Autowired
	SessionService sessionService;
	
	@GetMapping("/doimatkhau")
	public String getDoiMatKhau(Model model) {		
		return "/home/doimatkhau";
	}
	@PostMapping("/doimatkhau")
	public String postDoiMatKhau(Model model) {		
		String pwc = paramService.getString("passwordcu", "");
		String pwm = paramService.getString("passwordmoi", "");
		String pwxn = paramService.getString("passwordxacnhan", "");		
		Account account=sessionService.get("user");
		if(pwc.equals(account.getPassword())) {
			if(pwm.equals(pwxn)) {
				account.setPassword(pwm);
				daoAccount.save(account);
				sessionService.set("user", account);
				model.addAttribute("thongbaothanhcong", "Đổi mật khẩu thành công!");
				return "/home/doimatkhau";
			}
			else {
				model.addAttribute("thongbaothatbai", "Mật khẩu xác nhận không chính xác!");
				return "/home/doimatkhau";
			}
		}else {
			model.addAttribute("thongbaothatbai", "Mật khẩu cũ không chính xác!");
			return "/home/doimatkhau";
		}
	
	}
	@Autowired
	CategoryDAO daoCategory;
	@ModelAttribute("danhmuc")
	public List<Category> getDanhMuc(){
		return  daoCategory.findAll();
	}
}
