package ptithcm.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ptithcm.bean.CartDto;
import ptithcm.bean.Product;
import ptithcm.entity.BookEntity;

@Controller
@Transactional
public class HomeDao {
	@Autowired
	SessionFactory factory;	
	
	public List <BookEntity> dsBook (){
		Session session = factory.getCurrentSession();
		String hql = "FROM BookEntity";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<BookEntity> list = query.list();
		return list;
	}
	
	public List<BookEntity> dsBookbyIDCategory(int id_category, int pageNumber, int pagesize) {
		Session session = factory.getCurrentSession();
		String hql = "FROM BookEntity A WHERE A.category_id = "+id_category+" ORDER BY id_book DESC";
		Query query = session.createQuery(hql);
		query.setFirstResult((pageNumber-1) * pagesize);
		query.setMaxResults(pagesize);
		@SuppressWarnings("unchecked")
		List<BookEntity> list = query.list();
		if(list.isEmpty() == false) {
			return list;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BookEntity> dsBookbyIDCompany(int id_company, int pageNumber, int pagesize) {
		Session session = factory.getCurrentSession();
		String hql = "FROM BookEntity C WHERE C.id_company = "+id_company+ "ORDER BY id_book DESC";
		Query query = session.createQuery(hql);
		query.setFirstResult((pageNumber-1) * pagesize);
		query.setMaxResults(pagesize);
		List<BookEntity> list = query.list();
		if(list.isEmpty() == false) {
			return list;
		}
		return null;
	}
	
	public BookEntity getBookbyID(int id) {
		Session session = factory.getCurrentSession();
		String hql = "From BookEntity B WHERE B.id_book = "+id;
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<BookEntity> list = query.list();
		if(list.isEmpty() == true) {
			return null;
		}
		return list.get(0);
	}

	public List<BookEntity> dsBookbyAuthorID(int id_author, int pageNumber, int pagesize) {
		Session session = factory.getCurrentSession();
		String hql = "From BookEntity A WHERE A.id_author = "+id_author +" ORDER BY id_book DESC";
		Query query = session.createQuery(hql);
		query.setFirstResult((pageNumber-1) * pagesize);
		query.setMaxResults(pagesize);
		@SuppressWarnings("unchecked")
		List<BookEntity> list = query.list();
		if(list.isEmpty() == false) {
			return list;
		}
		return null;
	}
	
	public List<BookEntity> findBook(String key){
		Session session = factory.getCurrentSession();
		String hql = "From BookEntity B WHERE B.describe_book LIKE :key OR B.book_name LIKE :key";
		Query query = session.createQuery(hql);
		query.setParameter("key", "%" + key + "%");
		@SuppressWarnings("unchecked")
		List<BookEntity> list = query.list();
		return list;
	}
	
	public List <BookEntity> bookPage (int pageNumber, int pagesize, String order, String dir){
		Session session = factory.getCurrentSession();
		String hql = "FROM BookEntity ORDER BY "+order+" "+dir;
		Query query = session.createQuery(hql);
		query.setFirstResult((pageNumber-1) * pagesize);
		query.setMaxResults(pagesize);
		@SuppressWarnings("unchecked")
		List<BookEntity> list = query.list();
		return list;
	}
	
	public long getBookCount() {
		Session session = factory.getCurrentSession();
		String hql = "SELECT count(B.id_book) FROM BookEntity B";
		Query query = session.createQuery(hql);
		long soluong = (long) query.uniqueResult();
		return soluong;
	}
	
	public long getBookCountOfAuthor(int authorID) {
		Session session = factory.getCurrentSession();
		String hql = "SELECT count(B.id_book) FROM BookEntity B WHERE B.id_author = "+authorID;
		Query query = session.createQuery(hql);
		long soluong = (long) query.uniqueResult();
		return soluong;
	}
	
	public long getBookCountOfCompany(int companyID) {
		Session session = factory.getCurrentSession();
		String hql = "SELECT count(B.id_book) FROM BookEntity B WHERE B.id_company = "+companyID;
		Query query = session.createQuery(hql);
		long soluong = (long) query.uniqueResult();
		return soluong;
	}
	
	public long getBookCountOfCategory(int categoryID) {
		Session session = factory.getCurrentSession();
		String hql = "SELECT count(B.id_book) FROM BookEntity B WHERE B.category_id = "+categoryID;
		Query query = session.createQuery(hql);
		long soluong = (long) query.uniqueResult();
		return soluong;
	}
	
	public int addBook(BookEntity book) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
        try {
        	session.save(book);
        	tx.commit();
        }catch (Exception e){
        	tx.rollback();
        	return 0;
        } finally{
        	session.close();
        }
        return 1;
	}
	
	public List <BookEntity> bookPageOfHome (int pageNumber, int pagesize){
		Session session = factory.getCurrentSession();
		String hql = "FROM BookEntity ORDER BY id_book DESC";
		Query query = session.createQuery(hql);
		query.setFirstResult((pageNumber-1) * pagesize);
		query.setMaxResults(pagesize);
		@SuppressWarnings("unchecked")
		List<BookEntity> list = query.list();
		return list;
	}
	
	
	
	
	// Xử lý cho phần giỏ hàng
	public Product productFindBookbyID(int id) {
		Session session = factory.getCurrentSession();
		String hql = "From BookEntity B WHERE B.id_book = "+id;
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<BookEntity> list = query.list();
		if(list.isEmpty() == true) {
			return null;
		}
		Product product = new Product(id, list.get(0).getBook_name(), list.get(0).getPrice(), list.get(0).getPicture());
		return product;
	}
	
	public int getBookQuantybyID(int idBook) {
		Session session = factory.getCurrentSession();
		String hql = "From BookEntity B WHERE B.id_book = "+idBook;
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<BookEntity> list = query.list();
		if(list.isEmpty() == true) {
			return -1;
		}else {
			return list.get(0).getTotal_quantity();
		}
	}
	
	
	// UPDATE QUANTY ---  CART
	public int updateBookQuanty(ArrayList<CartDto> cart) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
        try {
        	for(int i = 0; i<cart.size(); i++) {
        		int idbook = cart.get(i).getProduct().getId();
        		int quanty = cart.get(i).getQuanty();
        		int current_quanty = getBookQuantybyID(idbook);
        		int conlai = current_quanty - quanty;
        		String hql = "UPDATE BookEntity B SET B.total_quantity = "+conlai+" WHERE B.id_book = "+idbook;
        		Query query = session.createQuery(hql);
        		query.executeUpdate();
        	}
        	tx.commit();
        }catch (Exception e){
        	tx.rollback();
        	return 0;
        } finally{
        	session.close();
        }
        return 1;
	}
	
	
	public List <BookEntity> dsBookRecommend(int money){
		Session session = factory.getCurrentSession();
		String hql = "From BookEntity B WHERE B.price < "+money +" ORDER BY price";
		Query query = session.createQuery(hql);
		query.setMaxResults(4);
		@SuppressWarnings("unchecked")
		List<BookEntity> list = query.list();
		return list;
	}
	
	
	
}
