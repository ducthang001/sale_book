<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix = 'c' %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
     <base id = "getPath" href="${pageContext.request.contextPath}/" />  
    
    <title>Danh sách các đơn hàng</title>
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
    <link rel="stylesheet" href="<c:url value ='/include/css/order_history.css' />" />
    <link rel="icon" href="<c:url value ='/images/favico.png' />" sizes="32x32" />
  </head>

  <body>
    <div id="app">
      <div class="background_black" id="background_black">
        <!-- Xu cac button confirm -->
        <form action = "<c:url value ='account/order/cancel.htm' />" method="post" class="confirm_container remove_class bg-white">
        	<input id = "input_order_cancel" type="hidden" name="idOrder" value="-1">
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
              type="submit">Xác Nhận</button>
            <button
              class="btn btn-danger me-1 mb-2 btn_huy_cancel"
              style="margin: 0px 10px"
              type="button"
            >Hủy</button>
          </div>
        </form>
        <!-- Ket thuc xu ly xoa book -->

        <!-- Xu ly chi tiet don hang -->
          <div class="row col-7 chitiet_class"
          style="
            background-color: #f2f7ff;
            border-radius: 3px 3px 0 0;
            padding: 0 !important;
            box-shadow: 0 4px 8px #62b0fd, 0 4px 12px rgb(0 0 0 / 8%);
          ">
          <div
            class="bg-primary"
            style="
              display: flex;
              align-items: center;
              justify-content: space-between;
              padding: 15px 20px;
            "
          >
            <div class="text-white add_book_class_header">Thông tin đơn hàng</div>
            <i
              class="far fa-times-circle btn_close_form"
              id="view_chitiet_exit"
              style="color: white; font-size: 25px"
            ></i>
          </div>

          <div style="padding: 20px">
                <div class="madonhang_id" style="margin-bottom: 15px; font-weight: bold;">Mã đơn hàng: #<span id = "id_order_detail_show">38</span>
                	<span>Nhận hàng tại: </span> <span id = "adsress_order_detail_show" >25/3 Lạc Long Quân, Q.10,TP HCM</span>
                </div>
                <div style="display: flex; justify-content: space-between; border: 1px solid #62b0fd; padding: 10px;">
                    <div class="customer">
                        <div style="font-weight: 500;">Khách Hàng:</div>
                        <div id = "customer_name_detail_show" style="font-weight: bold;">Đức Ngọc</div>
                    </div>

                    <div class="time">
                        <div style="font-weight: 500;">Thời gian:</div>
                        <div id = "time_detail_show" style="font-weight: bold;">16/03/2022</div>
                    </div>

                    <div class="delivery">
                        <div style="font-weight: 500;">Đơn vị giao:</div>
                        <div style="font-weight: bold;">Shopee Express</div>
                    </div>

                    <div class="vanchuyen">
                        <div style="font-weight: 500;">Tổng tiền:</div>
                        <div id = "total_price_detail_show" style="font-weight: bold;">770,000 VNĐ</div>
                    </div>
                </div>

                <div class="step_delivery" style="display: flex; justify-content: space-between; padding: 10px; margin-top: 20px;">
                    <div class="step_one" style="display: flex; flex-direction: column; justify-content: center; align-items: center; width: 100px;">
                        <i class="fa fa-box bg-success" style="padding: 10px;border: 2px solid;border-radius: 50%; color: #fff;"></i>
                        <span class="text"> Chờ xác nhận </span>
                    </div>

                    <div class="step_two" style="display: flex; flex-direction: column; justify-content: center; align-items: center; width: 100px;">
                        <i class="fa fa-check  bg-warning" style="padding: 10px;border: 2px solid;border-radius: 50%; color: #fff;"></i>
                        <span class="text"> Xác nhận </span>
                    </div>
                    
                     <div class="step_three" style="display: flex; flex-direction: column; justify-content: center; align-items: center; width: 100px;">
                        <i class="fas fa-truck bg-warning" style="padding: 10px;border: 2px solid;border-radius: 50%; color: #fff;"></i>
                        <span class="text"> Vận chuyển </span>
                    </div>

                    <div class="step_four" style="display: flex; flex-direction: column; justify-content: center; align-items: center; width: 100px;">
                        <i class="fa fa-user bg-warning" style="padding: 10px;border: 2px solid;border-radius: 50%; color: #fff;"></i>
                        <span class="text"> Đã giao </span>
                    </div>

                    <div class="progress_delivery">
                    	<div class="progress_after" style="position: absolute;top: 0;left: 0;height: 100%; background-color: #28a745;"></div>
                    </div>
                </div>

                <div class="books" id = "books_detail_show" style="display: flex;flex-wrap: wrap;">
                    <div class="one_book col-md-4" style="display: flex;">
                        <div class="image_book">
                            <img src="./images/tieng-anh7.jpg" style="width: 80px;height: 80px;padding: 7px;" alt="">
                        </div>
                        <div class="info_book" style="display: flex; flex-direction: column; padding: 2px 0px;">
                            <span>Trên đường băng</span>
                            <span>Tony</span>
                            <span>78,000VNĐ x 5</span>
                        </div>
                    </div>

                    <div class="one_book col-md-4" style="display: flex;">
                        <div class="image_book">
                            <img src="./images/tieng-anh7.jpg" style="width: 80px;height: 80px;padding: 7px;" alt="">
                        </div>
                        <div class="info_book" style="display: flex; flex-direction: column; padding: 2px 0px;">
                            <span>Trên đường băng</span>
                            <span>Tony</span>
                            <span>78,000VNĐ x 5</span>
                        </div>
                    </div>


                    <div class="one_book col-md-4" style="display: flex;">
                        <div class="image_book">
                            <img src="./images/tieng-anh7.jpg" style="width: 80px;height: 80px;padding: 7px;" alt="">
                        </div>
                        <div class="info_book" style="display: flex; flex-direction: column; padding: 2px 0px;">
                            <span>Trên đường băng</span>
                            <span>Tony</span>
                            <span>78,000VNĐ x 5</span>
                        </div>
                    </div>

                    <div class="one_book col-md-4" style="display: flex;">
                        <div class="image_book">
                            <img src="./images/tieng-anh7.jpg" style="width: 80px;height: 80px;padding: 7px;" alt="">
                        </div>
                        <div class="info_book" style="display: flex; flex-direction: column; padding: 2px 0px;">
                            <span>Trên đường băng</span>
                            <span>Tony</span>
                            <span>78,000VNĐ x 5</span>
                        </div>
                    </div>
                </div>
            </div>
      </div>
        <!-- Ket thuc xu ly chi tiet don hang -->
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

              <li class="sidebar-item">
                <a href="<c:url value ='/account/cart.htm' />" class="sidebar-link">
                 <i class="fas fa-book"></i>
                  <span>Giỏ hàng</span>
                </a>
              </li>

              <li
                class="sidebar-item active">
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
                          <h6 class="text-muted font-semibold">
                           Số tiền đã mua
                          </h6>
                          <h6 class="font-extrabold mb-0"><fmt:formatNumber type="number" pattern="##,###" value="${amountspent}" /> đ</h6>
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
                          <h6 class="font-extrabold mb-0">${ordersuccesscount} đơn</h6>
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
                          <h6 class="font-extrabold mb-0">${pendingordercount} đơn</h6>
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
                          <h6 class="font-extrabold mb-0"><a href="<c:url value ='/account/login.htm' />">${user.username}</a> | <span><a href="<c:url value ='/account/logout.htm' />">Logout</a></span></h6>
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
                            Danh sách đơn hàng
                          </h4>
                          <form action="">
                            <!-- Lưu kiểu tìm kiếm (Sort theo gì?) -->
                          <!-- <input type="text" hidden name="statusOrderSearch" value="3"> -->
                          <div style="display: flex; align-items: center;">
                            <div>
                            <label class="form-label" for="basic-form-dob">Ngày bắt đầu</label>
                            <input class="form-control" id="datepickerfrom_start" name="startDate" type="date">
                          </div>

                          <div style="margin-left: 15px;">
                            <label class="form-label" for="basic-form-dob">Ngày kết thúc</label>
                            <input class="form-control" id="datepickerfrom_end" name="endDate" type="date">
                          </div>

                          <button class="btn btn-success" style=" margin-left: 10px; display: flex; justify-content: center; align-items: center; padding: 10px;">
                            <i class="fas fa-search text-white"></i>
                          </button>
                          </div>
                          </form>
                        </div>

                        <div class="table-responsive pt-3">
                          <table class="table table-bordered text-center">
                            <thead>
                              <tr>
                                <th>ID</th>
                                <th>Thời gian</th>
                                <th>Tổng tiền</th>
                                <th>Địa chỉ nhận hàng</th>
                                <th>Trạng thái</th>
                                <th>Chi tiết</th>
                              </tr>
                            </thead>
                            <tbody>
                           		 <c:forEach var = "item" items = "${orders}">
                            	<tr class="table-white">
                                <td>${item.order_id}</td>
                                <td>${item.order_day}</td>
                                <td><fmt:formatNumber type="number" pattern="##,###" value="${item.total_price}" /> đ</td>
                                <td>${item.address}</td>
                                <td>
                                	<c:choose>
									    <c:when test="${item.order_status == 1}">
								         	<button class="btn btn-danger btn_change_status">
	                                        <!-- Lưu ID hủy đơn -->
	                                        <input type="hidden" class="${item.order_id}" name="actionStatus" btnname = "huydon" value="/admin/order/save/1?idOrder=${item.order_id}&amp;statusNew=3&amp;statusOld=1">
	                                        Hủy Đơn</button>
	                                       	</button>
									    </c:when>
									    
									    <c:when test="${item.order_status == 2}">
								         	<span class = "font-weight-bold text-success">Đã giao</span>
									    </c:when> 
									    
									    <c:when test="${item.order_status == 3}">
								         	<span class = "font-weight-bold text-primary">Đã yêu cầu hủy</span>
									    </c:when> 
									        
									    <c:otherwise>
									       <span class = "font-weight-bold text-danger">Đã hủy</span>
									    </c:otherwise>
									</c:choose>
								
                                </td>
                                <td>
                                    <button class="btn btn-info btn_change_status">
                                      <!-- Lưu ID order -->
                                    <input type="hidden" name="OrderId" value="${item.order_id}" btnname = "chitiet"> 
                                      Chi Tiết</button>
                                </td>
                              </tr>
                            </c:forEach>
                            </tbody>
                          </table>
                        </div>

                        <!-- Xu ly phan trang -->
                          <div class="container" style="margin-top:10px;display: flex;justify-content: center;align-items: center;">
                            <div>Số lượng: ${orders.size()}</div>
                            <nav aria-label="Page navigation">
                                <ul class="pagination" id="pagination" style="margin: 0 20px;"></ul>
                            </nav>
                         </div>
                        <!-- Ket thuc xu ly phan trang -->

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
    <script>
    var btn_change_status = document.querySelectorAll(".btn_change_status");
    var confirm_content = document.querySelector(".confirm_content");
    var background_black = document.querySelector("#background_black");
    var confirm_container = document.querySelector(".confirm_container");
    var btn_huy_cancel = document.querySelector(".btn_huy_cancel");
    var chitiet_class = document.querySelector(".chitiet_class");
    var btn_close_form = document.querySelector(".btn_close_form");
    var madonhang_id = document.querySelector(".madonhang_id");
    btn_change_status.forEach((element) => {
      element.addEventListener("click", () => {
        var content = element.textContent.trim();
        if (content === "Hủy Đơn") {
          var id = element.children[0].className;
          var link = element.children[0].getAttribute("value");
          document.querySelector("#input_order_cancel").value = id;
          confirm_content.innerHTML = "Bạn có muốn hủy đơn đơn hàng có mã: " + id;
          background_black.style.display = "block";
          confirm_container.style.display = "block";
        }

        if (content === "Chi Tiết") {
          console.log("CHI TIẾT");
          var id = element.children[0].getAttribute("value");
          // madonhang_id.innerHTML = "Mã đơn hàng " + id;
          show_detail_data(id);
          background_black.style.display = "block";
          chitiet_class.style.display = "block";
        }
      });
    });

    btn_huy_cancel.addEventListener("click", () => {
      background_black.style.display = "none";
      confirm_container.style.display = "none";
    });

    btn_close_form.addEventListener("click", () => {
      background_black.style.display = "none";
      chitiet_class.style.display = "none";
    });
    
    
    
    
    // VARIABLE 
    var id_order_detail_show = document.querySelector("#id_order_detail_show");
    var time_detail_show = document.querySelector("#time_detail_show");
    var customer_name_detail_show = document.querySelector("#customer_name_detail_show");
    var total_price_detail_show = document.querySelector("#total_price_detail_show");
    var adsress_order_detail_show = document.querySelector("#adsress_order_detail_show");
    var books_detail_show = document.querySelector("#books_detail_show");
    var path = document.querySelector("#getPath").getAttribute("href");
    console.log("path = "+path)

    var step_one = document.querySelector(".step_one");
    var step_two = document.querySelector(".step_two");
    var step_three = document.querySelector(".step_three");
    var step_four = document.querySelector(".step_four");
    var progress_after = document.querySelector(".progress_after");
    
	<!-- -->
    var arr_book_order = [
    	// <!--
    	<c:forEach var = "itempro" items = "${orders}">
    	{
    		idOrder: '${itempro.order_id}',
    		address: '${itempro.address}',
    		totalPrice: '${itempro.total_price}',
    		orderStatus: '${itempro.order_status}',
    		customerPhone: '${itempro.customer_phone}',
    		customerName: '${itempro.customer_name}',
    		orderDay: '${itempro.order_day}',
    		detail: [
    		<c:forEach var = "item" items = "${itempro.detail}">
    		   {
    			   idBook: '${item.book.id_book}',
    			   bookName: '${item.book.book_name}',
    			   bookPrice: '${item.price}',
    			   bookAuthor: '${item.book.author.author_name}',
    			   bookImage: '${item.book.picture}',
    			   quantity: '${item.quantity}',
    		   },
    	    </c:forEach>
    		   ]
    	},
        </c:forEach>  
    	// -->
    ];

    console.log(arr_book_order)
    
    
    function setdefautOrderShow(){
	step_one.children[0].classList.add("bg-warning");
	step_one.children[0].classList.remove("bg-success");
	step_one.children[0].classList.remove("bg-danger");
	step_two.children[0].classList.add("bg-warning");
	step_two.children[0].classList.remove("bg-success");
	step_two.children[0].classList.remove("bg-danger");
	step_three.children[0].classList.add("bg-warning");
	step_three.children[0].classList.remove("bg-success");
	step_three.children[0].classList.remove("bg-danger");
	step_four.children[0].classList.add("bg-warning");
	step_four.children[0].classList.remove("bg-success");
	step_four.children[0].classList.remove("bg-danger");
	step_four.children[1].innerHTML = "Đã giao";
	progress_after.style.width = "0%";
	progress_after.style.backgroundColor = "rgb(40, 167, 69)";
}
    
    
    function show_detail_data(id_order){
	var dsbook_order_html = "";
	for(var i = 0; i< arr_book_order.length; i++){
		if(arr_book_order[i].idOrder === id_order){
			console.log("BẰNG NHAU")
			id_order_detail_show.innerHTML = id_order;
			time_detail_show.innerHTML = arr_book_order[i].orderDay;
			customer_name_detail_show.innerHTML = arr_book_order[i].customerName;
			total_price_detail_show.innerHTML = arr_book_order[i].totalPrice + " VNĐ";
			adsress_order_detail_show.innerHTML = arr_book_order[i].address +", SĐT: "+arr_book_order[i].customerPhone;
			
			for(var j = 0; j<arr_book_order[i].detail.length; j++){
				dsbook_order_html += "<div class='one_book col-md-4' style='display: flex;'>"+
	            "<div class='image_book'>"+
	                "<img src='"+path+"/images/"+arr_book_order[i].detail[j].bookImage+"' style='width: 80px;height: 80px;padding: 7px;' alt=''>"+
	            "</div>"+
	            "<div class='info_book' style='display: flex; flex-direction: column; padding: 2px 0px;'>"+
	                "<span>"+arr_book_order[i].detail[j].bookName+"</span>"+
	                "<span>"+arr_book_order[i].detail[j].bookAuthor+"</span>"+
	                "<span>"+arr_book_order[i].detail[j].bookPrice+"VNĐ x "+arr_book_order[i].detail[j].quantity+"</span>"+
	            "</div>"+
      		"</div>";
			}
			
			
			console.log(dsbook_order_html)
			books_detail_show.innerHTML = dsbook_order_html;
			
			if(arr_book_order[i].orderStatus == 1){ // Chờ xác nhận
				setdefautOrderShow();
				step_one.children[0].classList.remove("bg-warning");
				step_one.children[0].classList.add("bg-success");
				progress_after.setAttribute("width", "0% !important")
			}else if(arr_book_order[i].orderStatus == 2){ // Đã xác nhận
				setdefautOrderShow();
				step_one.children[0].classList.remove("bg-warning");
				step_one.children[0].classList.add("bg-success");
				step_two.children[0].classList.remove("bg-warning");
				step_two.children[0].classList.add("bg-success");
				step_three.children[0].classList.remove("bg-warning");
				step_three.children[0].classList.add("bg-success");
				step_four.children[0].classList.remove("bg-warning");
				step_four.children[0].classList.add("bg-success");
				progress_after.style.width = "100%";
			}else if(arr_book_order[i].orderStatus == 3){ // Yêu cầu hủy => Chờ xác nhận
				setdefautOrderShow();
				step_one.children[0].classList.remove("bg-warning");
				step_one.children[0].classList.add("bg-danger");
				step_four.children[1].innerHTML = "Đã hủy";
				progress_after.style.width = "0%";
			}else if(arr_book_order[i].orderStatus == 4){ // Đã hủy
				setdefautOrderShow();
				step_one.children[0].classList.remove("bg-warning");
				step_one.children[0].classList.add("bg-danger");
				step_two.children[0].classList.remove("bg-warning");
				step_two.children[0].classList.add("bg-danger");
				step_three.children[0].classList.remove("bg-warning");
				step_three.children[0].classList.add("bg-danger");
				step_four.children[0].classList.remove("bg-warning");
				step_four.children[0].classList.add("bg-danger");
				step_four.children[1].innerHTML = "Đã hủy";
				progress_after.style.width = "100%";
				progress_after.style.backgroundColor = "rgb(220 53 69)";
			}
			return;
		}
	}
}
    
    function createToastHTML(message, type, color, icon){ //#47f764
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
            return html;
    }
    
    
    var toast_class = document.querySelector(".toast_class");
    var x = '${cancel}';
    var div = document.createElement("div");
	if(x != ""){
    	if(x == '0'){ // == 0 sai order_old
    		console.log("OKE");
    		// Toast thất bại
	  		div.innerHTML = createToastHTML("Không tìm thấy đơn hàng trong user này", "ERROR", "#DC3545", "fas fa-exclamation-triangle");
    	}else if(x == '1'){
    		// Toast thất bại
	  		div.innerHTML = createToastHTML("Đơn hàng đã cập nhật một trạng thái khác trước đó", "ERROR", "#DC3545", "fas fa-exclamation-triangle");
    	}else if(x == '2'){
    		// Toast thành công
	  		div.innerHTML = createToastHTML("Yêu cầu hủy đơn hàng thành công", "SUCCESS", "#47f764", "fas fa-check-circle");
    	}else if(x == '3'){
    		// Toast thất bại
	  		div.innerHTML = createToastHTML("Lỗi yêu cầu hủy đơn hàng, vui lòng thử lại sau", "ERROR", "#DC3545", "fas fa-exclamation-triangle");
    	}
    	
    	toast_class.appendChild(div);
		var toast_noi_dung = div.querySelector(".toast_content_name");
	    var toast_container = div.querySelector(".toast_container");
	    
	    setTimeout(() => {
	    	toast_container.classList.remove("toast_to_left");
	    	toast_container.classList.add("toast_to_right");
	  	}, 4000);

		 var toast_close = div.querySelector(".toast_close");
		 console.log(toast_close);
		 toast_close.addEventListener("click", () => {
			div.remove();
		 });
	}
	
	console.log(toast_class)
    
    
    
    </script>
    <script type="text/javascript" charset="UTF-8" src="<c:url value ='/include/js/order_history.js' />"></script>
  </body>
</html>
