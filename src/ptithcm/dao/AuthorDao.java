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
import ptithcm.entity.CompanyEntity;

@Controller
@Transactional
public class AuthorDao {
	@Autowired
	SessionFactory factory;
	
	public String findNameAuhthor(int id_author) {
		Session session = factory.getCurrentSession();
		String hql = "FROM AuthorEntity WHERE id_author = "+id_author;
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<AuthorEntity> list = query.list();
		if(list.isEmpty() == false) {
			return list.get(0).getAuthor_name();
		}
		return null;
	}
	
	public List <AuthorEntity> dsAuthor(){
		Session session = factory.getCurrentSession();
		String hql = "FROM AuthorEntity";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<AuthorEntity> list = query.list();
		return list;
	}
	
	public long getAuthorCount() {
		Session session = factory.getCurrentSession();
		String hql = "SELECT count(A.id_author) FROM AuthorEntity A";
		Query query = session.createQuery(hql);
		long soluong = (long) query.uniqueResult();
		return soluong;
	}
	
	public List <AuthorEntity> authorPage (int pageNumber, int pagesize, String order, String dir){
		Session session = factory.getCurrentSession();
		String hql = "FROM AuthorEntity ORDER BY "+order+" "+dir;
		Query query = session.createQuery(hql);
		query.setFirstResult((pageNumber-1) * pagesize);
		query.setMaxResults(pagesize);
		@SuppressWarnings("unchecked")
		List<AuthorEntity> list = query.list();
		return list;
	}
	
	public List<AuthorEntity> SearchAuthor(String key){
		Session session = factory.getCurrentSession();
		String hql = "From AuthorEntity A WHERE A.author_name LIKE :key";
		Query query = session.createQuery(hql);
		query.setParameter("key", "%" + key + "%");
		@SuppressWarnings("unchecked")
		List<AuthorEntity> list = query.list();
		return list;
	}
	
	public int searchAuthorCount(String key){
		Session session = factory.getCurrentSession();
		String hql = "SELECT count(A.id_author) From AuthorEntity A WHERE A.author_name LIKE :key";
		Query query = session.createQuery(hql);
		query.setParameter("key", "%" + key + "%");
		long soluong = (long) query.uniqueResult();
		return (int) soluong;
	}
	
	public int addAuthor(AuthorEntity author) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
        try {
        	session.save(author);
        	tx.commit();
        }catch (Exception e){
        	tx.rollback();
        	return 0;
        } finally{
        	session.close();
        }
        return 1;
	}
	
	public int editAuthor(int idAuthor, String authorName) {
		Session session = factory.getCurrentSession();
		String hql = "UPDATE AuthorEntity A SET author_name = :authorName WHERE A.id_author = "+idAuthor;
		Query query = session.createQuery(hql);
		query.setParameter("authorName", authorName);
		int result = query.executeUpdate();
		return result;
	}
	
	public int deleteAuthor(int idAuthor) {
		Session session = factory.getCurrentSession();
		String hql = "DELETE FROM AuthorEntity WHERE id_author = "+idAuthor;
		Query query = session.createQuery(hql);
		int result = query.executeUpdate();
		return result;
	}
	
	public boolean checkNameAuthor(int idAuthor, String authorName) {
		Session session = factory.getCurrentSession();
		String hql = "FROM AuthorEntity WHERE author_name = :authorName AND id_author <> "+ idAuthor;
		Query query = session.createQuery(hql);
		query.setParameter("authorName", authorName);
		@SuppressWarnings("unchecked")
		List<CompanyEntity> list = query.list();
		if(list.size() == 0) {
			return false;
		}
		return true;
	}
	
}
