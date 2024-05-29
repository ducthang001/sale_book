<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix='c'%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Đăng Nhập</title>

<link rel="stylesheet" href="<c:url value ='/include/css/style.css' />">
<link rel="stylesheet"
	href="<c:url value ='/include/css/sach_info.css' />">
<link rel="stylesheet"
	href="<c:url value ='/include/css/category.css' />">
<link rel="stylesheet" href="<c:url value ='/include/css/signup.css' />">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="icon" href="<c:url value ='/images/favico.png' />"
	sizes="32x32" />
</head>
<body>
	<!-- Navigation Header-->
	<header class="_100vw">
		<div class="_1200px flex_between pd-10_0">
			<div class="nav_left">
				<div class="logo">
					<a href="<c:url value ='/home.htm' />"><img
						src="<c:url value ='/images/logo.png' />" alt="Logo"><span>BOOK
							STORE</span></a>
				</div>
				<div class="search">
					<form action="<c:url value ='/search.htm' />">
						<input type="text" name="keyword"
							placeholder="Nhập sản phẩm cần tìm kiếm...">
						<button class="btn_search">
							<i class="fa fa-search"></i>
						</button>
					</form>
				</div>
			</div>
			<div class="nav_right">
				<a href="<c:url value ='/account/login.htm' />"><i
					class="fa fa-user"></i></a> <a
					href="<c:url value ='/account/cart.htm' />"> <i
					class="fa fa-shopping-cart"></i></a>
			</div>
		</div>
	</header>

	<!-- sigup -->
	<div class="flex_center signup bg-white">
		<div class="_1200px flex_center signup_container">
			<div class="signup_container_left">
				<h2>Đăng Nhập Tài Khoản</h2>
				<form action="" method="post" class="form_signup">
					<div class="input_sign input_username flex-col">
						<label>Tên đăng nhập</label> <input type="text" name="username">
					</div>
					<div class="input_sign input_password flex-col">
						<label>Mật khẩu</label> <input type="password" name="password">
					</div>

					<div class="g-recaptcha"
						data-sitekey="6LdXdiMUAAAAAKirZUzx5jMHJ-Gs65uX-Kw5K7YF"></div>

					<div class="check_remember"
						style="display: flex; align-items: center; margin-top: 7px; color: red">
						${login_status}</div>

					<div class="btn_signup_signin flex_center mg-10_0">
						<button class="btn">Đăng Nhập</button>
					</div>
				</form>

				<div class="login_signin">
					Bạn chưa có tài khoản <a href="signup.htm">Đăng ký</a>
				</div>
				<div class="losspass">
					<a href="<c:url value ='/account/verify.htm' />">Quên mật khẩu</a>
				</div>
			</div>
			<div class="signup_container_right"></div>
		</div>
	</div>

	<script src="https://www.google.com/recaptcha/api.js"></script>

	<script type="text/javascript">
		console.log(document.cookie);
		document.cookie = "userC" + '=; expires=Thu, 01-Jan-70 00:00:01 GMT;';
		console.log(document.cookie);
		console.log("Value = " + getCookie("passwordC"));

		function getCookie(cname) {
			let name = cname + "=";
			let decodedCookie = decodeURIComponent(document.cookie);
			let ca = decodedCookie.split(';');
			for (let i = 0; i < ca.length; i++) {
				let c = ca[i];
				while (c.charAt(0) == ' ') {
					c = c.substring(1);
				}
				if (c.indexOf(name) == 0) {
					return c.substring(name.length, c.length);
				}
			}
			return "";
		}
	</script>
</body>
</html>