package ptithcm.dao;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ptithcm.entity.OrderEntity;

@Transactional
@Controller
public class StatisticsDao {
	@Autowired
	SessionFactory factory;
	
	public long getProfitThisYear() {
		Session session = factory.getCurrentSession();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		String hql = "SELECT SUM(A.total_price) FROM OrderEntity A WHERE A.order_status = 2 AND YEAR(A.order_day) = "+year;
		Query query = session.createQuery(hql);
		if(query.uniqueResult() == null) {
			return 0;
		}
		long soluong = (long) query.uniqueResult();
		return soluong;
	}
	
	public long getOrdersCountThisYear() {
		Session session = factory.getCurrentSession();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		String hql = "SELECT COUNT(A.order_id) FROM OrderEntity A WHERE YEAR(A.order_day) = "+year;
		Query query = session.createQuery(hql);
		if(query.uniqueResult() == null) {
			return 0;
		}
		long soluong = (long) query.uniqueResult();
		return soluong;
	}
	
	public long getUsersCount() {
		Session session = factory.getCurrentSession();
		String hql = "SELECT COUNT(A.user_id) FROM AccountEntity A";
		Query query = session.createQuery(hql);
		if(query.uniqueResult() == null) {
			return 0;
		}
		long soluong = (long) query.uniqueResult();
		return soluong;
	}
	
	@SuppressWarnings("deprecation")
	public long[] getDetailProfitMonthOfYear(int year) {
		Session session = factory.getCurrentSession();
		String hql = "FROM OrderEntity A WHERE A.order_status = 2 AND YEAR(A.order_day) = "+year;
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<OrderEntity> list = query.list();
		
		long[] profitArr = new long [12];
		Arrays.fill(profitArr, 0);
		
		for(int i = 0; i<list.size(); i++) {
			int month = list.get(i).getOrder_day().getMonth();
			profitArr[month] = profitArr[month] + list.get(i).getTotal_price();
		}
		return profitArr;
		
	}
	
	
	public long amountSpentbyIdUser (int idUser){
		Session session = factory.getCurrentSession();
		String hql = "SELECT SUM(A.total_price) FROM OrderEntity A WHERE A.order_status = 2 AND A.user_id = "+idUser;
		Query query = session.createQuery(hql);
		if(query.uniqueResult() == null) {
			return 0;
		}
		long soluong = (long) query.uniqueResult();
		return soluong;
	}
	
	public long getPendingOrderCountbyIdUser (int idUser){
		Session session = factory.getCurrentSession();
		String hql = "SELECT COUNT(A.user_id) FROM OrderEntity A WHERE A.order_status = 1 AND A.user_id = "+idUser;
		Query query = session.createQuery(hql);
		if(query.uniqueResult() == null) {
			return 0;
		}
		long soluong = (long) query.uniqueResult();
		return soluong;
	}
	
	public long getOrderSuccessCountbyIdUser (int idUser){
		Session session = factory.getCurrentSession();
		String hql = "SELECT COUNT(A.user_id) FROM OrderEntity A WHERE A.order_status = 2 AND A.user_id = "+idUser;
		Query query = session.createQuery(hql);
		if(query.uniqueResult() == null) {
			return 0;
		}
		long soluong = (long) query.uniqueResult();
		return soluong;
	}
}
