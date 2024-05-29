package ptithcm.admin.controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ptithcm.controller.AccountController;
import ptithcm.serviceimpl.StatisticsServiceImpl;

@Controller
public class StatisticsAdminController {
	@Autowired
	StatisticsServiceImpl statisticsServiceImpl;
	
	@RequestMapping("admin/statistics")
	public String statisticsAdmin(ModelMap model, RedirectAttributes ra) {
		ra.addFlashAttribute("username", AccountController.getUser().getUsername());
		model.addAttribute("total_profit", statisticsServiceImpl.getProfitThisYear());
		model.addAttribute("total_orders", statisticsServiceImpl.getOrdersCountThisYear());
		model.addAttribute("total_users", statisticsServiceImpl.getUsersCount());
		
		String s = "";
		int year = Calendar.getInstance().get(Calendar.YEAR);
		long [] arr = statisticsServiceImpl.getDetailProfitMonthOfYear(year);
		for(int i = 0; i<arr.length; i++) {
			System.out.println("Thang "+(i+1)+": "+arr[i]);
			s += " "+ arr[i];
		}
		
		model.addAttribute("year_profit", year);
		model.addAttribute("profit_detail", s.trim());
		return "admin/statistics";
	}
	
	@RequestMapping(value = "admin/statistics", method = RequestMethod.POST)
	public String statisticsAdminYear(@RequestParam("year") int year, RedirectAttributes ra, ModelMap model) {	
		String s = "";
		System.out.println("YEAR = "+year);
		long [] arr = statisticsServiceImpl.getDetailProfitMonthOfYear(year);
		for(int i = 0; i<arr.length; i++) {
			System.out.println("Thang "+(i+1)+": "+arr[i]);
			s += " "+ arr[i];
		}
		
		ra.addFlashAttribute("profit_detail_month", s.trim());
		ra.addFlashAttribute("year_profit_detail", year);
		ra.addFlashAttribute("year_use_detail", 1);
		return "redirect:/admin/statistics.htm";
	}
}
