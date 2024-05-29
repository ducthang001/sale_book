package ptithcm.controller;
import java.util.ArrayList;
import java.util.Date;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ptithcm.bean.CartDto;
import ptithcm.entity.BookEntity;
import ptithcm.entity.OrderDetailEntity;
import ptithcm.entity.OrderEntity;
import ptithcm.serviceimpl.CartServiceImpl;
import ptithcm.serviceimpl.HomeServiceImpl;
import ptithcm.serviceimpl.ItemServiceImpl;
import ptithcm.serviceimpl.OrderDetailImpl;
import ptithcm.serviceimpl.OrderServiceImpl;

@Controller
public class CartController {
	@Autowired
	CartServiceImpl cartServiceImpl;
	
	@Autowired
	HomeServiceImpl homeServiceImpl;
	
	@Autowired
	OrderServiceImpl orderServiceImpl;
	
	@Autowired
	OrderDetailImpl orderDetailImpl;
	
	@Autowired
	ItemServiceImpl itemServiceImpl;
	
	@Autowired
	JavaMailSender mailer;
	
	@RequestMapping("account/cart")
	public String cart(ModelMap model, HttpSession session) {
		@SuppressWarnings("unchecked")
		ArrayList<CartDto> cart = (ArrayList<CartDto>) session.getAttribute("cart");
		if(cart == null) {
			cart = new ArrayList<CartDto>();
		}
		model.addAttribute("cart", cart);
		long total = cartServiceImpl.totalPrice(cart);
		System.out.println("TOTAL = "+total);
		model.addAttribute("total", total);
		return "account/cart";
	}
	
	@RequestMapping(value = "book/add", method = RequestMethod.POST)
	public String addCart(HttpSession session, @RequestParam("bookid") int id, @RequestParam("quanty") int quanty, RedirectAttributes ra) {
		@SuppressWarnings("unchecked")
		ArrayList<CartDto> cart = (ArrayList<CartDto>) session.getAttribute("cart");
		if(cart == null) {
			cart = new ArrayList<CartDto>();
		}
		
		int conlai = -1;
		if(homeServiceImpl.getBookQuantybyID(id) != -1) {
			conlai = homeServiceImpl.getBookQuantybyID(id);
		}
		int dadat = 0;
		for(int i = 0; i<cart.size(); i++) {
			if(id == cart.get(i).getProduct().getId()) {
				dadat = cart.get(i).getQuanty();
				break;
			}
		}
		
		if(quanty < 1) {
			ra.addFlashAttribute("add_result", 0); // < 1
		}else if(conlai == -1) {
			ra.addFlashAttribute("add_result", 1); // id no exist
		}else if(quanty > conlai) {
			ra.addFlashAttribute("add_result", 2);
			ra.addFlashAttribute("add_conlai", conlai);
		}else if(dadat + quanty > conlai) {
			ra.addFlashAttribute("add_result", 3);
			ra.addFlashAttribute("add_conlai", conlai);
			ra.addFlashAttribute("add_dadat", dadat);
		}else{
			cart = cartServiceImpl.addCart(id, quanty, cart, 0);
			session.setAttribute("cart", cart);
			ra.addFlashAttribute("add_result", 4);
		}
		return "redirect:/sach/"+id+".htm";
	}
	
	@RequestMapping(value = "book/edit", method = RequestMethod.POST)
	public String editCart(@RequestParam("bookid") int id, @RequestParam("quanty") int quanty, HttpSession session, RedirectAttributes ra) {
		@SuppressWarnings("unchecked")
		ArrayList<CartDto> cart = (ArrayList<CartDto>) session.getAttribute("cart");
		if(cart == null) {
			cart = new ArrayList<CartDto>();
		}
		
		int conlai = -1;
		if(homeServiceImpl.getBookQuantybyID(id) != -1) {
			conlai = homeServiceImpl.getBookQuantybyID(id);
		}
		
		if(quanty < 1) {
			ra.addFlashAttribute("edit_result", 0); // Bé hơn 1
		}else if(conlai == -1) {
			ra.addFlashAttribute("edit_result", 1); // không tồn tại ID
		}else if(quanty > conlai) {
			ra.addFlashAttribute("edit_result", 2); // Lớn hơn tồn kho
			ra.addFlashAttribute("edit_conlai", conlai); // Lớn hơn tồn kho
		}else {
			cart = cartServiceImpl.editCart(id, quanty, cart);
			session.setAttribute("cart", cart);
			ra.addFlashAttribute("edit_result", 3); // Thành công
		}
		return "redirect:/account/cart.htm";
	}
	
