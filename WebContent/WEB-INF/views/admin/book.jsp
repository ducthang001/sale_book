<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix='c'%>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>BOOK ADMIN</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"/>

<link rel="preconnect" href="https://fonts.gstatic.com" />
<link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet" />
<link rel="stylesheet"
	href="<c:url value = '/include/css/bootstrap.css' />" />
<link rel="stylesheet" href="<c:url value = '/include/css/toast.css' />" />
<link rel="stylesheet" href="<c:url value = '/include/css/app.css' />" />
<link rel="stylesheet" href="<c:url value = '/include/css/admin/book_admin.css' />" />
<link rel="icon" href="<c:url value = '/images/favico.png' />" sizes="32x32" />
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="<c:url value = '/include/js/jquery.twbsPagination.js' />" type="text/javascript"></script>
<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
	.errors {
		color: red;
		font-style: italic;
	}
}
</style>
</head>

<body>


	<div class="container-fluid">
	
	
	
		<div class="col-md-3">
			<div id="sidebar" class="active">
				<div class="sidebar-wrapper active">
					<div class="sidebar-header">
						<div class="d-flex justify-content-between">
							<div class="logo">
								<a href="<c:url value = '/admin/statistics.htm' />"
									style="text-decoration: none"><img
									src="<c:url value = '/images/logo.png' />" style="height: 40px"
									alt="Logo" srcset="" /> <span style="font-size: 25px">BOOKSTORE</span></a>
							</div>
						</div>
					</div>
					<div class="sidebar-menu">
						<ul class="menu">
							<li class="sidebar-title">Danh Mục Quản Lý</li>

							<li class="sidebar-item"><a
								href="<c:url value = '/admin/statistics.htm' />"
								class="sidebar-link"> <i class="fas fa-chart-bar"></i> <span>Statistics</span>
							</a></li>

							<li class="sidebar-item active"><a
								href="<c:url value = '/admin/book.htm' />" class="sidebar-link">
									<i class="fas fa-book"></i> <span>Books</span>
							</a></li>

							<li class="sidebar-item"><a
								href="<c:url value = '/admin/order.htm' />" class='sidebar-link'>
									<i class="fas fa-cart-plus"></i> <span>Orders</span>
							</a></li>

							<li class="sidebar-item"><a
								href="<c:url value = '/admin/review.htm' />"
								class="sidebar-link"> <i class="fas fa-feather"></i> <span>Review</span>
							</a></li>

							<li class="sidebar-item"><a
								href="<c:url value = '/admin/customer.htm' />"
								class="sidebar-link"> <i class="fas fa-user-circle"></i> <span>Customer</span>
							</a></li>

							<li class="sidebar-item"><a
								href="<c:url value = '/admin/author.htm' />"
								class="sidebar-link"> <i class="fas fa-user-astronaut"></i>
									<span>Author</span>
							</a></li>

							<li class="sidebar-item"><a
								href="<c:url value = '/admin/category.htm' />"
								class="sidebar-link"> <i class="fas fa-bookmark"></i> <span>Category</span>
							</a></li>

							<li class="sidebar-item"><a
								href="<c:url value = '/admin/publisher.htm' />"
								class="sidebar-link"> <i class="fas fa-house-user"></i> <span>Publisher</span>
							</a></li>
						</ul>
					</div>
					<button class="sidebar-toggler btn x">
						<i data-feather="x"></i>
					</button>
				</div>
			</div>
		</div>
		
		
		
		<div class="col-md-9" style="margin-left: 300px">

			<div class="container  ">
			
			
				<div class="bg-light p-5 rounded">

					<label class="mb-1"><h6 class="mb-0 text-sm">${thongbao}</h6></label>

					<form:form class="row g-3" modelAttribute="book"
						action="/QLSACH/admin/${btnStatus}.htm">

						<form:input path="id_book" type="hidden" class="form-control"
							id="exampleFormControlInput1" placeholder="Vui lòng nhập id sách" />

						<div class="col-md-16">
							<label for="exampleFormControlInput1" class="form-label">Tên
								Sách</label>
							<form:input path="book_name" type="text" class="form-control"
								id="exampleFormControlInput1"
								placeholder="Vui lòng nhập tên sách" />
							<label class="mb-1">
								<h6 class="mb-0 text-sm">
									<form:errors path="book_name" cssClass="errors" />
								</h6>
							</label>
						</div>

						<div class="col-md-20">
							<label for="exampleFormControlInput1" class="form-label">
								Mô tả: </label>

							<form:textarea path="describe_book" type="text"
								class="form-control" id="exampleFormControlInput1"
								placeholder="Vui lòng nhập Mô tả của sách" />
						</div>
						<label></label>
						<div class="col-md-4">
							<label for="exampleFormControlInput1" class="form-label">Hình
								ảnh</label>

							<form:input path="picture" type="file" class="form-control"
								id="exampleFormControlInput1"
								placeholder="Vui lòng chọn hình ảnh sách" />

							<c:if test="${btnStatus == 'btnEdit'}">
								<img style="height: 120px; width: auto;" class="card-img-top "
									src="images/${book.picture}" alt="Card image cap">
							</c:if>


							<label class="mb-1">
								<h6 class="mb-0 text-sm">
									<form:errors path="picture" cssClass="errors" />
								</h6>
							</label>


						</div>

						<div class="col-md-4">
							<label for="exampleFormControlInput1" class="form-label">Giá</label>
							<form:input path="price" type="number" class="form-control"
								id="exampleFormControlInput1"
								placeholder="Vui lòng nhập giá của sách" min="1" />
							<%-- <form:errors path="price" cssClass="errors"></form:errors> --%>
							<label class="mb-1">
								<h6 class="mb-0 text-sm">
									<form:errors path="price" cssClass="errors" />
								</h6>
							</label>
						</div>

						<div class="col-md-4">
							<label for="exampleFormControlInput1" class="form-label">Năm
								sản xuất</label>
							<form:input type="text" path="publish_day" class="form-control"
								id="exampleFormControlInput1"
								placeholder="Vui lòng nhập năm sản xuất của sách" />
							<%-- <form:errors path="publish_day" cssClass="errors"></form:errors> --%>
							<label class="mb-1">
								<h6 class="mb-0 text-sm">
									<form:errors path="publish_day" cssClass="errors" />
								</h6>
							</label>
						</div>

						<div class="col-md-4">
							<label for="exampleFormControlInput1" class="form-label">Số
								lượng</label>
							<form:input path="total_quantity" type="number"
								class="form-control" id="exampleFormControlInput1"
								placeholder="Vui lòng nhập số lượng sách" min="1" />
							<%-- <form:errors path="total_quantity" cssClass="errors"></form:errors> --%>
							<label class="mb-1">
								<h6 class="mb-0 text-sm">
									<form:errors path="total_quantity" cssClass="errors" />
								</h6>
							</label>
						</div>
						
						<div class="col-md-4">
							<label for="exampleFormControlInput1" class="form-label">Tác
								giả</label>
							<form:select path="author.id_author" items="${authors}"
								itemValue="id_author" itemLabel="author_name"
								class="form-select form-select-lg mb-6"
								aria-label=".form-select-lg example">

							</form:select>
						</div>
						
						<div class="col-md-4">
							<label for="exampleFormControlInput1" class="form-label">Thể
								loại</label>
							<form:select path="category.category_id" items="${categorys}"
								itemLabel="category_name" itemValue="category_id"
								class="form-select form-select-lg mb-6"
								aria-label=".form-select-lg example">
							</form:select>
						</div>
					
						<div class="col-md-4">
							<label for="exampleFormControlInput1" class="form-label">Công
								ty</label>
							<form:select path="company.id_company" items="${companys}"
								itemLabel="company_name" itemValue="id_company"
								class="form-select form-select-lg mb-6"
								aria-label=".form-select-lg example">
							</form:select>
						</div>

				         <!-------------Button Lưu-----------  -->
						<div class="col-12">
							<button name="${btnStatus}" class="btn btn-info">Lưu</button>
						</div>


					</form:form>

				</div>
				
						<!------------------search sách  ------------------>
				<div class="bg-light p-5 rounded">
					<jsp:useBean id="pagedListHolder" scope="request"
						class="org.springframework.beans.support.PagedListHolder" />
					<c:url value="admin/book.htm" var="pagedLink">
						<c:param name="p" value="~" />
					</c:url>
					<div class="d-flex flex-row justify-content-between">

						<div>
							<span id="result1"></span>

							<form class="d-inline-flex">
								<input name="searchInput" id="searchInput"
									class="form-control me-2" type="search" placeholder="Search"
									aria-label="Search">


								<button name="btnsearch" id="searchBook"
									class="btn btn-outline-success" type="submit">Search</button>
							</form>
						</div>
					</div>
				</div>
				<!-- ---------kết thúc search sách---------- -->
				
				<!-- ---------bảng sách-------------- -->
				<table class="table">
					<thead class="table-light">
						<tr class="table-primary">

							<th>Mã Sách</th>
							<th>Tên Sách</th>
							<th>Hình ảnh</th>
							<th>Giá</th>
							<th>Số lượng</th>
							<th>Tác giả</th>
							<th>Thể loại</th>
							<th>Alter</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="s" items="${pagedListHolder.pageList}">
							<%--    		<c:forEach var="s" items="${books}"> --%>
							<tr>

								<td>${s.id_book}</td>
								<td>${s.book_name}</td>
								<td><img style="height: 120px; width: auto;"
									class="card-img-top " src="images/${s.picture}"
									alt="Card image cap"></td>
								<td>${s.price}</td>
								<td>${s.total_quantity}</td>
								<td>${s.author.author_name}</td>
								<td>${s.category.category_name}</td>
								<td>
								<a href="admin/book/${s.id_book}.htm?linkEdit"><img
										width="50" height="40" src="<c:url value="/images/edit.png"/>">
								</a> 
								<%-- <a name="btnDelete"
								href="admin/book/${s.id_book}.htm?linkDelete"
								role="button" ><img width="30" height="30"
									src="<c:url value="/images/delete.png"/>"></a> --%>
									
								
								<form class = "form_xoa_pro" style = "    display: inline" action="admin/book/${s.id_book}.htm?linkDelete" method = "post">
									
										<img class = "btn_xoa"
										width="30" height="30"
										src="<c:url value="/images/delete.png"/>">
										
								</form>
								
								
								
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>



				<div>
					<tg:paging pagedListHolder="${pagedListHolder}"
						pagedLink="${pagedLink}" />
					<%-- //giong ten ben controller --%>
				</div>
				<script
					src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
					integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
					crossorigin="anonymous"></script>
			</div>
		</div>

	</div>



	
	
	<script>

		var form_xoa_pro = document.querySelectorAll(".form_xoa_pro")
		var btn_xoa = document.querySelectorAll(".btn_xoa")
		
		console.log(btn_xoa)
		
		btn_xoa.forEach((element) => {
			element.addEventListener("click", () => {
				console.log("CLICK")
				var result = confirm("Bạn có chắc chắn muốn xóa không?");
				console.log(result)
				if(result === true){
					element.parentElement.submit();
				}
			})
			
		})


	</script>
	
	<script type="text/javascript"
		src="<c:url value = '/include/js/admin/book_admin.js' />"></script>

</body>
</html>

