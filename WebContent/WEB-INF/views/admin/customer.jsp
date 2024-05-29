<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix = 'c' %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>CUSTOMER ADMIN</title>
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"/>
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"/>

    <link rel="preconnect" href="https://fonts.gstatic.com" />
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet"/>
    <link rel="stylesheet" href="<c:url value = '/include/css/bootstrap.css' />" />
    <link rel="stylesheet" href="<c:url value = '/include/css/toast.css' />" />
    <link rel="stylesheet" href="<c:url value = '/include/css/app.css' />" />
    <link rel="stylesheet" href="<c:url value = '/include/css/admin/customer_admin.css' />" />
    <link rel="icon" href="<c:url value = '/images/favico.png' />" sizes="32x32" />
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="<c:url value = '/include/js/jquery.twbsPagination.js' />" type="text/javascript"></script>
    <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js"></script>

	
  </head>

  <body>
    <div id="app doan">
      <div class="background_black" id="background_black">
        <!-- Xu ly xoa customer -->
        <div class="confirm_container remove_customer_class bg-white">
          <div
            class="confirm_header text-white bg-primary"
            style="padding: 10px; font-weight: 700"
          >
            <i class="fas fa-check-circle" style="color: #47f764"></i>
            Xác Nhận Xóa
          </div>
          <div
            class="confirm_content"
            style="padding: 10px; text-align: center"
          >
            Bạn chắc chắn muốn xóa khách hàng có id: <b>2</b>
          </div>
          <div class="confirm_buttons">
          <form id="formDelete" action="<c:url value = '/admin/customer/delete.htm' />" method="post">
          <input type="hidden" id="inputDelete" name="user_id" />
           <button
              class="btn btn-success me-1 mb-2 btn_xacnhan_xoa"
              style="margin: 0px 10px"
              type="button"
            >
              Xác Nhận
            </button>
          </form>
           
            <button
              class="btn btn-danger me-1 mb-2 btn_huy_xoa"
              style="margin: 0px 10px"
              type="button"
            >
              Hủy
            </button>
          </div>
        </div>
        <!-- Ket thuc xu ly xoa customer -->

        <!-- Xu ly thêm và sửa thông tin khách hàng -->
        <div class="row col-7 add_edit_customer_class"
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
            <div class="text-white add_book_class_header">Thêm Người Dùng</div>
            <i
              class="far fa-times-circle btn_close_form"
              style="color: white; font-size: 25px"
            ></i>
          </div>
                <form id="formSubmit" name="formSubmit" action="<c:url value = '/admin/customer/add.htm' />" method="post">
                <div style="padding: 20px; display:flex">
                    <div class="col-6">
                        <div style="margin-bottom: 20px">
                            <label class="form-label">Tên Đăng Nhập</label>
                            <input class="form-control showordisable"  id="user-name" name="username" type="text" placeholder="VD: NgocDZ"/>
                        </div>

                        <div style="margin-bottom: 20px">
                            <label class="form-label">Giới tính</label>
                            <select class="form-select" id = "user-gender" name = "gender" aria-label="Default select example">
                                <option selected value="1">Nam</option>
                                <option value="0">Nữ</option>
                            </select>
                        </div>

                        <div style="margin-bottom: 20px">
                            <label class="form-label">Email</label>
                            <input class="form-control" id="user-email" name="email" type="email" placeholder="VD: nguyenvana@gmail.com"/>
                        </div>

						<div >
                            <label class="form-label">Quyền</label>
                            <select class="form-select" id="user-role"  name = "role" aria-label="Default select example">
                                <option selected value="1">Admin</option>
                                <option value="0">Customer</option>
                            </select>
                        </div>
                        
                        

                    </div>
                    <div class="col-6">
                        <div style="margin-bottom: 20px">
                            <label class="form-label">Năm Sinh</label>
                            <input class="form-control" id="datepicker" name="age" type="number" placeholder="VD: 2001"/>
                        </div>

                        <div style="margin-bottom: 20px">
                            <label class="form-label">Số Điện Thoại</label>
                            <input class="form-control" id="user-phone" name="phone" type="number" placeholder="VD: 0378544081"/>
                        </div>

                        <div style="margin-bottom: 20px">
                            <label class="form-label">Nhập Mật Khẩu</label>
                            <input class="form-control showordisable" id="user-password" name="password" type="password"/>
                        </div>

                        <div >
                            <label class="form-label">Nhập Lại Mật Khẩu</label>
                            <input class="form-control showordisable" id="user-repassword" name="repassword" type="password"/>
                        </div>

                    </div>
  				
                </div>
                <div class="col-12"  style="margin-bottom: 20px ; margin-left: 20px ">
                                <label class="form-label">Địa chỉ</label>
                                <input class="form-control" id="user-address" name="address" type="text" placeholder="VD: Xóm 8 Diễn Hoàng, Diễn Châu, Nghệ An"/>
                        </div>
                        
                <div style="display: flex;justify-content: center;margin-bottom: 10px;">
                   <input class="form-control" id="user-id" value=-1 name="user_id" type="hidden"/>
                   <input class="form-control" id="defaultPhone" name="defaultPhone" type="hidden"/>
                   <input class="form-control" id="defaultEmail" name="defaultEmail" type="hidden"/>
                    <button type="submit" class="btn btn-success btn_add_edit_customer_submit">Xác Nhận</button>
                </div>
                </form>
            </div>
      </div>
      <!-- Ket thuc xu ly add edit customer -->

      <!-- Xu ly toast -->
      <div class="toast_container">
        <i class="fas fa-check-circle toast_icon"></i>
        <div class="toast_content">
          <span style="font-weight: 700">SUCCESS</span>
          <span class="toast_content_name" style="color: #656565"
            >Cập nhập thông tin sách thành công</span
          >
        </div>
        <div>
          <i
            class="fa fa-times toast_close"
            style="font-size: 20px; cursor: pointer; color: #656565"
            aria-hidden="true"
          ></i>
        </div>
      </div>
      <!-- Ket thuc xu ly toast -->
      <div id="sidebar" class="active">
        <div class="sidebar-wrapper active">
          <div class="sidebar-header">
            <div class="d-flex justify-content-between">
              <div class="logo">
                <a href="<c:url value = '/admin/statistics.htm' />" style="text-decoration: none"
                  ><img
                    src="<c:url value = '/images/logo.png' />"
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
              <li class="sidebar-title">Danh Mục Quản Lý</li>

              <li class="sidebar-item">
                <a href="<c:url value = '/admin/statistics.htm' />" class="sidebar-link">
                  <i class="fas fa-chart-bar"></i>
                  <span>Statistics</span>
                </a>
              </li>

              <li class="sidebar-item">
                <a href="<c:url value = '/admin/book.htm' />" class="sidebar-link">
                 <i class="fas fa-book"></i>
                  <span>Books</span>
                </a>
              </li>

              <li
                class="sidebar-item">
                <a href="<c:url value = '/admin/order.htm' />" class='sidebar-link'>
                    <i class="fas fa-cart-plus"></i>
                    <span>Orders</span>
                </a>
            </li>

              <li class="sidebar-item">
                <a href="<c:url value = '/admin/review.htm' />" class="sidebar-link">
                  <i class="fas fa-feather"></i>
                  <span>Review</span>
                </a>
              </li>

              <li class="sidebar-item active">
                <a href="<c:url value = '/admin/customer.htm' />" class="sidebar-link">
                  <i class="fas fa-user-circle"></i>
                  <span>Customer</span>
                </a>
              </li>

              <li class="sidebar-item">
                <a href="<c:url value = '/admin/author.htm' />" class="sidebar-link">
                  <i class="fas fa-user-astronaut"></i>
                  <span>Author</span>
                </a>
              </li>

              <li class="sidebar-item">
                <a href="<c:url value = '/admin/category.htm' />" class="sidebar-link">
                  <i class="fas fa-bookmark"></i>
                  <span>Category</span>
                </a>
              </li>

              <li class="sidebar-item">
                <a href="<c:url value = '/admin/publisher.htm' />" class="sidebar-link">
                  <i class="fas fa-house-user"></i>
                  <span>Publisher</span>
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
                            Total Profit
                          </h6>
                          <h6 class="font-extrabold mb-0"><fmt:formatNumber type="number" pattern="##,###" value="${total_profit}" /> đ</h6>
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
                          <h6 class="text-muted font-semibold">Total Oders</h6>
                          <h6 class="font-extrabold mb-0">${total_orders}</h6>
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
                          <h6 class="text-muted font-semibold">Total Users</h6>
                          <h6 class="font-extrabold mb-0">${total_users}</h6>
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
                          <h6 class="font-extrabold mb-0"><a href="<c:url value ='/account/login.htm' />">${username}</a> | 
                          	<span><a href="<c:url value ='/account/logout.htm' />">Logout</a></span>
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
                        <h4
                          class="card-title"
                          style="margin-bottom: 0 !important"
                        >
                          Tìm kiếm người dùng nhanh
                        </h4>
                        <form action="<c:url value = '/admin/customer.htm' />" id = "form_search_submit">
                          <div class="input-group" style="padding: 10px 0px">
                            <input
                              type="search"
                              class="form-control rounded"
                              placeholder="Search"
                              name="keyword"
                              id="keyword"
                              aria-label="Search"
                              aria-describedby="search-addon"
                            />
                            <button
                              type="submit"
                              class="btn btn-outline-primary"
                            >
                              search
                            </button>
                          </div>
                        </form>
                      </div>
                    </div>
                  </div>
                </div>
              </div>

              <!-- Ket thuc tim kiem  -->

              <div class="row">
                <div class="col-12">
                  <div class="col-lg-12 stretch-card" style="padding: 0">
                    <div class="card">
                  
                    	<div id="message" style="margin: 20px 20px 0 20px; display:none "
											class="alert alert-success col-4"></div>
							<div id="messageFailed" style="margin: 20px 20px 0 20px; display:none "
											class="alert alert-danger col-4"></div>			
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
                            Danh sách người dùng hiện có
                          </h4>
                          <button
                            id="info"
                            class="btn btn-success btn_edit_customer add_customer_sign"
                          >
                           <input type="hidden" id="user-form-id" name="userId" value="0">
                            <i class="fas fa-plus"></i>
                            Thêm User
                          </button>
                        </div>

                        <div class="table-responsive pt-3">
                          <table class="table table-bordered text-center">
                            <thead>
                              <tr>
                                <th><a href="#">ID</a></th>
                                <th><a href="#">Tên người dùng</a></th>
                                <th><a href="#">Email</a></th>
                                <th><a href="#">SĐT</a></th>
                                <th>Thay Đổi</th>
                              </tr>
                            </thead>
                            <tbody>
                            
                            
                            <c:forEach var = "item" items = "${users}">
                            	<tr class="table-white">
                                <td>${item.user_id}</td>
                                <td>${item.username}</td>
                                <td>
                                 ${item.email}
                                </td>
                                <td>${item.phone}</td>
                                <td class="align-middle text-center">
                                  <button
                                    type="button"
                                    class="btn btn-warning m-1 btn_edit_customer"
                                  >
                                  <input type="hidden" name="userId" value="${item.user_id}">
                                    <i class="far fa-edit"></i>
                                  </button>
                                  <button
                                    type="button"
                                    class="btn btn-danger btn_delete_customer"
                                  >
                                  <input type="hidden" name="userId" value="${item.user_id}">
                                    <i
                                      style="color: white"
                                      class="fa fa-trash-alt"
                                    ></i>
                                  </button>
                                </td>
                              </tr>
                            </c:forEach>
                            </tbody>
                          </table>
                        </div>

                        <!-- Xu ly phan trang -->
                          <div class="container" style="margin-top:10px;display: flex;justify-content: center;align-items: center;">
                            <div>Số lượng: ${count}</div>
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
      <form action="<c:url value = '/admin/customer.htm' />" id = "form_page">
    		<input type="hidden" value = "1" id = "page_input" name = "page">
    	
    	</form>
    </div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.3/jquery.validate.min.js" integrity="sha512-37T7leoNS06R80c8Ulq7cdCDU5MNQBwlYoy1TX/WUsLFC2eYNqtKlV0QjH7r8JpG/S0GUMZwebnVFLPd6SU5yg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

    <script type="text/javascript">
      var form_search_submit = document.querySelector("#form_search_submit");
      console.log(form_search_submit);
      var form_page = document.querySelector("#form_page");
    	var page_input = document.querySelector("#page_input");
    	console.log(form_page);
    	var currentpage = ${currentpage};
    	
		var maxpage = ${maxpage};
  	$(function () {
      window.pagObj = $('#pagination').twbsPagination({
      	totalPages: maxpage,
          visiblePages: 10,
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
  
  	 $("#formSubmit").validate({
  		rules: {
			"username": {
				required: true,
			
			},
			"email": {
				required: true,
				email: true
			},
			"password": {
				required: true,
				
			},
			"repassword": {
				equalTo: "#user-password",
				required: true
			},
			"age": {
				required: true,
				maxlength: 4,
				minlength:4
			},
			"phone": {
				required: true,
				maxlength: 10,
				minlength:10
			},
			"address": {
				required: true,
			},
			"role": {
				required: true,
			},
			"gender": {
				required: true,
			},
		},
		messages: {
			"username": {
				required: "Bắt buộc nhập username",
				
			},
			"password": {
				required: "Bắt buộc nhập password",
				
			},
			"repassword": {
				equalTo: "Hai password phải giống nhau",
				required: "Bắt buộc nhập lại mật khẩu",
			},
			"email": {
				required: "Bắt buộc nhập email",
				email: "Nhập sai định dạng email"
			},
			"age": {
				required: "Bắt buộc nhập năm sinh",
				maxlength: "Nhập tối đa 4 kí tự",
				minlength:"Nhập tối thiểu 4 kí tự"
			},
			"phone": {
				required: "Bắt buộc nhập số điện thoại",
				maxlength: "Nhập tối đa 10 số",
				minlength:"Nhập tối thiểu 10 số"
			},
			"address": {
				required: "Bắt buộc nhập địa chỉ",
			},
			"gender": {
				required: "Bắt buộc chọn giới tính",
			},
			"role": {
				required: "Bắt buộc chọn quyền",
			},
		},	 
  		 
  		 
  	 
  		  submitHandler: function(form) {
  		    // some other code
  		    // maybe disabling submit button
  		    // then:
  		    $(form).submit();
  		  }
  		 });
  
  	var customer_arr = [
  		<c:forEach var = "item" items = "${users}">
  		    {user_id : '${item.user_id}',
  		    	age: '${item.age}',
  		    	email: '${item.email}',
  		    	gender: '${item.gender}',
  		    	password: '${item.password}',
  		    	phone: '${item.phone}',
  		    	username: '${item.username}',
  		    	role: '${item.role}',
  		    	address: '${item.address}',
  		    
  		    },
  		    </c:forEach>  
  		];


  	function loadCustomerEdit(id_edit){
  		for(var i = 0; i<customer_arr.length; i++){
  			if(customer_arr[i]["user_id"] === id_edit){
  			
  				$("#user-name").val(customer_arr[i]["username"]);
  				$("#user-email").val(customer_arr[i]["email"]);
  				$("#datepicker").val(customer_arr[i]["age"]);
  				$("#user-password").val(customer_arr[i]["password"]);
  				$("#user-address").val(customer_arr[i]["address"]);
  				$("#user-phone").val(customer_arr[i]["phone"]);
  				$("#user-repassword").val(customer_arr[i]["password"]);
  				$("#user-gender").val(customer_arr[i]["gender"]);
  				$("#user-role").val(customer_arr[i]["role"]);
  				$("#user-id").val(customer_arr[i]["user_id"]);
  				$("#defaultEmail").val(customer_arr[i]["email"]);
  				$("#defaultPhone").val(customer_arr[i]["phone"]);
  				return;
  			}
  		}
  	}  	
    
    // JS
    
    var btn_close_form = document.querySelector(".btn_close_form");
var toast_container = document.querySelector(".toast_container");
var add_edit_customer_class = document.querySelector(".add_edit_customer_class");
var remove_customer_class = document.querySelector(".remove_customer_class");
var btn_add_edit_customer_submit = document.querySelector(".btn_add_edit_customer_submit");
var btn_delete_customer = document.querySelectorAll(".btn_delete_customer");
console.log(btn_delete_customer);
var toast_noi_dung = document.querySelector(".toast_content_name");

var background_black = document.querySelector("#background_black");
btn_close_form.addEventListener("click", () => {
  background_black.style.display = "none";
  add_edit_customer_class.style.display = "none";
  console.log("CLICK");
});

all_btn_edit_customer = document.querySelectorAll(".btn_edit_customer");
all_btn_edit_customer.forEach((element) => {
  element.addEventListener("click", () => {
    var showordisable = document.querySelectorAll(".showordisable");
    var add_book_class_header = document.querySelector(
      ".add_book_class_header"
    );
    // thẻ input đặt ẩn đầu tiên
    var id_edit = element.children[0].getAttribute("value");
    if (element.classList.contains("add_customer_sign")) {
    	showordisable.forEach((e) =>{
    	    e.removeAttribute("disabled");
    	});
  
      
      
  	$("#user-name").val("");
		$("#user-email").val("");
		$("#datepicker").val("");
		$("#user-password").val("");
		$("#user-address").val("");
		$("#user-phone").val("");
		$("#user-repassword").val("");
		$("#user-gender").val("");
		$("#user-role").val("");
		$("#user-id").val(-1);
      
      
      add_book_class_header.innerHTML = "Thêm Người Dùng";
    } else {
    	showordisable.forEach((e) =>{
    	    e.setAttribute("disabled", "");
    	});
     
   		loadCustomerEdit(id_edit);
      add_book_class_header.innerHTML = "Chỉnh Sửa Người Dùng";
    }
    background_black.style.display = "block";
    add_edit_customer_class.style.display = "block";
  });
});

btn_add_edit_customer_submit.addEventListener("click", () => {
 /*  setTimeout(() => {
    toast_container.classList.remove("toast_to_left");
    toast_container.classList.add("toast_to_right");
  }, 2000);
  toast_container.classList.remove("toast_to_right");
  toast_container.classList.add("toast_to_left");
  toast_noi_dung.innerHTML = "Cập nhập thông tin sách thành công";
  toast_container.style.display = "flex";
  background_black.style.display = "none";
  add_edit_customer_class.style.display = "none"; */
});

var toast_close = document.querySelector(".toast_close");
toast_close.addEventListener("click", () => {
  toast_container.style.display = "none";
});

btn_delete_customer.forEach((element) => {
  element.addEventListener("click", () => {
    // thẻ input đặt ẩn đầu tiên
    var id_delete = element.children[0].getAttribute("value");
    var content = document.querySelector(".confirm_content");
    $("#inputDelete").val(id_delete);
    
    content.innerHTML = "Bạn chắc chắn muốn xóa khách hàng có id: " + id_delete;
    background_black.style.display = "block";
    remove_customer_class.style.display = "block";
  });
});

var btn_xacnhan_xoa = document.querySelector(".btn_xacnhan_xoa");
btn_xacnhan_xoa.addEventListener("click", () => {
  // Xoa sach thanh cong

  if($("#inputDelete").val() == ${userLogin.user_id}){
	  
	  alert("Không được xóa tài khoản hiện tại")
  }else{
	  $("#formDelete").submit();
  }
  inputDelete
  setTimeout(() => {
    toast_container.classList.remove("toast_to_left");
    toast_container.classList.add("toast_to_right");
  }, 2000);
  toast_container.classList.remove("toast_to_right");
  toast_container.classList.add("toast_to_left");
  toast_noi_dung.innerHTML = "Xóa sách thành công";
  toast_container.style.display = "flex";
  background_black.style.display = "none";
  remove_customer_class.style.display = "none";
});

var btn_huy_xoa = document.querySelector(".btn_huy_xoa");
btn_huy_xoa.addEventListener("click", () => {
  background_black.style.display = "none";
  remove_customer_class.style.display = "none";
});


const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const message = urlParams.get('messageSuccess')
const keyword = urlParams.get('keyword')
const messageFailed = urlParams.get('messageFailed')
if(message){
	document.getElementById("message").innerHTML = message
	
	document.getElementById("message").style.display = "block";
	setTimeout(function() {
		$(".alert").fadeOut();
	}, 3000);
}
if(messageFailed){
	document.getElementById("messageFailed").innerHTML = messageFailed
	
	document.getElementById("messageFailed").style.display = "block";
	setTimeout(function() {
		$(".alert").fadeOut();
	}, 3000);
}
if(keyword){
	document.getElementById("keyword").value = keyword
}
</script>
    <script src="assets/js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript" src="customer_admin.js"></script>
  </body>
</html>
    