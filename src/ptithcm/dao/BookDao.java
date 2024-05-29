package ptithcm.dao;

import java.util.List;

import javax.transaction.Transactional;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ptithcm.entity.AuthorEntity;
import ptithcm.entity.BookEntity;
import ptithcm.entity.CategoryEntity;
import ptithcm.entity.CompanyEntity;

@Controller
@Transactional
public class BookDao {
	@Autowired
	SessionFactory factory;
	
	public List<BookEntity> getBooks(){
		//Session session = factory.getCurrentSession();
		Session session = factory.openSession();
		String hql = "FROM BookEntity";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<BookEntity> list = query.list();
		if(session.isOpen()) session.close(); 
		return list;
	}
	
public Integer insertBook(BookEntity pd) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(pd);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}

	public Integer deleteBook(BookEntity pd) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
	
		try {
			session.delete(pd);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			System.out.println("gia tri cua e: "+e.getMessage());
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}
	
	public Integer updateBook(BookEntity pd, BookEntity bookUpdate) {
		if(pd.getPicture().trim().equals("")) {
			
			pd.setPicture(bookUpdate.getPicture());
		}
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		try {
			session.update(pd);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}
	
	public List<AuthorEntity> getAuthor(){
		//Session session = factory.getCurrentSession();
		Session session = factory.openSession();
		String hql = "FROM AuthorEntity";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<AuthorEntity> list = query.list();
		if(session.isOpen()) session.close(); 
		return list;
	}
	
	public List<CategoryEntity> getCategory(){
		//Session session = factory.getCurrentSession();
		Session session = factory.openSession();
		String hql = "FROM CategoryEntity";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<CategoryEntity> list = query.list();
		if(session.isOpen()) session.close(); 
		return list;
	}
	
	public List<CompanyEntity> getCompany(){
		//Session session = factory.getCurrentSession();
		Session session = factory.openSession();
		String hql = "FROM CompanyEntity";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<CompanyEntity> list = query.list();
		if(session.isOpen()) session.close(); 
		return list;
	}
	public List<BookEntity> searchBook(String book_name) {
		//Session session = factory.getCurrentSession();
		Session session = factory.openSession();
		//String hql = "FROM ProductsEntity where product_name LIKE '"+ product_name + "%'";
		String hql = "FROM BookEntity where book_name LIKE :book_name";
		Query query = session.createQuery(hql);
		query.setParameter("book_name", "%" +  book_name + "%");
		@SuppressWarnings("unchecked")
		List<BookEntity> list = query.list();
		return list;
	}
	
	public BookEntity editBook(Integer id_book){
		//Session session = factory.getCurrentSession();
		Session session = factory.openSession();
		String hql = "FROM BookEntity where id_book =:id_book";
		Query query = session.createQuery(hql);
		query.setParameter("id_book", id_book);
		BookEntity list =(BookEntity)query.list().get(0);
		if(session.isOpen()) session.close(); 
		return list;
	}
	
}
