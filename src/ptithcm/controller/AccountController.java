package ptithcm.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ptithcm.bean.CartDto;
import ptithcm.captcha.CaptchaServlet;
import ptithcm.entity.AccountEntity;
import ptithcm.entity.ItemEntity;
import ptithcm.entity.OrderEntity;
import ptithcm.serviceimpl.AccountServiceImpl;
import ptithcm.serviceimpl.CartServiceImpl;
import ptithcm.serviceimpl.HomeServiceImpl;
import ptithcm.serviceimpl.ItemServiceImpl;
import ptithcm.serviceimpl.OrderServiceImpl;
import ptithcm.serviceimpl.StatisticsServiceImpl;

@Controller
public class AccountController {
	@Autowired
	AccountServiceImpl accountServiceImpl;
	
	@Autowired
	ItemServiceImpl itemServiceImpl;
	
	@Autowired
	CartServiceImpl cartServiceImpl;
	
	@Autowired
	HomeServiceImpl homeServiceImpl;
	
	@Autowired
	OrderServiceImpl orderServiceImpl;
	
	@Autowired
	StatisticsServiceImpl statisticsServiceImpl;
	
	@Autowired
	JavaMailSender mailer;
	
	private static AccountEntity user;
	
	@RequestMapping("account/login")
	public String login(HttpServletRequest request, HttpServletResponse response){
		// testguimail();

		HttpSession session = request.getSession();
		
		if(session != null) {
			
			if(session.getAttribute("username") != null && user != null) {
				return "redirect:/account/info.htm";
			}
			
		}
		
		return "account/login";
	}
	
	//
	
