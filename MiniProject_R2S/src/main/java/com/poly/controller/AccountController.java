package com.poly.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poly.FileUploadUtil;
import com.poly.entity.Account;
import com.poly.entity.Product;
import com.poly.service.AccountService;
import com.poly.service.ParamService;

@Controller
public class AccountController {
	@Autowired
	AccountService accountService;
	@Autowired
	ParamService paramService;


	@RequestMapping("admin/quanliaccount")
	public String showForm(Model m) {
		// tạo account rỗng
		m.addAttribute("ACCOUNT", new Account());
		return "/home/quanliaccount";
	}

	@PostMapping("admin/SaveOrUpdate")
	public String saveOrUpdate(@Validated @ModelAttribute("ACCOUNT") Account ac, BindingResult result, Model m,
			@RequestParam("img") MultipartFile multipartFile) throws IOException {
		if (result.hasErrors() || multipartFile.isEmpty()) {
			m.addAttribute("ERROR_PHOTO", "Không Được Để Trống Ảnh!!!");
			return "/home/quanliaccount";
		}
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		String uploadDir = "assets/img/";
		ac.setPhoto(fileName);
		accountService.save(ac);
		paramService.save(multipartFile, uploadDir);
		m.addAttribute("message","Thêm tài khoản thành công!!!");
		m.addAttribute("ACCOUNT", new Account());
		return "/home/quanliaccount";
	}

	@RequestMapping("admin/views")
	public String showView(Model m) {
		m.addAttribute("ACCOUNTS", accountService.findAll());
		return "/home/viewaccount";
	}

	@RequestMapping(value = "admin/views", params = "btnXóa")
	public String DeleteView(@RequestParam("username") String username, Model m) {
		accountService.deleteById(username);
		return "redirect:/admin/views";
	}

	@GetMapping("admin/quanliaccount/{username}")
	public String EditAcc(@PathVariable("username") String username, Model model) {
		Optional<Account> account = accountService.findById(username);
		if (account.isPresent()) {
			model.addAttribute("ACCOUNT", account.get());
		} else {
			model.addAttribute("ACCOUNT", new Account());
		}
		return "/home/quanliaccount";
	}

}
