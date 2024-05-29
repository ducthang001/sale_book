<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix = 'c' %>
    <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Kết quả tìm kiếm</title>
    <link rel="stylesheet" href="<c:url value ='/include/css/style.css' />">
    <link rel="stylesheet" href="<c:url value ='/include/css/sach_info.css' />">
    <link rel="stylesheet" href="<c:url value ='/include/css/category.css' />">
    <link rel="stylesheet" href="<c:url value ='/include/css/signup.css' />">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
    <link rel="icon" href="<c:url value ='/images/favico.png' />" sizes="32x32" />
</head>
<body>
    <!-- Navigation Header-->
    <header class="_100vw">
        <div class="_1200px flex_between pd-10_0">
            <div class="nav_left">
                <div class="logo">
                    <a href="./home.htm"><img src="./images/logo.png" alt="Logo"><span>BOOK STORE</span></a>
                </div>
                <div class="search">
                    <form action="./search.htm">
                    <input type="text" name = "keyword" placeholder="Nhập sản phẩm cần tìm kiếm..." value = "${keyword}">
                    <button class="btn_search">
                        <i class="fa fa-search"></i>
                    </button>
                 </form>
                 </div>
            </div>
            <div class="nav_right">
                <a href="./account/login.htm"><i class="fa fa-user"></i></a>
                <a href="/cart.html"> <i class="fa fa-shopping-cart"></i></a>
            </div>
        </div>
    </header>

    <!-- favorite book -->
    <div class="favorite_book_list flex_center mg-10_0">
        <div class="_1200px bg-white flex_col pd-10">

            <div class="favorite_book_list_icon_title " style="padding: 5px 0px; border-bottom: 1px solid #007bff;">
                    <img src="./images/top-buy.png" alt="favorite book">
                    <span class="favorite_book_list_title mg-l-10">Kết quả tìm kiếm cho <c:out value = "${keyword}"/></span>
            </div>

            <div class="favorite_book_item flex_center mg-10_0">
            
            	<c:forEach var = "item" items = "${books}">
					<div class="favorite_book flex_col_center">
                    <a href="sach/${item.id_book}.htm">
                    <div class="book_logo">
                        <img src="./images/${item.picture}" alt="sách được yêu thích">
                    </div>
                    <span class="info"></span>
                        <h3 class="book_name">${item.book_name}</h3>
                        <span class="book_author">${item.author.author_name}</span>
                        <p class="book_price">${item.price}đ</p>
                        <form class = "add_to_cart" action="<c:url value = '/home/add.htm' />" method="get">
                            <input type="hidden" name="bookid" value="${item.id_book}">
                            <button class="btn_buynow" type = "button"><i class="fas fa-cart-plus"></i> Xem Ngay</button>
                   	</form>
                    </a>
                </div>
				</c:forEach>

            </div>
        </div>
    </div>

        <!-- footer -->
    <footer class="flex_center" style="background-color: var(--blue);">
        <div class="_1200px flex" style="padding: 30px 0px;">
            <div class="footer_left" style="flex: 3; color: #fff;">
                <h3 class="pd-10_0">Giới Thiệu</h3>
                <p>Bookstore là một công ty TNHH chuyên bán sách giá rẻ cho mọi người.</p>
            </div>
            <div class="footer_mid" style="flex: 4; color: #fff; text-align: center;">
                <h3 class="pd-10_0">Hotline Chăm Sóc Khách Hàng</h3>
                <div>
                    <p><a href="tel:0378544081" style="text-decoration: none;color: #f60;font-weight: 700;">0384815404 (Miễn Phí)</a></p>
                    <p>Từ thứ Hai đến thứ Bảy (08:00 - 17:00)</p>
                    <p>Chủ nhật (08:00 - 12:00)</p>
                    <div class="mg-10_0">
                        <img style = "height: 30px;" src="../images/vietnampost.png" alt="">
                        <img style = "height: 30px;" src="../images/viettelpost.png" alt="">
                        <img style = "height: 30px;" src="../images/ahamove.png" alt="">
                    </div>
                </div>
            </div>

            <div class="footer_right" style="flex: 3; color: #fff;">
                <h3 class="pd-10_0">Chi Nhánh Cửa Hàng</h3>
                <div>
                    <p>CN1: 97 Đ. Man Thiện, Hiệp Phú TP.HCM</p>
                    <p>CN2: 86/37 Đình Phong Phú, Tăng Nhơn Phú B, Q9, TP. Thủ Đức</p>
                    <p class="mg-10_0"><img src="../images/da-thong-bao-bct.png" alt=""></p>
                </div>
            </div>
        </div>
    </footer>

    <!-- end footer -->
        
</body>
</html>