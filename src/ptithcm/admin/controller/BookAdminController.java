package ptithcm.admin.controller;


import java.sql.Date;
import java.util.List;


import javax.servlet.http.HttpServletRequest;



import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.support.PagedListHolder;

import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



import ptithcm.entity.AuthorEntity;
import ptithcm.entity.BookEntity;
import ptithcm.entity.CategoryEntity;
import ptithcm.entity.CompanyEntity;

import ptithcm.serviceimpl.BookServicelmpl;


@Controller
@RequestMapping("admin")
public class BookAdminController {
	@Autowired
	SessionFactory factory;
	
	@Autowired
	BookServicelmpl bookServicelmpl;
	
	BookEntity bookUpdate ; 
	
	@RequestMapping(value = "book",method = RequestMethod.GET)
	public String index(HttpServletRequest request,ModelMap model,@ModelAttribute("book") BookEntity book ) {
		
		List<BookEntity> books = bookServicelmpl.getBooks();
		model.addAttribute("books", new BookEntity());
		
		PagedListHolder pagedListHolder = new PagedListHolder(books);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(4);
		//model.addAttribute("btnStatus", "btnAdd");

		
		
		List<AuthorEntity> authors=bookServicelmpl.getAuthor();		
		model.addAttribute("authors", authors);
		
		List<CategoryEntity> categorys=bookServicelmpl.getCategory();		
		model.addAttribute("categorys", categorys);
		
		List<CompanyEntity> companys=bookServicelmpl.getCompany();		
		model.addAttribute("companys", companys);
		

		model.addAttribute("pagedListHolder", pagedListHolder);
//		for(int i=0; i<books.size();i++) {
//			System.out.println(books.get(i).getBook_name());
		
//		}
		
		model.addAttribute("btnStatus", "btnAdd");
	
		return "admin/book";
	}

	@RequestMapping(value="book", params = "btnsearch")
	public String searchBook(HttpServletRequest request, ModelMap model,
			@ModelAttribute("book") BookEntity book) {
		
		List<BookEntity> books =bookServicelmpl.searchBook(request.getParameter("searchInput"));
		PagedListHolder pagedListHolder = new PagedListHolder(books);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(2);
		
		model.addAttribute("pagedListHolder", pagedListHolder);
		
		 return "admin/book";
	}
	
	@RequestMapping(value="book/{id}", params = "linkEdit")
	public String edit_Book(HttpServletRequest request, ModelMap model,
			@ModelAttribute("book") BookEntity book,
			@PathVariable("id") Integer id ) {
		
		List<BookEntity> books =bookServicelmpl.getBooks();
		
		List<AuthorEntity> authors=bookServicelmpl.getAuthor();		
		model.addAttribute("authors", authors);
		
		List<CategoryEntity> categorys=bookServicelmpl.getCategory();		
		model.addAttribute("categorys", categorys);
		
		List<CompanyEntity> companys=bookServicelmpl.getCompany();		
		model.addAttribute("companys", companys);
		
		
		PagedListHolder pagedListHolder = new PagedListHolder(books);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(4);
		
		model.addAttribute("btnStatus", "btnEdit");
		model.addAttribute("book",bookServicelmpl.editBook(id));
		model.addAttribute("pagedListHolder", pagedListHolder);
		
		bookUpdate = bookServicelmpl.editBook(id); 
		
		 return "admin/book";
	}
	
	
	
