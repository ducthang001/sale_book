package ptithcm.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ptithcm.entity.OrderDetailEntity;

@Controller
@Transactional
public class OrderDetailDao {
	@Autowired
	SessionFactory factory;
	
	public int addOrderDetail(OrderDetailEntity orderdetail) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
        try {
        	session.save(orderdetail);
        	tx.commit();
        }catch (Exception e){
        	tx.rollback();
        	return 0;
        } finally{
        	session.close();
        }
        return 1;
	}
	
	public List<Object> getTopTheBestBook() {
		Session session = factory.getCurrentSession();
		String hql = "SELECT O.id_book, O.book.book_name, O.book.author.author_name, O.book.picture, O.book.price, SUM(O.quantity) AS TONG FROM OrderDetailEntity AS O GROUP BY O.id_book, O.book.book_name, O.book.picture, O.book.author.author_name, O.book.picture, O.book.author.author_name, O.book.price ORDER BY TONG DESC";
		Query query = session.createQuery(hql).setMaxResults(4);
		@SuppressWarnings("unchecked")
		List<Object> list = query.list();
		return list;
	}
}
