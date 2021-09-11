<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Đăng Nhập</title>
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
				<div class="alert-success" role="alert" style="margin-bottom: 15px;">
					${messenger}</div>
				<div class="alert-danger" role="alert" style="margin-bottom: 15px;">
					${messengerLoi}</div>

				<div class="container d-flex align-items-center flex-column">
					<div class="span3">
						<form:form action="#" modelAttribute="item" method="POST"
							enctype="multipart/form-data">
							<h3>Quản Lý Sản Phẩm</h3>
							<div class="control-group">
								<label><strong>Loại sản phẩm:</strong> <a
									href="/admin/quanliloaisanpham" style="color: blue;">Thêm
										loại</a></label><br>
								<form:select path="category.id" cssStyle="width: 400px;"
									class="form-control">
									<form:options items="${loaisp}"></form:options>
								</form:select>
							</div>
							<div class="control-group">
								<label><strong>Tên sản phẩm:</strong></label><br>
								<form:input path="name" class="form-control"
									placeholder="Nhập tên sản phẩm?" cssStyle="width: 400px;" />
								<div>
									<form:errors path="name" element="li" />
								</div>
							</div>
							<div class="form-group">
								<label><strong>Giá sản phẩm:</strong></label>
								<div class="controls">
									<form:input path="price" class="form-control"
										placeholder="Nhập giá sản phẩm?" cssStyle="width: 400px;" />
									<form:errors path="price" />
								</div>
							</div>
							<div
								style="background-color: white; height: 160px; width: 170px; margin-bottom: 10px;">
								<img src="/assets/img/${item.image}" alt="" />
							</div>

							<div class="controls">
								<input type="file" name="photo1" >
							</div>
							<div class="controls">
								<button formaction="/admin/sanpham/uphinh"
									class="btn btn-secondary">Tải hình</button>
							</div>
							<div class="control-group">
								<label><strong>Miêu tả:</strong></label>
								<div class="controls">
									<form:textarea path="mieuta" class="form-control"
										placeholder="Nhập miêu tả sản phẩm?" cssStyle="width: 400px;" />
								</div>
							</div>


							<div class="actions">
								<button formaction="/admin/sanpham/them" class="btn btn-success">Thêm</button>

								<button formaction="/admin/sanpham/capnhat"
									class="btn btn-warning">Cập nhật</button>
								<button formaction="/admin/sanpham/moi" class="btn btn-primary">Làm
									mới</button>
							</div>

						</form:form>
					</div>
				</div>

			</div>
		</div>
	</div>
	<div class="span9">
		<table class="table table-striped">
			<tr>
				<th scope="col">Id</th>
				<th scope="col">Name</th>
				<th scope="col">Price</th>
				<th scope="col">Date</th>
				<th scope="col"></th>
			</tr>
			<c:forEach var="item" items="${page.content}">
				<tr>
					<td>${item.id}</td>
					<td>${item.name}</td>
					<td><fmt:formatNumber value="${item.price}" /></td>
					<td>${item.createDate}</td>
					<td><a class="btn btn-danger"
						href="javascript:deleteSP(${item.id})">xóa</a> <a
						class="btn btn-info" href="/admin/sanpham/edit/${item.id}">edit</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<div style="text-align: center;">
			<a href="/admin/quanlisanpham?p=0" class="btn btn-primary">Đầu</a> <a
				href="/admin/quanlisanpham?p=${page.number-1 < 0 ? 0:page.number-1}"
				class="btn btn-primary">Trước</a> <a
				href="/admin/quanlisanpham?p=${page.number+1 > page.totalPages-1 ? page.totalPages-1:page.number+1}"
				class="btn btn-primary">Tiếp</a> <a
				href="/admin/quanlisanpham?p=${page.totalPages-1}"
				class="btn btn-primary">Cuối</a>
		</div>
	</div>
	<script type="text/javascript">
		function deleteSP(id) {
			if (confirm("Bạn mún xóa sản phẩm đúng không?") == true) {
				location.href = "/admin/sanpham/xoa/" + id;
			}
		}
	</script>
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
