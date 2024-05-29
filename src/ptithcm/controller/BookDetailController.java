package ptithcm.controller;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ptithcm.bean.CartDto;
import ptithcm.entity.ReviewEntity;
import ptithcm.entity.ReviewId;
import ptithcm.serviceimpl.AccountServiceImpl;
import ptithcm.serviceimpl.CartServiceImpl;
import ptithcm.serviceimpl.HomeServiceImpl;
import ptithcm.serviceimpl.OrderDetailImpl;
import ptithcm.serviceimpl.ReviewServiceImpl;

@Transactional
@Controller
public class BookDetailController {
	@Autowired
	HomeServiceImpl homeServiceImpl;
	
	@Autowired
	AccountServiceImpl accountServiceImpl;
	
	@Autowired
	ReviewServiceImpl reviewServiceImpl;
	
	@Autowired
	CartServiceImpl cartServiceImpl;
	
	@Autowired
	OrderDetailImpl orderDetailImpl;
	
	@RequestMapping("/sach/{id_book}")
	public String viewBook(@PathVariable("id_book") int id_book, ModelMap model, HttpSession session) {
		if(homeServiceImpl.getBookbyID(id_book) == null) {
			return "redirect:/404.htm";
		}
		model.addAttribute("id_book", id_book);
		model.addAttribute("book", homeServiceImpl.getBookbyID(id_book));
		model.addAttribute("account", accountServiceImpl);
		model.addAttribute("topbuy", orderDetailImpl.getTopTheBestBook());
		DecimalFormat df = new DecimalFormat("#.#");
		// System.out.println("AVG = "+df.format(reviewServiceImpl.getAVGBookStar(1).get(0)));
		if(reviewServiceImpl.getAVGBookStar(id_book).get(0) == null) {
			model.addAttribute("average", "Chưa có đánh giá");
		}else {
			model.addAttribute("average", df.format(reviewServiceImpl.getAVGBookStar(id_book).get(0)));
		}
		
		int id_author = (int) homeServiceImpl.getBookbyID(id_book).getAuthor().getId_author(); // NULL NẾU KHÔNG TÌM THẤY SÁCH => ERR
		model.addAttribute("bookofauthor", homeServiceImpl.dsBookbyAuthorID(id_author, 1, 4));
		System.out.println("ID AUTHOR = "+id_author);
		
		if(AccountController.getUser() == null) {
			model.addAttribute("login", 0);
			model.addAttribute("reviewed", 0);
		}else {
			model.addAttribute("login", 1);
			
			model.addAttribute("role", AccountController.getUser().getRole());
			model.addAttribute("accountId", AccountController.getUser().getUser_id());
			if(reviewServiceImpl.checkUserReview(AccountController.getUser().getUser_id(), id_book) == false) { // Chưa đánh giá
				model.addAttribute("reviewed", 0);
				model.addAttribute("content", "");
				model.addAttribute("star_reviewed", 0);
			}else {
				model.addAttribute("reviewed", 1);
				model.addAttribute("content", reviewServiceImpl.getReviewUserCommentABook(AccountController.getUser().getUser_id(), id_book).getComments());
				model.addAttribute("star_reviewed", reviewServiceImpl.getReviewUserCommentABook(AccountController.getUser().getUser_id(), id_book).getStar());
			}
		}
		
		return "sach";
	}
	
	
	@RequestMapping(value = "sach/{id_book}/reference/add", method = RequestMethod.POST)
	public String homeAddBook(@PathVariable("id_book") int id, @RequestParam("bookid") int bookid, RedirectAttributes ra, HttpSession session) {
		@SuppressWarnings("unchecked")
		ArrayList<CartDto> cart = (ArrayList<CartDto>) session.getAttribute("cart");
		if(cart == null) {
			cart = new ArrayList<CartDto>();
		}
		
		int conlai = -1;
		int quanty = 1;
		if(homeServiceImpl.getBookQuantybyID(bookid) != -1) {
			conlai = homeServiceImpl.getBookQuantybyID(bookid);
		}
		int dadat = 0;
		for(int i = 0; i<cart.size(); i++) {
			if(bookid == cart.get(i).getProduct().getId()) {
				dadat = cart.get(i).getQuanty();
				break;
			}
		}
		
		if(conlai == -1) {
			ra.addFlashAttribute("add_result", 1);
		}else if(quanty > conlai) {
			ra.addFlashAttribute("add_result", 2);
			ra.addFlashAttribute("add_conlai", conlai);
		}else if(dadat + quanty > conlai) {
			ra.addFlashAttribute("add_result", 3);
			ra.addFlashAttribute("add_conlai", conlai);
			ra.addFlashAttribute("add_dadat", dadat);
		}else{
			cart = cartServiceImpl.addCart(bookid, quanty, cart, 0);
			session.setAttribute("cart", cart);
			ra.addFlashAttribute("add_result", 4);
		}
		return "redirect:/sach/"+id+".htm";
	}
	
	@RequestMapping(value = "sach/review/add", method = RequestMethod.GET)
	public String reviewBook(@RequestParam("bookid") int bookid, @RequestParam("star") int star, @RequestParam("review") String review, RedirectAttributes ra) {
		if(homeServiceImpl.getBookbyID(bookid) == null) {
			ra.addFlashAttribute("review_result", 0);
			return "redirect:/sach/"+1+".htm";
		}
		if(star < 1 || star > 5) {
			ra.addFlashAttribute("review_result", 1);
		}else {
			if(reviewServiceImpl.checkUserReview(AccountController.getUser().getUser_id(), bookid) == false) { // Chưa đánh giá => Add
				ReviewEntity re = new ReviewEntity();
				re.setStar(star);
				re.setComments(review);
				re.setTime(new Date());
				ReviewId A = new ReviewId();
				A.setId_book_review(bookid);
				A.setUser_id(AccountController.getUser().getUser_id());
				re.setId(A);
				int result = reviewServiceImpl.addReview(re);
				if(result > 0) {
					ra.addFlashAttribute("review_result", 2);
				}else {
					ra.addFlashAttribute("review_result", 3);
				}
			}else { // UPDATE
				int result = reviewServiceImpl.updateReview(review, star, AccountController.getUser().getUser_id(), bookid);
				if(result > 0) {
					ra.addFlashAttribute("review_result", 4);
				}else {
					ra.addFlashAttribute("review_result", 5);
				}
			}
		}
		return "redirect:/sach/"+bookid+".htm";
	}
}
