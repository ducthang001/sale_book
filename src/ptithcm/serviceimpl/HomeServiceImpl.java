package ptithcm.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.bean.CartDto;
import ptithcm.bean.Product;
import ptithcm.dao.HomeDao;
import ptithcm.entity.BookEntity;
import ptithcm.service.HomeService;

@Service
public class HomeServiceImpl implements HomeService {
	@Autowired
	private HomeDao homeDao;
	
	@Override
	public List<BookEntity> dsBook() {
		return homeDao.dsBook();
	}

	@Override
	public List <BookEntity> dsBookbyIDCategory (int id_category, int pageNumber, int pagesize) {
		return homeDao.dsBookbyIDCategory(id_category, pageNumber, pagesize);
	}
	
	@Override
	public BookEntity getBookbyID(int id) {
		return homeDao.getBookbyID(id);
	}
	
	@Override
	public List<BookEntity> dsBookbyAuthorID(int id_author, int pageNumber, int pagesize){
		return homeDao.dsBookbyAuthorID(id_author, pageNumber, pagesize);
	}

	@Override
	public List<BookEntity> findBook(String key) {
		return homeDao.findBook(key);
	}

	@Override
	public List<BookEntity> dsBookbyIDCompany(int id_company, int pageNumber, int pagesize) {
		return homeDao.dsBookbyIDCompany(id_company, pageNumber, pagesize);
	}

	@Override
	public long getBookCount() {
		return homeDao.getBookCount();
	}
	
	@Override
	public int addBook(BookEntity book) {
		return homeDao.addBook(book);
	}
	
	@Override
	public List <BookEntity> bookPage (int pageNumber, int pagesize, String order, String dir){
		return homeDao.bookPage(pageNumber, pagesize, order, dir);
	}
	
	@Override
	public Product productFindBookbyID(int id) {
		return homeDao.productFindBookbyID(id);
	}
	
	@Override
	public int getBookQuantybyID(int idBook) {
		return homeDao.getBookQuantybyID(idBook);
	}

	@Override
	public int updateBookQuanty(ArrayList<CartDto> cart) {
		return homeDao.updateBookQuanty(cart);
	}
	
	@Override
	public List <BookEntity> bookPageOfHome (int pageNumber, int pagesize){
		return homeDao.bookPageOfHome(pageNumber, pagesize);
	}

	@Override
	public long getBookCountOfAuthor(int authorID) {
		return homeDao.getBookCountOfAuthor(authorID);
	}

	@Override
	public long getBookCountOfCompany(int companyID) {
		return homeDao.getBookCountOfCompany(companyID);
	}

	@Override
	public long getBookCountOfCategory(int categoryID) {
		return homeDao.getBookCountOfCategory(categoryID);
	}
	
	@Override
	public List <BookEntity> dsBookRecommend(int money){
		return homeDao.dsBookRecommend(money);
	}
	

}
