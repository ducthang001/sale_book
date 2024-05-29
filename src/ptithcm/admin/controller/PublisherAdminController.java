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
import ptithcm.entity.CompanyEntity;
import ptithcm.serviceimpl.CompanyServiceImpl;
import ptithcm.serviceimpl.HomeServiceImpl;
import ptithcm.serviceimpl.StatisticsServiceImpl;

@Controller
public class PublisherAdminController {
	@Autowired
	CompanyServiceImpl companyServiceImpl;
	
	@Autowired
	HomeServiceImpl homeServiceImpl;
	
	@Autowired
	StatisticsServiceImpl statisticsServiceImpl;
	
	private int currentpage = 1;
	private int maxpage = 1;
	private int pagesize = 1;
	private int hienthi = 1;
	private String order = "id_company"; // id_company / company_name
	private String dir = "asc";
	
	@RequestMapping("admin/publisher")
	public String publisherAdmin(ModelMap model, RedirectAttributes ra) {
		ra.addFlashAttribute("username", AccountController.getUser().getUsername());
		getMaxPage();
		if(order.equals("id_company")) {
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
		model.addAttribute("count", companyServiceImpl.getCompanyCount());
		model.addAttribute("publishers", companyServiceImpl.companyPage(currentpage, hienthi, order, dir));
		model.addAttribute("maxpage", maxpage);
		model.addAttribute("currentpage", currentpage);
		model.addAttribute("pagesize", pagesize);
		model.addAttribute("total_profit", statisticsServiceImpl.getProfitThisYear());
		model.addAttribute("total_orders", statisticsServiceImpl.getOrdersCountThisYear());
		model.addAttribute("total_users", statisticsServiceImpl.getUsersCount());
		return "admin/publisher";
	}
	
	@RequestMapping(value = "admin/publisher", params = "page")
	public String publisherPage(@RequestParam(required = true) int page, @RequestParam("order") String o,
			@RequestParam("dir") String d, HttpServletRequest request) {
		if(o.equals("id")) {
			order = "id_company";
		}else if(o.equals("name")) {
			order = "company_name";
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
		request.setAttribute("count", companyServiceImpl.getCompanyCount());
		request.setAttribute("publishers", companyServiceImpl.companyPage(page, hienthi, order, dir));
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("currentpage", page);
		request.setAttribute("pagesize", pagesize);
		request.setAttribute("total_profit", statisticsServiceImpl.getProfitThisYear());
		request.setAttribute("total_orders", statisticsServiceImpl.getOrdersCountThisYear());
		request.setAttribute("total_users", statisticsServiceImpl.getUsersCount());
		//
		currentpage = page;
		
		if(order.equals("id_company")) {
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
		
	    return "admin/publisher";
	}
	
	@RequestMapping(value = "admin/publisher", params = "keyword")
	public String publisherSearch(@RequestParam(required = true) String keyword, HttpServletRequest request) {
		keyword = keyword.trim();
		getMaxPage();
		request.setAttribute("count", companyServiceImpl.searchCompanyCount(keyword));
		request.setAttribute("publishers", companyServiceImpl.searchCompany(keyword));
		request.setAttribute("maxpage", 0);
		request.setAttribute("currentpage", 1);
		request.setAttribute("pagesize", 0);
		request.setAttribute("orderLink", "asc");
		request.setAttribute("total_profit", statisticsServiceImpl.getProfitThisYear());
		request.setAttribute("total_orders", statisticsServiceImpl.getOrdersCountThisYear());
		request.setAttribute("total_users", statisticsServiceImpl.getUsersCount());
	    return "admin/publisher";
	}
	
	@RequestMapping(value = "admin/publisher/edit", params = "idPublisher", method = RequestMethod.POST)
	public String publisherEdit(@RequestParam(required = true) int idPublisher, @RequestParam("namePublisher") String namePublisher, RedirectAttributes ra) {
		namePublisher = namePublisher.trim();
		System.out.println("------------------------------");
		System.out.println("ID PUB = "+idPublisher);
		System.out.println("NAME PUB = "+namePublisher);
		System.out.println("------------------------------");
		if(namePublisher.length() < 2) {
			ra.addFlashAttribute("edit_result", -1);
		}
		else if(companyServiceImpl.checkNameCompany(idPublisher, namePublisher) == true) {
			ra.addFlashAttribute("edit_result", 0);
		}else {
			int result = companyServiceImpl.editCompany(idPublisher, namePublisher);
			if(result > 0) {
				ra.addFlashAttribute("edit_result", 1);
			}else {
				ra.addFlashAttribute("edit_result", 2);
			}
		}
		String od;
		if(order.equals("id_company")) {
			od = "id";
		}else {
			od = "name";
		}
	    return "redirect:/admin/publisher.htm?page="+currentpage+"&order="+od+"&dir="+dir;
	}
	
	@RequestMapping(value = "admin/publisher/add", params = "publisherName", method = RequestMethod.POST)
	public String publisherAdd(@RequestParam(required = true) String publisherName, RedirectAttributes ra) {
		publisherName = publisherName.trim();
		CompanyEntity A = new CompanyEntity();
		A.setCompany_name(publisherName);
		if(publisherName.length() < 2) {
			ra.addFlashAttribute("add_result", -1);
		}
		else if(companyServiceImpl.checkNameCompany(-1, publisherName) == true) {
			ra.addFlashAttribute("add_result", 0);
		}else{
			int result = companyServiceImpl.addCompany(A);
			if(result > 0) {
				ra.addFlashAttribute("add_result", 1);
			}else {
				ra.addFlashAttribute("add_result", 2);
			}
		}
		String od;
		if(order.equals("id_company")) {
			od = "id";
		}else {
			od = "name";
		}
	    return "redirect:/admin/publisher.htm?page="+currentpage+"&order="+od+"&dir="+dir;
	}

	
	@RequestMapping(value = "admin/publisher/delete", params = "idPublisher", method = RequestMethod.POST)
	public String publisherDelete(@RequestParam(required = true) int idPublisher,  RedirectAttributes ra) {
		if(homeServiceImpl.dsBookbyIDCompany(idPublisher, 1, 4) != null) {
			ra.addFlashAttribute("delete_result", 0);
		}
		else{
			int result = companyServiceImpl.deleteCompany(idPublisher);
			if(result > 0) {
				ra.addFlashAttribute("delete_result", 1);
			}else {
				ra.addFlashAttribute("delete_result", 2);
			}
		}
		String od;
		if(order.equals("id_company")) {
			od = "id";
		}else {
			od = "name";
		}
		
		getMaxPage();
		if(currentpage > maxpage) {
			if(currentpage != 1)
				currentpage -= 1;
		}
	    return "redirect:/admin/publisher.htm?page="+currentpage+"&order="+od+"&dir="+dir;
	}

	
	public int getMaxPage() {
		int publishercount = (int) companyServiceImpl.getCompanyCount();
		if(publishercount == 0) {
			hienthi = 1;
		}else if(publishercount < 6) {
			hienthi = publishercount;
			pagesize = 1;
		}
		else if(publishercount < 11) {
			hienthi = 5;
			pagesize = 2;
		}else {
			hienthi = 5;
			pagesize = 3;
		}
		if(publishercount % hienthi == 0) {
			maxpage = publishercount / hienthi;
		}else {
			maxpage = (int)(publishercount / hienthi) + 1;
		}
		return maxpage;
	}
}
