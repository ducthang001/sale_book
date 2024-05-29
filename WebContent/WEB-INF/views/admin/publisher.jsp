<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix = 'c' %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>PUBLISHER ADMIN</title>
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"/>
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"/>

    <link rel="preconnect" href="https://fonts.gstatic.com" />
    <link
      href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="<c:url value = '/include/css/bootstrap.css' />" />
    <link rel="stylesheet" href="<c:url value = '/include/css/toast.css' />" />
    <link rel="stylesheet" href="<c:url value = '/include/css/app.css' />" />
    <link rel="stylesheet" href="<c:url value = '/include/css/admin/publisher_admin.css' />" />
    <link rel="icon" href="<c:url value = '/images/favico.png' />" sizes="32x32" />
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="<c:url value = '/include/js/jquery.twbsPagination.js' />" type="text/javascript"></script>
  </head>

  <body>
    <div id="app doan">
      <div class="background_black" id="background_black">
        <!-- Xu cac button confirm -->
        <form action = "<c:url value = '/admin/publisher/delete.htm' />" method="post" class="confirm_container remove_class bg-white">
        	<input type="hidden" name="idPublisher" id = "input_id_delete" value="1">
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
            Bạn chắc chắn muốn xóa tác giả có id: <b>2</b>
            <b class="ten_can_xoa">Nguyễn Đức Ngọc</b>
          </div>
          <div class="confirm_buttons">
            <button
              class="btn btn-success me-1 mb-2 btn_xacnhan"
              style="margin: 0px 10px"
              type="submit"
            >Xác Nhận
            </button>
            <button
              class="btn btn-danger me-1 mb-2 btn_huy_cancel"
              style="margin: 0px 10px"
              type="button"
            >
              Hủy
            </button>
          </form>
        </div>
      </div>
      <!-- Ket thuc xoa tác giả -->
      <!-- Xu ly toast -->
      <div class="toast_class"></div>
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

              <li class="sidebar-item">
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

              <li class="sidebar-item active">
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
                          Tìm kiếm NXB nhanh
                        </h4>
                        <form action="" id = "form_search_submit">
                          <div class="input-group" style="padding: 10px 0px">
                            <input
                              type="search"
                              class="form-control rounded"
                              placeholder="Search"
                              name="keyword"
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
                            Danh sách NXB
                          </h4>
                          <div style="display: flex;">
                              <button id="info" class="btn btn-success btn_add">
                                <i class="fas fa-plus"></i>
                                <span>Thêm Nhà Xuất Bản</span>
                              </button>
                              <div class="form_add display_none" style="margin-left: 20px;">
                                  <form action="<c:url value = '/admin/publisher/add.htm' />" method="post" style="display: flex;">
                                    <input class="form-control" name="publisherName" type="text" value="" id = "input_add" placeholder="VD: NXH Kim Đồng">
                                    <button style="margin-left: 5px;" type="submit" class="btn btn-success" id = "btn_add_submit">Thêm</button>
                                  </form>
                              </div>
                              
                          </div>
                         
                          
                        </div>

                        <div class="table-responsive pt-3">
                          <table class="table table-bordered text-center">
                            <thead>
                              <tr>
                                <th><a href="<c:url value = '/admin/publisher.htm?page=${currentpage}&order=id&dir=${orderLink}' />">ID</a></th>
                                <th style="width: 65%;"><a href="<c:url value = '/admin/publisher.htm?page=${currentpage}&order=name&dir=${orderLink}' />">Tên nhà xuất bản</a></th>
                                <th>Thay Đổi</th>
                              </tr>
                            </thead>
                            <tbody id = "table_data">
                            <c:forEach var = "item" items = "${publishers}">
                            	<tr class="table-white">
                                <td>${item.id_company}</td>
                                <td>
                                  <span class="ten_hien_thi">${item.company_name}</span>
                                  <div class="form_chinh_sua display_none">
                                    <form action="<c:url value = '/admin/publisher/edit.htm' />"  method="post" style="display: flex;">
                                      <input type="hidden" name="idPublisher" value="${item.id_company}">
                                      <input class="form-control class_input_edit" name="namePublisher" type="text" value="${item.company_name}"/>
                                      <button style="margin-left: 10px;" type="submit" class="btn btn-success btn_edit_submit">Sửa</button>
                                    </form>
                                  </div>
                                </td>
                                <td class="align-middle text-center">
                                  <button
                                    type="button"
                                    class="btn btn-warning m-1 btn_edit"
                                  >
                                    <i class="far fa-edit"></i>
                                  </button>
                                  <button
                                    type="button"
                                    class="btn btn-danger btn_delete"
                                  >
                                  <input type="hidden" name="idPublisher" value="${item.id_company}" tennhaxuatban = "${item.company_name}">
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
         <form action="<c:url value = '/admin/publisher.htm' />" id = "form_page">
    		<input type="hidden" value = "1" id = "page_input" name = "page">
    		<input type="hidden" value = "${orderpage}" name = "order">
    		<input type="hidden" value = "${dirpage}" name = "dir">
    	</form>
      </div>

	<script type="text/javascript">
      	var form_page = document.querySelector("#form_page");
      	var page_input = document.querySelector("#page_input");
      	console.log(form_page);
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
    	// AJAX
    	
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
    	
    		var btn_edit = document.querySelectorAll(".btn_edit");
    		var remove_class = document.querySelector(".remove_class");
    		var background_black = document.querySelector("#background_black");
    		var btn_huy_cancel = document.querySelector(".btn_huy_cancel");
    		var btn_xacnhan = document.querySelector(".btn_xacnhan");
		    var form_chinh_sua = document.querySelectorAll(".form_chinh_sua");
		    var input_form_control = document.querySelectorAll(".class_input_edit");
		    var btn_delete = document.querySelectorAll(".btn_delete");
		    var confirm_content = document.querySelector(".confirm_content");
		  
		  input_form_control.forEach((element) => {
			  element.addEventListener('change', (e) => {
				  element.setAttribute("value", e.target.value);
				  console.log(element);
			  })
		  })
		  
		  btn_edit.forEach(element => {
		    element.addEventListener("click", () => {
		        var icon = element.children[0];
		        console.log("CLICK");
		        var get_parent_element = element.parentElement.parentElement;
		        var ten_hien_thi = get_parent_element.querySelector(".ten_hien_thi");
		        var form_chinh_sua = get_parent_element.querySelector(".form_chinh_sua");
		        ten_hien_thi.classList.toggle("display_none");
		        form_chinh_sua.classList.toggle("display_block");
		        if (icon.className ==="far fa-times-circle"){
		            icon.className = "far fa-edit";
		            element.className = "btn btn-warning m-1 btn_edit";
		        }
		        else{
		            icon.className = "far fa-times-circle";
		            element.className = "btn btn-info m-1 btn_edit";
		        }
		    });
		});
		
		  
			btn_delete.forEach(element => {
			    element.addEventListener("click", () => {
			    	console.log(element.children[0]);
			    	var id_delete = element.children[0].getAttribute("value");
			        var ten_nxb = element.children[0].getAttribute("tennhaxuatban");
			        $("#input_id_delete").val(id_delete);
			        confirm_content.innerHTML = "Bạn chắc chắn muốn xóa thể loại có ID = "+id_delete+ " <p><b>"+ten_nxb+"</b></p>";
			        background_black.style.display = "block";
			        remove_class.style.display = "block";
			    	})
			    })
			

		var btn_huy_cancel = document.querySelector(".btn_huy_cancel");
		btn_huy_cancel.addEventListener("click", () => {
		    background_black.style.display = "none";
		    remove_class.style.display = "none";
		})
		  
    	
  			var btn_add = document.querySelector(".btn_add");
			var form_add = document.querySelector(".form_add");
			btn_add.addEventListener("click", () => {
			  var fa_classname = btn_add.children[0];
			  var text_add = btn_add.children[1];
			  if (fa_classname.className === "fas fa-plus") {
			    fa_classname.className = "far fa-times-circle";
			    btn_add.className = "btn btn-danger btn_add";
			    text_add.innerHTML = "Thoát";
			  } else {
			    fa_classname.className = "fas fa-plus";
			    btn_add.className = "btn btn-success btn_add";
			    text_add.innerHTML = "Thêm tác giả";
			  }
			  form_add.classList.toggle("display_none");
			})
			
			var input_add = document.querySelector("#input_add");
			input_add.addEventListener("change", (e) => {
				input_add.setAttribute("value", e.target.value);
			})
			
			// AJAX
			    	var result = '${add_result}';
			    	if(result != ""){
			    		if(result == '-1'){ // < 2 ky tu
				    		// Toast thất bại
					  		createToastHTML("Tên NXB phải từ 2 ký tự trở lên", "ERROR", "#DC3545", "fas fa-exclamation-triangle");
				    	}else if(result == '0'){ // == 0 sai order_old
				    		// Toast thất bại
					  		createToastHTML("Tên NXB đã tồn tại trên hệ thống.", "ERROR", "#DC3545", "fas fa-exclamation-triangle");
				    	}else if(result == '1'){
				    		// Toast thành công
					  		createToastHTML("Thêm NXB thành công.", "SUCCESS", "#47f764", "fas fa-check-circle");
				    	}else if(result == '2'){
				    		// Toast thất bại
					  		createToastHTML("Thêm NXB thất bại.", "ERROR", "#DC3545", "fas fa-exclamation-triangle");
				    	}
			    	}
				    	
			    	var result = '${edit_result}';
			    	if(result != ""){
			    		if(result == '-1'){ // < 2 ky tu
				    		// Toast thất bại
					  		createToastHTML("Tên NXB phải từ 2 ký tự trở lên", "ERROR", "#DC3545", "fas fa-exclamation-triangle");
				    	}else if(result == '0'){ // == 0 sai order_old
				    		// Toast thất bại
					  		createToastHTML("Tên NXB đã tồn tại trên hệ thống.", "ERROR", "#DC3545", "fas fa-exclamation-triangle");
				    	}else if(result == '1'){
				    		// Toast thành công
					  		createToastHTML("Chỉnh sửa tên NXB thành công.", "SUCCESS", "#47f764", "fas fa-check-circle");
				    	}else if(result == '2'){
				    		// Toast thất bại
					  		createToastHTML("Chỉnh sửa tên NXB thất bại.", "ERROR", "#DC3545", "fas fa-exclamation-triangle");
				    	}
			    	}
				    	
			    	var result = '${delete_result}';
			    	if(result != ""){
				    	if(result == '0'){ // == 0 sai order_old
				    		// Toast thất bại
					  		createToastHTML("NXB đã có sách trên hệ thống.", "ERROR", "#DC3545", "fas fa-exclamation-triangle");
				    	}else if(result == '1'){
				    		// Toast thành công
					  		createToastHTML("Xóa NXB thành công.", "SUCCESS", "#47f764", "fas fa-check-circle");
				    	}else if(result == '2'){
				    		// Toast thất bại
					  		createToastHTML("Xóa NXB thất bại.", "ERROR", "#DC3545", "fas fa-exclamation-triangle");
				    	}
			    	}
			    	
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
    <script type="text/javascript" src="<c:url value = '/include/js/admin/publisher_admin.js' />"></script>
  </body>
</html>
