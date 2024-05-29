package ptithcm.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ptithcm.controller.AccountController;
import ptithcm.serviceimpl.HomeServiceImpl;
import ptithcm.serviceimpl.ReviewServiceImpl;
import ptithcm.serviceimpl.StatisticsServiceImpl;

@Controller
public class ReviewAdminController {
	@Autowired
	HomeServiceImpl homeServiceImpl;
	
	@Autowired
	ReviewServiceImpl reviewServiceImpl;
	
	@Autowired
	StatisticsServiceImpl statisticsServiceImpl;
	
	private int currentpage = 1;
	private int maxpage = 1;
	private int pagesize = 1;
	private int hienthi = 1;
	private String order = "book_name"; // id_book / book_name
	private String dir = "asc";
	
	
	@RequestMapping("admin/review")
	public String reviewAdmin(ModelMap model, RedirectAttributes ra) {
		ra.addFlashAttribute("username", AccountController.getUser().getUsername());
		getMaxPage();
		if(order.equals("id_book")) {
			model.addAttribute("orderpage", "id");
		}else {
			model.addAttribute("orderpage", "name");
		}
		model.addAttribute("dirpage", dir);
		if(dir.equals("asc")) {
			model.addAttribute("orderLink", "desc");
		}else {
			model.addAttribute("orderLink", "asc");
		}
		model.addAttribute("count", homeServiceImpl.getBookCount());
		model.addAttribute("books", homeServiceImpl.bookPage(currentpage, hienthi, order, dir));
		model.addAttribute("reviews", reviewServiceImpl);
		model.addAttribute("maxpage", maxpage);
		model.addAttribute("currentpage", currentpage);
		model.addAttribute("pagesize", pagesize);
		model.addAttribute("total_profit", statisticsServiceImpl.getProfitThisYear());
		model.addAttribute("total_orders", statisticsServiceImpl.getOrdersCountThisYear());
		model.addAttribute("total_users", statisticsServiceImpl.getUsersCount());
		return "admin/review";
	}
	
	@RequestMapping(value = "admin/review", params = "keyword")
	public String authorSearch(@RequestParam(required = true) String keyword, HttpServletRequest request) {
		keyword = keyword.trim();
		getMaxPage();
		request.setAttribute("count", homeServiceImpl.findBook(keyword).size());
		request.setAttribute("books", homeServiceImpl.findBook(keyword));
		request.setAttribute("reviews", reviewServiceImpl);
		request.setAttribute("maxpage", 0);
		request.setAttribute("currentpage", 1);
		request.setAttribute("pagesize", 0);
		request.setAttribute("orderLink", "asc");
		request.setAttribute("total_profit", statisticsServiceImpl.getProfitThisYear());
		request.setAttribute("total_orders", statisticsServiceImpl.getOrdersCountThisYear());
		request.setAttribute("total_users", statisticsServiceImpl.getUsersCount());
	    return "admin/review";
	}
	
	@RequestMapping(value = "admin/review", params = "page")
	public String authorPage(@RequestParam(required = true) int page, @RequestParam("order") String o,
			@RequestParam("dir") String d, HttpServletRequest request) {
		if(o.equals("id")) {
			order = "id_book";
		}else if(o.equals("name")) {
			order = "book_name";
		}
		
		if(d.equals("asc")) {
			dir = "asc";
		}else if(d.equals("desc")) {
			dir = "desc";
		}
		
		getMaxPage();
		if(page > maxpage) {
			page = 1;
		}
		request.setAttribute("count", homeServiceImpl.getBookCount());
		request.setAttribute("books", homeServiceImpl.bookPage(page, hienthi, order, dir));
		request.setAttribute("reviews", reviewServiceImpl);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("currentpage", page);
		request.setAttribute("pagesize", pagesize);
		request.setAttribute("total_profit", statisticsServiceImpl.getProfitThisYear());
		request.setAttribute("total_orders", statisticsServiceImpl.getOrdersCountThisYear());
		request.setAttribute("total_users", statisticsServiceImpl.getUsersCount());
		//
		currentpage = page;
		
		if(order.equals("id_book")) {
			request.setAttribute("orderpage", "id");
		}else {
			request.setAttribute("orderpage", "name");
		}
		request.setAttribute("dirpage", dir);
		if(dir.equals("asc")) {
			request.setAttribute("orderLink", "desc");
		}else {
			request.setAttribute("orderLink", "asc");
		}
		
	    return "admin/review";
	}
	
	public int getMaxPage() {
		int bookcount = (int) homeServiceImpl.getBookCount();
		if(bookcount == 0) {
			hienthi = 1;
		}else if(bookcount < 6) {
			hienthi = bookcount;
			pagesize = 1;
		}
		else if(bookcount < 11) {
			hienthi = 5;
			pagesize = 2;
		}else {
			hienthi = 5;
			pagesize = 3;
		}
		if(bookcount % hienthi == 0) {
			maxpage = bookcount / hienthi;
		}else {
			maxpage = (int)(bookcount / hienthi) + 1;
		}
		return maxpage;
	}
	@RequestMapping(value = "sach/review/delete", method = RequestMethod.POST)
	public String ReviewDelete(@RequestParam int idBook,@RequestParam int idUser,ModelMap model) {
		
		reviewServiceImpl.deleteReview(idBook, idUser);
		 return "redirect:/sach/"+idBook+".htm";
	}
}
