package ptithcm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ptithcm.serviceimpl.HomeServiceImpl;

@Controller
public class SearchController {
	@Autowired
	HomeServiceImpl homeServiceImpl;
	
	@RequestMapping("search")
	public String getFoos(@RequestParam(required = true) String keyword, ModelMap model) {
		model.addAttribute("keyword", keyword);
		model.addAttribute("books", homeServiceImpl.findBook(keyword));
	    return "search";
	}
}
