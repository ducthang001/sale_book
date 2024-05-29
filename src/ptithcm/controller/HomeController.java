package ptithcm.controller;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ptithcm.bean.CartDto;
import ptithcm.serviceimpl.CartServiceImpl;
import ptithcm.serviceimpl.CategoryServiceImpl;
import ptithcm.serviceimpl.HomeServiceImpl;
import ptithcm.serviceimpl.OrderDetailImpl;

@Controller
@Transactional
public class HomeController {
	@Autowired
	 HomeServiceImpl homeServiceImpl;
	@Autowired
	CategoryServiceImpl categoryServiceImpl;
	@Autowired
	OrderDetailImpl orderDetailImpl;
	@Autowired
	CartServiceImpl cartServiceImpl;
	
	private int currentpage = 1;
	private int maxpage = 1;
	private int pagesize = 1;
	private int hienthi = 1;
	
	
	@RequestMapping("home")
	public String home(ModelMap model) {
		getMaxPage();
		model.addAttribute("books", homeServiceImpl.bookPageOfHome(1, hienthi));
		model.addAttribute("topbuy", orderDetailImpl.getTopTheBestBook());
		model.addAttribute("categories", categoryServiceImpl.dsCategory());
		currentpage = 1;
		if(maxpage == 1) {
			model.addAttribute("maxpage", 0);
		}else {
			model.addAttribute("maxpage", maxpage);
		}
		model.addAttribute("currentpage", currentpage);
		model.addAttribute("pagesize", pagesize);
		model.addAttribute("bookrecommend", homeServiceImpl.dsBookRecommend(100000));
		return "home";
	}
	
	
	@RequestMapping(value = "home", params = "page")
	public String authorPage(@RequestParam(required = true) int page, ModelMap model) {
		getMaxPage();
		if(page > maxpage) {
			page = 1;
		}
		model.addAttribute("books", homeServiceImpl.bookPageOfHome(page, hienthi));
		model.addAttribute("topbuy", orderDetailImpl.getTopTheBestBook());
		model.addAttribute("categories", categoryServiceImpl.dsCategory());
		model.addAttribute("maxpage", maxpage);
		model.addAttribute("bookrecommend", homeServiceImpl.dsBookRecommend(100000));
		model.addAttribute("currentpage", page);
		currentpage = page;
		model.addAttribute("pagesize", pagesize);
	    return "home";
	}
	
	
	
	@RequestMapping("home/add")
	public String homeAddBook(@RequestParam("bookid") int bookid, RedirectAttributes ra, HttpSession session) {
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
		return "redirect:/home.htm?page="+currentpage;
	}
	
	@RequestMapping("404")
	public String page404() {
		return "404";
	}
	
	public int getMaxPage() {
		int bookcount = (int) homeServiceImpl.getBookCount();
		if(bookcount == 0) {
			hienthi = 1;
			currentpage = 0;
		}else if(bookcount < 9) {
			hienthi = bookcount;
			pagesize = 1;
		}
		else if(bookcount < 17) {
			hienthi = 8;
			pagesize = 2;
		}else {
			hienthi = 8;
			pagesize = 3;
		}
		if(bookcount % hienthi == 0) {
			maxpage = bookcount / hienthi;
		}else {
			maxpage = (int)(bookcount / hienthi) + 1;
		}
		return maxpage;
	}
	
	
	
	
}
