
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
<title>Cập Nhật TK</title>
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
<link href="css/styles.css" rel="stylesheet" />
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



	<div>
		<div class="row"
			style="border: 1px darkgrey solid; border-radius: 10px; width: 50%; margin: 0 auto; padding: 30px;">
			<div class="col-sm-12">
				<div class="span7">
					<form:form action="/capnhattaikhoan" modelAttribute="item"
						method="POST" enctype="multipart/form-data">
						<h3>Cập Nhật Tài Khoản</h3>
						<div class="control-group">
							<label class="control-label">Họ và tên:</label>
							<div class="controls">
								<form:input path="fullname" placeholder="Nhập họ tên của bạn?"
									class="form-control" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">Địa chỉ email:</label>
							<div class="controls">
								<form:input path="email" placeholder="Nhập email của bạn?"
									class="form-control" />
							</div>
						</div>
						<div class="controls">
							<input type="file" name="photo1">
						</div>
						<div
							style="background-color: white; height: 210px; width: 170px; margin-bottom: 10px; margin-top: 15px;">
							<img src="/assets/img/${item.photo}" alt="" />
						</div>
						<div class="controls">
							<button formaction="/admin/sanpham/uphinh_user"
								class="btn btn-danger">Tải hình</button>
						</div>
						<p style="color: green;">${messenger}</p>
						<div class="actions">
							<input tabindex="9" class="btn btn-primary" type="submit"
								value="Cập nhật">
						</div>
					</form:form>
				</div>

				<div class="span3"></div>
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
