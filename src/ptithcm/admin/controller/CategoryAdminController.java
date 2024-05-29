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
import ptithcm.entity.CategoryEntity;
import ptithcm.serviceimpl.CategoryServiceImpl;
import ptithcm.serviceimpl.HomeServiceImpl;
import ptithcm.serviceimpl.StatisticsServiceImpl;

@Controller
public class CategoryAdminController {
	@Autowired
	CategoryServiceImpl categoryServiceImpl;
	@Autowired
	HomeServiceImpl homeServiceImpl;
	
	@Autowired
	StatisticsServiceImpl statisticsServiceImpl;
	
	private int currentpage = 1;
	private int maxpage = 1;
	private int pagesize = 1;
	private int hienthi = 1;
	private String order = "category_id"; // category_id ,category_name
	private String dir = "asc";
	
	@RequestMapping("admin/category")
	public String categoryAdmin(ModelMap model, RedirectAttributes ra) {
		ra.addFlashAttribute("username", AccountController.getUser().getUsername());
		getMaxPage();
		if(order.equals("category_id")) {
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
		model.addAttribute("count", categoryServiceImpl.getCategoryCount());
		model.addAttribute("categories", categoryServiceImpl.categoryPage(currentpage, hienthi, order, dir));
		model.addAttribute("maxpage", maxpage);
		model.addAttribute("currentpage", currentpage);
		model.addAttribute("pagesize", pagesize);
		model.addAttribute("total_profit", statisticsServiceImpl.getProfitThisYear());
		model.addAttribute("total_orders", statisticsServiceImpl.getOrdersCountThisYear());
		model.addAttribute("total_users", statisticsServiceImpl.getUsersCount());
		return "admin/category";
	}
	
	@RequestMapping(value = "admin/category", params = "page")
	public String categoryPage(@RequestParam(required = true) int page, @RequestParam("order") String o,
			@RequestParam("dir") String d, HttpServletRequest request) {
		if(o.equals("id")) {
			order = "category_id";
		}else if(o.equals("name")) {
			order = "category_name";
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
		request.setAttribute("count", categoryServiceImpl.getCategoryCount());
		request.setAttribute("categories", categoryServiceImpl.categoryPage(page, hienthi, order, dir));
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("currentpage", page);
		request.setAttribute("pagesize", pagesize);
		request.setAttribute("total_profit", statisticsServiceImpl.getProfitThisYear());
		//
		currentpage = page;
		if(order.equals("category_id")) {
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
		
	    return "admin/category";
	}
	
	@RequestMapping(value = "admin/category", params = "keyword")
	public String categorySearch(@RequestParam(required = true) String keyword, HttpServletRequest request) {
		getMaxPage();
		request.setAttribute("count", categoryServiceImpl.searchCategoryCount(keyword));
		request.setAttribute("categories", categoryServiceImpl.searchCategory(keyword));
		request.setAttribute("maxpage", 0);
		request.setAttribute("currentpage", 1);
		request.setAttribute("pagesize", 0);
		request.setAttribute("orderLink", "asc");
		request.setAttribute("total_profit", statisticsServiceImpl.getProfitThisYear());
		request.setAttribute("total_orders", statisticsServiceImpl.getOrdersCountThisYear());
		request.setAttribute("total_users", statisticsServiceImpl.getUsersCount());
	    return "admin/category";
	}
	
	@RequestMapping(value = "admin/category/edit", params = "nameCategory", method = RequestMethod.POST)
	public String categoryEdit(@RequestParam(required = true) int idCategory, @RequestParam("nameCategory") String nameCategory, RedirectAttributes ra) {
		nameCategory = nameCategory.trim();
		if(nameCategory.length() < 2) {
			ra.addFlashAttribute("edit_result", -1);
		}
		else if(categoryServiceImpl.checkNameCategory(idCategory, nameCategory) == true) {
			ra.addFlashAttribute("edit_result", 0);
		}else {
			int result = categoryServiceImpl.editCategory(idCategory, nameCategory);
			if(result > 0) {
				ra.addFlashAttribute("edit_result", 1);
			}else {
				ra.addFlashAttribute("edit_result", 2);
			}
		}
		String od;
		if(order.equals("category_id")) {
			od = "id";
		}else {
			od = "name";
		}
	    return "redirect:/admin/category.htm?page="+currentpage+"&order="+od+"&dir="+dir;
	}
	
	@RequestMapping(value = "admin/category/add", params = "nameCategory", method = RequestMethod.POST)
	public String categoryAdd(@RequestParam(required = true) String nameCategory, RedirectAttributes ra) {
		nameCategory = nameCategory.trim();
		CategoryEntity A = new CategoryEntity();
		A.setCategory_name(nameCategory);
		if(nameCategory.length() < 2) {
			ra.addFlashAttribute("add_result", -1);
		}
		else if(categoryServiceImpl.checkNameCategory(-1, nameCategory) == true) {
			ra.addFlashAttribute("add_result", 0);
		}else {
			int result = categoryServiceImpl.addCategory(A);
			if(result > 0) {
				ra.addFlashAttribute("add_result", 1);
			}else {
				ra.addFlashAttribute("add_result", 2);
			}
		}
		String od;
		if(order.equals("category_id")) {
			od = "id";
		}else {
			od = "name";
		}
		return "redirect:/admin/category.htm?page="+currentpage+"&order="+od+"&dir="+dir;
	}
	
	@RequestMapping(value = "admin/category/delete", params = "idCategory", method = RequestMethod.POST)
	public String categoryDelete(@RequestParam(required = true) int idCategory,  RedirectAttributes ra) {
		if(homeServiceImpl.dsBookbyIDCategory(idCategory,1 ,4) != null) {
			ra.addFlashAttribute("delete_result", 0);
		}else {
			int result = categoryServiceImpl.deleteCategory(idCategory);
			if(result > 0) {
				ra.addFlashAttribute("delete_result", 1);
			}else {
				ra.addFlashAttribute("delete_result", 2);
			}
		}
		String od;
		if(order.equals("category_id")) {
			od = "id";
		}else {
			od = "name";
		}
		getMaxPage();
		if(currentpage > maxpage) {
			if(currentpage != 1)
				currentpage -= 1;
		}
		return "redirect:/admin/category.htm?page="+currentpage+"&order="+od+"&dir="+dir;
	}
	
	public int getMaxPage() {
		int categorycount = (int) categoryServiceImpl.getCategoryCount();
		if(categorycount == 0) {
			hienthi = 1;
		}else if(categorycount < 6) {
			hienthi = categorycount;
			pagesize = 1;
		}
		else if(categorycount < 11) {
			hienthi = 5;
			pagesize = 2;
		}else {
			hienthi = 5;
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
