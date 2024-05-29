package ptithcm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.entity.CompanyEntity;

@Service
public interface CompanyService {
	@Autowired
	public String findNameCompany(int id_company);
	@Autowired
	public List <CompanyEntity> dsCompany();
	@Autowired
	public long getCompanyCount();
	@Autowired
	public List <CompanyEntity> companyPage (int pageNumber, int pagesize, String order, String dir);
	@Autowired
	public List<CompanyEntity> searchCompany(String key);
	@Autowired
	public int searchCompanyCount(String key);
	@Autowired
	public int addCompany(CompanyEntity company);
	@Autowired
	public int editCompany(int idPublisher, String namePublisher);
	@Autowired
	public int deleteCompany(int idPublisher);
	@Autowired
	public boolean checkNameCompany(int idPublisher, String namePublisher);
	@Autowired
	public int getIDcompanybyName(String company_name);
	@Autowired
	public CompanyEntity getCompanybyName(String company_name);
}
