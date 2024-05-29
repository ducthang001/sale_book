package ptithcm.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ptithcm.entity.AccountEntity;

@Service
public interface AccountService {
	@Autowired
	public boolean checkLogin(String username, String password);
	
	@Autowired
	public AccountEntity getInfoLogin(String username, String password);
	
	@Autowired
	public List <AccountEntity> dsUser ();
	
	@Autowired
	public long getUserCount();
	
	@Autowired
	public String findUsernameReview(int idUser);
	
	@Autowired
	public int updateUser(int user_id, String email, String phone, int age, int gender, String address, int role);
	
	@Autowired
	public AccountEntity updateUserAfterUpdate(String username);
	
	@Autowired
	public boolean checkUsernameExist(String username);
	
	
	@Autowired
	public boolean checkPhoneExist(String phone);
	
	@Autowired
	public boolean checkEmailExist(String email);
	
	@Autowired
	public int addAccount(AccountEntity account);
	
	@Autowired
	public AccountEntity checkInfoGetPasswordExist(String username, String email);
	
	@Autowired
	public boolean checkOldPassWordExactly(String password, String username);
	
	@Autowired
	public int updatePassword(String password, String username);
	
	@Autowired
	public boolean checkIDandUsernameExist(String username, int idUser);
	
	@Autowired
	public List <AccountEntity> customerPage (int pageNumber, int pagesize,String order, String dir);
	
	@Autowired
	public int deleteCustomer(int idCustomer);
	
	@Autowired
	public int searchCustomerCount(String key);
	
	@Autowired
	public List<AccountEntity> SearchCustomer(String key);
}
