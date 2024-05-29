package ptithcm.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ptithcm.entity.CategoryEntity;
import ptithcm.entity.CompanyEntity;
@Controller
@Transactional
public class CategoryDao {
	@Autowired
	SessionFactory factory;
	
	public List <CategoryEntity> dsCategory (){
		Session session = factory.getCurrentSession();
		String hql = "FROM CategoryEntity";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<CategoryEntity> list = query.list();
		return list;
	}
	

	public String findNameCategory(int id_category) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CategoryEntity C where C.category_id = "+id_category;
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<CategoryEntity> list = query.list();
		if(list.isEmpty() == false) {
			return list.get(0).getCategory_name();
		}
		return null;
	}
	
	public long getCategoryCount() {
		Session session = factory.getCurrentSession();
		String hql = "SELECT count(C.category_id) FROM CategoryEntity C";
		Query query = session.createQuery(hql);
		long soluong = (long) query.uniqueResult();
		return soluong;
	}
	
	public List <CategoryEntity> categoryPage (int pageNumber, int pagesize, String order, String dir){
		Session session = factory.getCurrentSession();
		String hql = "FROM CategoryEntity ORDER BY "+order+" "+dir;
		Query query = session.createQuery(hql);
		query.setFirstResult((pageNumber-1) * pagesize);
		query.setMaxResults(pagesize);
		@SuppressWarnings("unchecked")
		List<CategoryEntity> list = query.list();
		return list;
	}
	
	public List<CategoryEntity> searchCategory(String key){
		Session session = factory.getCurrentSession();
		String hql = "From CategoryEntity A WHERE A.category_name LIKE :key";
		Query query = session.createQuery(hql);
		query.setParameter("key", "%" + key + "%");
		@SuppressWarnings("unchecked")
		List<CategoryEntity> list = query.list();
		return list;
	}
	
	public int searchCategoryCount(String key){
		Session session = factory.getCurrentSession();
		String hql = "SELECT count(A.category_id) From CategoryEntity A WHERE A.category_name LIKE :key";
		Query query = session.createQuery(hql);
		query.setParameter("key", "%" + key + "%");
		long soluong = (long) query.uniqueResult();
		return (int) soluong;
	}
	
	public int addCategory(CategoryEntity category) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
        try {
        	session.save(category);
        	tx.commit();
        }catch (Exception e){
        	tx.rollback();
        	return 0;
        } finally{
        	session.close();
        }
        return 1;
	}
	
	public int editCategory(int idCategory, String nameCategory) {
		Session session = factory.getCurrentSession();
		String hql = "UPDATE CategoryEntity A SET category_name = :nameCategory WHERE A.category_id = "+idCategory;
		Query query = session.createQuery(hql);
		query.setParameter("nameCategory", nameCategory);
		int result = query.executeUpdate();
		return result;
	}
	
	public int deleteCategory(int idCategory) {
		Session session = factory.getCurrentSession();
		String hql = "DELETE FROM CategoryEntity WHERE category_id = "+idCategory;
		Query query = session.createQuery(hql);
		int result = query.executeUpdate();
		return result;
	}
	
	public boolean checkNameCategory(int idCategory, String authorName) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CategoryEntity WHERE category_name = :authorName AND category_id <> "+ idCategory;
		Query query = session.createQuery(hql);
		query.setParameter("authorName", authorName);
		@SuppressWarnings("unchecked")
		List<CompanyEntity> list = query.list();
		if(list.size() == 0) {
			return false;
		}
		return true;
	}
	
	public int getIDcategorybyName(String nameCategory) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CategoryEntity WHERE category_name = "+nameCategory;
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<CategoryEntity> list = query.list();
		if(list.isEmpty() == false) {
			return list.get(0).getCategory_id();
		}
		return -1;
	}
	
	public CategoryEntity getCategorybyName(String category_name) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CategoryEntity WHERE category_name = :category_name";
		Query query = session.createQuery(hql);
		query.setParameter("category_name", category_name);
		@SuppressWarnings("unchecked")
		List<CategoryEntity> list = query.list();
		if(list.isEmpty() == false) {
			return list.get(0);
		}
		return null;
	}
	
}
