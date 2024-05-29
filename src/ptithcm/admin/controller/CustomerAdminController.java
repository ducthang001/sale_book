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
import ptithcm.entity.AccountEntity;
import ptithcm.entity.AuthorEntity;
import ptithcm.serviceimpl.AccountServiceImpl;
import ptithcm.serviceimpl.StatisticsServiceImpl;

@Controller
public class CustomerAdminController {
	@Autowired
	AccountServiceImpl accountServiceImpl;
	
	@Autowired
	StatisticsServiceImpl  statisticsServiceImpl;
	
	private int currentpage = 1;
	private int maxpage = 1;
	private int pagesize = 1;
	private int hienthi = 5;
	private int page = 1;
	private String order = "username"; 
	private String dir = "asc"; 
	@RequestMapping("admin/customer")
	public String customerAdmin(ModelMap model, RedirectAttributes ra, HttpServletRequest request) {
	
		if(request.getParameter("page") !="" && request.getParameter("page") != null){
			page = Integer.parseInt(request.getParameter("page"));
		}
		if(request.getParameter("keyword") !="" && request.getParameter("keyword") != null){
			request.setAttribute("count", accountServiceImpl.searchCustomerCount(request.getParameter("keyword")));
			request.setAttribute("users", accountServiceImpl.SearchCustomer(request.getParameter("keyword")));
			request.setAttribute("maxpage", 0);
			request.setAttribute("currentpage", 1);
			request.setAttribute("pagesize", 0);
		}else{
			model.addAttribute("maxpage", (int) Math.ceil((double) accountServiceImpl.getUserCount() / hienthi));
			model.addAttribute("currentpage", page);
			model.addAttribute("pagesize", pagesize);
			model.addAttribute("users", accountServiceImpl.customerPage(page, hienthi,order,dir));
			model.addAttribute("count", accountServiceImpl.getUserCount());
		}
		model.addAttribute("userLogin", AccountController.getUser());
		model.addAttribute("total_profit", statisticsServiceImpl.getProfitThisYear());
		model.addAttribute("total_orders", statisticsServiceImpl.getOrdersCountThisYear());
		model.addAttribute("total_users", statisticsServiceImpl.getUsersCount());
		
		ra.addFlashAttribute("username", AccountController.getUser().getUsername());
		
		return "admin/customer";
	}
	@RequestMapping(value = "admin/customer/add", method = RequestMethod.POST)
	public String customerAdd(AccountEntity account,ModelMap model,@RequestParam String defaultEmail,@RequestParam String defaultPhone) {
		if(account.getUser_id() >0){
			if(!defaultEmail.equals(account.getEmail())){
				if(accountServiceImpl.checkEmailExist(account.getEmail())){
					model.addAttribute("messageFailed", "Email đã tồn tại");
				}
			}else if(!defaultPhone.equals(account.getPhone())){
				if(accountServiceImpl.checkPhoneExist(account.getPhone())){
					model.addAttribute("messageFailed", "Số điện đã tồn tại");
				}
			}else{
				int kqUpdate = accountServiceImpl.updateUser(account.getUser_id(),account.getEmail(),account.getPhone(),account.getAge()
						,account.getGender(),account.getAddress(),account.getRole());
				model.addAttribute("messageSuccess", "Cập nhật người dùng thành công");
			}
		}else{
			if(accountServiceImpl.checkUsernameExist(account.getUsername())){
				model.addAttribute("messageFailed", "Username đã tồn tại");
			}
			else if(accountServiceImpl.checkEmailExist(account.getEmail())){
				model.addAttribute("messageFailed", "Email đã tồn tại");
			}
			else if(accountServiceImpl.checkPhoneExist(account.getPhone())){
				model.addAttribute("messageFailed", "Số điện đã tồn tại");
			}
			else{
				int kq =accountServiceImpl.addAccount(account);
				model.addAttribute("messageSuccess", "Thêm mới người dùng thành công");
			}
			
			
		}
		
		
		 return "redirect:/admin/customer.htm";
	}
	@RequestMapping(value = "admin/customer/delete", method = RequestMethod.POST)
	public String customerDelete(AccountEntity account,ModelMap model) {
		if(account.getUser_id() >0){
			try {
				int kq = accountServiceImpl.deleteCustomer(account.getUser_id());
				model.addAttribute("messageSuccess", "Xóa người dùng thành công");
			} catch (Exception e) {
				model.addAttribute("messageFailed", "Xóa người dùng thất bại do ràng buộc khóa ngoại");
				
			}
			
		}
		
		
		 return "redirect:/admin/customer.htm?page=1";
	}

}
