package ptithcm.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ptithcm.entity.BookEntity;
import ptithcm.serviceimpl.HomeServiceImpl;
import ptithcm.serviceimpl.OrderDetailImpl;

@Controller
@Transactional
public class TEST {
	@Autowired
	SessionFactory factory;
	
	@Autowired
	HomeServiceImpl homeServiceImpl;
	
	@Autowired
	OrderDetailImpl orderDetailImpl;
	
	private int currentpage = 1;
	private int maxPage = 1;
	private int pageSize = 3;
	
	@RequestMapping("/phantrang")
	public String phantrang(ModelMap model) {
		model.addAttribute("books", dsBook(currentpage));
		getCountBook();
		model.addAttribute("maxPage", maxPage);
		model.addAttribute("currentpage", 1);
		return "phantrang";
	}
	@RequestMapping(value = "/phantrang",  params = "page")
	public String bookpage(@RequestParam("page") int page, ModelMap model) {
		currentpage = page;
		getCountBook();
		model.addAttribute("maxPage", maxPage);
		model.addAttribute("books", dsBook(page));
		model.addAttribute("currentpage", currentpage);
		return "phantrang";
	}
	
	public List <BookEntity> dsBook (int pageNumber){
		Session session = factory.getCurrentSession();
		String hql = "FROM BookEntity ORDER BY id_book";
		Query query = session.createQuery(hql);
		query.setFirstResult((pageNumber-1) * pageSize);
		query.setMaxResults(pageSize);
		@SuppressWarnings("unchecked")
		List<BookEntity> list = query.list();
		return list;
	}
	
	
	public void getCountBook (){
		Session session = factory.getCurrentSession();
		Long count = ((Long) session.createQuery("select count(id_book) from BookEntity").uniqueResult());
		int totalItem = count.intValue();
		if(totalItem % pageSize == 0) {
			maxPage = totalItem / pageSize;
		}else {
			maxPage = (int)(totalItem / pageSize) + 1;
		}
		
	}
	
	@RequestMapping("test")
	public String test(ModelMap mode) {
		mode.addAttribute("homeServiceImpl", homeServiceImpl);
		mode.addAttribute("topbuy", orderDetailImpl.getTopTheBestBook());
		return "test";
	}
	
	
}
