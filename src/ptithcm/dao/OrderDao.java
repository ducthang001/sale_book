package ptithcm.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ptithcm.entity.OrderEntity;

@Controller
@Transactional
public class OrderDao {
	@Autowired
	SessionFactory factory;
	
	public List <OrderEntity> dsOrder (){
		Session session = factory.getCurrentSession();
		String hql = "FROM OrderEntity";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<OrderEntity> list = query.list();
		return list;
	}
	
	public List <OrderEntity> orderPage (int pageNumber, int pagesize, String order, String dir, int status){
		Session session = factory.getCurrentSession();
		String hql = "FROM OrderEntity ORDER BY "+order+" "+dir;
		if(status > 0) {
			hql = "FROM OrderEntity WHERE order_status = " +status+" ORDER BY "+order+" "+dir;
		}
		Query query = session.createQuery(hql);
		query.setFirstResult((pageNumber-1) * pagesize);
		query.setMaxResults(pagesize);
		@SuppressWarnings("unchecked")
		List<OrderEntity> list = query.list();
		return list;
	}
	
	public long getOrderCount(int status) {
		Session session = factory.getCurrentSession();
		String hql = "SELECT count(C.order_id) FROM OrderEntity C";
		if(status > 0) {
			hql = "SELECT count(C.order_id) FROM OrderEntity C WHERE order_status = "+status;
		}
		Query query = session.createQuery(hql);
		long soluong = (long) query.uniqueResult();
		return soluong;
	}
	
	public List<OrderEntity> searchOrder(Date dateA, Date dateB){
		Session session = factory.getCurrentSession();
		String hql = "From OrderEntity A WHERE A.order_day BETWEEN :dateA AND :dateB";
		Query query = session.createQuery(hql);
		query.setParameter("dateA", dateA);
		query.setParameter("dateB", dateB);
		@SuppressWarnings("unchecked")
		List<OrderEntity> list = query.list();
		return list;
	}
	
	public List<OrderEntity> searchOrderUser(Date dateA, Date dateB, int idUser){
		Session session = factory.getCurrentSession();
		String hql = "From OrderEntity A WHERE A.user_id = "+idUser+" AND A.order_day BETWEEN :dateA AND :dateB";
		Query query = session.createQuery(hql);
		query.setParameter("dateA", dateA);
		query.setParameter("dateB", dateB);
		@SuppressWarnings("unchecked")
		List<OrderEntity> list = query.list();
		return list;
	}
	
	
	public boolean checkOrderOfUserExist(int idOrder, int idUser){
		Session session = factory.getCurrentSession();
		String hql = "From OrderEntity A WHERE A.user_id = "+idUser+" AND A.order_id = "+idOrder;
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<OrderEntity> list = query.list();
		if(list.isEmpty() == true) {
			return false;
		}else {
			return true;
		}
	}
	
	public boolean checkOldStatusExactly(int oldStatus, int idOrder) {
		Session session = factory.getCurrentSession();
		String hql = "From OrderEntity A WHERE A.order_status = "+oldStatus +" AND A.order_id = "+idOrder;
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<OrderEntity> list = query.list();
		if(list.isEmpty() == true) {
			return false;
		}else {
			return true;
		}
	}
	
	public int saveOrder(int newStatus, int idOrder) {
		Session session = factory.getCurrentSession();
		String hql = "UPDATE OrderEntity A SET order_status = "+newStatus+" WHERE A.order_id = "+idOrder;
		Query query = session.createQuery(hql);
		int result = query.executeUpdate();
		return result;
	}
	
	public List <OrderEntity> getOrderbyIdUser (int idUser){
		Session session = factory.getCurrentSession();
		String hql = "FROM OrderEntity A WHERE A.user_id = "+idUser+" ORDER BY A.order_day DESC";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<OrderEntity> list = query.list();
		return list;
	}
	
	public OrderEntity getOrderbyIDOrder (int idOrder){
		Session session = factory.getCurrentSession();
		String hql = "FROM OrderEntity A WHERE A.order_id = "+idOrder;
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<OrderEntity> list = query.list();
		if(list.isEmpty() == true) {
			return null;
		}else {
			return list.get(0);
		}
	}
	
	public int addOrder(OrderEntity order) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
        try {
        	session.save(order);
        	tx.commit();
        }catch (Exception e){
        	tx.rollback();
        	return 0;
        } finally{
        	session.close();
        }
        return 1;
	}
	
	public int getLastOrderID() {
		Session session = factory.getCurrentSession();
		String hql = "FROM OrderEntity";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<OrderEntity> list = query.list();
		if(list.isEmpty() == true) {
			return -1;
		}else {
			return list.get(list.size() - 1).getOrder_id();
		}
	}
	
	
}
