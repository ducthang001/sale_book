package ptithcm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.entity.AuthorEntity;
import ptithcm.entity.BookEntity;
import ptithcm.entity.CategoryEntity;
import ptithcm.entity.CompanyEntity;

@Service
public interface BookService {
	@Autowired
	public List<BookEntity> getBooks();
	
	@Autowired
	public Integer insertBook(BookEntity pd);
	
	@Autowired
	public Integer deleteBook(BookEntity pd);
	
	@Autowired
	public Integer updateBook(BookEntity pd, BookEntity bookUpdate);
	
	@Autowired
	public List<AuthorEntity> getAuthor();
	
	@Autowired
	public List<CategoryEntity> getCategory();
	
	@Autowired
	public List<CompanyEntity> getCompany();
	
	@Autowired
	public List<BookEntity> searchBook(String book_name);
	
	@Autowired
	public BookEntity editBook(Integer id_book);
}
