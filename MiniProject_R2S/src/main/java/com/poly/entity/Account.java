package com.poly.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "Accounts")
public class Account  implements Serializable {
	@Id
	@NotEmpty(message = "Tài Khoản Không Được Để Trống!!!")
	String username;
	@NotEmpty(message = "Mật Khẩu Không Được Để Trống!!!") 
	String password;
	@NotEmpty(message = "Họ Tên Không Được Để Trống!!!")
	String fullname;
	@NotEmpty(message = "Email Không Được Để Trống!!!")
	@Email(message = "Email Không Đúng Định Dạng!!!")
	String email;
	String photo;
	boolean activated = true;
	boolean admin = false;

}
