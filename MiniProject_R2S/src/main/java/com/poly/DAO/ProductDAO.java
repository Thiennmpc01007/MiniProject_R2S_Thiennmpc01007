package com.poly.DAO;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Integer>{
	@Query("SELECT o FROM Product o WHERE o.category.id LIKE ?1")
	Page<Product> showByLoaiSP(String idCategory, Pageable pageable);
	
	@Query("SELECT o FROM Product o WHERE o.category.id NOT LIKE ?1")
	Page<Product> showSanPhamKhac(String idCategory, Pageable pageable);
	
	
	
}
