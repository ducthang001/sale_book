package ptithcm.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ptithcm.entity.CompanyEntity;

@Controller
@Transactional
public class CompanyDao {
	@Autowired
	SessionFactory factory;
	
	public String findNameCompany(int id_company) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CompanyEntity C where C.id_company = "+id_company;
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<CompanyEntity> list = query.list();
		if(list.isEmpty() == false) {
			return list.get(0).getCompany_name();
		}
		return null;
	}
	
	public List <CompanyEntity> dsCompany() {
		Session session = factory.getCurrentSession();
		String hql = "FROM CompanyEntity";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<CompanyEntity> list = query.list();
		return list;
	}
	
	public long getCompanyCount() {
		Session session = factory.getCurrentSession();
		String hql = "SELECT count(C.id_company) FROM CompanyEntity C";
		Query query = session.createQuery(hql);
		long soluong = (long) query.uniqueResult();
		return soluong;
	}
	
	public List <CompanyEntity> companyPage (int pageNumber, int pagesize, String order, String dir){
		Session session = factory.getCurrentSession();
		String hql = "FROM CompanyEntity ORDER BY "+order+" "+dir;
		Query query = session.createQuery(hql);
		query.setFirstResult((pageNumber-1) * pagesize);
		query.setMaxResults(pagesize);
		@SuppressWarnings("unchecked")
		List<CompanyEntity> list = query.list();
		return list;
	}
	
	public List<CompanyEntity> searchCompany(String key){
		Session session = factory.getCurrentSession();
		String hql = "From CompanyEntity A WHERE A.company_name LIKE :key";
		Query query = session.createQuery(hql);
		query.setParameter("key", "%" + key + "%");
		@SuppressWarnings("unchecked")
		List<CompanyEntity> list = query.list();
		return list;
	}
	
	public int searchCompanyCount(String key){
		Session session = factory.getCurrentSession();
		String hql = "SELECT count(C.id_company) From CompanyEntity C WHERE C.company_name LIKE :key";
		Query query = session.createQuery(hql);
		query.setParameter("key", "%" + key + "%");
		long soluong = (long) query.uniqueResult();
		return (int) soluong;
	}
	
	public int editCompany(int idPublisher, String namePublisher) {
		Session session = factory.getCurrentSession();
		String hql = "UPDATE CompanyEntity A SET company_name = :namePublisher WHERE A.id_company = "+idPublisher;
		Query query = session.createQuery(hql);
		query.setParameter("namePublisher", namePublisher);
		int result = query.executeUpdate();
		return result;
	}
	
	public int deleteCompany(int idPublisher) {
		Session session = factory.getCurrentSession();
		String hql = "DELETE FROM CompanyEntity WHERE id_company = "+idPublisher;
		Query query = session.createQuery(hql);
		int result = query.executeUpdate();
		return result;
	}
	
	public boolean checkNameCompany(int idPublisher, String namePublisher) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CompanyEntity WHERE company_name = :namePublisher AND id_company <> "+ idPublisher;
		Query query = session.createQuery(hql);
		query.setParameter("namePublisher", namePublisher);
		@SuppressWarnings("unchecked")
		List<CompanyEntity> list = query.list();
		if(list.isEmpty() == true) {
			return false;
		}
		return true;
	}
	
	public int addCompany(CompanyEntity company) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
        try {
        	session.save(company);
        	tx.commit();
        }catch (Exception e){
        	tx.rollback();
        	return 0;
        } finally{
        	session.close();
        }
        return 1;
	}
	
	
	public int getIDcompanybyName(String company_name) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CompanyEntity WHERE company_name = :company_name";
		Query query = session.createQuery(hql);
		query.setParameter("company_name", company_name);
		@SuppressWarnings("unchecked")
		List<CompanyEntity> list = query.list();
		if(list.isEmpty() == false) {
			return list.get(0).getId_company();
		}
		return -1;
	}
	
	public CompanyEntity getCompanybyName(String company_name) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CompanyEntity WHERE company_name = :company_name";
		Query query = session.createQuery(hql);
		query.setParameter("company_name", company_name);
		@SuppressWarnings("unchecked")
		List<CompanyEntity> list = query.list();
		if(list.isEmpty() == false) {
			return list.get(0);
		}
		return null;
	}
}
