<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix = 'c' %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BOOK THONG KE</title>
    
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"/>
    <link rel="stylesheet" href="<c:url value = '/include/css/bootstrap.css' />" />
    <link rel="icon" href="<c:url value = '/images/favico.png' />" sizes="32x32" />
    <link rel="stylesheet" href="<c:url value = '/include/css/app.css' />" />
</head>

<body>
    <div id="app doan">
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
                  />
                  <span style="font-size: 25px">BOOKSTORE</span></a
                >
              </div>
        </div>
    </div>
    <div class="sidebar-menu">
        <ul class="menu">
              <li class="sidebar-title">Danh Mục Quản Lý</li>

              <li class="sidebar-item active">
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

              <li class="sidebar-item">
                <a href="<c:url value = '/admin/publisher.htm' />" class="sidebar-link">
                  <i class="fas fa-house-user"></i>
                  <span>Publisher</span>
                </a>
              </li>
            </ul>
    </div>
    <button class="sidebar-toggler btn x"><i data-feather="x"></i></button>
</div>
        </div>
        <div id="main">
            <header class="mb-3">
                <a href="#" class="burger-btn d-block d-xl-none">
                    <i class="bi bi-justify fs-3"></i>
                </a>
            </header>
            
<div class="page-heading">
    <h3>Thống Kê Năm</h3>
</div>
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
            <div class="row">
                <div class="row">
                <div class="col-12" style="position: relative;">
                    <form action = "<c:url value ='/admin/statistics.htm' />" method="post" class = "form_year_select" style="position: absolute;z-index: 10000;top: 20px;right: 40px;">
                        <select class="form-select" name="year" id="year_profit" aria-label="Default select example">
                            <c:forEach var = "i" begin = "${year_profit - 2}" end =  "${year_profit}">
                            	<c:choose>
								    <c:when test="${year_use_detail == 1 && year_profit_detail == year_profit - i + (year_profit - 2)}">
								        <option selected value="${year_profit - i + (year_profit - 2)}">${year_profit - i + (year_profit - 2)}</option>
								    </c:when>
								    <c:when test="${year_use_detail != 1 && year_profit == year_profit - i + (year_profit - 2)}">
								        <option selected value="${year_profit - i + (year_profit - 2)}">${year_profit - i + (year_profit - 2)}</option>
								    </c:when> 
								    <c:otherwise>
								       <option value="${year_profit - i + (year_profit - 2)}">${year_profit - i + (year_profit - 2)}</option>
								    </c:otherwise>
								</c:choose>
                            
	                    	 	
            				</c:forEach>
                            
                        </select>
                      </form>
                    <div class="card">
                        <div class="card-header">
                            <h4>Thống kê doanh thu</h4>
                        </div>
                        <div class="card-body">
                            <div id="chart-profile-visit"></div>
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
    var optionsProfileVisit;
    var result = '${profit_detail_month}';
	if(result != ""){
		optionsProfileVisit = {
	    		annotations: {
	    			position: 'back'
	    		},
	    		dataLabels: {
	    			enabled:false
	    		},
	    		chart: {
	    			type: 'bar',
	    			height: 300
	    		},
	    		fill: {
	    			opacity:1
	    		},
	    		plotOptions: {
	    		},
	    		series: [{
	    			name: 'sales',
	    			data: "${profit_detail_month}".split(" ")
	    		}],
	    		colors: '#435ebe',
	    		xaxis: {
	    			categories: ["Jan","Feb","Mar","Apr","May","Jun","Jul", "Aug","Sep","Oct","Nov","Dec"],
	    		},
	    	};
	}else{
		optionsProfileVisit = {
	    		annotations: {
	    			position: 'back'
	    		},
	    		dataLabels: {
	    			enabled:false
	    		},
	    		chart: {
	    			type: 'bar',
	    			height: 300
	    		},
	    		fill: {
	    			opacity:1
	    		},
	    		plotOptions: {
	    		},
	    		series: [{
	    			name: 'sales',
	    			data: "${profit_detail}".split(" ")
	    		}],
	    		colors: '#435ebe',
	    		xaxis: {
	    			categories: ["Jan","Feb","Mar","Apr","May","Jun","Jul", "Aug","Sep","Oct","Nov","Dec"],
	    		},
	    	};
	}
    
    
    	console.log("${profit_detail}".split(" "));
    	
    	
    	 var year_profit = document.querySelector("#year_profit");
         var form_year_select = document.querySelector(".form_year_select");

         year_profit.addEventListener("change", () => {
           form_year_select.submit();
         })
    </script>
    <script src="<c:url value = '/include/apexcharts/apexcharts.js' />"></script>
    <script src="<c:url value = '/include/apexcharts/dashboard.js' />"></script>
</body>

</html>
