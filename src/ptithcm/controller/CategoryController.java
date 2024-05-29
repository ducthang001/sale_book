package ptithcm.controller;
import java.util.ArrayList;

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
import ptithcm.serviceimpl.CartServiceImpl;
import ptithcm.serviceimpl.CategoryServiceImpl;
import ptithcm.serviceimpl.HomeServiceImpl;

@Controller
@Transactional
public class CategoryController {
	@Autowired
	CategoryServiceImpl categoryServiceImpl;
	
	@Autowired
	HomeServiceImpl homeServiceImpl;
	
	@Autowired
	CartServiceImpl cartServiceImpl;
	
	private int currentpage = 1;
	private int maxpage = 1;
	private int pagesize = 1;
	private int hienthi = 1;
	
	@RequestMapping("/category/{id_category}")
	public String viewBook(@PathVariable("id_category") int id_category, ModelMap model) {
		if(categoryServiceImpl.findNameCategory(id_category) == null) {
			return "redirect:/404.htm";
		}
		getMaxPage(id_category);
		model.addAttribute("id_category", id_category);
		model.addAttribute("book_category", homeServiceImpl.dsBookbyIDCategory(id_category, 1, hienthi));
		model.addAttribute("categories", categoryServiceImpl.dsCategory());
		model.addAttribute("category_name", categoryServiceImpl.findNameCategory(id_category));
		model.addAttribute("currentpage", 1);
		model.addAttribute("maxpage", maxpage);
		model.addAttribute("pagesize", pagesize);
		model.addAttribute("id_category", id_category);
		currentpage = 1;
		return "category";
	}
	
	@RequestMapping(value = "/category/{id_category}", params = "page")
	public String authorPage(@PathVariable("id_category") int id_category, @RequestParam(required = true) int page, ModelMap model) {
		getMaxPage(id_category);
		if(page > maxpage) {
			page = 1;
		}
		model.addAttribute("categories", categoryServiceImpl.dsCategory());
		model.addAttribute("category_name", categoryServiceImpl.findNameCategory(id_category));
		model.addAttribute("maxpage", maxpage);
		model.addAttribute("currentpage", page);
		currentpage = page;
		model.addAttribute("pagesize", pagesize);
		model.addAttribute("id_category", id_category);
		model.addAttribute("book_category", homeServiceImpl.dsBookbyIDCategory(id_category, page, hienthi));
	    return "category";
	}
	
	
	@RequestMapping(value = "category/add/{id_category}", method = RequestMethod.POST)
	public String homeAddBook(@PathVariable("id_category") int id_category, @RequestParam("bookid") int bookid, RedirectAttributes ra, HttpSession session) {
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
		return "redirect:/category/"+id_category+".htm?page="+currentpage;
	}
	
	public int getMaxPage(int id_category) {
		int categorycount = (int) homeServiceImpl.getBookCountOfCategory(id_category);
		if(categorycount == 0) {
			hienthi = 1;
		}else if(categorycount < 9) {
			hienthi = categorycount;
			pagesize = 1;
		}
		else if(categorycount < 17) {
			hienthi = 8;
			pagesize = 2;
		}else {
			hienthi = 8;
			pagesize = 3;
		}
		if(categorycount % hienthi == 0) {
			maxpage = categorycount / hienthi;
		}else {
			maxpage = (int)(categorycount / hienthi) + 1;
		}
		return maxpage;
	}
}
