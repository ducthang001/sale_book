<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix = 'c' %>
    <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang không tồn tại</title>
    <link rel="stylesheet" href="<c:url value ='/include/css/style.css' />">
    <link rel="stylesheet" href="<c:url value ='/include/css/sach_info.css' />">
    <link rel="stylesheet" href="<c:url value ='/include/css/category.css' />">
    <link rel="stylesheet" href="<c:url value ='/include/css/signup.css' />">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="icon" href="<c:url value ='/images/favico.png' />" sizes="32x32" />
</head>
<body>
    <!-- Navigation Header-->
    <header class="_100vw">
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
                <h2 style = "padding: 30px 15px;color: #0166d6; font-size: 45px;">TRANG YÊU CẦU KHÔNG TỒN TẠI</h2>
            </div>
            <div class="signup_container_right">
                
            </div>
        </div>
    </div>
</body>
</html>