	@RequestMapping(value = "btnAdd",method = RequestMethod.POST)
	public String addProduct(HttpServletRequest request,ModelMap model, 
			@ModelAttribute("book") BookEntity book, BindingResult errors) {

		System.out.println("ABC = "+book.getPublish_day());
		
		if((String)book.getBook_name()=="") {
		errors.rejectValue("book_name", "book", "Xin vui lòng nhập tên sách!");
	}
	if((String)book.getPicture()=="") {
		errors.rejectValue("picture", "book", "Xin vui lòng chọn ảnh!");
	}
	if((int)book.getPrice()==0) {
		errors.rejectValue("price", "book", "Xin vui lòng nhập giá!");
	}
	if(book.getPublish_day()==null) {
		errors.rejectValue("publish_day", "book", "Xin vui lòng nhập năm sản xuất!");
	}
	if((int)book.getTotal_quantity()==0) {
		errors.rejectValue("total_quantity", "book", "Xin vui lòng nhập tổng số lượng sách!");
	}
	if(errors.hasErrors()) {
		model.addAttribute("thongbao","Vui lòng sửa các lỗi dưới");
		model.addAttribute("btnStatus", "btnAdd");
		
		PagedListHolder pagedListHolder = new PagedListHolder(bookServicelmpl.getBooks());
		model.addAttribute("pagedListHolder", pagedListHolder);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(4);
		
		List<BookEntity> books =bookServicelmpl.getBooks();
		model.addAttribute("books", new BookEntity());
		
		List<AuthorEntity> authors=bookServicelmpl.getAuthor();		
		model.addAttribute("authors", authors);
		
		List<CategoryEntity> categorys=bookServicelmpl.getCategory();		
		model.addAttribute("categorys", categorys);
		
		List<CompanyEntity> companys=bookServicelmpl.getCompany();		
		model.addAttribute("companys", companys);
		return "admin/book";
	}
	
		Integer temp = bookServicelmpl.insertBook(book);
		if (temp != 0) {
			model.addAttribute("message", "Thêm thành công");
		} else {
			model.addAttribute("message", "Thêm thất bại!");
		}
		
		PagedListHolder pagedListHolder = new PagedListHolder(bookServicelmpl.getBooks());
		
		List<AuthorEntity> authors=bookServicelmpl.getAuthor();		
		model.addAttribute("authors", authors);
		
		List<CategoryEntity> categorys=bookServicelmpl.getCategory();		
		model.addAttribute("categorys", categorys);
		
		List<CompanyEntity> companys=bookServicelmpl.getCompany();		
		model.addAttribute("companys", companys);
		
		
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		
		pagedListHolder.setPageSize(4);
		model.addAttribute("pagedListHolder", pagedListHolder);
		

		return "redirect:/admin/book.htm";
	}
	@RequestMapping(value="btnEdit",method = RequestMethod.POST)
	public String edit_Product(HttpServletRequest request, ModelMap model,
			@ModelAttribute("book") BookEntity book,BindingResult errors) {
		
		if((String)book.getBook_name()=="") {
			errors.rejectValue("book_name", "book", "Xin vui lòng nhập tên sách!");
		}
//		if((String)book.getPicture()=="") {
//			errors.rejectValue("picture", "book", "Xin vui lòng chọn ảnh!");
//		}
		if((int)book.getPrice()==0) {
			errors.rejectValue("price", "book", "Xin vui lòng nhập giá!");
		}
		if(book.getPublish_day()==null) {
			errors.rejectValue("publish_day", "book", "Xin vui lòng nhập năm sản xuất!");
		}
		if((int)book.getTotal_quantity()==0) {
			errors.rejectValue("total_quantity", "book", "Xin vui lòng nhập tổng số lượng sách!");
		}
		if(errors.hasErrors()) {
			model.addAttribute("thongbao","Vui lòng sửa các lỗi dưới");
			model.addAttribute("btnStatus", "btnEdit");
			
			List<AuthorEntity> authors=bookServicelmpl.getAuthor();		
			model.addAttribute("authors", authors);
			
			List<CategoryEntity> categorys=bookServicelmpl.getCategory();		
			model.addAttribute("categorys", categorys);
			
			List<CompanyEntity> companys=bookServicelmpl.getCompany();		
			model.addAttribute("companys", companys);
			return "admin/book";
		}

		Integer temp = bookServicelmpl.updateBook(book, bookUpdate);
		if (temp != 0) {
			model.addAttribute("message", "Update thành công");
		} else {
			model.addAttribute("message", "Update thất bại!");
		}

		PagedListHolder pagedListHolder = new PagedListHolder(bookServicelmpl.getBooks());
		
		List<AuthorEntity> authors=bookServicelmpl.getAuthor();		
		model.addAttribute("authors", authors);
		
		List<CategoryEntity> categorys=bookServicelmpl.getCategory();		
		model.addAttribute("categorys", categorys);
		
		List<CompanyEntity> companys=bookServicelmpl.getCompany();		
		model.addAttribute("companys", companys);
		
		
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(4);
		
		model.addAttribute("pagedListHolder", pagedListHolder);

		return "redirect:/admin/book.htm";
	}
	@RequestMapping(value = "book/{id}.htm", params = "linkDelete")
	public String delete_Book(HttpServletRequest request, ModelMap model, @ModelAttribute("book") BookEntity book,
			@PathVariable("id") Integer id) {
		
		System.out.println("vào delete");
		Integer temp = bookServicelmpl.deleteBook(bookServicelmpl.editBook(id));
		
		List<AuthorEntity> authors=bookServicelmpl.getAuthor();		
		model.addAttribute("authors", authors);
		
		List<CategoryEntity> categorys=bookServicelmpl.getCategory();		
		model.addAttribute("categorys", categorys);
		
		List<CompanyEntity> companys=bookServicelmpl.getCompany();		
		model.addAttribute("companys", companys);
		System.out.println("giá trị của temp là : "+temp);
		if (temp != 0) {
			model.addAttribute("message", "Delete thành công");
		} else {
			model.addAttribute("message", "Delete thất bại!");
		}
		
		PagedListHolder pagedListHolder = new PagedListHolder(bookServicelmpl.getBooks());
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		
		pagedListHolder.setPageSize(4);
		model.addAttribute("pagedListHolder", pagedListHolder);
		//model.addAttribute("products", this.getProducts());
		

		return "admin/book";
	}
	
}
