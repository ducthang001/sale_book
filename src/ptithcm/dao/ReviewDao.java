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

import ptithcm.entity.ReviewEntity;

@Controller
@Transactional
public class ReviewDao {
	@Autowired
	SessionFactory factory;
	
	public List <ReviewEntity> dsReview (){
		Session session = factory.getCurrentSession();
		String hql = "FROM ReviewEntity";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<ReviewEntity> list = query.list();
		return list;
	}
	
	public List<int[]> getListStarCount(int idbook){
		Session session = factory.getCurrentSession();
		String hql = "Select star, count(*) from ReviewEntity where id_book="+idbook+" group by star order by star desc";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<int[]> list = query.list();
		return list;
	}
	
	public List<float []> getAVGBookStar(int idbook){
		Session session = factory.getCurrentSession();
		String hql = "Select AVG(star) from ReviewEntity where id_book="+idbook;
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<float []> list = query.list();
		return list;
	}
	
	public boolean checkUserReview(int userID, int bookID) {
		Session session = factory.getCurrentSession();
		String hql = "FROM ReviewEntity R WHERE R.id.user_id = "+userID +" AND R.id.id_book = "+bookID;
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<ReviewEntity> list = query.list();
		if(list.isEmpty() == true) {
			return false;
		}else {
			return true;
		}
	}
	
	public ReviewEntity getReviewUserCommentABook (int userID, int bookID){
		Session session = factory.getCurrentSession();
		String hql = "FROM ReviewEntity R WHERE R.id.user_id = "+userID +" AND R.id.id_book = "+bookID;
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<ReviewEntity> list = query.list();
		if(list.isEmpty() == true) {
			return null;
		}
		return list.get(0);
	}
	
	public int addReview(ReviewEntity review) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
        try {
        	session.save(review);
        	tx.commit();
        }catch (Exception e){
        	tx.rollback();
        	System.out.println("LỖI = "+e);
        	return 0;
        } finally{
        	session.close();
        }
        return 1;
	}
	
	public int updateReview(String comments, int star, int userID, int bookID) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
        try {
        	String hql = "UPDATE ReviewEntity R SET R.comments = :comments, R.time = :dateA, R.star = "+ star +" WHERE R.id.user_id = "+userID +" AND R.id.id_book = "+bookID;
        	Query query = session.createQuery(hql);
    		query.setParameter("comments", comments);
    		query.setParameter("dateA", new Date());
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
	public int deleteReview(int idBook,int idUser) {
		Session session = factory.getCurrentSession();
		String hql = "DELETE FROM ReviewEntity WHERE USER_ID = "+idUser +" AND ID_BOOK ="+idBook;
		Query query = session.createQuery(hql);
		int result = query.executeUpdate();
		return result;
	}
}
