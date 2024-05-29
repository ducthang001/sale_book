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
import ptithcm.entity.ItemEntity;


@Controller
@Transactional
public class ItemDao {
	@Autowired
	SessionFactory factory;

	public List <ItemEntity> findItemByIDUser (int userID){
		Session session = factory.getCurrentSession();
		String hql = "FROM ItemEntity A WHERE A.user_id = "+userID;
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<ItemEntity> list = query.list();
		return list;
	}
	
	public boolean checkIDBookOfUserItemExits (int userID, int idBook){
		Session session = factory.getCurrentSession();
		String hql = "FROM ItemEntity A WHERE A.user_id = "+userID+" AND A.id_book = "+idBook;
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<ItemEntity> list = query.list();
		if(list.isEmpty() == true) {
			return false;
		}else {
			return true;
		}
	}
	
	public int updateItem (ArrayList<CartDto> cart, int userID) {
		Session session = factory.getCurrentSession();
		int result = 1;
		for(int i = 0; i<cart.size(); i++) {
			int quantity_books = cart.get(i).getQuanty();
			int id_book = cart.get(i).getProduct().getId();
			String hql = "UPDATE ItemEntity A SET quantity_books = "+quantity_books+" WHERE  A.id_book = "+id_book+" AND A.user_id = "+userID;
			System.out.println("hql = "+hql);
			Query query = session.createQuery(hql);
			result = query.executeUpdate();
			if(result < 1) {
				result = 0;
				break;
			}
		}
		return result;
	}
	
	public int addItem(ItemEntity item) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
        try {
        	session.save(item);
        	tx.commit();
        }catch (Exception e){
        	tx.rollback();
        	return 0;
        } finally{
        	session.close();
        }
        return 1;
	} 
	
	public int deleteItem(int idBook, int idUser) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
        try {
        	String hql = "DELETE FROM ItemEntity A WHERE A.user_id = "+idUser+" AND A.id_book = "+idBook;
    		Query query = session.createQuery(hql);
    		query.executeUpdate();
        	tx.commit();
        }catch (Exception e){
        	tx.rollback();
        	return 0;
        } finally{
        	session.close();
        }
        return 1;
	}
	
	public int deleteAllItemsOfUser(int userID) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
        try {
        	String hql = "DELETE FROM ItemEntity A WHERE A.user_id = "+userID;
    		Query query = session.createQuery(hql);
    		query.executeUpdate();
        	tx.commit();
        }catch (Exception e){
        	tx.rollback();
        	return 0;
        } finally{
        	session.close();
        }
        return 1;
	}
	
}
