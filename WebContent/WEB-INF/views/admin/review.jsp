<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix = 'c' %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>REVIEW ADMIN</title>
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
    <link rel="icon" href="<c:url value = '/images/favico.png' />" sizes="32x32" />
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="<c:url value = '/include/js/jquery.twbsPagination.js' />" type="text/javascript"></script>
  </head>

  <body>
    <div id="app">
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

              <li class="sidebar-item active">
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
                          Tìm kiếm sách nhanh
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
                            Danh sách đánh giá
                          </h4>
                        </div>

                        <div class="table-responsive pt-3">
                          <table class="table table-bordered text-center">
                            <thead>
                              <tr>
                                <th><a href="<c:url value = '/admin/review.htm?page=${currentpage}&order=id&dir=${orderLink}' />">ID</a></th>
                                <th><a href="<c:url value = '/admin/review.htm?page=${currentpage}&order=name&dir=${orderLink}' />">Tên sách</a></th>
                                <th>Ảnh</th>
                                <th>Tổng số lượt đánh giá</th>
                                <th>Chi tiết</th>
                              </tr>
                            </thead>
                            <tbody>
                            <c:forEach var = "item" items = "${books}">
                            	<tr class="table-white">
                                <td>${item.id_book}</td>
                                <td>${item.book_name}</td>
                                <td>
                                  <img
                                    src="<c:url value = '/images/${item.picture}' />"
                                    alt=""
                                    style="height: 120px; width: auto"
                                  />
                                </td>
                                <td>
								<c:choose>
								    <c:when test="${item.reviews.size() == 0}">
								        <div class="ratings"> 
	                                      <i class="fa fa-star"></i> 
	                                      <i class="fa fa-star"></i> 
	                                      <i class="fa fa-star"></i> 
	                                      <i class="fa fa-star"></i> 
	                                      <i class="fa fa-star"></i>
                                      	  <span>(0)</span>
								   		</div>
								    </c:when>    
								    <c:otherwise>
								        <c:forEach var = "rate" items = "${reviews.getListStarCount(item.id_book)}">
								        	<div class="ratings"> 
								        		<c:forEach var="i" begin="1" end="${rate[0]}" step="1">
												    <i class="fa fa-star rating-color"></i> 
												</c:forEach>
												<c:forEach var="i" begin="1" end="${5 - rate[0]}" step="1">
												    <i class="fa fa-star"></i> 
												</c:forEach>
		                                      	  <span>(${rate[1]})</span>
								   			</div>
								        </c:forEach>
								    </c:otherwise>
								</c:choose>
                                	
                                </td>
                                <td class="align-middle text-center">
                                  <a href="<c:url value = '/sach/${item.id_book}.htm' />" type="button" class="btn btn-success m-1 btn_read_review">
                                    Chi tiết
                                  </a>
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
       <form action="<c:url value = '/admin/review.htm' />" id = "form_page">
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
</script>
  </body>
</html>