	@RequestMapping(value = "book/delete", method = RequestMethod.POST)
	public String deleteCart(HttpSession session, @RequestParam("bookid") int id, RedirectAttributes ra) {
		@SuppressWarnings("unchecked")
		ArrayList<CartDto> cart = (ArrayList<CartDto>) session.getAttribute("cart");
		if(cart == null) {
			cart = new ArrayList<CartDto>();
		}
		
		if(homeServiceImpl.getBookbyID(id) == null) {
			ra.addFlashAttribute("delete_result", 0); // Không tìm thấy ID
		}else {
			cart = cartServiceImpl.deleteCart(id, cart);
			session.setAttribute("cart", cart); // 
			ra.addFlashAttribute("delete_result", 1);
		}
		
		return "redirect:/account/cart.htm";
	}
	
	
	public String checkBeforeCheckout(ArrayList<CartDto> cart) {
		String message = "";
		for(int i = 0; i<cart.size(); i++) {
			
			int idbook = cart.get(i).getProduct().getId();
			if(cart.get(i).getQuanty() > homeServiceImpl.getBookQuantybyID(idbook)) {
				int conlai = homeServiceImpl.getBookQuantybyID(idbook);
				message += " " + idbook + " " + conlai;
			}
		}
		return message.trim();
	}
	
