package ptithcm.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ptithcm.entity.AccountEntity;

@Transactional
@Controller
public class AccountDao {
	@Autowired
	SessionFactory factory;
	
	public boolean checkLogin(String username, String password){
		Session session = factory.getCurrentSession();
//		String hql = "SELECT * FROM AccountEntity where username = '"+username+"' AND password = '"+password+"'";
		//bi loi
		
		// Su dung phuong phap parameter
		String hql = "FROM AccountEntity where username=:username AND password=:password";

		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		query.setParameter("password", password);
		
		@SuppressWarnings("unchecked")
		List<AccountEntity> list = query.list();
		if(list.isEmpty() == false) {
			return true;
		}
		return false;
	}
	
	public AccountEntity getInfoLogin(String username, String password){
		Session session = factory.getCurrentSession();
//		String hql = "FROM AccountEntity where username = '"+username+"' AND password = '"+password+"'";
		//bi loi
		
		// Su dung phuong phap Parameter
		String hql = "FROM AccountEntity where username=:username AND password=:password";
		
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		query.setParameter("password", password);
		
//		Query query = session.createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<AccountEntity> list = query.list();
		if(list.isEmpty() == false) {
			return list.get(0);
		}
		return null;
	}
	
	public AccountEntity updateUserAfterUpdate(String username) {
		Session session = factory.getCurrentSession();
		String hql = "FROM AccountEntity where username = '"+username+"'";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<AccountEntity> list = query.list();
		if(list.isEmpty() == false) {
			return list.get(0);
		}
		return null;
	}
	
	public List <AccountEntity> dsUser (){
		Session session = factory.getCurrentSession();
		String hql = "FROM AccountEntity";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<AccountEntity> list = query.list();
		return list;
	}
	
	public long getUserCount() {
		Session session = factory.getCurrentSession();
		String hql = "SELECT count(A.username) FROM AccountEntity A";
		Query query = session.createQuery(hql);
		long soluong = (long) query.uniqueResult();
		return soluong;
	}
	
	public String findUsernameReview(int idUser) {
		Session session = factory.getCurrentSession();
		String hql = "FROM AccountEntity A WHERE A.user_id = "+idUser;
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<AccountEntity> list = query.list();
		if(list.isEmpty() == true) {
			return null;
		}else {
			return list.get(0).getUsername();
		}
	}
	
	public int updateUser(int user_id, String email, String phone, int age, int gender, String address, int role) { // username, email, phone, age, gender
		Session session = factory.getCurrentSession();
		String hql = "UPDATE AccountEntity A SET age = "+age+", A.email = :email, A.address = :address , A.role =:role , A.phone = :phone, A.gender = "+gender+" WHERE A.user_id = :user_id";
		Query query = session.createQuery(hql);
		query.setParameter("email", email);
		query.setParameter("address", address);
		query.setParameter("role", role);
		query.setParameter("phone", phone);
		query.setParameter("user_id", user_id);
		int result = query.executeUpdate();
		return result;
	}
	
	public boolean checkUsernameExist(String username) {
		Session session = factory.getCurrentSession();
		String hql = "FROM AccountEntity A WHERE A.username = :username";
		Query query = session.createQuery(hql);
		query.setParameter("username", username); 
		@SuppressWarnings("unchecked")
		List<AccountEntity> list = query.list();
		if(list.isEmpty() == true) {
			return false;
		}else {
			return true;
		}
	}
	public boolean checkPhoneExist(String phone) {
		Session session = factory.getCurrentSession();
		String hql = "FROM AccountEntity A WHERE A.phone = :phone";
		Query query = session.createQuery(hql);
		query.setParameter("phone", phone); 
		@SuppressWarnings("unchecked")
		List<AccountEntity> list = query.list();
		if(list.isEmpty() == true) {
			return false;
		}else {
			return true;
		}
	}
	public boolean checkEmailExist(String email) {
		Session session = factory.getCurrentSession();
		String hql = "FROM AccountEntity A WHERE A.email = :email";
		Query query = session.createQuery(hql);
		query.setParameter("email", email); 
		@SuppressWarnings("unchecked")
		List<AccountEntity> list = query.list();
		if(list.isEmpty() == true) {
			return false;
		}else {
			return true;
		}
	}
	public boolean checkIDandUsernameExist(String username, int idUser) {
		Session session = factory.getCurrentSession();
		String hql = "FROM AccountEntity A WHERE A.username = :username AND A.user_id = "+idUser;
		Query query = session.createQuery(hql);
		query.setParameter("username", username); 
		@SuppressWarnings("unchecked")
		List<AccountEntity> list = query.list();
		if(list.isEmpty() == true) {
			return false;
		}else {
			return true;
		}
	}
	
