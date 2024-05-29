<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix = 'c' %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lấy lại mật khẩu đăng nhập</title>
    <link rel="stylesheet" href="<c:url value ='/include/css/style.css' />">
    <link rel="stylesheet" href="<c:url value ='/include/css/sach_info.css' />">
    <link rel="stylesheet" href="<c:url value ='/include/css/category.css' />">
    <link rel="stylesheet" href="<c:url value ='/include/css/signup.css' />">
    <link rel="stylesheet" href="<c:url value ='/include/css/toast.css' />" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"/>
    <link rel="icon" href="<c:url value ='/images/favico.png' />" sizes="32x32" />
</head>
<body>
    <!-- Navigation Header-->
    <header class="_100vw">
    	<div class = "toast_class"></div>
        <div class="_1200px flex_between pd-10_0">
            <div class="nav_left">
                <div class="logo">
                    <a href="<c:url value ='/home.htm' />"><img src="<c:url value ='/images/logo.png' />" alt="Logo"><span>BOOK STORE</span></a>
                </div>
                <div class="search">
                <form action="<c:url value ='/search.htm' />">
                    <input type="text" name="keyword" placeholder="Nhập sản phẩm cần tìm kiếm...">
                    <button class="btn_search">
                        <i class="fa fa-search"></i>
                    </button>
                 </form>
                </div>
            </div>
            <div class="nav_right">
                <a href="<c:url value ='/account/login.htm' />"><i class="fa fa-user"></i></a>
                <a href="<c:url value ='/account/cart.htm' />"> <i class="fa fa-shopping-cart"></i></a>
            </div>
        </div>
    </header>
    
    <!-- sigup -->
    <div class="flex_center signup bg-white">
        <div class="_1200px flex_center signup_container">
            <div class="signup_container_left">
                <h2>Cấp lại mật khẩu</h2>
                <form action="<c:url value ='/account/verify/submit.htm' />" method="post" class="form_signup">
                    <div class="input_sign input_username flex-col">
                        <label>Tên đăng nhập</label>
                        <input type="text" name = "username">
                    </div>
                    <div class="input_sign input_phone flex-col">
                        <label>Địa chỉ Email</label>
                        <input type="email" name = "email">
                    </div>
                    <div class="btn_signup_signin flex_center mg-10_0">
                        <button class="btn">Cấp lại mật khẩu</button>
                    </div>
                </form>

                <div class="login_signin">Bạn đã có tài khoản <a href="<c:url value ='/account/login.htm' />">Đăng nhập</a></div>
                <div class="losspass"><a href="<c:url value ='/account/signup.htm' />">Chưa có tài khoản</a></div>
            </div>
            <div class="signup_container_right">
                
            </div>
    </div>
    
    <script>
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
    
    var result = '${add_result}';
	if(result != ""){
    	if(result == '0'){
	  		createToastHTML("Tên đăng nhập hoặc email bị thiếu.", "ERROR", "#DC3545", "fas fa-exclamation-triangle");
    	}else if(result == '1'){
	  		createToastHTML("Không tìm thấy tài khoản chứa tên đăng nhập và email này!", "ERROR", "#DC3545", "fas fa-exclamation-triangle");
    	}else if(result == '2'){
	  		createToastHTML("Thành công! kiểm tra địa chỉ email để lấy mật khẩu đăng nhập nhé!" , "SUCCESS", "#47f764", "fas fa-check-circle");
    	}else if(result == '3'){
	  		createToastHTML("Lấy lại mật khẩu thất bại!", "ERROR", "#DC3545", "fas fa-exclamation-triangle");
    	}
	}
    
    </script>
</body>
</html>