package ptithcm.admin.controller;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ptithcm.controller.AccountController;
import ptithcm.serviceimpl.OrderServiceImpl;
import ptithcm.serviceimpl.StatisticsServiceImpl;

@Controller
public class OrderAdminController {
	@Autowired
	OrderServiceImpl orderServiceImpl;
	
	@Autowired
	StatisticsServiceImpl statisticsServiceImpl;
	
	private int currentpage = 1;
	private int maxpage = 1;
	private int pagesize = 1;
	private int hienthi = 1;
	private String order = "order_id"; // order_id / customer_name / order_day // total_price // customer_phone // address
	private String dir = "asc";
	private int status_var;
	
	@RequestMapping("admin/order")
	public String orderAdmin(ModelMap model, RedirectAttributes ra) {
		ra.addFlashAttribute("username", AccountController.getUser().getUsername());
		getMaxPage(0);
		status_var = 0;
		if(order.equals("order_id")) {
			model.addAttribute("orderpage", "id");
		}else if(order.equals("customer_name")){
			model.addAttribute("orderpage", "name");
		}else if(order.equals("order_day")){
			model.addAttribute("orderpage", "date");
		}else if(order.equals("total_price")){
			model.addAttribute("orderpage", "price");
		}
		
		model.addAttribute("dirpage", dir);
		if(dir.equals("asc")) {
			model.addAttribute("orderLink", "desc");
		}else {
			model.addAttribute("orderLink", "asc");
		}
		model.addAttribute("count", orderServiceImpl.getOrderCount(0));
		model.addAttribute("orders", orderServiceImpl.orderPage(currentpage, hienthi, order, dir, 0));
		model.addAttribute("maxpage", maxpage);
		model.addAttribute("status", 0);
		model.addAttribute("currentpage", currentpage);
		model.addAttribute("pagesize", pagesize);
		model.addAttribute("total_profit", statisticsServiceImpl.getProfitThisYear());
		model.addAttribute("total_orders", statisticsServiceImpl.getOrdersCountThisYear());
		model.addAttribute("total_users", statisticsServiceImpl.getUsersCount());
		return "admin/order";
	}
	
	
	@RequestMapping(value = "admin/order", params = "page")
	public String authorPage(@RequestParam(required = true) int page, @RequestParam("order") String o,
			@RequestParam("dir") String d, @RequestParam("status") int status,  HttpServletRequest request) {
		status_var = status;
		if(o.equals("id")) {
			order = "order_id";
		}else if(o.equals("name")) {
			order = "customer_name";
		}else if(o.equals("date")) {
			order = "order_day";
		}else if(o.equals("price")) {
			order = "total_price";
		}
		
		if(d.equals("asc")) {
			dir = "asc";
		}else if(d.equals("desc")) {
			dir = "desc";
		}
		
		getMaxPage(status);
		if(page > maxpage) {
			page = 1;
		}
		request.setAttribute("orders", orderServiceImpl.orderPage(page, hienthi, order, dir, status));
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("currentpage", page);
		request.setAttribute("pagesize", pagesize);
		request.setAttribute("status", status);
		request.setAttribute("count", orderServiceImpl.getOrderCount(status));
		request.setAttribute("total_profit", statisticsServiceImpl.getProfitThisYear());
		request.setAttribute("total_orders", statisticsServiceImpl.getOrdersCountThisYear());
		request.setAttribute("total_users", statisticsServiceImpl.getUsersCount());
		//
		currentpage = page;
		
		if(order.equals("order_id")) {
			request.setAttribute("orderpage", "id");
		}else if(order.equals("customer_name")){
			request.setAttribute("orderpage", "name");
		}else if(order.equals("order_day")){
			request.setAttribute("orderpage", "date");
		}else if(order.equals("total_price")){
			request.setAttribute("orderpage", "price");
		}
		
		request.setAttribute("dirpage", dir);
		if(dir.equals("asc")) {
			request.setAttribute("orderLink", "desc");
		}else {
			request.setAttribute("orderLink", "asc");
		}
		
	    return "admin/order";
	}
	
	
	@RequestMapping(value = "admin/order/search", params = "startDate")
	public String orderSearch(@RequestParam(required = true) @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate, @RequestParam("endDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate,  HttpServletRequest request) {
		request.setAttribute("orders", orderServiceImpl.searchOrder(startDate, endDate));
		request.setAttribute("count", orderServiceImpl.searchOrder(startDate, endDate).size());
		request.setAttribute("maxpage", 0);
		request.setAttribute("currentpage", 1);
		request.setAttribute("pagesize", 0);
		request.setAttribute("orderLink", "asc");
		request.setAttribute("status", 0);
		request.setAttribute("total_profit", statisticsServiceImpl.getProfitThisYear());
		request.setAttribute("total_orders", statisticsServiceImpl.getOrdersCountThisYear());
		request.setAttribute("total_users", statisticsServiceImpl.getUsersCount());
	    return "admin/order";
	}
	
	@RequestMapping("admin/order/save")
	public String orderSave(@RequestParam("page") int page, @RequestParam("idOrder") int idOrder, 
			@RequestParam("statusNew") int statusNew, RedirectAttributes ra,
			@RequestParam("statusOld") int statusOld, HttpServletResponse response, ModelMap model) {
		
		
		
		if(orderServiceImpl.checkOldStatusExactly(statusOld, idOrder) == false) { // Khong dung thong tin
			ra.addFlashAttribute("kq", 0);
		}else {
			if(orderServiceImpl.saveOrder(statusNew, idOrder) > 0) {
				ra.addFlashAttribute("kq", 1);
			}else {
				ra.addFlashAttribute("kq", 2);
			}
		}
		
		getMaxPage(status_var);
		ra.addFlashAttribute("count", orderServiceImpl.getOrderCount(status_var));
		ra.addFlashAttribute("orders", orderServiceImpl.orderPage(page, hienthi, order, dir, status_var));
		ra.addFlashAttribute("maxpage", maxpage);
		ra.addFlashAttribute("status", status_var);
		ra.addFlashAttribute("currentpage", page);
		ra.addFlashAttribute("pagesize", pagesize);
		
		String od = "";
		if(order.equals("order_id")) {
			od = "id";
		}else if(order.equals("customer_name")){
			od = "name";
		}else if(order.equals("order_day")){
			od = "date";
		}else if(order.equals("total_price")){
			od = "price";
		}
		
		return "redirect:/admin/order.htm?page="+page+"&order="+od+"&dir="+dir+"&status="+status_var;
	}
	// http://localhost:8080/admin/order/save.htm/page=1?idOrder=1&statusNew=4&statusOld=3
	
	
	public int getMaxPage(int status) {
		int ordercount = (int) orderServiceImpl.getOrderCount(status);
		if(ordercount == 0) {
			hienthi = 1;
		}else if(ordercount < 6) {
			hienthi = ordercount;
			pagesize = 1;
		}
		else if(ordercount < 11) {
			hienthi = 5;
			pagesize = 2;
		}else {
			hienthi = 5;
			pagesize = 3;
		}
		if(ordercount % hienthi == 0) {
			maxpage = ordercount / hienthi;
		}else {
			maxpage = (int)(ordercount / hienthi) + 1;
		}
		return maxpage;
	}
}

/// END