	public int addAccount(AccountEntity account) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
        try {
        	session.save(account);
        	tx.commit();
        }catch (Exception e){
        	tx.rollback();
        	return 0;
        } finally{
        	session.close();
        }
        return 1;
	}
	
	public AccountEntity checkInfoGetPasswordExist(String username, String email) {
		Session session = factory.getCurrentSession();
		String hql = "FROM AccountEntity A WHERE A.username = :username AND A.email = :email";
		Query query = session.createQuery(hql);
		query.setParameter("username", username); 
		query.setParameter("email", email); 
		@SuppressWarnings("unchecked")
		List<AccountEntity> list = query.list();
		if(list.isEmpty() == true) {
			return null;
		}else {
			return list.get(0);
		}
	}
	
	public boolean checkOldPassWordExactly(String password, String username) {
		Session session = factory.getCurrentSession();
		String hql = "FROM AccountEntity A WHERE A.username = :username AND A.password = :password";
		Query query = session.createQuery(hql);
		query.setParameter("username", username); 
		query.setParameter("password", password); 
		@SuppressWarnings("unchecked")
		List<AccountEntity> list = query.list();
		if(list.isEmpty() == true) {
			return false;
		}else {
			return true;
		}
	}
	
	public int updatePassword(String password, String username) {
		Session session = factory.getCurrentSession();
		String hql = "UPDATE AccountEntity A SET A.password = :password WHERE A.username = :username";
		Query query = session.createQuery(hql);
		query.setParameter("password", password);
		query.setParameter("username", username);
		int result = query.executeUpdate();
		return result;
	}
	public List <AccountEntity> AccountPage (int pageNumber, int pagesize,String order, String dir){
		Session session = factory.getCurrentSession();
		String hql = "FROM AccountEntity ORDER BY "+order+" "+dir;
		Query query = session.createQuery(hql);
		query.setFirstResult((pageNumber-1) * pagesize);
		query.setMaxResults(pagesize);
		@SuppressWarnings("unchecked")
		List<AccountEntity> list = query.list();
		return list;
	}
	public int deleteCustomer(int idCustomer) {
		Session session = factory.getCurrentSession();

		
		String hql = "DELETE FROM AccountEntity WHERE USER_ID = "+idCustomer;
		Query query = session.createQuery(hql);
		int result = query.executeUpdate();
		return result;
	}
	public int searchCustomerCount(String key){
		Session session = factory.getCurrentSession();
		String hql = "SELECT count(A.user_id) From AccountEntity A WHERE A.username LIKE :key";
		Query query = session.createQuery(hql);
		query.setParameter("key", "%" + key + "%");
		long soluong = (long) query.uniqueResult();
		return (int) soluong;
	}
	public List<AccountEntity> searchCustomer(String key){
		Session session = factory.getCurrentSession();
		String hql = "From AccountEntity A WHERE A.username LIKE :key";
		Query query = session.createQuery(hql);
		query.setParameter("key", "%" + key + "%");
		@SuppressWarnings("unchecked")
		List<AccountEntity> list = query.list();
		return list;
	}
}
