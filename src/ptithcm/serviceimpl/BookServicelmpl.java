package ptithcm.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.dao.BookDao;
import ptithcm.entity.AuthorEntity;
import ptithcm.entity.BookEntity;
import ptithcm.entity.CategoryEntity;
import ptithcm.entity.CompanyEntity;
import ptithcm.service.BookService;

@Service
public class BookServicelmpl implements BookService {
	@Autowired
	BookDao bookDao;
	
	@Override
	public List<BookEntity> getBooks() {
		return bookDao.getBooks();
	}

	@Override
	public Integer insertBook(BookEntity pd) {
		// TODO Auto-generated method stub
		return bookDao.insertBook(pd);
	}

	@Override
	public Integer deleteBook(BookEntity pd) {
		// TODO Auto-generated method stub
		return bookDao.deleteBook(pd);
	}

	@Override
	public Integer updateBook(BookEntity pd, BookEntity bookUpdate) {
		// TODO Auto-generated method stub
		return bookDao.updateBook(pd, bookUpdate);
	}

	@Override
	public List<AuthorEntity> getAuthor() {
		// TODO Auto-generated method stub
		return bookDao.getAuthor();
	}

	@Override
	public List<CategoryEntity> getCategory() {
		// TODO Auto-generated method stub
		return bookDao.getCategory();
	}

	@Override
	public List<CompanyEntity> getCompany() {
		// TODO Auto-generated method stub
		return bookDao.getCompany();
	}

	@Override
	public List<BookEntity> searchBook(String book_name) {
		// TODO Auto-generated method stub
		return bookDao.searchBook(book_name);
	}

	@Override
	public BookEntity editBook(Integer id_book) {
		// TODO Auto-generated method stub
		return bookDao.editBook(id_book);
	}
	
}