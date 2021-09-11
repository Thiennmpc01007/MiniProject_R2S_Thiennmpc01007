package com.poly.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.multipart.MultipartFile;

import com.poly.DAO.CategoryDAO;
import com.poly.DAO.ProductDAO;
import com.poly.entity.Category;
import com.poly.entity.Product;
import com.poly.service.ParamService;
import com.poly.service.SessionService;


@Controller
public class SanPhamController {
	@Autowired
	ProductDAO daoProduct;
	@Autowired
	CategoryDAO daoCategory;
	@Autowired
	SessionService sessionService;
	@Autowired
	HttpServletRequest app;
	@Autowired
	ParamService paramService;
	
	Product product = new Product();
	String messenger;
	String messengerLoi;
	
	@GetMapping("/sanpham/{idloaisp}")
	public String showSP(Model model,
		@PathVariable("idloaisp") Optional<String> idLoaiSanPham,
		@RequestParam("p") Optional<Integer> p) {
			String loaiSPs = idLoaiSanPham.orElse(sessionService.get("loaiSP"));			
			sessionService.set("loaiSP", loaiSPs);
			Pageable pageable = PageRequest.of(p.orElse(0), 9);
			Page<Product> page =  daoProduct.showByLoaiSP(loaiSPs, pageable);
			model.addAttribute("page", page);
			model.addAttribute("idLoaiSP", loaiSPs);
			Category category = daoCategory.findById(loaiSPs).get();
			model.addAttribute("category", category);
			return "/home/index";
	}
	
	@GetMapping("/admin/sanpham/edit/{idSP}")
	public String getSPEdit(Model model,@PathVariable("idSP") Integer idSP) {
		product = daoProduct.findById(idSP).get();
			return "redirect:/admin/quanlisanpham";
	}
	@GetMapping("/admin/quanlisanpham")
	public String paginate(Model model ,@RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0), 14);
		Page<Product> page = daoProduct.findAll(pageable);
		model.addAttribute("page", page);		
		model.addAttribute("item", product);	
		model.addAttribute("messenger", messenger);	
		model.addAttribute("messengerLoi", messengerLoi);	
		return "/home/quanlisanpham";
	}
	

	
	@PostMapping("/admin/sanpham/uphinh")
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
				product.setImage(fileName);
			}			
			return "redirect:/admin/quanlisanpham";
	}
	
	@PostMapping("/admin/sanpham/them")
	public String postThemSp(@Validated Product item,BindingResult rs) {	
		if(rs.hasErrors()) {
			messenger=null;
			messengerLoi="Thêm sản phẩm thất bại!";
		}else {
		    item.setImage(product.getImage());
			daoProduct.save(item);
			messenger="Thêm sản phẩm thành công!";
			messengerLoi=null;
			product = new Product();		
		}
		return "redirect:/admin/quanlisanpham";
	}
	
	@PostMapping("/admin/sanpham/capnhat")
	public String postCapNhat(@Validated Product item,BindingResult rs) {	
		if(rs.hasErrors()) {
			messenger=null;
			messengerLoi="Cập nhật sản phẩm thất bại!";
		}else {
			item.setId(product.getId());
			item.setImage(product.getImage());
			item.setCreateDate(product.getCreateDate());	
			daoProduct.save(item);
			messenger="Cập nhật sản phẩm thành công!";
			messengerLoi=null;
		}
		if(item.getId()!=null) {
		   return "redirect:/admin/sanpham/edit/"+item.getId();
		}else {
			return "redirect:/admin/quanlisanpham";
		}
	}
	
	@PostMapping("/admin/sanpham/moi")
	public String postMoi() {	
		product = new Product();
		String messenger=null;
		String messengerLoi=null;
		return "redirect:/admin/quanlisanpham";
	}
	
	@GetMapping("/admin/sanpham/xoa/{idSP}")
	public String postXoa(@PathVariable("idSP") Integer idSP) {	
		try {
			Product p = daoProduct.findById(idSP).get();
			if(p!=null) {
				  daoProduct.deleteById(idSP);
				  messenger="Xóa thành công!";
				  messengerLoi=null;
			}else {
				messengerLoi="không có sản phẩm này!";
				messenger=null;
			}
		} catch (Exception e) {
			messengerLoi="không thể xóa sản phẩm đã được bán!";
			messenger=null;
		}
		return "redirect:/admin/quanlisanpham";
	}
	
	
	@ModelAttribute("actives")
	public Map<Boolean, String> getActives(){
		Map<Boolean, String> map = new HashMap<>();
		map.put(true, "Còn hàng");
		map.put(false, "Không còn hàng");
		return map;
	}
	@ModelAttribute("loaisp")
	public Map<String, String> getloaiSP(){
		Map<String, String> map = new HashMap<>();
		List<Category> categorys = daoCategory.findAll();
		for (Category category : categorys) {
			map.put(category.getId(), category.getName());
		}		
		return map;
	}
	
	@ModelAttribute("danhmuc")
	public List<Category> getDanhMuc(){
		return  daoCategory.findAll();
	}
	
}
