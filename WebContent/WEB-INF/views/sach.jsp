<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix = 'c' %>
    <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sách ID ${id_book} ${book.book_name}</title>
    <link rel="stylesheet" href="<c:url value = '/include/css/style.css'/>">
    <link rel="stylesheet" href="<c:url value = '/include/css/sach_info.css'/>">
    <link rel="stylesheet" href="<c:url value = '/include/css/toast.css' />" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<link rel="icon" href="<c:url value ='/images/favico.png' />" sizes="32x32" />
</head>
<body>
    <!-- Navigation Header-->
    <header class="_100vw">
    	<div class = "toast_class"></div>
        <div class="_1200px flex_between pd-10_0">
            <div class="nav_left">
                <div class="logo">
                    <a href="<c:url value ='/home.htm' />"><img src="../images/logo.png" alt="Logo"><span>BOOK STORE</span></a>
                </div>
                <div class="search">
                    <form action="../search.htm">
                    <input type="text" name = "keyword" placeholder="Nhập sản phẩm cần tìm kiếm...">
                    <button class="btn_search">
                        <i class="fas fa-search"></i>
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

    <!-- Book info -->
    <div class="product_book flex_center mg-20_0">
        <div class="_1200px bg-white pd-10" style="display: flex">
            <div class="product_image flex-3">
                <img src="../images/${book.picture}" alt="Anh 9">
            </div>
            <div class="product_book_info flex-7">
                <h1 class="product_book_title">${book.book_name}</h1>
                <div class="row">
                    <div class="flex flex-1">
                        <span class="text">Tác giả: </span>
                        <p class="product_book_author mg-l-10"><strong><a href = '../author/${book.author.id_author}.htm'>${book.author.author_name}</a></strong></p>
                    </div>

                    <div class="flex flex-1">
                        <span class="text">Thể loại: </span>
                        <p class="product_book_category mg-l-10"><strong><a href = '../category/${book.category.category_id}.htm'>${book.category.category_name}</a></strong></p>
                    </div>
                </div>

                <div class="row">
                    <div class="flex-1 flex">
                        <span class="text">Nhà xuất bản: </span>
                        <p class="product_book_nxb mg-l-10"><strong> <a href = '../company/${book.company.id_company}.htm'>${book.company.company_name}</a></strong></p>
                    </div>

                    <div class="flex flex-1">
                        <span class="text">Ngày xuất bản: </span>
                        <p class="product_book_ngayxb mg-l-10"><strong>${book.publish_day}</strong></p>
                    </div>
                </div>
                
                <div class="ranking" style="margin: 10px 0px;"> </div>

                <div class="product_book_price"><strong><fmt:formatNumber type="number" pattern="##,###" value="${book.price}" />đ</strong></div>
                <div style="margin-bottom: 10px;">
                    <span class="product_book_slt">Số lượng tồn:</span>
                    <span class="mg-l-10"><strong id = "current_quanty">${book.total_quantity}</strong></span>
                </div>

                <table class="table" style="width: 50%">
							<thead class="thead-dark">
								<tr>
									<th scope="col">Số lượng</th>
									<th scope="col">Đặt mua</th>
								</tr>
							</thead>
							<tbody>
								<tr>
							        <td style="display: inline-block; padding: 5px 0px;">
							        <form id ="form_add" action = "<c:url value = '/book/add.htm' />" method="post">
							        	<input type="hidden" name = "bookid" id="book_id_input" value="${book.id_book}">
							        	<input type="number" id="quanty_input" name = "quanty" class="form-control" style="width: 70px" value="1" min="1" max="${book.total_quantity}">
							        </form>
							        </td>
							        <td>
							            <button type="button" id="btn_add_to_cart" class="btn btn-success" style="display: flex;align-items: center;">
							                    <i class="fas fa-plus-circle" style="padding: 0px 5px;"></i>
							                    <p>Giỏ Hàng</p>
							            </button>
							        </td>
								</tr>
							</tbody>
						</table>
            </div>
        </div>
    </div>

    <!-- Book description -->

    <div class="book_description flex_center mg-20_0">
        <div class="_1200px bg-white pd-10">
            <h3>Mô Tả Sản Phẩm</h3>
            ${book.describe_book}
        </div>
    </div>

    <!-- Same Author -->
    <div class="favorite_book_list flex_center mg-10_0">
        <div class="_1200px bg-white flex_col pd-10">

            <div class="favorite_book_list_icon_title">
                    <img src="../images/favorite_book.png" alt="favorite book">
                    <span class="favorite_book_list_title mg-l-10">Cùng tác giả</span>
            </div>
            <div class="favorite_book_item flex_center mg-10_0">
            	<c:forEach var = "item" items = "${bookofauthor}">
					<div class="favorite_book flex_col_center">
                    <a href="./${item.id_book}.htm">
                    <div class="book_logo">
                        <img src="../images/${item.picture}" alt="sách được yêu thích">
                    </div>
                    <span class="info"></span>
                        <h3 class="book_name">${item.book_name}</h3>
                        <span class="book_author">${item.author.author_name}</span>
                        <p class="book_price"><fmt:formatNumber type="number" pattern="##,###" value="${item.price}" />đ</p>
                        
                        <form class = "add_to_cart" action="<c:url value = '/sach/${id_book}/reference/add.htm' />" method="post">
                            <input type="hidden" name="bookid" value="${item.id_book}">
                            <button class="btn_buynow"><i class="fas fa-cart-plus"></i> Thêm vào giỏ hàng</button>
                   		</form>
                    </a>
                </div>
				</c:forEach>
            
            </div>
        </div>
    </div>

    <!-- book rating -->
    <div class="rating flex_center mg-20_0">
        <div class="_1200px bg-white pd-10">
            <h3 class="rating_title mg-10_0">Đánh giá sản phẩm</h3>

            <div class="overall_detail flex_center">
                <div class="overall_rating">
                    <div>Đánh Giá Trung Bình</div>
                    <p class="point">${average}</p>
                    <div class="star">
                        <i class="fa fa-star" style="color: #48bb78"></i>
                        <i class="fa fa-star" style="color: #48bb78"></i>
                        <i class="fa fa-star" style="color: #48bb78"></i>
                        <i class="fa fa-star" style="color: #48bb78"></i>
                        <i class="fa fa-star" style="color: rgba(83, 79, 79, 0.13)"></i>
                    </div>
                    <div class="rating_total">${book.reviews.size()} đánh giá</div>
                </div>

            <div class="rating_total_detail">
                <div class="five_star flex_align_center mg-10_0">
                    <span>5</span>
                    <i class="fa fa-star" style="color: #48bb78"></i>
                    <span class="progress_star mg-l-10">
                        <span class="progress_star_over" style="width: 100px"></span>
                    </span>
                    <span class="percent">50%</span>
                </div>
                <div class="four_star flex_align_center mg-10_0">
                    <span>4</span>
                    <i class="fa fa-star" style="color: #48bb78"></i>
                    <span class="progress_star mg-l-10">
                        <span class="progress_star_over" style="width: 40px"></span>
                    </span>
                    <span class="percent">20%</span>
                </div>
                <div class="three_star flex_align_center mg-10_0">
                    <span>3</span>
                    <i class="fa fa-star" style="color: #48bb78"></i>
                    <span class="progress_star mg-l-10">
                        <span class="progress_star_over" style="width: 40px"></span>
                    </span>
                    <span class="percent">20%</span>
                </div>
                <div class="two_star flex_align_center mg-10_0">
                    <span>2</span>
                    <i class="fa fa-star" style="color: #48bb78"></i>
                    <span class="progress_star mg-l-10">
                        <span class="progress_star_over" style="width: 20px"></span>
                    </span>
                    <span class="percent">10%</span>
                </div>
                <div class="one_star flex_align_center mg-10_0">
                    <span>1</span>
                    <i class="fa fa-star" style="color: #48bb78"></i>
                    <span class="progress_star mg-l-10">
                        <span class="progress_star_over" style="width: 0px"></span>
                    </span>
                    <span class="percent">0%</span>
                </div>
            </div>
            
            
            <c:if test="${(login == 1 && reviewed == 0) || login == 0}">
            <button class="btn_show_review_input">Gửi đánh giá của bạn</button> <!-- CHƯA ĐÁNH GIÁ / ĐÁNH GIÁ  (ĐÃ LOGIN) -->
            </c:if>
            
            <c:if test="${login == 1 && reviewed == 1}">
            <button class="btn_show_review_input">Chỉnh sửa đánh giá của bạn</button> <!-- CHƯA ĐÁNH GIÁ / ĐÁNH GIÁ  (ĐÃ LOGIN) -->
            </c:if>
            </div>
            
            <!-- FORM ĐÁNH GIÁ -->
            <c:if test="${login == 1}">
	            <form action = "<c:url value = '/sach/review/add.htm' />" class="form_review display_flex display_none" style="background-color: #f8f9fa; padding: 20px;">
	                <input type="hidden" name="bookid" id = "book_id_review" value="${book.id_book}">
	                <input type="hidden" name="star" id = "star_review" value="0">
	                <div class="form_left" style="flex: 4; text-align: center;">
	                    <p><b>Bạn chấm sản phẩm này bao nhiêu sao?</b></p>
	                    <ul class="list_star_review" style="margin-top:10px">
	                    	<c:forEach var = "i" begin = "1" end =  "5">
	                    	 	<c:if test="${i <= star_reviewed}">
                    				<i class="fa fa-star one_star_review" star_value = "${i-1}" style="color: #48bb78; font-size: 20px;"></i>
                    			</c:if>
                    			<c:if test="${i > star_reviewed}">
                    				<i class="fa fa-star one_star_review" star_value = "${i-1}" style="color: #676666; font-size: 20px;"></i>
                    			</c:if>
            				</c:forEach>
	                      
	                    </ul>
	                </div>
	
	
	                <div class="form_right" style="flex: 6">
	                    <textarea name="review" id="" rows="3" placeholder="Bạn có khuyên người khác mua sản phẩm này không? Tại sao?">${content}</textarea>
	                    <button class="btn_review_submit" type="submit">Gửi đánh giá</button>
	                </div>
	            </form>
            </c:if>
            
            <c:if test="${login == 0}">
              	<div class="form_review display_none" style="background-color: #f8f9fa; padding: 20px;">
              	  <p style="text-align: center;font-size: 19px;color: #cb1c22;font-weight: 700;">Vui lòng đăng nhập tài khoản để đánh giá sản phẩm</p>
            	</div>
            </c:if>
            
            <div class="rate">
                <!-- one rate -->
                <c:forEach var = "item" items = "${book.reviews}">
                	<div class="one_rate">
                    <div class="user_image">
                        <img src="../images/user.png" alt="user image">
                    </div>
                    <div class="rating_content">
                        <div class="username_star pd-5_0">
                            <div class="user_name"><strong>${account.findUsernameReview(item.id.user_id)}</strong></div>
                            <div class="star">
                            	<c:forEach var = "i" begin = "1" end =  "${item.star}">
                                	<i class="fa fa-star" style="color: orange"></i>
                            	</c:forEach>
                            </div>
                        </div>
                        <p class="pd-5_0">
                            ${item.comments}
                        </p>
                        <div class="time_rate pd-5_0">
                            <i class="fas fa-clock" style="color: green"></i>
                            <span>${item.time}</span>
                        </div>
                    </div>
                   
                    	
                    	 <c:if test="${role == 1 ||accountId == item.id.user_id }">
                     <span onclick="deleteFunc(${item.id.user_id},${book.id_book })" class="span_delete">Xóa</span>
                     </c:if>
                  
                   
                </div>
                </c:forEach>
            </div>
        </div>
			<input type="hidden" id = "dadat" value="${have}">
    </div>
    <!-- I think you like it -->
    <div class="favorite_book_list flex_center mg-10_0">
        <div class="_1200px bg-white flex_col pd-10">

            <div class="favorite_book_list_icon_title">
                    <img src="../images/favorite_book.png" alt="favorite book">
                    <span class="favorite_book_list_title mg-l-10">Có thể bạn sẽ thích</span>
            </div>

            <div class="favorite_book_item flex_center mg-10_0">
            	<c:forEach var = "ds" items = "${topbuy}">            	
                 <div class="favorite_book flex_col_center">
                    <a href="<c:url value = '/sach/${ds[0]}.htm' />">
                    <div class="book_logo">
                        <img src="<c:url value = '/images/${ds[3]}' />" alt="sách được yêu thích">
                    </div>
                    <span class="info"></span>
                        <h3 class="book_name">${ds[1]}</h3>
                        <span class="book_author">${ds[2]}</span>
                        <p class="book_price"><fmt:formatNumber type="number" pattern="##,###" value="${ds[4]}" />đ</p>
                        <p class="book_author" style="margin-top: 10px;background-color: #48bb78;color: #fff;border-radius: 17px;padding: 0;">Đã bán ${ds[5]}</p>
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
	
	<script type="text/javascript">
	
	
	function deleteFunc(id,bookid){
		$("#idBook").val(bookid);
		$("#idUser").val(id);
		console.log("idBook "+bookid +" idUSer " +id)
		$("#formDelete").submit();
	}
	
	
	
	var arr_star = [0, 0, 0, 0, 0];
	var size = ${book.reviews.size()};
	<c:forEach var = "item" items = "${book.reviews}">
		arr_star[${item.star - 1}] += 1;    
	</c:forEach> 
	
	var star_percent = [0, 0, 0, 0, 0];
	if(size === 0){
		star_percent = [0, 0, 0, 0, 0]
	}else{
		for(var i = 0; i<star_percent.length; i++){
			star_percent[i] = Math.round(arr_star[i] * 100 / ${book.reviews.size()});
		}
	}
	
	
	var sum = 0;
	var sum_review = 0;
	for (var i = 0; i<arr_star.length; i++){
		sum += (i+1) * arr_star[i];
		sum_review += arr_star[i];
	}
	var average = 0;
	if(sum_review === 0)
		average = 0;
	else average = sum/sum_review;
	console.log("Average = "+Math.floor(average))
	
	var star_class = document.querySelector(".overall_rating .star");
	console.log(star_class)
	var star_html = "";
	for(var i = 1; i<=5; i++){
		if(i <= average)
			star_html += "<i class='fa fa-star' style='color: #48bb78'></i>";
		else{
			star_html += "<i class='fa fa-star' style='color: rgba(83, 79, 79, 0.13)'></i>";
		}
	}
	
	star_class.innerHTML = star_html;
	
	var ranking_htm = "Xếp hạng: ";
	for(var i = 1; i<=5; i++){
		if(i <= average){
			ranking_htm += "<i class='fa fa-star' style='color: orange; font-size:15px'></i>";
		}else{
			ranking_htm += "<i class='fa fa-star' style='color: rgba(83, 79, 79, 0.13); font-size:15px'></i>";
		}
	}
	
	
	var ranking_class = document.querySelector(".ranking");
	ranking_class.innerHTML = ranking_htm;
	
	
	var rating_total_detail = document.querySelector(".rating_total_detail");
	rating_total_detail.innerHTML = "<div class='five_star flex_align_center mg-10_0'>"
		+ "<span>5</span>"
		+ "<i class='fa fa-star' style='color: #48bb78'></i>"
		+ " <span class='progress_star mg-l-10'>"
		+ "<span class='progress_star_over' style='width: "+star_percent[4] * 200 / 100+"px'></span>"
		+ "</span>"
		+ "<span class='percent'>"+star_percent[4]+"%</span>"
		+ "</div>"
		+ "<div class='four_star flex_align_center mg-10_0'>"
		+ "<span>4</span>"
		+ "<i class='fa fa-star' style='color: #48bb78'></i>"
		+ "<span class='progress_star mg-l-10'>"
		+ "<span class='progress_star_over' style='width: "+star_percent[3] * 200 / 100+"px'></span>"
		+ "</span>"
		+ "<span class='percent'>"+star_percent[3]+"%</span>"
		+ "</div>"
		+ "<div class='three_star flex_align_center mg-10_0'>"
		+ "<span>3</span>"
		+ "<i class='fa fa-star' style='color: #48bb78'></i>"
		+ "<span class='progress_star mg-l-10'>"
		+ "<span class='progress_star_over' style='width: "+star_percent[2] * 200 / 100+"px'></span>"
		+ "</span>"
		+ "<span class='percent'>"+star_percent[2]+"%</span>"
		+ "</div>"
		+ "<div class='two_star flex_align_center mg-10_0'>"
		+ "<span>2</span>"
		+ "<i class='fa fa-star' style='color: #48bb78'></i>"
		+ "<span class='progress_star mg-l-10'>"
		+ "<span class='progress_star_over' style='width: "+star_percent[1] * 200 / 100+"px'></span>"
		+ "</span>"
		+ "<span class='percent'>"+star_percent[1]+"%</span>"
		+ "</div>"
		+ "<div class='one_star flex_align_center mg-10_0'>"
		+ "<span>1</span>"
		+ "<i class='fa fa-star' style='color: #48bb78'></i>"
		+ "<span class='progress_star mg-l-10'>"
		+ "<span class='progress_star_over' style='width: "+star_percent[0] * 200 / 100+"px'></span>"
		+ "</span>"
		+ "<span class='percent'>"+star_percent[0]+"%</span>"
		+ "</div>";
				
	
	console.log(arr_star)
	console.log(star_percent)
	
	
	
	
	// VIẾT ĐÁNH GIÁ
	var btn_show_review_input = document.querySelector(".btn_show_review_input")
        var form_review = document.querySelector(".form_review");
        var list_stars = document.querySelectorAll(".one_star_review");
        console.log(list_stars)
        btn_show_review_input.addEventListener("click", () => {
            form_review.classList.toggle("display_none");
        })

        function changeStarColor(index){
            for(var k = 0; k<5; k++){
                if(k <= index){
                    list_stars[k].style.color = "#48bb78";
                }else{
                    list_stars[k].style.color = "#676666";
                }
            }


        }

        list_stars.forEach((element) => {
            element.addEventListener("click", () => {
                var starClick = element.getAttribute("star_value");
                var star_review = document.querySelector("#star_review");
                star_review.value = parseInt(starClick) + 1;
                changeStarColor(starClick);
            })
        })
	
	
	// ADD TO CART
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
    	
	
		var btn_add_to_cart = document.querySelector("#btn_add_to_cart");
        var form_add = document.querySelector("#form_add");
        var quanty_input = document.querySelector("#quanty_input");
        quanty_input.addEventListener("change", (e) => {
            quanty_input.value = e.target.value;
        })

        btn_add_to_cart.addEventListener("click", () => {
            form_add.submit();
        });
        
        var result = '${add_result}';
    	if(result != ""){
	    	if(result == '0'){
		  		createToastHTML("Số lượng không được nhỏ hơn 1", "ERROR", "#DC3545", "fas fa-exclamation-triangle");
	    	}else if(result == '1'){
		  		createToastHTML("Không tìm thấy ID sách trong dữ liệu.", "ERROR", "#DC3545", "fas fa-exclamation-triangle");
	    	}else if(result == '2'){
		  		createToastHTML("Thêm thất bại do số lượng sản phẩm không đủ! Còn lại là: "+'${add_conlai}', "ERROR", "#DC3545", "fas fa-exclamation-triangle");
	    	}else if(result == '3'){
		  		createToastHTML("Thêm thất bại! còn lại "+'${add_conlai}'+" sản phẩm, giỏ hàng bạn có: "+'${add_dadat}', "ERROR", "#DC3545", "fas fa-exclamation-triangle");
	    	}else if(result == '4'){
		  		createToastHTML("Thêm sản phẩm vào giỏ hàng thành công", "SUCCESS", "#47f764", "fas fa-check-circle");
	    	}
	    	
    	}
    	
    	var result = '${review_result}';
    	if(result != ""){
	    	if(result == '0'){
		  		createToastHTML("Không tìm thấy ID sách bạn đang đánh giá", "ERROR", "#DC3545", "fas fa-exclamation-triangle");
	    	}else if(result == '1'){
		  		createToastHTML("Số sao phải lớn hơn 0 và nhỏ hơn 5.", "ERROR", "#DC3545", "fas fa-exclamation-triangle");
	    	}else if(result == '2'){
		  		createToastHTML("Thêm đánh giá sản phẩm thành công", "SUCCESS", "#47f764", "fas fa-check-circle");
	    	}else if(result == '3'){
		  		createToastHTML("Thêm đánh giá sản phẩm thất bại", "ERROR", "#DC3545", "fas fa-exclamation-triangle");
	    	}else if(result == '4'){
		  		createToastHTML("Cập nhật đánh giá lại sản phẩm thành công", "SUCCESS", "#47f764", "fas fa-check-circle");
	    	}else if(result == '5'){
		  		createToastHTML("Cập nhật đánh giá lại sản phẩm thất bạn", "ERROR", "#DC3545", "fas fa-exclamation-triangle");
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
</body>
</html>