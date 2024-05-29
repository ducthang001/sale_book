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
import ptithcm.serviceimpl.CompanyServiceImpl;
import ptithcm.serviceimpl.HomeServiceImpl;

@Controller
public class CompanyController {
	@Autowired
	HomeServiceImpl  homeServiceImpl;
	
	@Autowired
	CategoryServiceImpl categoryServiceImpl;
	
	@Autowired
	AuthorServiceImpl authorServiceImpl;
	
	@Autowired
	CompanyServiceImpl companyServiceImpl;
	
	@Autowired
	CartServiceImpl cartServiceImpl;
	
	private int currentpage = 1;
	private int maxpage = 1;
	private int pagesize = 1;
	private int hienthi = 1;
	
	@RequestMapping("/company/{id_company}")
	public String viewBook(@PathVariable("id_company") int id_company, ModelMap model) {
		if(companyServiceImpl.findNameCompany(id_company) == null) {
			return "redirect:/404.htm";
		}
		getMaxPage(id_company);
		model.addAttribute("id_author", id_company);
		model.addAttribute("book_company", homeServiceImpl.dsBookbyIDCompany(id_company, 1, hienthi));
		model.addAttribute("categories", categoryServiceImpl.dsCategory());
		model.addAttribute("company_name", companyServiceImpl.findNameCompany(id_company));
		model.addAttribute("id_company", id_company);
		model.addAttribute("currentpage", 1);
		model.addAttribute("maxpage", maxpage);
		model.addAttribute("pagesize", pagesize);
		currentpage = 1;
		return "company";
	}
	
	@RequestMapping(value = "company/{id_company}", params = "page")
	public String authorPage(@PathVariable("id_company") int id_company, @RequestParam(required = true) int page, ModelMap model) {
		getMaxPage(id_company);
		if(page > maxpage) {
			page = 1;
		}
		model.addAttribute("categories", categoryServiceImpl.dsCategory());
		model.addAttribute("company_name", companyServiceImpl.findNameCompany(id_company));
		model.addAttribute("maxpage", maxpage);
		model.addAttribute("currentpage", page);
		model.addAttribute("id_company", id_company);
		currentpage = page;
		model.addAttribute("book_company", homeServiceImpl.dsBookbyIDCompany(id_company, page, hienthi));
		model.addAttribute("pagesize", pagesize);
	    return "company";
	}
	
	@RequestMapping(value = "company/add/{id_company}", method = RequestMethod.POST)
	public String homeAddBook(@PathVariable("id_company") int id_company, @RequestParam("bookid") int bookid, RedirectAttributes ra, HttpSession session) {
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
		return "redirect:/company/"+id_company+".htm?page="+currentpage;
	}
	
	public int getMaxPage(int id_company) {
		int companycount = (int) homeServiceImpl.getBookCountOfCompany(id_company);
		if(companycount == 0) {
			hienthi = 1;
		}else if(companycount < 9) {
			hienthi = companycount;
			pagesize = 1;
		}
		else if(companycount < 17) {
			hienthi = 8;
			pagesize = 2;
		}else {
			hienthi = 8;
			pagesize = 3;
		}
		if(companycount % hienthi == 0) {
			maxpage = companycount / hienthi;
		}else {
			maxpage = (int)(companycount / hienthi) + 1;
		}
		return maxpage;
	}
}
