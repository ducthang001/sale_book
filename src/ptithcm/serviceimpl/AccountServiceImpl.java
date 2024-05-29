package ptithcm.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ptithcm.dao.AccountDao;
import ptithcm.entity.AccountEntity;
import ptithcm.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountDao accountDao;
	
	@Override
	public boolean checkLogin(String username, String password) {
		return accountDao.checkLogin(username, password);
	}

	@Override
	public AccountEntity getInfoLogin(String username, String password) {
		return accountDao.getInfoLogin(username, password);
	}

	@Override
	public List<AccountEntity> dsUser() {
		return accountDao.dsUser();
	}
	
	@Override
	public long getUserCount() {
		return accountDao.getUserCount();
	}
	
	@Override
	public String findUsernameReview(int idUser) {
		return accountDao.findUsernameReview(idUser);
	}
	
	@Override
	public int updateUser(int user_id, String email, String phone, int age, int gender,String address, int role) {
		return accountDao.updateUser(user_id, email, phone, age, gender,address, role);
	}
	
	@Override
	public AccountEntity updateUserAfterUpdate(String username) {
		return accountDao.updateUserAfterUpdate(username);
	}

	@Override
	public boolean checkUsernameExist(String username) {
		return accountDao.checkUsernameExist(username);
	}
	
	@Override
	public int addAccount(AccountEntity account) {
		return accountDao.addAccount(account);
	}

	@Override
	public AccountEntity checkInfoGetPasswordExist(String username, String email) {
		return accountDao.checkInfoGetPasswordExist(username, email);
	}
	
	@Override
	public int updatePassword(String password, String username) {
		return accountDao.updatePassword(password, username);
	}

	@Override
	public boolean checkOldPassWordExactly(String password, String username) {
		return accountDao.checkOldPassWordExactly(password, username);
	}
	
	@Override
	public boolean checkIDandUsernameExist(String username, int idUser) {
		return accountDao.checkIDandUsernameExist(username, idUser);
	}

	@Override
	public List<AccountEntity> customerPage(int pageNumber, int pagesize,String order, String dir) {
		// TODO Auto-generated method stub
		return accountDao.AccountPage(pageNumber, pagesize,order,dir);
	}

	@Override
	public int deleteCustomer(int idCustomer) {
		// TODO Auto-generated method stub
		return accountDao.deleteCustomer(idCustomer);
	}

	@Override
	public int searchCustomerCount(String key) {
		// TODO Auto-generated method stub
		return accountDao.searchCustomerCount(key);
	}

	@Override
	public List<AccountEntity> SearchCustomer(String key) {
		// TODO Auto-generated method stub
		return accountDao.searchCustomer(key);
	}

	@Override
	public boolean checkPhoneExist(String phone) {
		// TODO Auto-generated method stub
		return accountDao.checkPhoneExist(phone);
	}

	@Override
	public boolean checkEmailExist(String email) {
		// TODO Auto-generated method stub
		return accountDao.checkEmailExist(email);
	}
	
}