	public void sentEmail_Order(String email, ArrayList<CartDto> cart, String address, String phone) {
		try {	
			String s = "<html><body<p><b style='font-size: 20px; font-family: Arial, Helvetica,sans-serif; margin: 10px 0px;'>Đơn hàng của bạn sẽ được giao tới trong vòng 1-2 ngày tới. Chi tiết đơn hàng bao gồm: </b></p>"
					+ "    <div style='max-width: 100%;border: 1px solid;background-color: #007bff;color: white;font-size: 18px;font-family: arial;'>"
					+ "    <div style='display: flex; text-align: center;background-color: #2ab84a;'>"
					+ "        <p style='margin: 10px 0px;width: 40px;'>STT</p>"
					+ "        <p style='margin: 10px 0px;width: 350px;'>Tên Đơn Hàng</p>"
					+ "        <p style='margin: 10px 0px;width: 250px;'>Đơn giá</p>"
					+ "        <p style='margin: 10px 0px;width: 250px;'>Số lượng</p>"
					+ "        <p style='margin: 10px 0px;width: 250px;'>Thành tiền</p>"
					+ "    </div>";
			
			for(int i = 0; i<cart.size(); i++) {
				String bookname = cart.get(i).getProduct().getBookname();
				String bookprice = ChuyenSoThanhTien(String.valueOf(cart.get(i).getProduct().getPrice()));
				String bookquanty = String.valueOf(cart.get(i).getQuanty());
				String booktotal_price = ChuyenSoThanhTien(String.valueOf(cart.get(i).getQuanty() * cart.get(i).getProduct().getPrice()));
				s += " <div style='display: flex; text-align: center; color: white;'>"
						+ "        <p style='width: 40px; color: white;'>"+(i+1)+"</p>"
						+ "        <p style='width: 350px; color: white;'>"+bookname+"</p>"
						+ "        <p style='width: 250px; color: white;'>"+bookprice+" VNĐ</p>"
						+ "        <p style='width: 250px; color: white;'>"+bookquanty+"</p>"
						+ "        <p style='width: 250px; color: white;'>"+booktotal_price+" VNĐ</p>"
						+ "    </div>";
			}		

			s += " <div style=' text-align: center;padding: 15px; background-color: #c54b57;'>"
					+ "        Tổng tiền: "+ChuyenSoThanhTien(String.valueOf(cartServiceImpl.totalPrice(cart)))+" VNĐ _ Địa chỉ: "+address+", SĐT: "+phone+""
					+ "    </div>"
					+ "    </div>"
					+ "</body>"
					+ "</html>";
			   
			String from = "BOOKSTORE.PTIT.HCM";
			String to = email;
			String body = s;
			String subject = "Đặt hàng thành công!";
			MimeMessage mail = mailer.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail, "utf-8");
			helper.setFrom(from, from);
			helper.setTo(to);
			helper.setReplyTo(from, from);
			helper.setSubject(subject);
			helper.setText(body, true);
			mailer.send(mail);
			System.out.println("Gửi mail thành công");
		} catch (Exception e) {
			System.out.println("Gửi mail thất bại");
		}
	}
	
	public String ChuyenSoThanhTien(String s) {
        String temp = "";
        int pos = s.length() % 3 - 1;
        if (pos < 0) {
            pos = 2;
        }

        for(int i = 0; i < s.length(); ++i) {
            temp = temp + s.charAt(i);
            if (pos == i) {
                if (i != s.length() - 1) {
                    temp = temp + ",";
                }

                pos += 3;
            }
        }

        return temp;
    }
	
	@RequestMapping("checkout")
	public String checkout(HttpSession session, @RequestParam("fullname") String fullname,
			@RequestParam("email") String email, @RequestParam("phone") String phone,
			@RequestParam("address") String address, RedirectAttributes ra) {
		fullname = fullname.trim();
		phone = phone.trim();
		address = address.trim();
		email = email.trim();
		
		@SuppressWarnings("unchecked")
		ArrayList<CartDto> cart = (ArrayList<CartDto>) session.getAttribute("cart");
		if(cart == null) {
			cart = new ArrayList<CartDto>();
			return null;
		}
		if(cart.size() == 0) {
			ra.addFlashAttribute("checkout_result", -1); // Giỏ hàng trống
			return "redirect:/account/cart.htm";
		}
		
		String message = checkBeforeCheckout(cart);
		if(message.length() != 0) { // TỒN KHO KHÔNG ĐỦ
			ra.addFlashAttribute("checkout_result", message);
			return "redirect:/account/cart.htm";
		}else {
			
			// TẠO BẢNG ORDER
			OrderEntity order = new OrderEntity();
			order.setAddress(address);
			order.setCustomer_name(fullname);
			order.setCustomer_phone(phone);
			order.setOrder_status(1);
			order.setTotal_price(cartServiceImpl.totalPrice(cart));
			if(AccountController.getUser() != null) {
				order.setUser_id(AccountController.getUser().getUser_id());
			}
			order.setOrder_day(new Date());
			
			int result = orderServiceImpl.addOrder(order);
			if(result > 0) {
				System.out.println("THEM ORDER THANH CONG");
			}else {
				System.out.println("THEM ORDER THAT BAI");
				ra.addFlashAttribute("checkout_result", 0); // LỖI TẠO ORDER
				return "redirect:/account/cart.htm";
			}
			
			// Da them order thanh cong => THÊM DETAIL CHO ORDER
			if(result > 0) {
				int last_orderID = orderServiceImpl.getLastOrderID();
				for(int i = 0; i<cart.size(); i++) {
					OrderDetailEntity detail = new OrderDetailEntity();
					int idbook = cart.get(i).getProduct().getId();
					int quanty = cart.get(i).getQuanty();
					int price = cart.get(i).getProduct().getPrice();
					detail.setBook(new BookEntity());
					detail.getBook().setId_book(idbook);
					detail.setQuantity(quanty);
					detail.setPrice(price);
					detail.setOrders(new OrderEntity());
					detail.getOrders().setOrder_id(last_orderID);
					result = orderDetailImpl.addOrderDetail(detail);
					if(result > 0) {
						System.out.println("THEM DETAIL THANH CONG");
					}else {
						System.out.println("THEM DETAIL THAT BAI");
						ra.addFlashAttribute("checkout_result", 1); // LỖI TẠO ORDER
						return "redirect:/account/cart.htm";
					}
				}
				
				// UPDATE SỐ LƯỢNG TỒN KHO
				homeServiceImpl.updateBookQuanty(cart);
				
				// Xóa hết ITEM trong bảng nếu là user
				if(AccountController.getUser() != null) {
					itemServiceImpl.deleteAllItemsOfUser(AccountController.getUser().getUser_id());
				}
				
				// Gửi mail
				sentEmail_Order(email, cart, address, phone);
				
				// Đưa cart session về null;
				cart = null;
				// Xóa giao diện
				
				session.setAttribute("cart", cart);
				// model.addAttribute("message", loadData(cart));
			}
		}
		ra.addFlashAttribute("checkout_result", 2); // THÀNH CÔNG
		return "redirect:/account/cart.htm";
	}
}
