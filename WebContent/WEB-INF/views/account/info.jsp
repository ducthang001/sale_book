<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix='c'%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Thông tin tài khoản ${user.username}</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css" />

<link rel="preconnect" href="https://fonts.gstatic.com" />
<link
	href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap"
	rel="stylesheet" />
<link rel="stylesheet"
	href="<c:url value ='/include/css/bootstrap.css' />" />
<link rel="stylesheet" href="<c:url value ='/include/css/toast.css' />" />
<link rel="stylesheet" href="<c:url value ='/include/css/app.css' />" />
<link rel="stylesheet" href="<c:url value ='/include/css/info.css' />" />
<link rel="icon" href="<c:url value ='/images/favico.png' />"
	sizes="32x32" />
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

</head>

<body>
	<div id="app doan">
		<!-- Xu ly toast -->
		<div class="toast_class"></div>
		<!-- Ket thuc xu ly toast -->
		<div id="sidebar" class="active">
			<div class="sidebar-wrapper active">
				<div class="sidebar-header">
					<div class="d-flex justify-content-between">
						<div class="logo">
							<a href="<c:url value ='/home.htm' />"
								style="text-decoration: none"><img
								src="<c:url value ='/images/logo.png' />" style="height: 40px"
								alt="Logo" srcset="" /> <span style="font-size: 25px">BOOKSTORE</span></a>
						</div>
					</div>
				</div>
				<div class="sidebar-menu">
					<ul class="menu">
						<li class="sidebar-title">Danh Mục Khách Hàng</li>

						<li class="sidebar-item active"><a
							href="<c:url value ='/account/info.htm' />" class="sidebar-link">
								<i class="fas fa-chart-bar"></i> <span>Thông tin tài
									khoản</span>
						</a></li>

						<li class="sidebar-item"><a
							href="<c:url value ='/account/cart.htm' />" class="sidebar-link">
								<i class="fas fa-book"></i> <span>Giỏ hàng</span>
						</a></li>

						<li class="sidebar-item"><a
							href="<c:url value ='/account/order.htm' />" class='sidebar-link'>
								<i class="fas fa-cart-plus"></i> <span>Danh sách đơn hàng</span>
						</a></li>
					</ul>
				</div>
				<button class="sidebar-toggler btn x">
					<i data-feather="x"></i>
				</button>
			</div>
		</div>
		<div id="main">
			<div class="page-content">
				<section class="row">
					<div class="col-12 col-lg-12">
						<div class="row">
							<div class="col-6 col-lg-3 col-md-6">
								<div class="card">
									<div class="card-body px-3 py-4-5">
										<div class="row">
											<div class="col-md-4">
												<div class="stats-icon purple">
													<i class="fas fa-money-bill-alt text-c-red f-18"></i>
												</div>
											</div>
											<div class="col-md-8">
												<h6 class="text-muted font-semibold">Số tiền đã mua</h6>
												<h6 class="font-extrabold mb-0">
													<fmt:formatNumber type="number" pattern="##,###"
														value="${amountspent}" />
													đ
												</h6>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-6 col-lg-3 col-md-6">
								<div class="card">
									<div class="card-body px-3 py-4-5">
										<div class="row">
											<div class="col-md-4">
												<div class="stats-icon blue">
													<i class="fas fa-database text-c-blue f-18"></i>
												</div>
											</div>
											<div class="col-md-8">
												<h6 class="text-muted font-semibold">Đơn thành công</h6>
												<h6 class="font-extrabold mb-0">${ordersuccesscount}
													đơn</h6>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-6 col-lg-3 col-md-6">
								<div class="card">
									<div class="card-body px-3 py-4-5">
										<div class="row">
											<div class="col-md-4">
												<div class="stats-icon green">
													<i class="fas fa-dollar-sign text-c-green f-18"></i>
												</div>
											</div>
											<div class="col-md-8">
												<h6 class="text-muted font-semibold">Đang chờ xác nhận</h6>
												<h6 class="font-extrabold mb-0">${pendingordercount}
													đơn</h6>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-6 col-lg-3 col-md-6">
								<div class="card">
									<div class="card-body px-3 py-4-5">
										<div class="row">
											<div class="col-md-4">
												<div class="stats-icon red">
													<i class="fas fa-user text-c-yellow f-18"></i>
												</div>
											</div>
											<div class="col-md-8">
												<h6 class="text-muted font-semibold">Login Info</h6>
												<h6 class="font-extrabold mb-0">
													<a href="<c:url value ='/account/login.htm' />">${user.username}</a>
													| <span><a
														href="<c:url value ='/account/logout.htm' />">Logout</a></span>
												</h6>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<!-- Phan tim kiem -->
						<div class="row">
							<div class="col-12">
								<div class="col-lg-12 stretch-card" style="padding: 0">
									<div class="card">
										<div class="card-body">
											<h4 class="card-title"
												style="margin-bottom: 20px; text-align: center;">Chỉnh
												sửa thông tin tài khoản</h4>
											<div class="col-12"
												style="display: flex; justify-content: center;">
												<div class="col-6">
													<form action="<c:url value ='/account/info.htm' />"
														method="post" id="form_edit_info_submit">
														<div style="margin-bottom: 20px">
															<label class="form-label">Tên Đăng Nhập</label> <input
																class="form-control showordisable" name="username"
																disabled value="${user.username}" type="text"
																placeholder="Nhập tên tài khoản">
														</div>
														<div style="margin-bottom: 20px">
															<label class="form-label">Địa chỉ Email</label> <input
																class="form-control showordisable" id="info_email_input"
																name="email" value="${user.email}" type="email"
																placeholder="Nhập địa chỉ email">
															<p class="text-danger"
																style="font-size: 14px; font-weight: bold; display: none;">Địa
																chỉ Email không hợp lệ</p>
														</div>

														<div style="margin-bottom: 20px">
															<label class="form-label">Số điện thoại</label> <input
																class="form-control showordisable" id="info_phone_input"
																name="phone" value="${user.phone}" type="number"
																placeholder="Nhập số điện thoại">
															<p class="text-danger"
																style="font-size: 14px; font-weight: bold; display: none;">Số
																điện thoại không hợp lệ</p>
														</div>

														<div style="margin-bottom: 20px">
															<label class="form-label">Năm Sinh</label> <input
																class="form-control showordisable" name="age"
																value="${user.age}" type="number"
																placeholder="Nhập số tuổi bạn hiện tại">
														</div>
														<div style="margin-bottom: 20px">
															<label class="form-label">Địa chỉ</label> <input
																class="form-control showordisable" name="address"
																value="${user.address}" type="text"
																placeholder="Nhập địa chỉ hiện tại">
														</div>
														<div style="margin-bottom: 20px; display: flex">
															<div style="margin: 0; margin-right: 20px;"
																class="form-label">Giới tính</div>
															<div class="form-check" style="margin-right: 20px;">
																<input class="form-check-input" type="radio"
																	name="gender" value="1"
																	<c:if test="${user.gender == 1}">checked</c:if> /> <label
																	class="form-check-label">Nam</label>
															</div>
															<div class="form-check">
																<input class="form-check-input" type="radio"
																	name="gender" value="0"
																	<c:if test="${user.gender == 0}">checked</c:if> /> <label
																	class="form-check-label">Nữ</label>
															</div>
														</div>


														<div style="margin-bottom: 20px; text-align: center">
															<input name="role" value=${user.role } type="hidden" />
															<input name="user_id"
																value=${user.user_id
																} type="hidden" />
															<button type="button" id="btn_edit_info"
																class="btn btn-success">Cập nhật thông tin</button>
														</div>
													</form>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>



						<div class="row">
							<div class="col-12">
								<div class="col-lg-12 stretch-card" style="padding: 0">
									<div class="card">
										<div class="card-body">
											<h4 class="card-title"
												style="margin-bottom: 20px; text-align: center;">Thay
												đổi mật khẩu</h4>
											<div class="col-12"
												style="display: flex; justify-content: center;">
												<div class="col-6">
													<form
														action="<c:url value ='/account/updatepassword.htm' />"
														method="post">

														<%-- JSP comment --%>
														<div style="margin-bottom: 20px">
															<label class="form-label">Nhập mật khẩu cũ</label> <input
																class="form-control showordisable" name="oldpassword"
																type="password" placeholder="Nhập mật khẩu cũ">
														</div>
														<div style="margin-bottom: 20px">
															<label class="form-label">Nhập mật khẩu mới</label> <input
																class="form-control showordisable" name="newpassword"
																type="password" placeholder="Nhập mật khẩu mới">
														</div>

														<div style="margin-bottom: 20px">
															<label class="form-label">Nhập lại mật khẩu mới</label> <input
																class="form-control showordisable" name="renewpassword"
																type="password" placeholder="Nhập lại mật khẩu mới">
														</div>
														<div style="margin-bottom: 20px; text-align: center">
															<button type="submit" class="btn btn-success">Cập
																nhật mật khẩu</button>
														</div>
													</form>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<!-- Ket thuc tim kiem  -->
					</div>
				</section>
			</div>

		</div>
	</div>
	<script type="text/javascript">
    
    function createToastHTML(message, type, color, icon){ //#47f764
		var toast_class = document.querySelector(".toast_class");
		var div = document.createElement("div");
		var html = "<div class = 'toast_to_left' style='position:relative; height: 90px;margin-bottom: 10px;'>"+
    	    "<div class='toast_container' style = 'border-left: 8px solid "+color+" !important;'>"+
            "<i class='"+icon+" toast_icon' style = 'color: "+color+" !important;'></i>"+
            "<div class='toast_content'>"+
              "<span style='font-weight: 700; color: "+color+";'>"+type+"</span>"+
              "<span class='toast_content_name' style='color: #656565'>"+message+"</span>"+
           " </div>"+
            "<div>"+
              "<i class='fa fa-times toast_close' style='font-size: 20px; cursor: pointer; color: #656565'aria-hidden='true'></i>"+
            "</div></div></div>";
            div.innerHTML = html;
        toast_class.appendChild(div);
  		var toast_noi_dung = div.querySelector(".toast_content_name");
  	    var toast_container = div.querySelector(".toast_container");
  	    
			setTimeout(() => {
			    toast_container.classList.remove("toast_to_left");
			    toast_container.classList.add("toast_to_right");
			}, 2000);
			
			setTimeout(() => {
			    div.remove();
			}, 3000);

		  	var toast_close = div.querySelector(".toast_close");
		  	console.log(toast_close);
		  	toast_close.addEventListener("click", () => {
		    	div.remove();
		  	});
	}
    
    var x = '${update}';
	if(x != ""){
		if(x == '-2'){ // == 0 sai order_old
    		// Toast thất bại
	  		createToastHTML("Địa chỉ email này đã tồn tại trên hệ thống!", "ERROR", "#DC3545", "fas fa-exclamation-triangle");
    	}else if(x == '-1'){ // == 0 sai order_old
    		// Toast thất bại
	  		createToastHTML("Số điện thoại này đã tồn tại trên hệ thống!", "ERROR", "#DC3545", "fas fa-exclamation-triangle");
    	}else if(x == '1'){
    		// Toast thành công
	  		createToastHTML("Cập nhật thông tin tài khoản thành công", "SUCCESS", "#47f764", "fas fa-check-circle");
    	}else if(x == '2'){
    		// Toast thất bại
	  		createToastHTML("Cập nhật thông tin tài khoản thất bại", "ERROR", "#DC3545", "fas fa-exclamation-triangle");
    	}
	}
	
	 var result = '${password_update}';
		if(result != ""){
	    	if(result == '0'){
		  		createToastHTML("Một số ký tự không được phép nhập khi đặt mật khẩu hoặc để trống.", "ERROR", "#DC3545", "fas fa-exclamation-triangle");
	    	}else if(result == '1'){
		  		createToastHTML("Mật khẩu cũ không chính xác!", "ERROR", "#DC3545", "fas fa-exclamation-triangle");
	    	}else if(result == '2'){
		  		createToastHTML("Mật khẩu mới và nhập lại không trùng nhau", "ERROR", "#DC3545", "fas fa-exclamation-triangle");
	    	}else if(result == '3'){
		  		createToastHTML("Cập nhật mật khẩu mới thành công", "SUCCESS", "#47f764", "fas fa-check-circle");
	    	}else if(result == '4'){
		  		createToastHTML("Cập nhật mật khẩu mới thất bại", "ERROR", "#DC3545", "fas fa-exclamation-triangle");
	    	}
		}
	
		
		var info_phone_input = document.querySelector("#info_phone_input");
		var info_email_input = document.querySelector("#info_email_input");
		var btn_edit_info = document.querySelector("#btn_edit_info");
		var form_edit_info_submit = document.querySelector("#form_edit_info_submit");
        var reg_phone = /(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})\b/;
        var reg_email = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
        
        function isValid(){
        	var check = true;
        	if(info_email_input.value.trim().length == 0){
        		check = true;
        		info_email_input.parentElement.children[2].style.display = "none";
        		info_email_input.style.border = "1px solid #dce7f1";
        	}
        	else if(reg_email.test(info_email_input.value.trim()) == false){
        		info_email_input.parentElement.children[2].style.display = "block";
        		info_email_input.style.border = "1px solid red";
                check = false;
            }
            	
        	if(info_phone_input.value.trim().length == 0){
        		info_phone_input.parentElement.children[2].style.display = "none";
            	info_phone_input.style.border = "1px solid #dce7f1";
            	check = true;
        	}
        	else if(reg_phone.test(info_phone_input.value.trim()) == false || info_phone_input.value.trim().length > 10){
            	info_phone_input.parentElement.children[2].style.display = "block";
            	info_phone_input.style.border = "1px solid red";
                check = false;
            }

            return check;
        }
			        
        info_phone_input.addEventListener("input", (e) => {
        	info_phone_input.value = e.target.value;
        	if(info_phone_input.value.trim().length == 0){
        		info_phone_input.parentElement.children[2].style.display = "none";
            	info_phone_input.style.border = "1px solid #dce7f1";
        	}
        	else if(reg_phone.test(info_phone_input.value.trim()) == false || info_phone_input.value.trim().length > 10){
            	info_phone_input.parentElement.children[2].style.display = "block";
            	info_phone_input.style.border = "1px solid red";
            }else{
            	info_phone_input.parentElement.children[2].style.display = "none";
            	info_phone_input.style.border = "1px solid #dce7f1";
            }
        })
        
        
         	info_email_input.addEventListener("input", (e) => {
         		info_email_input.value = e.target.value;      
         		if(info_email_input.value.trim().length == 0){
         			info_email_input.parentElement.children[2].style.display = "none";
	            	info_email_input.style.border = "1px solid #dce7f1";
            	}
            	else if(reg_email.test(info_email_input.value.trim()) == false){
	            	info_email_input.parentElement.children[2].style.display = "block";
	            	info_email_input.style.border = "1px solid red";
	            }else{
	            	info_email_input.parentElement.children[2].style.display = "none";
	            	info_email_input.style.border = "1px solid #dce7f1";
	            }
	        })
        
	        btn_edit_info.addEventListener("click", () => {
	        	console.log("CLICK")
	        	if(isValid() === true){
	        		form_edit_info_submit.submit();
	        	}
	        });
        
        
        
	
		document.documentElement.scrollTop = getCookie("ScrollY");

	    window.addEventListener("scroll", () => {
	       var x = pageYOffset;
	       document.cookie = "ScrollY="+x;
	    });

	    function getCookie(cname) {
	       let name = cname + "=";
	       let decodedCookie = decodeURIComponent(document.cookie);
	       let ca = decodedCookie.split(';');
	       for(let i = 0; i <ca.length; i++) {
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
