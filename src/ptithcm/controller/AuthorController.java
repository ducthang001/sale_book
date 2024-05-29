package ptithcm.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ptithcm.bean.CartDto;
import ptithcm.serviceimpl.AuthorServiceImpl;
import ptithcm.serviceimpl.CartServiceImpl;
import ptithcm.serviceimpl.CategoryServiceImpl;
import ptithcm.serviceimpl.HomeServiceImpl;

@Controller
public class AuthorController {
	@Autowired
	HomeServiceImpl  homeServiceImpl;
	
	@Autowired
	CategoryServiceImpl categoryServiceImpl;
	
	@Autowired
	AuthorServiceImpl authorServiceImpl;
	
	@Autowired
	CartServiceImpl cartServiceImpl;
	
	private int currentpage = 1;
	private int maxpage = 1;
	private int pagesize = 1;
	private int hienthi = 1;
	
	@RequestMapping("/author/{id_author}")
	public String viewBook(@PathVariable("id_author") int id_author, ModelMap model) {
		if(authorServiceImpl.findNameAuhthor(id_author) == null) {
			return "redirect:/404.htm";
		}
		getMaxPage(id_author);
		currentpage = 1;
		model.addAttribute("book_author", homeServiceImpl.dsBookbyAuthorID(id_author, 1, hienthi));
		model.addAttribute("id_author", id_author);
		model.addAttribute("categories", categoryServiceImpl.dsCategory());
		model.addAttribute("author_name", authorServiceImpl.findNameAuhthor(id_author));
		model.addAttribute("currentpage", 1);
		model.addAttribute("maxpage", maxpage);
		model.addAttribute("pagesize", pagesize);
		return "author";
	}
	
	
	@RequestMapping(value = "author/{id_author}", params = "page")
	public String authorPage(@PathVariable("id_author") int id_author, @RequestParam(required = true) int page, ModelMap model) {
		getMaxPage(id_author);
		if(page > maxpage) {
			page = 1;
		}
		currentpage = page;
		model.addAttribute("book_author", homeServiceImpl.dsBookbyAuthorID(id_author, page, hienthi));
		model.addAttribute("categories", categoryServiceImpl.dsCategory());
		model.addAttribute("author_name", authorServiceImpl.findNameAuhthor(id_author));
		model.addAttribute("maxpage", maxpage);
		model.addAttribute("currentpage", page);
		model.addAttribute("pagesize", pagesize);
		model.addAttribute("id_author", id_author);
	    return "author";
	}
	
	@RequestMapping(value = "author/add/{id_author}", method = RequestMethod.POST)
	public String homeAddBook(@PathVariable("id_author") int id_author, @RequestParam("bookid") int bookid, RedirectAttributes ra, HttpSession session) {
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
		return "redirect:/author/"+id_author+".htm?page="+currentpage;
	}
	
	public int getMaxPage(int id_author) {
		int authorcount = (int) homeServiceImpl.getBookCountOfAuthor(id_author);
		if(authorcount == 0) {
			hienthi = 1;
		}else if(authorcount < 9) {
			hienthi = authorcount;
			pagesize = 1;
		}
		else if(authorcount < 17) {
			hienthi = 8;
			pagesize = 2;
		}else {
			hienthi = 8;
			pagesize = 3;
		}
		if(authorcount % hienthi == 0) {
			maxpage = authorcount / hienthi;
		}else {
			maxpage = (int)(authorcount / hienthi) + 1;
		}
		return maxpage;
	}
}
