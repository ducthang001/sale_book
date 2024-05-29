package ptithcm.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.bean.CartDto;
import ptithcm.bean.Product;
import ptithcm.entity.BookEntity;
@Service
public interface HomeService {
	@Autowired
	public List <BookEntity> dsBook ();
	
	@Autowired
	public List <BookEntity> dsBookbyIDCategory (int id_category, int pageNumber, int pagesize);
	
	@Autowired
	public BookEntity getBookbyID(int id);
	
	@Autowired
	public List<BookEntity> dsBookbyAuthorID(int id_author, int pageNumber, int pagesize);
	
	@Autowired
	public List<BookEntity> findBook(String key);
	
	@Autowired
	public List<BookEntity> dsBookbyIDCompany(int id_company, int pageNumber, int pagesize);
	
	@Autowired
	public long getBookCount();
	
	@Autowired
	public int addBook(BookEntity book);
	
	@Autowired
	public List <BookEntity> bookPage (int pageNumber, int pagesize, String order, String dir);
	
	@Autowired
	public Product productFindBookbyID(int id);
	
	@Autowired
	public int getBookQuantybyID(int idBook);
	
	@Autowired
	public int updateBookQuanty(ArrayList<CartDto> cart);
	
	@Autowired
	public List <BookEntity> bookPageOfHome (int pageNumber, int pagesize);
	
	@Autowired
	public long getBookCountOfAuthor(int authorID);
	
	@Autowired
	public long getBookCountOfCompany(int companyID);
	
	@Autowired
	public long getBookCountOfCategory(int categoryID);
	
	@Autowired
	public List <BookEntity> dsBookRecommend(int money);
}



