package ptithcm.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.dao.CompanyDao;
import ptithcm.entity.CompanyEntity;
import ptithcm.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService{
	@Autowired
	CompanyDao companyDao;

	@Override
	public String findNameCompany(int id_company) {
		return companyDao.findNameCompany(id_company);
	}

	@Override
	public List<CompanyEntity> dsCompany() {
		return companyDao.dsCompany();
	}
	
	@Override
	public long getCompanyCount() {
		return companyDao.getCompanyCount();
	}

	@Override
	public List<CompanyEntity> companyPage(int pageNumber, int pagesize, String order, String dir) {
		return companyDao.companyPage(pageNumber, pagesize, order, dir);
	}

	@Override
	public List<CompanyEntity> searchCompany(String key) {
		return companyDao.searchCompany(key);
	}

	@Override
	public int searchCompanyCount(String key) {
		return companyDao.searchCompanyCount(key);
	}

	@Override
	public int addCompany(CompanyEntity company) {
		return companyDao.addCompany(company);
	}

	@Override
	public int editCompany(int idPublisher, String namePublisher) {
		return companyDao.editCompany(idPublisher, namePublisher);
	}

	@Override
	public int deleteCompany(int idPublisher) {
		return companyDao.deleteCompany(idPublisher);
	}
	
	@Override
	public boolean checkNameCompany(int idPublisher, String namePublisher) {
		return companyDao.checkNameCompany(idPublisher, namePublisher);
	}
	
	@Override
	public int getIDcompanybyName(String company_name) {
		return companyDao.getIDcompanybyName(company_name);
	}
	
	@Override
	public CompanyEntity getCompanybyName(String company_name) {
		return companyDao.getCompanybyName(company_name);
	}
}
