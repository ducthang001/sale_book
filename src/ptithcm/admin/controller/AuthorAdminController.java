package ptithcm.admin.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ptithcm.controller.AccountController;
import ptithcm.entity.AuthorEntity;
import ptithcm.serviceimpl.AuthorServiceImpl;
import ptithcm.serviceimpl.HomeServiceImpl;
import ptithcm.serviceimpl.StatisticsServiceImpl;

@Controller
public class AuthorAdminController {
	@Autowired
	AuthorServiceImpl authorServiceImpl;
	
	@Autowired
	StatisticsServiceImpl statisticsServiceImpl;
	
	@Autowired
	HomeServiceImpl homeServiceImpl;
	private int currentpage = 1;
	private int maxpage = 1;
	private int pagesize = 1;
	private int hienthi = 1;
	private String order = "id_author"; // author_name / id_author
	private String dir = "asc"; 
	
	@RequestMapping("admin/author")
	public String authorAdmin(HttpServletRequest request, RedirectAttributes ra) {
		getMaxPage();
		
		ra.addFlashAttribute("username", AccountController.getUser().getUsername());
		if(order.equals("id_author")) {
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
		request.setAttribute("count", authorServiceImpl.getAuthorCount());
		request.setAttribute("authors", authorServiceImpl.authorPage(currentpage, hienthi, order, dir));
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("currentpage", currentpage);
		request.setAttribute("pagesize", pagesize);
		request.setAttribute("total_profit", statisticsServiceImpl.getProfitThisYear());
		System.out.println("PROFIT = "+statisticsServiceImpl.getProfitThisYear());
		request.setAttribute("total_orders", statisticsServiceImpl.getOrdersCountThisYear());
		request.setAttribute("total_users", statisticsServiceImpl.getUsersCount());
		return "admin/author";
	}
	
	@RequestMapping(value = "admin/author", params = "page")
	public String authorPage(@RequestParam(required = true) int page, @RequestParam("order") String o,
			@RequestParam("dir") String d, HttpServletRequest request) {
		if(o.equals("id")) {
			order = "id_author";
		}else if(o.equals("name")) {
			order = "author_name";
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
		request.setAttribute("count", authorServiceImpl.getAuthorCount());
		request.setAttribute("authors", authorServiceImpl.authorPage(page, hienthi, order, dir));
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("currentpage", page);
		request.setAttribute("total_profit", statisticsServiceImpl.getProfitThisYear());
		request.setAttribute("total_orders", statisticsServiceImpl.getOrdersCountThisYear());
		request.setAttribute("total_users", statisticsServiceImpl.getUsersCount());
		//
		currentpage = page;
		request.setAttribute("pagesize", pagesize);
		
		if(order.equals("id_author")) {
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
		
	    return "admin/author";
	}
	
	@RequestMapping(value = "admin/author", params = "keyword")
	public String authorSearch(@RequestParam(required = true) String keyword, HttpServletRequest request) {
		getMaxPage();
		request.setAttribute("count", authorServiceImpl.searchAuthorCount(keyword));
		request.setAttribute("authors", authorServiceImpl.SearchAuthor(keyword));
		request.setAttribute("maxpage", 0);
		request.setAttribute("currentpage", 1);
		request.setAttribute("pagesize", 0);
		request.setAttribute("orderLink", "asc");
		request.setAttribute("total_profit", statisticsServiceImpl.getProfitThisYear());
		request.setAttribute("total_orders", statisticsServiceImpl.getOrdersCountThisYear());
		request.setAttribute("total_users", statisticsServiceImpl.getUsersCount());
	    return "admin/author";
	}
	
	@RequestMapping(value = "admin/author/add", params = "authorName", method = RequestMethod.POST)
	public String authorAdd(@RequestParam(required = true) String authorName, RedirectAttributes ra) {
		authorName = authorName.trim();
		AuthorEntity A = new AuthorEntity();
		A.setAuthor_name(authorName);
		if(authorName.length() < 2) {
			ra.addFlashAttribute("add_result", -1);
		}
		else if(authorServiceImpl.checkNameAuthor(-1, authorName) == true) {
			ra.addFlashAttribute("add_result", 0);
		}else {
			int result = authorServiceImpl.addAuthor(A);
			if(result > 0) {
				ra.addFlashAttribute("add_result", 1);
			}else {
				ra.addFlashAttribute("add_result", 2);
			}
		}
		String od;
		if(order.equals("id_author")) {
			od = "id";
		}else {
			od = "name";
		}
	    return "redirect:/admin/author.htm?page="+currentpage+"&order="+od+"&dir="+dir;
	}
	
	@RequestMapping(value = "admin/author/edit", params = "authorName", method = RequestMethod.POST)
	public String authorEdit(@RequestParam(required = true) String authorName, @RequestParam("idAuthor") int idAuthor,  RedirectAttributes ra) {
		authorName = authorName.trim();
		if(authorName.length() < 2) {
			ra.addFlashAttribute("edit_result", -1);
		}
		else if(authorServiceImpl.checkNameAuthor(idAuthor, authorName) == true) {
			ra.addFlashAttribute("edit_result", 0);
		}
		else {
			int result = authorServiceImpl.editAuthor(idAuthor, authorName);
			if(result > 0) {
				ra.addFlashAttribute("edit_result", 1);
			}else {
				ra.addFlashAttribute("edit_result", 0);
			}
		}
		
		String od;
		if(order.equals("id_author")) {
			od = "id";
		}else {
			od = "name";
		}
	    return "redirect:/admin/author.htm?page="+currentpage+"&order="+od+"&dir="+dir;
	}
	
	@RequestMapping(value = "admin/author/delete", params = "idAuthor", method = RequestMethod.POST)
	public String authorDelete(@RequestParam(required = true) int idAuthor,  RedirectAttributes ra) {
		if(homeServiceImpl.dsBookbyAuthorID(idAuthor,1, 4) != null) {
			ra.addFlashAttribute("delete_result", 0);
		}else {
			int result = authorServiceImpl.deleteAuthor(idAuthor);
			if(result > 0) {
				ra.addFlashAttribute("delete_result", 1);
			}else {
				ra.addFlashAttribute("delete_result", 2);
			}
		}
		
		String od;
		if(order.equals("id_author")) {
			od = "id";
		}else {
			od = "name";
		}
		getMaxPage();
		if(currentpage > maxpage) {
			if(currentpage != 1)
				currentpage -= 1;
		}
		return "redirect:/admin/author.htm?page="+currentpage+"&order="+od+"&dir="+dir;
	}
	
	
	public int getMaxPage() {
		int authorcount = (int) authorServiceImpl.getAuthorCount(); // 6
		if(authorcount == 0) {
			hienthi = 1;
		}else if(authorcount < 6) {
			hienthi = authorcount;
			pagesize = 1;
		}
		else if(authorcount < 11) {
			hienthi = 5;
			pagesize = 2;
		}else {
			hienthi = 5;
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