	public boolean SentNewPassWord(String email, String username, String password) {
		try {
//			Random rd = new Random();
//		    int pass = 100000 + rd.nextInt(999999);
			String from = "BOOKSTORE.PTIT.HCM";
			String to = email;
			String body = "<h2>Thế giới sách số một Việt Nam xin cập nhật mật khẩu đăng nhập hiện tại của bạn là: "+password +"</h2>";
			String subject = "Cấp lại mật khẩu đăng nhập của tài khoản "+username+" để đăng nhập vào kênh thế giới mua sắm sách số một Việt Nam";
			MimeMessage mail = mailer.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail);
			helper.setFrom(from, from);
			helper.setTo(to);
			helper.setReplyTo(from, from);
			helper.setSubject(subject);
			helper.setText(body, true);
			mailer.send(mail);
			System.out.println("Gửi mail thành công");
			return true;
		} catch (Exception e) {
			System.out.println("Gửi mail thất bại");
			return false;
		}
	}
	
	public boolean checkLogin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session != null) {
			if(session.getAttribute("username") != null && user != null) {
				return true;
			}
		}
		return false;
	}
	
	@RequestMapping("account/signup")
	public String signup(HttpSession session) {
		if(session != null) {
			if(session.getAttribute("username") != null && user != null) {
				return "redirect:/account/info.htm";
			}
		}
		return "account/signup";
	}
	
	@RequestMapping("account/verify")
	public String verify(HttpSession session) {
		if(session != null) {
			if(session.getAttribute("username") != null && user != null) {
				return "redirect:/account/info.htm";
			}
		}
		return "account/verify";
	}
	
	@RequestMapping(value = "account/verify/submit", method = RequestMethod.POST)
	public String register(@RequestParam("username") String username, @RequestParam("email") String email, RedirectAttributes ra) {
		username = username.trim();
		email = email.trim();
		if(username.length() < 3 || email.length() == 0 || username.contains(" ") == true || email.contains(" ") == true || email.length() == 0) {
			ra.addFlashAttribute("add_result", 0); // Lỗi username...
		}else if(accountServiceImpl.checkInfoGetPasswordExist(username, email) == null) {
			ra.addFlashAttribute("add_result", 1); // Username + email chưa chính xác
		}else {
			AccountEntity B = accountServiceImpl.checkInfoGetPasswordExist(username, email);
			String password = B.getPassword();
			boolean result = SentNewPassWord(email, username, password);
			if(result == true) {
				ra.addFlashAttribute("add_result", 2); // Thành công
			}else {
				ra.addFlashAttribute("add_result", 3); // Thất bại /account/updatepassword/submit.htm
			}
		}
		
		return "redirect:/account/verify.htm";
		
	}
	
	@RequestMapping(value = "account/updatepassword", method = RequestMethod.POST)
	public String updatepasswordsubmit(@RequestParam("oldpassword") String oldpassword, 
			@RequestParam("newpassword") String newpassword, 
			@RequestParam("renewpassword") String renewpassword, 
			RedirectAttributes ra) {
		oldpassword = oldpassword.trim();
		renewpassword = renewpassword.trim();
		newpassword = newpassword.trim();
		String username_login = AccountController.getUser().getUsername();
		
		// Code lam bi loi csrf
//		accountServiceImpl.updatePassword(renewpassword, username_login);
		
		// Su dung cac dieu kien de tranh loi CSRF
		if(oldpassword.length() == 0 || oldpassword.contains(" ") == true || newpassword.length() == 0 || newpassword.contains(" ") == true || renewpassword.contains(" ") == true || renewpassword.length() == 0) {
			ra.addFlashAttribute("password_update", 0); // Lỗi trống hoặc sai ký tự
		}else if(accountServiceImpl.checkOldPassWordExactly(oldpassword, username_login) == false) {
			ra.addFlashAttribute("password_update", 1); // Mật khẩu cũ không chính xác
		}else if(renewpassword.equals(newpassword) == false) {
			ra.addFlashAttribute("password_update", 2); // Hai mật khẩu không chính xác
		}else {
			int result = accountServiceImpl.updatePassword(renewpassword, username_login);
			if(result > 0) {
				ra.addFlashAttribute("password_update", 3); // Thành công
			}else {
				ra.addFlashAttribute("password_update", 4); // Thất bại 
			}
		}
		
		return "redirect:/account/info.htm";
		
	}

	@RequestMapping(value = "account/signup/submit", method = RequestMethod.POST)
	public String register(@RequestParam("username") String username, @RequestParam("phone") String phone, 
				@RequestParam("password") String password, @RequestParam("repassword") String repassword, RedirectAttributes ra) {
		username = username.trim();
		phone = phone.trim();
		repassword = repassword.trim();
		password = password.trim();
		if(username.length() == 0 || password.length() == 0 || username.contains(" ") == true || phone.contains(" ") == true || repassword.contains(" ") == true || phone.contains(" ") == true) {
			ra.addFlashAttribute("add_result", 0); // Lỗi username...
		}else if(username.length() < 3) {
			ra.addFlashAttribute("add_result", 1); // Lỗi username... < 3 ký tự
		}else if(phone.length() != 10) {
			ra.addFlashAttribute("add_result", 2); // Lỗi số điện thoại
		}else if(password.equals(repassword) == false) {
			ra.addFlashAttribute("add_result", 3); // Mật khẩu không chính xác 
		}else if(accountServiceImpl.checkUsernameExist(username) == true) {
			ra.addFlashAttribute("add_result", 4); // Username đã tồn tại
		}else if(accountServiceImpl.checkPhoneExist(phone) == true) {
			ra.addFlashAttribute("add_result", -1); // Phone đã tồn tại
		}else {
			AccountEntity A = new AccountEntity();
			A.setRole(0);
			A.setPhone(phone);
			A.setUsername(username);
			A.setPassword(password);
			int result = accountServiceImpl.addAccount(A);
			if(result > 0) {
				ra.addFlashAttribute("add_result", 5); // Thành công
			}else {
				ra.addFlashAttribute("add_result", 6); // Thất bại
			}
		}
		
		return "redirect:/account/signup.htm";
		
	}
	
	@RequestMapping("account/info")
	public String info(ModelMap model, HttpServletRequest request) {
		
		
		if(checkLogin(request) == false) {
			return "redirect:/account/login.htm"; // "account/login";
		}
		// Đang đăng nhập mà nếu bị xóa tài khoản thì sẽ bị logout
		if(user != null && accountServiceImpl.checkIDandUsernameExist(AccountController.getUser().getUsername(), AccountController.getUser().getUser_id()) == false) {
			return  "redirect:/account/logout.htm";
		}
		model.addAttribute("user", user);
		int user_id = AccountController.getUser().getUser_id();
		model.addAttribute("amountspent", statisticsServiceImpl.amountSpentbyIdUser(user_id));
		model.addAttribute("pendingordercount", statisticsServiceImpl.getPendingOrderCountbyIdUser(user_id));
		model.addAttribute("ordersuccesscount", statisticsServiceImpl.getOrderSuccessCountbyIdUser(user_id));
		return "account/info";
	}
	
	@RequestMapping(value = "account/info", method = RequestMethod.POST)
	public String updateInfo(@RequestParam("email") String email,@RequestParam("address") String address,
		@RequestParam("phone") String phone, @RequestParam("age") int age,  @RequestParam("gender") int gender,@RequestParam("role") int role, @RequestParam("user_id") int user_id,
		ModelMap model, RedirectAttributes ra, HttpServletRequest request) {
		String username = user.getUsername();
		System.out.println("username: "+username);
		System.out.println("email: "+email);
		System.out.println("phone: "+phone);
		System.out.println("age: "+age);
		System.out.println("gender: "+gender);
		if(checkLogin(request) == false) {
			return "redirect:/account/login.htm"; // "account/login";
		}
		email = email.trim();
		phone = phone.trim();
		String email_login = AccountController.getUser().getEmail();
		String phone_login = AccountController.getUser().getPhone();
		if(email.equals(email_login) == false && email.length() != 0) {
			if(accountServiceImpl.checkEmailExist(email) == true) {
				System.out.println("VAO DAY LAM 1");
				ra.addFlashAttribute("update", -2);
				return  "redirect:/account/info.htm";
			}
		}
		
		if(phone.equals(phone_login) == false && phone.length() != 0) {
			if(accountServiceImpl.checkPhoneExist(phone) == true) {
				System.out.println("VAO DAY LAM 2");
				ra.addFlashAttribute("update", -1);
				return  "redirect:/account/info.htm";
			}
		}
		
		int result = accountServiceImpl.updateUser(user_id, email, phone, age, gender,address,role);
		if(result > 0) {
			user = accountServiceImpl.updateUserAfterUpdate(username);
			ra.addFlashAttribute("update", 1);
		}else {
			ra.addFlashAttribute("update", 2);
		}
		// INFO ******
		ra.addFlashAttribute("user", user);
		System.out.println("SAU KHI EDIT");
		System.out.println("USERNAME NEW = "+user.getEmail());
		return  "redirect:/account/info.htm";
	}
	
	@RequestMapping("account/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("username");
		session.removeAttribute("role");
		user = null;
		// session.setAttribute(null, session);
		session.setAttribute("cart", null);
		return "redirect:/account/login.htm"; // "account/login";
	}
	
	@RequestMapping("account/order")
	public String order(HttpServletRequest request, ModelMap model) {
		if(checkLogin(request) == false) {
			return "redirect:/account/login.htm"; // "account/login";
		}
		// Đang đăng nhập mà nếu bị xóa tài khoản thì sẽ bị logout
		if(user != null && accountServiceImpl.checkIDandUsernameExist(AccountController.getUser().getUsername(), AccountController.getUser().getUser_id()) == false) {
			return  "redirect:/account/logout.htm";
		}
		model.addAttribute("user", user);
		System.out.println("ORDER cua user: "+user.getUser_id());
		model.addAttribute("orders", orderServiceImpl.getOrderbyIdUser(user.getUser_id()));
		int user_id = AccountController.getUser().getUser_id();
		model.addAttribute("amountspent", statisticsServiceImpl.amountSpentbyIdUser(user_id));
		model.addAttribute("pendingordercount", statisticsServiceImpl.getPendingOrderCountbyIdUser(user_id));
		model.addAttribute("ordersuccesscount", statisticsServiceImpl.getOrderSuccessCountbyIdUser(user_id));
		return "account/order";
	}
	
	@RequestMapping(value = "account/order", params = "startDate")
	public String orderSearch(@RequestParam(required = true) @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate, @RequestParam("endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate, HttpServletRequest request) {
		if(checkLogin(request) == false) {
			return "redirect:/account/login.htm"; // "account/login";
		}
		request.setAttribute("orders", orderServiceImpl.searchOrderUser(startDate, endDate, user.getUser_id()));
		request.setAttribute("count", orderServiceImpl.searchOrderUser(startDate, endDate, user.getUser_id()).size());
		request.setAttribute("user", user);
		return "account/order";
	}
	
	@RequestMapping(value = "account/order/cancel", params = "idOrder", method = RequestMethod.POST)
	public String cancelOrder(@RequestParam(required = true) int idOrder, HttpServletRequest request, RedirectAttributes ra) {
		if(checkLogin(request) == false) {
			return "redirect:/account/login.htm"; // "account/login";
		}
		int userID = user.getUser_id();
		boolean checkOrderExacly = orderServiceImpl.checkOrderOfUserExist(idOrder, userID);
		if(checkOrderExacly == false) { // Khong ton tai orderID trong userID nay
			ra.addFlashAttribute("cancel", 0);
		}else {
			// Kiểm tra xem đơn hàng này có status phải = 1 không // Nếu là 1 thì OKE, còn = 3 thì đã yêu cầu hủy, = 4 thì đã hủy, = 2 là đã xác nhận
			OrderEntity or = orderServiceImpl.getOrderbyIDOrder(idOrder); // Chắc chắn != null
			int status = or.getOrder_status();
			if(status != 1) {
				ra.addFlashAttribute("cancel", 1); // Đơn hàng đã được cập nhật trước đó, vui lòng tải lại trang
			}else {
				if(orderServiceImpl.saveOrder(3, idOrder) > 0) { // Cập nhật new status = 3 (Đã yêu cầu hủy)
					ra.addFlashAttribute("cancel", 2); // THÀNH CÔNG
				}else {
					ra.addFlashAttribute("cancel", 3); // Lỗi UPDATE
				}
			}			
		}
		return "redirect:/account/order.htm";
	}
	
	@RequestMapping(value = "account/login", method = RequestMethod.POST)
	public String checklogin(HttpSession ss, HttpServletRequest request, @RequestParam("username") String username, @RequestParam("password") String password, HttpServletResponse response, ModelMap model) throws IOException {
		System.out.println("URL LOGIN = "+request.getRequestURI());
		
		String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
		boolean verify = CaptchaServlet.verify(gRecaptchaResponse);

		if(accountServiceImpl.getInfoLogin(username, password) != null && verify) {
			
			user = accountServiceImpl.getInfoLogin(username, password);
			ss.setAttribute("username", username);
			if(user.getRole() == 1) {
				ss.setAttribute("role", 1);
			}else {
				ss.setAttribute("role", 0);
			}
			HttpSession session = request.getSession();
			System.out.println("url_forward = "+session.getAttribute("url_forward"));
			System.out.println("role = "+session.getAttribute("role"));
			System.out.println("User = "+user.getUsername());
			if(session != null) {
				if(session.getAttribute("url_forward") != null && ss.getAttribute("role") != null && (int) ss.getAttribute("role") == 1) {
					return "redirect:"+session.getAttribute("url_forward")+"";
				}
			}
			
			// SET SESSION + CART SESSION
			int id_user = user.getUser_id();
			ArrayList<ItemEntity> dsItem =  (ArrayList<ItemEntity>) itemServiceImpl.findItemByIDUser(id_user);
			@SuppressWarnings("unchecked")
			ArrayList<CartDto> cart = (ArrayList<CartDto>) session.getAttribute("cart");
			if(cart == null) {
				cart = new ArrayList<CartDto>();
			}
			
			// Them cac san pham da co trong session nhung chua co trong sql
			for(int k = 0; k<cart.size(); k++) {
				int idbook = cart.get(k).getProduct().getId();
				// System.out.println("TIM KIEM USER ID = "+id_user+" va ID BOOK = "+idbook);
				if(itemServiceImpl.checkIDBookOfUserItemExits(id_user, idbook) == false) {
					System.out.println("ID BOOK KHONG TON TAI TRONG BANG TABLE SQL");
					ItemEntity item_en = new ItemEntity();
					item_en.setId_book(idbook);
					item_en.setQuantity_books(cart.get(k).getQuanty());
					item_en.setUser_id(id_user);
					itemServiceImpl.addItem(item_en);
				}
			}
			
			// Dua cac san pham tu sql vao session
			for(int i = 0; i<dsItem.size(); i++) {
				int idbook = dsItem.get(i).getId_book();
				int quanty_session = 0;
				for(int j = 0; j<cart.size(); j++) {
					if(cart.get(j).getProduct().getId() == idbook) {
						quanty_session = cart.get(j).getQuanty();
						break;
					}
				}
				if(dsItem.get(i).getQuantity_books() + quanty_session > homeServiceImpl.getBookbyID(idbook).getTotal_quantity()) {
					cart = cartServiceImpl.addCart(dsItem.get(i).getId_book(), (dsItem.get(i).getQuantity_books() - quanty_session), cart, 1);
					continue;
				}
				cart = cartServiceImpl.addCart(dsItem.get(i).getId_book(), dsItem.get(i).getQuantity_books(), cart, 1);
			}
			session.setAttribute("cart", cart);
			
			// Cap nhat lai san pham trong sql
			itemServiceImpl.updateItem(cart, id_user);
			return "redirect:/account/info.htm"; //  "account/info";
		}
		

		if(!verify) {
			model.addAttribute("login_status", "Thông tin xác thực captcha không đúng");
		} else {
			model.addAttribute("login_status", "Sai thông tin tài khoản hoặc mật khẩu");
		}
		
		return "account/login";
	}

	public static AccountEntity getUser() {
		return user;
	}
	
	
	
	
}
