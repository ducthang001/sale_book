<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix = 'c' %>
    <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang Tác Giả</title>
    <link rel="stylesheet" href="<c:url value = '/include/css/style.css' />">
    <link rel="stylesheet" href="<c:url value = '/include/css/sach_info.css' />">
    <link rel="stylesheet" href="<c:url value = '/include/css/category.css' />">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"/>
    <link rel="stylesheet" href="<c:url value = '/include/css/toast.css' />" />
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="<c:url value = '/include/js/jquery.twbsPagination.js' />" type="text/javascript"></script>
    <link rel="icon" href="<c:url value ='/images/favico.png' />" sizes="32x32" />
</head>
<body>
    <!-- Navigation Header-->
    <header class="_100vw">
    <div class = "toast_class"></div>
        <div class="_1200px flex_between pd-10_0">
            <div class="nav_left">
                <div class="logo">
                    <a href="<c:url value = '/home.htm' />"><img src="../images/logo.png" alt="Logo"><span>BOOK STORE</span></a>
                </div>
                <div class="search">
                    <form action="<c:url value = '/search.htm' />">
                    <input type="text" name = "keyword" placeholder="Nhập sản phẩm cần tìm kiếm...">
                    <button class="btn_search">
                        <i class="fa fa-search"></i>
                    </button>
                 </form>
                </div>
            </div>
            <div class="nav_right">
                <a href="<c:url value = '/account/login.htm' />"><i class="fa fa-user"></i></a>
                <a href="<c:url value = '/account/cart.htm' />"> <i class="fa fa-shopping-cart"></i></a>
            </div>
        </div>
    </header>

    <!-- Catagory Carousel-->
    <div class="category_carousel _100vw mg-10_0">
        <div class="_1200px flex_between">
            <div class="category_carousel_left bg-white">
                <div class="category_title bg-blue color-white pd-10_50">Danh Mục Sách</div>
                <ul class="category_ul">
                    <c:forEach var = "i" begin = "0" end =  "4">
                    	<li class="category_ui_item pd-10"><a href="../category/${categories[i].category_id}.htm">${categories[i].category_name}</a></li>
            		</c:forEach>
                </ul>
            </div>

            <div class="category_carousel_right">
                <div class="carousel_images">
                    <a href="#"><img src="../images/sale.jpg" alt="sale"></a>
                    <a href="#"><img src="../images/sach-thieu-nhi.jpg" alt="sale"></a>
                    <a href="#"><img src="../images/sale-thang3.jpg" alt="sale"></a>
                </div>
                <div class="carousel_pre_after">
                        <div class="pre_bg-main" style="padding: 2px 10px;">
                            <i id = "pre" class="fa fa-angle-left"></i>
                        </div>
                        <div class="next_bg-main" style="padding: 2px 10px;">
                            <i id = "next" class="fa fa-angle-right"></i>
                        </div>
                    </div>

            </div>
        </div>
    </div>

    <!-- favorite book -->
    <div class="favorite_book_list flex_center mg-10_0">
        <div class="_1200px bg-white flex_col pd-10">

            <div class="favorite_book_list_icon_title " style="padding: 5px 0px; border-bottom: 1px solid #007bff;">
                    <img src="../images/top-buy.png" alt="favorite book">
                    <span class="favorite_book_list_title mg-l-10">Sách viết bởi tác giả: ${author_name}</span>
            </div>

            <div class="favorite_book_item flex_center mg-10_0">
                
                <c:forEach var = "item" items = "${book_author}">
                	<div class="favorite_book flex_col_center">
                    <a href="../sach/${item.id_book}.htm">
                    <div class="book_logo">
                        <img src="../images/${item.picture}" alt="sách được yêu thích">
                    </div>
                    <span class="info"></span>
                        <h3 class="book_name">${item.book_name}</h3>
                        <span class="book_author">${item.author.author_name}</span>
                        <p class="book_price"><fmt:formatNumber type="number" pattern="##,###" value="${item.price}" />đ</p>
                        <form class = "add_to_cart" action="<c:url value = '/author/add/${id_author}.htm' />" method="post">
                            <input type="hidden" name="bookid" value="${item.id_book}">
                            <button class="btn_buynow"><i class="fas fa-cart-plus"></i> Thêm vào giỏ hàng</button>
                   	</form>
                    </a>
                </div>  	
            	</c:forEach>
            </div>
            <nav aria-label="Page navigation">
                  <ul class="pagination" id="pagination" style="margin: 0 20px;"></ul>
             </nav>
             <form action="<c:url value = '/author/${id_author}.htm' />" id = "form_page">
    			<input type="hidden" value = "1" id = "page_input" name = "page">
    		</form>
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


<script>
    var form_page = document.querySelector("#form_page");
  	var page_input = document.querySelector("#page_input");
  	var currentpage = ${currentpage};
  	var pagesize = ${pagesize};
	var maxpage = ${maxpage};
	$(function () {
	    window.pagObj = $('#pagination').twbsPagination({
	    	totalPages: maxpage,
	        visiblePages: pagesize,
	        startPage: currentpage,
	        onPageClick: function (event, page) {
	            if(currentpage !== page){
	              page_input.setAttribute("value", page);
	              console.info(page + ' (from options)');
	              form_page.submit();
	            }
	        }
	    })
	});
    
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
    	if(result == '1'){
	  		createToastHTML("Không tìm thấy ID sách trong dữ liệu.", "ERROR", "#DC3545", "fas fa-exclamation-triangle");
    	}else if(result == '2'){
	  		createToastHTML("Thêm thất bại do số lượng sản phẩm không đủ! Còn lại là: "+'${add_conlai}', "ERROR", "#DC3545", "fas fa-exclamation-triangle");
    	}else if(result == '3'){
	  		createToastHTML("Thêm thất bại! còn lại "+'${add_conlai}'+" sản phẩm, giỏ hàng bạn có: "+'${add_dadat}', "ERROR", "#DC3545", "fas fa-exclamation-triangle");
    	}else if(result == '4'){
	  		createToastHTML("Thêm sản phẩm vào giỏ hàng thành công", "SUCCESS", "#47f764", "fas fa-check-circle");
    	}
	}
	
	
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
    <!-- end footer -->
    <script type="text/javascript" src="<c:url value ='/include/js/javascript.js'/> "></script>
</body>
</html>