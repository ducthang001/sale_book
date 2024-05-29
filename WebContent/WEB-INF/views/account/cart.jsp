<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix = 'c' %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Thông tin giỏ hàng</title>
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"
    />

    <link rel="preconnect" href="https://fonts.gstatic.com" />
    <link
      href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="<c:url value ='/include/css/bootstrap.css' />" />
    <link rel="stylesheet" href="<c:url value ='/include/css/toast.css' />" />
    <link rel="stylesheet" href="<c:url value ='/include/css/app.css' />" />
    <link rel="stylesheet" href="<c:url value ='/include/css/cart.css' />" />
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <link rel="icon" href="<c:url value ='/images/favico.png' />" sizes="32x32" />
  </head>

  <body>
    <div id="app">
      <div class="background_black" id="background_black">
        <!-- Xu cac button confirm -->
        <div class="confirm_container remove_class bg-white">
          <div
            class="confirm_header text-white bg-primary"
            style="padding: 10px; font-weight: 700"
          >
            <i class="fas fa-check-circle" style="color: #47f764"></i>
            Thông báo
          </div>
          <div
            class="confirm_content"
            style="padding: 10px; text-align: center"
          >
            Bạn chắc chắn muốn xóa sách có id: <b>2</b>
          </div>
          <div class="confirm_buttons">
            <button
              class="btn btn-success me-1 mb-2 btn_xacnhan_xoa"
              style="margin: 0px 10px"
              type="button"
            >
              <a href="#" class="set_link" style="text-decoration: none; color: white;">Xác Nhận</a>
            </button>
            <button
              class="btn btn-danger me-1 mb-2 btn_huy_cancel"
              style="margin: 0px 10px"
              type="button"
            >
              Hủy
            </button>
          </div>
        </div>
        <!-- Ket thuc xu ly xoa book -->
      </div>
      
      <!-- Xu ly toast -->
      <div class="toast_class"></div>
      <!-- Ket thuc xu ly toast -->
      <div id="sidebar" class="active">
        <div class="sidebar-wrapper active">
          <div class="sidebar-header">
            <div class="d-flex justify-content-between">
              <div class="logo">
                <a href="<c:url value ='/home.htm' />" style="text-decoration: none"
                  ><img
                    src="<c:url value ='/images/logo.png' />"
                    style="height: 40px"
                    alt="Logo"
                    srcset=""
                  />
                  <span style="font-size: 25px">BOOKSTORE</span></a
                >
              </div>
            </div>
          </div>
          <div class="sidebar-menu">
            <ul class="menu">
              <li class="sidebar-title">Danh Mục Khách Hàng</li>

              <li class="sidebar-item">
                <a href="<c:url value ='/account/info.htm' />" class="sidebar-link">
                  <i class="fas fa-chart-bar"></i>
                  <span>Thông tin tài khoản</span>
                </a>
              </li>

              <li class="sidebar-item active">
                <a href="<c:url value ='/account/cart.htm' />" class="sidebar-link">
                 <i class="fas fa-book"></i>
                  <span>Giỏ hàng</span>
                </a>
              </li>

              <li
                class="sidebar-item">
                <a href="<c:url value ='/account/order.htm' />" class='sidebar-link'>
                    <i class="fas fa-cart-plus"></i>
                    <span>Danh sách đơn hàng</span>
                </a>
            </li>
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
                <div class="col-12">
                  <div class="col-lg-12 stretch-card" style="padding: 0">
                    <div class="card">
                      <div class="card-body">
                        <div
                          style="
                            display: flex;
                            align-items: center;
                            justify-content: space-between;
                          "
                        >
                          <h4
                            class="card-title"
                            style="margin-bottom: 0 !important"
                          >
                            Giỏ hàng hiện tại
                          </h4>
                           <button style= "margin-top: 20px;" class = "btn btn-success btn_thanhtoan">Thanh toán ngay</button>
                        </div>
						
						<div id = "table_load">
							<div class="table-responsive pt-3">
                          	<table class="table table-bordered text-center">
                            <thead>
                              <tr>
                                <th>STT</th>
                                <th style = "width: 100px">Ảnh</th>
                                <th>Tên sách</th>
                                <th>Đơn giá</th>
                                <th style="width: 10%;">Số lượng</th>
                                <th>Thành tiền</th>
                                <th>Chi tiết</th>
                              </tr>
                            </thead>
                            <tbody>
                            <c:forEach var = "item" items = "${cart}">
                            	<tr class="table-white">
                                <td>${item.product.id}</td>
                                 <td><img src="<c:url value = '/images/${item.product.image}'/>" alt="" style="height: 120px; width: auto"/>
                                </td>
                                <td  class="cart_book_name">${item.product.bookname}</td>
                                <td>${item.product.price} đ</td>
                                <td>
                                	<form action="<c:url value = '/book/edit.htm'/>" method ="post" class = "form_edit">
	                                	<input type="hidden" name = "bookid" class="id_book_edit" value="${item.product.id}">
	                                	<input style="text-align: center;" min = "1" max = "20" class="form-control edit_quanty_input" type="number" name = "quanty" value="${item.quanty}">
	                                	<p class = "text-danger soluongconlai_${item.product.id}" style="font-size: 14px;font-weight: bold; display:none;">Số lượng còn lại là: 0</p>
                                	</form>
                                </td>
                                <td><fmt:formatNumber type="number" pattern="##,###" value="${item.total_price}" /> đ</td>
                                <td>
                                	<form action="<c:url value ='/book/delete.htm' />" method="post" style="display: inline;">
                                		<input type="hidden" name = "bookid" value = "${item.product.id}" class = "cart_delete">
	                                    <button type = "submit" class="btn btn-danger btn_delete_cart">Xóa</button>
									</form>
                                    <button class="btn btn-primary"><a href="<c:url value ='/sach/${item.product.id}.htm' />" style = "text-decoration:none; color:#fff">Chi tiết</a></button>
                                </td>
                              </tr>
                            </c:forEach>
                            </tbody>
                          </table>
                        </div>

                        <!-- Xu ly phan trang -->
                          <div class="container" style="margin-top:10px;display: flex;justify-content: center;align-items: center; flex-direction: column;">
                            <div style="font-size: 20px; color: #C92127; font-weight: bold;">Thành tiền: <fmt:formatNumber type="number" pattern="##,###" value="${total}" /> VND</div>
                         </div>
                        <!-- Ket thuc xu ly phan trang -->
						</div>
                        
                      </div>   
                    </div>
                  </div>

                  <div class="row display_none form_xacnhanmuahang">
                        <div class="col-12">
                        <div class="col-lg-12 stretch-card" style="padding: 0">
                            <div class="card">
                            <div class="card-body">
                                <h4 class="card-title" style="margin-bottom: 20px; text-align: center;">
                                Thông tin mua hàng
                                </h4>
                                <div class="col-12" style="display: flex; justify-content: center;">
                                <div class="col-6">
                                    <form action="<c:url value = '/checkout.htm' />" id="form_checkout">
                                        <div style="margin-bottom: 20px">
                                        <label class="form-label">Tên Khách Hàng</label>
                                        <input class="form-control showordisable" name="fullname" id = "fullname" type="text" placeholder="Nhập tên khách hàng">
                                        <p class = "text-danger" style="font-size: 14px;font-weight: bold; display:none;">Họ tên không được rỗng</p>
                                        </div>
                                        
                                        <div style="margin-bottom: 20px">
                                        <label class="form-label">Địa chỉ Email</label>
                                        <input class="form-control showordisable" id = "email" name="email" type="email" placeholder="Nhập địa chỉ email">
                                        <p class = "text-danger" style="font-size: 14px;font-weight: bold;display:none;">Địa chỉ email không hợp lệ</p>
                                        </div>

                                        <div style="margin-bottom: 20px">
                                        <label class="form-label">Số điện thoại</label>
                                        <input class="form-control showordisable" name="phone" id = "phone" type="number" placeholder="Nhập số điện thoại">
                                        <p class = "text-danger" style="font-size: 14px;font-weight: bold;display:none;">Số điện thoại không hợp lệ</p>
                                        </div>

                                        <div style="margin-bottom: 20px">
                                        <label class="form-label">Địa chỉ nhận hàng</label>
                                        <input class="form-control showordisable" name="address" id = "address" type="text" placeholder="Nhập địa chỉ nhận hàng">
                                        <p class = "text-danger" style="font-size: 14px;font-weight: bold; display:none;">Địa chỉ không được rỗng</p>
                                        </div>
                                <div style="margin-bottom: 20px; text-align: center">
                                  <button type="button" class="btn btn-success btn_xacnhanmuahang">Xác nhận đặt hàng</button>
                                </div>
                                </form>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
                </div>
              </div>
            </div>
          </section>
        </div>
      </div>
    </div>
    <script type="text/javascript">
    
    var toast_class = document.querySelector(".toast_class");
    var div = document.createElement("div");
    
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
    
    
    console.log("cart ban dau = "+${cart.size()})
    var cart_size = parseInt(${cart.size()})
    var btn_thanhtoan = document.querySelector(".btn_thanhtoan");
    var form_xacnhanmuahang = document.querySelector(".form_xacnhanmuahang");
    
    console.log("cart ban dau = "+cart_size)
    if(cart_size == 0){
    	btn_thanhtoan.remove();
    	form_xacnhanmuahang.remove();
    }
    
    	var edit_quanty_input;
    	
    	var result = '${delete_result}';
    	if(result != ""){
	    	if(result == '0'){
		  		createToastHTML("Xóa không thành công do không tìm thấy ID sách", "ERROR", "#DC3545", "fas fa-exclamation-triangle");
	    	}else if(result == '1'){
		  		createToastHTML("Xóa sách khỏi giỏ hàng thành công", "SUCCESS", "#47f764", "fas fa-check-circle");
	    	}
	    	
    	}
    	
    	var result = '${edit_result}';
    	if(result != ""){
	    	if(result == '0'){
		  		createToastHTML("Số lượng sản phẩm không được nhỏ hơn 1", "ERROR", "#DC3545", "fas fa-exclamation-triangle");
	    	}else if(result == '1'){
	    		createToastHTML("Không thể chỉnh sửa số lượng ID sách chưa tồn tại trong giỏ hàng", "ERROR", "#DC3545", "fas fa-exclamation-triangle");
	    	}else if(result == '2'){
	    		createToastHTML("Số lượng nhập lớn hơn số lượng hiện có. Còn lại là ${edit_conlai}", "ERROR", "#DC3545", "fas fa-exclamation-triangle");
	    	}else if(result == '3'){
		  		createToastHTML("Chỉnh sửa số lượng sản phẩm thành công", "SUCCESS", "#47f764", "fas fa-check-circle");
	    	}
	    	
    	}
    	
    	var result = '${checkout_result}';
    	if(result != ""){
    		if(result == '-1'){
	    		createToastHTML("Giỏ hàng của bạn đang trống, không thể thanh toán", "ERROR", "#DC3545", "fas fa-exclamation-triangle");
	    	}else if(result == '0'){
	    		createToastHTML("Lỗi tạo Order đơn hàng", "ERROR", "#DC3545", "fas fa-exclamation-triangle");
	    	}else if(result == '1'){
	    		createToastHTML("Lỗi tạo chi tiết đợn hàng", "ERROR", "#DC3545", "fas fa-exclamation-triangle");
	    	}else if(result == '2'){
		  		createToastHTML("Chúc mừng, bạn đã mua hàng thành công!", "SUCCESS", "#47f764", "fas fa-check-circle");
	    	}else{
	    		console.log(result);
	    		console.log(result.split(" "));
	    		myArray = result.split(" ");
	    		for(var i = 0; i<myArray.length; i+=2){
	    			var bien = ".soluongconlai_"+myArray[i];
	  		        var bien_html = document.querySelector(bien);
	  		        console.log(bien_html);
  		        	bien_html.innerHTML = "Số lượng còn lại là: "+ myArray[i+1];
  		        	bien_html.style.display = "block";
	    		}
	    		createToastHTML("Số lượng sản phẩm tồn kho không đủ số lượng bạn yêu cầu", "ERROR", "#DC3545", "fas fa-exclamation-triangle");
	    	}
	    	
    	}
    	
    	var edit_quanty_input = document.querySelectorAll(".edit_quanty_input");
    	    edit_quanty_input.forEach((element) => {
    	        element.addEventListener("keypress", (e) => {
    	          if ((e.which != 8 && e.which != 0 && e.which < 48) ||  e.which > 57) {
    	            e.preventDefault();
    	          }
    	        })
    	    })
    		
    	    edit_quanty_input.forEach((element) => {
    	      element.addEventListener("change", (e) => {
    	         element.value = e.target.value;
    	         element.parentElement.submit();
    	      })
    	    });
    	                
    	             
    	
    	
    	// XU LY THANH TOÁN
        var reg_email = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
        var reg_phone = /(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})\b/;
        var fullname = document.querySelector("#fullname");
        var email = document.querySelector("#email");
        var phone = document.querySelector("#phone");
        var address = document.querySelector("#address");
        
        fullname.addEventListener("input", (e) => {
                fullname.value = e.target.value;
                if(fullname.value.trim().length == 0){
                    fullname.parentElement.children[2].style.display = "block";
                    fullname.style.border = "1px solid red";
                }else{
                    fullname.parentElement.children[2].style.display = "none";
                    fullname.style.border = "1px solid #dce7f1";
                }
            })
            
         	email.addEventListener("input", (e) => {
	            email.value = e.target.value;      
	            if(reg_email.test(email.value.trim()) == false){
	                email.parentElement.children[2].style.display = "block";
	                email.style.border = "1px solid red";
	            }else{
	            	email.parentElement.children[2].style.display = "none";
	                email.style.border = "1px solid #dce7f1";
	            }
	        })
            
            phone.addEventListener("input", (e) => {
            	phone.value = e.target.value;
                if(reg_phone.test(phone.value.trim()) == false || phone.value.trim().length !== 10){
                	phone.parentElement.children[2].style.display = "block";
                	phone.style.border = "1px solid red";
                }else{
                	phone.parentElement.children[2].style.display = "none";
                	phone.style.border = "1px solid #dce7f1";
                }
            })
            
            address.addEventListener("input", (e) => {
            	address.value = e.target.value;
                if(address.value.trim().length == 0){
                	address.parentElement.children[2].style.display = "block";
                	address.style.border = "1px solid red";
                }else{
                	address.parentElement.children[2].style.display = "none";
                	address.style.border = "1px solid #dce7f1";
                }
            })
            
            
         function isValid(){
        	var check = true;
            if(fullname.value.trim().length == 0){
            	fullname.parentElement.children[2].style.display = "block";
                fullname.style.border = "1px solid red";
                check = false;
            }

            if(reg_email.test(email.value.trim()) == false){
            	email.parentElement.children[2].style.display = "block";
            	email.style.border = "1px solid red";
                check = false;
            }
            	
            if(reg_phone.test(phone.value.trim()) == false || phone.value.trim().length !== 10){
            	phone.parentElement.children[2].style.display = "block";
            	phone.style.border = "1px solid red";
                check = false;
            }

            if(address.value.trim().length == 0){
            	address.parentElement.children[2].style.display = "block";
            	address.style.border = "1px solid red";
            	check = false;
            }
            return check;
        }
            
		
       	btn_xacnhanmuahang = document.querySelector(".btn_xacnhanmuahang");
        var form_checkout = document.querySelector("#form_checkout");
        btn_xacnhanmuahang.addEventListener("click", () => {
        	if(isValid() === true){
        		form_checkout.submit();
        	}
        });
        	
        btn_thanhtoan.addEventListener("click", () => {
       		form_xacnhanmuahang.classList.toggle("display_none");
    	})
    
    
    // COOKIE
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
    <script type="text/javascript" src="<c:url value ='/include/js/cart.js' />"></script>
  </body>
</html>
