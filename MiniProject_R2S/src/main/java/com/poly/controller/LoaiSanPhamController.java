package com.poly.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.DAO.CategoryDAO;
import com.poly.entity.Category;


@Controller
public class LoaiSanPhamController {
	@Autowired
	CategoryDAO daoCategory;
	
	
	Category category = new Category();
	String messenger;
	String messengerLoi;
	
	
	@GetMapping("/admin/loaisanpham/edit/{idLSP}")
	public String getSPEdit(Model model,@PathVariable("idLSP") String idLSP) {
		category = daoCategory.findById(idLSP).get();
			return "redirect:/admin/quanliloaisanpham";
	}
	@GetMapping("/admin/quanliloaisanpham")
	public String paginate(Model model ,@RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0), 7);
		Page<Category> page = daoCategory.findAll(pageable);
		model.addAttribute("page", page);		
		model.addAttribute("item", category);	
		model.addAttribute("messenger", messenger);	
		model.addAttribute("messengerLoi", messengerLoi);	
		return "/home/quanliloaisanpham";
	}
	@PostMapping("/admin/loaisanpham/them")
	public String postThemSp(@Validated Category item,BindingResult rs) {	
		if(rs.hasErrors()) {
			messenger=null;
			messengerLoi="Thêm loại sản phẩm thất bại!";
		}else {
			daoCategory.save(item);
			messenger="Thêm loại sản phẩm thành công!";
			messengerLoi=null;
			category = new Category();
		}
		return "redirect:/admin/quanliloaisanpham";
	}
	
	@PostMapping("/admin/loaisanpham/capnhat")
	public String postCapNhat(@Validated Category item,BindingResult rs) {	
		if(rs.hasErrors()) {
			messenger=null;
			messengerLoi="Cập nhật loại sản phẩm thất bại!";
		}else {
			daoCategory.save(item);
			messenger="Cập nhật loại sản phẩm thành công!";
			messengerLoi=null;
		}
		if(item.getId()!=null) {
		   return "redirect:/admin/loaisanpham/edit/"+item.getId();
		}else {
			return "redirect:/admin/quanliloaisanpham";
		}
	}
	
	@PostMapping("/admin/loaisanpham/moi")
	public String postMoi() {	
		category = new Category();
		String messenger=null;
		String messengerLoi=null;
		return "redirect:/admin/quanliloaisanpham";
	}
	
	@GetMapping("/admin/loaisanpham/xoa/{idLSP}")
	public String postXoa(@PathVariable("idLSP") String idLSP) {	
		try {
			Category p = daoCategory.findById(idLSP).get();
			if(p!=null) {
				daoCategory.deleteById(idLSP);
				  messenger="Xóa thành công!";
				  messengerLoi=null;
			}else {
				messengerLoi="không có loại sản phẩm này!";
				messenger=null;
			}
		} catch (Exception e) {
			messengerLoi="không thể xóa loại sản phẩm đã có sản phẩm!";
			messenger=null;
		}
		return "redirect:/admin/quanliloaisanpham";
	}
	
	
	
	@ModelAttribute("danhmuc")
	public List<Category> getDanhMuc(){
		return  daoCategory.findAll();
	}
}
