package com.poly.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Categories")
public class Category implements Serializable{
	@Id
	@NotBlank(message = "Vui lòng nhập Id loại sản phẩm!")
	String id;
	
	@NotBlank(message = "Vui lòng nhập tên loại sản phẩm!")
	String name;
	@OneToMany(mappedBy = "category")
	List<Product> products;
}
