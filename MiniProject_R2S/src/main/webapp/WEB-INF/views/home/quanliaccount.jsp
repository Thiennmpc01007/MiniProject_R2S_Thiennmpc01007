<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Quản Lý Account</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v5.15.3/js/all.js"
	crossorigin="anonymous"></script>
<!-- Google fonts-->
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"
	rel="stylesheet" type="text/css" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="/css/styles.css" rel="stylesheet" />
<style type="text/css">
.error {
	color: red;
}
</style>
</head>
<body id="page-top">
	<!-- Navigation-->
	<nav
		class="navbar navbar-expand-lg bg-secondary text-uppercase fixed-top"
		id="mainNav">
		<div class="container">
			<a class="navbar-brand" href="/index">Mini-Project</a>
			<button
				class="navbar-toggler text-uppercase font-weight-bold bg-primary text-white rounded"
				type="button" data-bs-toggle="collapse"
				data-bs-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				Menu <i class="fas fa-bars"></i>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ms-auto">
					<c:if test="${user!=null}">
						<li class="nav-item dropdown"><a
							class="nav-link py-3 px-0 px-lg-3 dropdown-toggle" href="#"
							id="navbarDropdown" role="button" data-bs-toggle="dropdown"
							aria-expanded="false"> Tài Khoản</a>
							<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
								<li class="nav-item mx-0 mx-lg-1"><a class="dropdown-item"
									href="/taikhoan">Cập nhật tài khoản</a></li>
								<li class="nav-item mx-0 mx-lg-1"><a class="dropdown-item"
									href="/doimatkhau">Đổi mật khẩu</a></li>
								<li class="nav-item mx-0 mx-lg-1"><a class="dropdown-item"
									href="javascript:dangXuat()">Đăng xuất</a></li>
							</ul></li>
					</c:if>
					<c:choose>
						<c:when test="${user.admin==true}">
							<li class="nav-item dropdown"><a
								class="nav-link py-3 px-0 px-lg-3 rounded"
								href="/admin/quanliloaisanpham">QL loại</a></li>
							<li class="nav-item dropdown"><a
								class="nav-link py-3 px-0 px-lg-3 rounded"
								href="/admin/quanlisanpham">Quản lí sản phẩm</a></li>
							<li class="nav-item mx-0 mx-lg-1"><a
								class="nav-link py-3 px-0 px-lg-3 rounded"
								href="/admin/quanliaccount">Quản lí User</a></li>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${user!=null}">
							<li class="nav-item mx-0 mx-lg-1"><a
								class="nav-link py-3 px-0 px-lg-3 rounded"> <Spam> <Strong>${user.fullname}</Strong></Spam></a></li>
						</c:when>
						<c:otherwise>
							<li class="nav-item dropdown"><a
								class="nav-link py-3 px-0 px-lg-3 rounded" href="/dangky">Đăng
									ký</a></li>
							<li class="nav-item dropdown"><a
								class="nav-link py-3 px-0 px-lg-3 rounded" href="/dangnhap">Đăng
									nhập</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
	</nav>
	<hr>
	<hr>
	<hr>
	<hr>
	<hr>

	<div class="container" style="margin-top: 10px;">
		<div class="row"
			style="border: 1px darkgrey solid; border-radius: 10px; width: 50%; margin: 0 auto; padding: 20px;">
			<div class="col-sm-12">
				<h2>Quản Lý Account</h2>
				<form:form action="/admin/SaveOrUpdate" modelAttribute="ACCOUNT"
					method="post" enctype="multipart/form-data">
					<div class="form-group">
						<label>Tài Khoản:</label>
						<form:errors cssClass="error" path="username"></form:errors>
						<form:input type="text" class="form-control" path="username"
							placeholder="Enter UserName" />
					</div>
					<div class="form-group">
						<label>Mật Khẩu:</label>
						<form:errors cssClass="error" path="password"></form:errors>
						<form:input type="password" class="form-control" path="password"
							placeholder="Enter password" />

					</div>
					<div class="form-group">
						<label>Họ và Tên:</label>
						<form:errors cssClass="error" path="fullname"></form:errors>
						<form:input type="text" class="form-control" path="fullname"
							placeholder="Enter fullname" />
					</div>
					<div class="form-group">
						<label>Email:</label>
						<form:errors cssClass="error" path="email"></form:errors>
						<form:input type="text" class="form-control" path="email"
							placeholder="Enter email" />
					</div>
					<div class="form-group">
						<label>Ảnh:</label> <b class="error" style="color: red;">${ERROR_PHOTO}</b>
						<input type="file" class="form-control" name="img"
							accept="img/png, img/jpeg" />
					</div>
					<div class="form-group">
						<label>Quyền:</label>
						<div class="radio">
							<label class="checkbox-inline"> <form:checkbox
									path="activated" />Acticated
							</label> <label class="checkbox-inline"> <form:checkbox
									path="admin" />Admin
							</label>
						</div>
						<p style="color: green;">${message}</p>
					</div>
					<form:button type="submit" class="btn btn-primary">Xác Nhận</form:button>
					<form:button type="reset" class="btn btn-danger">Cancel</form:button>
					<a href="/admin/views" class="btn btn-success">Danh Sách
						Account</a>
				</form:form>
			</div>
		</div>
	</div>
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
	<script src="js/scripts.js"></script>
	<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
	<script type="text/javascript">
		function dangXuat() {
			if (confirm("Bạn mún đăng xuất đúng không?") == true) {
				location.href = "/dangxuat";
			}
		}
	</script>
</body>
</html>
